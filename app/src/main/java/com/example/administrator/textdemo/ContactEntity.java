package com.example.administrator.textdemo;

import java.util.List;

/**
 * Created by Aono on 2017-08-09.
 * package_name: com.leley.app.entity
 * project_name: leley_android
 */

public class ContactEntity {

    private String name;
    private String email;
    private String qq;
    private String describe;
    private List<String> phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
