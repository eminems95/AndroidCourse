/*

package com.anotherdeveloper.androidcourse.realm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.anotherdeveloper.androidcourse.models.Contact;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


*/
/**
 * Created by Marcin on 2018-01-11.
 * :)
 *//*



public class RealmDatabaseManager implements
        Realm.Transaction,
        Realm.Transaction.OnSuccess,
        Realm.Transaction.OnError {

    ArrayList<Contact> contacts;
    Context context;
    Realm realm = Realm.getDefaultInstance();

    public RealmDatabaseManager(ArrayList<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public void execute(@NonNull Realm realm) {
        realm.deleteAll();
        for (Contact contact : contacts) {
            realm.copyToRealmOrUpdate(contact);
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(context, "Transacted to database", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(@NonNull Throwable error) {
        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();

    }
    public void closeRealm(){
        realm.close();
    }

    public void loadFromRealmDatabase() {
        realm = Realm.getDefaultInstance();
        RealmResults<Contact> contactRealmResults = realm.where(Contact.class).findAll();
        contacts = new ArrayList<>(contactRealmResults);
    }

    public void saveIntoDatabase() {
        realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(this, this, this);
    }
}

*/
