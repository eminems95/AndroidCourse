package com.anotherdeveloper.androidcourse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anotherdeveloper.androidcourse.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marcin on 2017-12-16.
 * :)
 */

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_details)
    Toolbar toolbar;
    @BindView(R.id.imageview_details_collapsingtoolbarbackground)
    ImageView collapsingToolbarImageView;
    @BindView(R.id.textview_details_phonenumber)
    TextView phoneNumberTextView;
    @BindView(R.id.textview_details_email)
    TextView emailTextView;
    @BindView(R.id.collapsingtoolbarlayout_details_usernamecontainer)
    CollapsingToolbarLayout collapsingToolbarLayout;
    String contactName;
    String contactNumber;
    String contactEmail;
    String contactImageURI;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);
        ButterKnife.bind(this);
        intent = getIntent();
        getValuesFromIntent();
        initLayout();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", contactName);
        outState.putString("number", contactNumber);
        outState.putString("image", contactImageURI);
        outState.putString("email", contactEmail);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contactName = savedInstanceState.getString("name");
        contactNumber = savedInstanceState.getString("number");
        contactImageURI = savedInstanceState.getString("image");
        contactEmail = savedInstanceState.getString("email");
        initLayout();
    }

    private void initLayout() {
        loadImage();
        initSupportActionBar();
        phoneNumberTextView.setText(contactNumber);
        emailTextView.setText(contactEmail);
    }

    private void getValuesFromIntent() {
        contactName = intent.getStringExtra("name");
        contactNumber = intent.getStringExtra("number");
        contactEmail = intent.getStringExtra("email");
        contactImageURI = intent.getStringExtra("image");
    }

    public void loadImage() {
        Picasso.with(getApplicationContext()).load(contactImageURI).into(collapsingToolbarImageView);
    }

    private void initSupportActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(contactName);
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
