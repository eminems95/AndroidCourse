package com.anotherdeveloper.zadanie_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marcin on 2017-12-16.
 :)
 */

public class A2 extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbarImage) ImageView toolbarImageView;
    @BindView(R.id.phone_number_text_view) TextView phoneNumberTextView;
    @BindView(R.id.collapsingToolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    String contactName;
    String contactNumber;
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
        outState.putString("name",contactName);
        outState.putString("number",contactNumber);
        outState.putString("image",contactImageURI);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contactName = savedInstanceState.getString("name");
        contactNumber = savedInstanceState.getString("number");
        contactImageURI = savedInstanceState.getString("image");
        initLayout();
    }

    private void initLayout() {
        loadImage();
        initSupportActionBar();
        phoneNumberTextView.setText(contactNumber);
    }

    private void getValuesFromIntent() {
        contactName = intent.getStringExtra("name");
        contactNumber = intent.getStringExtra("number");
        contactImageURI = intent.getStringExtra("image");
    }

    public void loadImage() {
        Picasso.with(getApplicationContext()).load(contactImageURI).into(toolbarImageView);
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
