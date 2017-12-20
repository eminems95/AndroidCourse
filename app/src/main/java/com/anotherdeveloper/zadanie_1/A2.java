package com.anotherdeveloper.zadanie_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Marcin on 2017-12-16.
 */

public class A2 extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarImage)
    ImageView toolbaImageView;
    @BindView(R.id.phone_number_text_view)
    TextView phoneNumber;
    @BindView(R.id.email_text_view)
    TextView email;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    Contact contact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);
        ButterKnife.bind(this);
        contact = new Contact("Adam",
                "123456789",
                "http://lh3.googleusercontent.com/GWfQhWY8bFwJipJZW5zdZ3EPG7oOP88diyyDzYVDJGZde7EcKsF9LbVXETD0RZtusM9R=w300" );
        initLayout();
    }

    private void initLayout() {
        loadImage();
        phoneNumber.setText(contact.getPhoneNumber());
        initSupportActionBar();
    }

    public void loadImage(){
        Picasso.with(getApplicationContext()).load(contact.getProfilePhoto()).fit().into(toolbaImageView);
    }

    private void initSupportActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        collapsingToolbarLayout.setTitle(contact.getName());
    }

    public void finishActivity() {
        Toast.makeText(getApplicationContext(), "Activity closed", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
