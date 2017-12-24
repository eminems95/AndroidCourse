package com.anotherdeveloper.zadanie_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.anotherdeveloper.zadanie_1.adapters.ContactsRecyclerViewAdapter;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class A1 extends AppCompatActivity {

    ArrayList<Contact> contacts;
    @BindView(R.id.contacts_recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);
        ButterKnife.bind(this);
        initList();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ContactsRecyclerViewAdapter(contacts, R.layout.recycler_view_row, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void initList() {
        contacts = new ArrayList<>();
        contacts.add(new Contact("Marcin", "521521123", "https://i.pinimg.com/736x/a2/de/39/a2de3954697c636276192afea0a6f661--web-design-tools-extension.jpg"));
        contacts.add(new Contact("Siemomysł", "603521123", "https://s3-us-west-2.amazonaws.com/s.cdpn.io/305956/profile/profile-512.jpg?6"));
        contacts.add(new Contact("Krycha", "511912123", "http://web-creator.pl/szablony/projektowanie-wnetrz/images/demo/team/team_1.png.jpg"));
        contacts.add(new Contact("Grzęsław", "581510153", "https://vignette.wikia.nocookie.net/mrbean/images/4/4b/Mr_beans_holiday_ver2.jpg/revision/latest/scale-to-width-down/250?cb=20100424114324"));
        contacts.add(new Contact("Angelika", "321001123", "http://geedmo.com/codecanyon/47admin/app/img/user/06.jpg"));
    }
}
