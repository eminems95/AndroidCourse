package com.anotherdeveloper.androidcourse.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anotherdeveloper.androidcourse.R;
import com.anotherdeveloper.androidcourse.adapters.ContactsRecyclerViewAdapter;
import com.anotherdeveloper.androidcourse.api.Client;
import com.anotherdeveloper.androidcourse.api.Service;
import com.anotherdeveloper.androidcourse.models.Contact;
import com.anotherdeveloper.androidcourse.utilities.ConnectivityManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class A1 extends AppCompatActivity implements
        ConnectivityManagement.IConnectionCheck,
        Realm.Transaction,
        Realm.Transaction.OnSuccess,
        Realm.Transaction.OnError,
        Callback<Contact[]> {

    ArrayList<Contact> contacts;
    Realm realm;
    ConnectivityManagement connectivityManagement;
    boolean isConnected = false;

    @BindView(R.id.contacts_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        contacts = new ArrayList<>();
        initRecyclerView();
        initConnectivityManagement();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void onOnline(boolean isConnected) {

            loadJSON();
    }

    @Override
    public void onOffline(boolean isConnected) {

            loadFromRealmDatabase();
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
        Toast.makeText(getApplicationContext(), "Transacted to database", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(@NonNull Throwable error) {
        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onResponse(@NonNull Call<Contact[]> call, @NonNull Response<Contact[]> response) {
        contacts.clear();
        List<Contact> contactsFromAPI = Arrays.asList(response.body());
        contacts = new ArrayList<>(contactsFromAPI);
        refreshRecyclerView(R.layout.recycler_view_row);
        saveIntoDatabase();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(@NonNull Call<Contact[]> call, @NonNull Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ContactsRecyclerViewAdapter(contacts, R.layout.recycler_view_row, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initConnectivityManagement() {
        connectivityManagement = new ConnectivityManagement(this);
        isConnected = connectivityManagement.getConnectionStatus(toolbar);
        if (isConnected){
            onOnline(isConnected);
        }else{
            onOffline(isConnected);
        }
    }


    private void loadFromRealmDatabase() {
        realm = Realm.getDefaultInstance();
        RealmResults<Contact> contactRealmResults = realm.where(Contact.class).findAll();
        contacts = new ArrayList<>(contactRealmResults);
        refreshRecyclerView(R.layout.recycler_view_row);
        progressBar.setVisibility(View.GONE);
    }

    private void loadJSON() {
        progressBar.setVisibility(View.VISIBLE);
        contacts = new ArrayList<>();
        try {
            Service service = Client.getClient().create(Service.class);
            final Call<Contact[]> contactsListCall = service.listUsers();
            contactsListCall.enqueue(this);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void refreshRecyclerView(int itemLayout) {
        recyclerView.setAdapter(new ContactsRecyclerViewAdapter(contacts, itemLayout, getApplicationContext()));
        recyclerView.invalidate();
        recyclerView.smoothScrollToPosition(0);
    }

    private void saveIntoDatabase() {
        realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(this, this, this);
    }
}
