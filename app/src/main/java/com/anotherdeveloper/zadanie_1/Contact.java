package com.anotherdeveloper.zadanie_1;

import java.net.URI;

/**
 * Created by Marcin on 2017-12-19.
 */

public class Contact {
    String name;
    String phoneNumber;
    String profilePhoto;

    public Contact(String name, String phoneNumber, String profilePhoto) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profilePhoto = profilePhoto;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }
}
