package com.example.administrator.textdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts.Data;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;

/**
 * Created by Aono on 2017-08-09.
 * package_name: com.leley.app.utils
 * project_name: leley_android
 */

public class ContactUtils {

    public static boolean addContact(Context context, ContactEntity contact, @DrawableRes int resId) {
        try {
            ContentValues values = new ContentValues();

            // 下面的操作会根据RawContacts表中已有的rawContactId使用情况自动生成新联系人的rawContactId
            Uri rawContactUri = context.getContentResolver().insert(
                    ContactsContract.RawContacts.CONTENT_URI, values);
            long rawContactId = ContentUris.parseId(rawContactUri);

            // 向data表插入姓名数据
            String name = contact.getName();
            if (!TextUtils.isEmpty(name)) {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
                values.put(StructuredName.GIVEN_NAME, name);
                context.getContentResolver().insert(
                        ContactsContract.Data.CONTENT_URI, values);
            }

            // 向data表插入电话数据
//            String mobile_number = contact.getPhone().get(0);
            for (String mobile_number : contact.getPhone()) {
                if (!TextUtils.isEmpty(mobile_number)) {
                    values.clear();
                    values.put(Data.RAW_CONTACT_ID, rawContactId);
                    values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
                    values.put(Phone.NUMBER, mobile_number);
                    values.put(Phone.TYPE, Phone.TYPE_MOBILE);
                    context.getContentResolver().insert(
                            ContactsContract.Data.CONTENT_URI, values);
                }
            }

            // 向data表插入Email数据
            String email = contact.getEmail();
            if (!TextUtils.isEmpty(email)) {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
                values.put(Email.DATA, email);
                values.put(Email.TYPE, Email.TYPE_WORK);
                context.getContentResolver().insert(
                        ContactsContract.Data.CONTENT_URI, values);
            }

            // 向data表插入QQ数据
            String qq = contact.getQq();
            if (!TextUtils.isEmpty(qq)) {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, Im.CONTENT_ITEM_TYPE);
                values.put(Im.DATA, qq);
                values.put(Im.PROTOCOL, Im.PROTOCOL_QQ);
                context.getContentResolver().insert(
                        ContactsContract.Data.CONTENT_URI, values);
            }

            // 向data表插入备注信息
            String describe = contact.getDescribe();
            if (!TextUtils.isEmpty(describe)) {
                values.clear();
                values.put(Data.RAW_CONTACT_ID, rawContactId);
                values.put(Data.MIMETYPE, Note.CONTENT_ITEM_TYPE);
                values.put(Note.NOTE, describe);
                context.getContentResolver().insert(
                        ContactsContract.Data.CONTENT_URI, values);
            }

            // 向data表插入头像数据
            Bitmap sourceBitmap = BitmapFactory.decodeResource(
                    context.getResources(), resId);
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            // 将Bitmap压缩成PNG编码，质量为100%存储
            sourceBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            byte[] avatar = os.toByteArray();
            values.put(Data.RAW_CONTACT_ID, rawContactId);
            values.put(Data.MIMETYPE, Photo.CONTENT_ITEM_TYPE);
            values.put(Photo.PHOTO, avatar);
            context.getContentResolver().insert(
                    ContactsContract.Data.CONTENT_URI, values);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean hasContactFromPhoneBook(Context context, String phoneNum, String leleyName) {
        String contactName;
        ContentResolver cr = context.getContentResolver();
        Cursor pCur = cr.query(
                Phone.CONTENT_URI, null,
                Phone.NUMBER + " = ?",
                new String[] { phoneNum }, null);
        if (pCur != null && pCur.moveToFirst()) {
            contactName = pCur.getString(pCur.getColumnIndex(Phone.DISPLAY_NAME));
            pCur.close();
            return leleyName.equals(contactName.trim());
        }
        return false;
    }
}
