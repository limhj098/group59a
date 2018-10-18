package com.example.hyejingracelim.donationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }
//
    /**
     * This method logs the user out of the account using an Intent
     *
     */
    public void logout(View view){
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }

}
