package com.anotherdeveloper.zadanie_1.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marcin on 2017-12-25.
 * :)
 */

public class Client {

    private static final String HOME_URL = "https://jaksiemaszcare-training-mobile.herokuapp.com/api/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(HOME_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
