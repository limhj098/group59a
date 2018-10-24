package com.example.hyejingracelim.donationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.google.firebase.auth.AuthResult;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity implements  View.OnClickListener {

    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private Spinner usertype;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    private User _user;

    protected static List<User> registeredUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        nameField = (EditText) findViewById(R.id.name_input);
        emailField = (EditText) findViewById(R.id.email_input);
        passwordField = (EditText) findViewById(R.id.password_input);
        usertype = (Spinner) findViewById(R.id.user_type);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.legalUsers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertype.setAdapter(adapter);

        buttonSignup = (Button) findViewById(R.id.register_button);

        buttonSignup.setOnClickListener(this);

    }

    public void registerClick() {
      //  _user = new User();
       // registeredUsers.add(_user);

     //   _user.setName(nameField.getText().toString());
     //   _user.setEmail(emailField.getText().toString());
        String email = emailField.getText().toString().trim();
     //   _user.setPassword((passwordField.getText().toString()));
        String pass = passwordField.getText().toString().trim();
     // _user.setUserType((UserType) usertype.getSelectedItem());
/*
        Intent i2 = new Intent(this, UserActivity.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_EMAIL", email);
        extras.putString("EXTRA_PASS", pass);
        i2.putExtras(extras);
        startActivity(i2);
*/
        progressDialog.setMessage("Registering User.");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    public void onClick(View view){
        registerClick();
    }

    /*
    public void cancelClick(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }

*/
}

