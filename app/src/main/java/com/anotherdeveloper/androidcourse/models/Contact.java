package com.anotherdeveloper.androidcourse.models;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Marcin on 2017-12-19.
 * :)
 */

public class Contact extends RealmObject {

    @PrimaryKey
    @Expose
    private String id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String phone;
    @Expose
    private String avatar;
    @Ignore
    private boolean isSeen = false;
    public Contact() {
    }

    public Contact(String id, String firstName, String lastName, String email, String phone, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
