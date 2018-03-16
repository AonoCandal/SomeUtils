package com.example.administrator.textdemo;

import android.Manifest;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private static final int REQ_PERMISSION_CONTACT = 123123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = "一一一一二二二二";
        int bstart = str.indexOf("一一一一");
        int bend = bstart + "一一一一".length();
        int fstart = str.indexOf("二二二二");
        int fend = fstart + "二二二二".length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.white)), bstart, bend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.b7b7b7)), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.b7b7b7)), bstart, bend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), fstart, fend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        style.setSpan(new AbsoluteSizeSpan(12, true), fstart, fend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(18, true), bstart, bend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView tvColor = (TextView) findViewById(R.id.tv_color);
        tvColor.setText(style);
        tvColor.setBackgroundResource(R.drawable.border_radius_service_none);


        TextView mTvSize = (TextView) findViewById(R.id.tv_size);
        String str2 = "¥1200.09";
        int a1s = str2.indexOf("¥");
        int a1e = a1s + 1;
        int b1s = str2.indexOf(".");
        int b1e = str2.length();
        int c1s = a1e;
        int c1e = str2.indexOf(".");
        SpannableStringBuilder style2 = new SpannableStringBuilder(str2);
        style2.setSpan(new AbsoluteSizeSpan(12, true), a1s, a1e, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style2.setSpan(new AbsoluteSizeSpan(12, true), b1s, b1e, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style2.setSpan(new AbsoluteSizeSpan(18, true), c1s, c1e, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvSize.setText(style2);

        String str3 = "1\n22\n333\n4444";
        SpannableString spanString = new SpannableString(str3);
        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//        for (Integer index : getPointIndex(str3)) {
//            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
//            spanString.setSpan(span, index, index+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        spanString.setSpan(span, 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView viewPoint = (TextView) findViewById(R.id.tv_point);
        viewPoint.append(spanString);


        addContact();
    }

    private void addContact() {
        //添加联系人
        if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)){
            if (!ContactUtils.hasContactFromPhoneBook(this, "01056592201", "乐乐医")) {
                addContactNUM();
            }
        }else {
            EasyPermissions.requestPermissions(this, "获取联系人权限", REQ_PERMISSION_CONTACT, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS);
        }
    }

    private void addContactNUM() {
//        if (!PrefUtils.getBoolean(this, NOT_WRITE_CONTACT, false)){
//            ArrayList<String> phone = new ArrayList<>();
//            phone.add("01056592201");
//            phone.add("02886337555");
//            ContactEntity contactEntity = new ContactEntity();
//            contactEntity.setName("乐乐医");
//            contactEntity.setPhone(phone);
//            ContactUtils.addContact(this, contactEntity, R.mipmap.ic_pt_launcher);
//        }
    }

    public static ArrayList<Integer> getPointIndex(String string) {
        ArrayList<Integer> arr = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < string.split("\n").length; i++) {
            if (i == 0) {
                arr.add(i, 0);
            } else {
                arr.add(i, string.indexOf("\n", pos + 1));
            }
            pos = arr.get(i);
        }
        return arr;
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        addContact();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
