package com.anotherdeveloper.androidcourse.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);


        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(HOME_URL).client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
