package com.anotherdeveloper.androidcourse;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Marcin on 2017-12-26.
 * :)
 */

public class AndroidCourse extends Application {

    public static final String CACHE_REALM = "cache.realm";

    @Override
    public void onCreate() {
        super.onCreate();
        initPicassoBuilder();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(CACHE_REALM).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initPicassoBuilder() {
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }
}
