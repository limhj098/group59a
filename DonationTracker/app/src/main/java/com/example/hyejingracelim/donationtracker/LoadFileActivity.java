package com.example.hyejingracelim.donationtracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.auth.FirebaseAuth;

public class LoadFileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadfile);
        readFile.readData("LocationData.csv");
    }

    public void loadClick(View view) {
        Button button = (Button) view;
        button.setVisibility(View.GONE);
        readFile.getData();
    }
}
