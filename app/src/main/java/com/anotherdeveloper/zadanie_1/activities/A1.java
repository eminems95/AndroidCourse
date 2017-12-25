package com.anotherdeveloper.zadanie_1.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anotherdeveloper.zadanie_1.R;
import com.anotherdeveloper.zadanie_1.adapters.ContactsRecyclerViewAdapter;
import com.anotherdeveloper.zadanie_1.api.Client;
import com.anotherdeveloper.zadanie_1.api.Service;
import com.anotherdeveloper.zadanie_1.models.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class A1 extends AppCompatActivity {

    List<Contact> contacts;
    @BindView(R.id.contacts_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);
        ButterKnife.bind(this);
        contacts = new ArrayList<>();

        loadJSON();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ContactsRecyclerViewAdapter(contacts, R.layout.recycler_view_row, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadJSON() {
        progressBar.setVisibility(View.VISIBLE);
        try {
            Service service = Client.getClient().create(Service.class);
            final Call<Contact[]> contactsListCall = service.listUsers();
            contactsListCall.enqueue(new Callback<Contact[]>() {
                @Override
                public void onResponse(@NonNull Call<Contact[]> call, @NonNull Response<Contact[]> response) {
                    contacts.clear();
                    List<Contact> contactsFromAPI = Arrays.asList(response.body());
                    recyclerView.setAdapter(new ContactsRecyclerViewAdapter(contactsFromAPI, R.layout.recycler_view_row, getApplicationContext()));
                    recyclerView.invalidate();
                    recyclerView.smoothScrollToPosition(0);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(@NonNull Call<Contact[]> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "Can't download contacts properly", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
