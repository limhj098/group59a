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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class itemDisplayActivity extends AppCompatActivity implements View.OnClickListener{

    private String myTime;
    private String name;
    private String shortDescription;
    private String fullDescription;
    private String location;
    private String catagory;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_display_page);

        Button buttonAddMore = (Button) findViewById(R.id.add_item);
        buttonAddMore.setOnClickListener(this);
/*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            myTime = extras.getString("time");
            name = extras.getString("name");
            shortDescription = extras.getString("shortDescription");
            fullDescription = extras.getString("fullDescription");
            location = extras.getString("location");
            catagory = extras.getString("catagory");
        }
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
