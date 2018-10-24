package com.example.hyejingracelim.donationtracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class itemDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_display_page);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String myTime = extras.getString("time");
            String name = extras.getString("name");
            String shortDescription = extras.getString("shortDescription");
            String fullDescription = extras.getString("fullDescription");
            String location = extras.getString("location");
            String catagory = extras.getString("catagory");
        }
    }


}
