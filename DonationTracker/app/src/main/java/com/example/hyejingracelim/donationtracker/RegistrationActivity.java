package com.example.hyejingracelim.donationtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.auth.AuthResult;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import android.util.Log;

/**
 * Registers the User to the database
 */
public class RegistrationActivity extends AppCompatActivity implements  View.OnClickListener {

    private static final String TAG = "RegistrationActivity";

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText emailField;
    //Cannot be local because it is used be the registerClick method after onCreate
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        emailField = findViewById(R.id.email_input);
        passwordField = findViewById(R.id.password_input);
        Spinner user = findViewById(R.id.user_type);
        ArrayAdapter<String> adapter = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, User.legalUsers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user.setAdapter(adapter);

        Button sign = findViewById(R.id.register_button);

        sign.setOnClickListener(this);

    }

    private void registerClick() {

        String email = emailField.getText().toString().trim();
        String pass = passwordField.getText().toString().trim();

        progressDialog.setMessage("Registering User.");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener
                        (RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText
                                    (RegistrationActivity.this,
                                            "Success", Toast.LENGTH_SHORT).show();
                            sendEmailVerification();
                            finish();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText
                                    (RegistrationActivity.this,
                                            "Failed", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    public void sendEmailVerification() {
        // [START send_email_verification]
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
        // [END send_email_verification]
    }


    @Override
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

