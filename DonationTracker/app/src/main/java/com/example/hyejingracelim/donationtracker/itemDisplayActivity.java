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
import android.support.v7.widget.RecyclerView;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class itemDisplayActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private RecyclerView resultList;

    private DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_display_page);

        searchField = (EditText) findViewById(R.id.searchBox);
        searchButton = (Button) findViewById(R.id.searchButton);

        resultList = (RecyclerView) findViewById(R.id.result_list);

        Button buttonAddMore = (Button) findViewById(R.id.add_item);
/*
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });
        */

    }

    public void onClick(View view){
        submitClick();
    }

    public void submitClick() {
        Intent i = new Intent(this, DataEntryActivity.class);
        startActivity(i);
        finish();
    }


}
