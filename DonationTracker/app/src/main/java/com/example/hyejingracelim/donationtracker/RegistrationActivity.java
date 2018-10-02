package com.example.hyejingracelim.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private Spinner usertype;

    private User _user;

    protected static List<User> registeredUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameField = (EditText) findViewById(R.id.name_input);
        emailField = (EditText) findViewById(R.id.email_input);
        passwordField = (EditText) findViewById(R.id.password_input);
        usertype = (Spinner) findViewById(R.id.user_type);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.legalUsers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertype.setAdapter(adapter);

    }

    public void registerClick(View view) {
        _user = new User();
        registeredUsers.add(_user);

        _user.setName(nameField.getText().toString());
        _user.setEmail(emailField.getText().toString());
        _user.setPassword((passwordField.getText().toString()));
        _user.setUserType((UserType) usertype.getSelectedItem());

        Intent i2 = new Intent(this, UserActivity.class);
        startActivity(i2);

    }

    public void cancelClick(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }




}

