package com.anotherdeveloper.androidcourse.api;

import com.anotherdeveloper.androidcourse.models.Contact;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Marcin on 2017-12-25.
 * :)
 */

public interface Service {

    @GET(".")
    Call<Contact[]> listUsers();
}
