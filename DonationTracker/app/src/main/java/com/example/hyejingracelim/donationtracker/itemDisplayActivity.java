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

    private String myTime;
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String location;
    private String catagory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_display_page);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myTime = extras.getString("time");
            name = extras.getString("name");
            shortDescription = extras.getString("shortDescription");
            fullDescription = extras.getString("fullDescription");
            location = extras.getString("location");
            catagory = extras.getString("catagory");
        }
    }


}
