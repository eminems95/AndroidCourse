package com.anotherdeveloper.zadanie_1.api;

import com.anotherdeveloper.zadanie_1.models.Contact;

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
