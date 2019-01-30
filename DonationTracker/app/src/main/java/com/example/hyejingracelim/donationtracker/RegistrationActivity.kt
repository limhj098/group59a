package com.example.hyejingracelim.donationtracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.AuthResult
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import android.app.ProgressDialog
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseUser
import android.util.Log

/**
 * Registers the User to the database
 */
class RegistrationActivity : AppCompatActivity(), View.OnClickListener {

    private var progressDialog: ProgressDialog? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var emailField: EditText? = null
    //Cannot be local because it is used be the registerClick method after onCreate
    private var passwordField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)

        emailField = findViewById(R.id.email_input)
        passwordField = findViewById(R.id.password_input)
        val user = findViewById<Spinner>(R.id.user_type)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, User.legalUsers)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        user.adapter = adapter

        val sign = findViewById<Button>(R.id.register_button)

        sign.setOnClickListener(this)

    }

    private fun registerClick() {

        val email = emailField!!.text.toString().trim { it <= ' ' }
        val pass = passwordField!!.text.toString().trim { it <= ' ' }

        progressDialog!!.setMessage("Registering User.")
        progressDialog!!.show()

        firebaseAuth!!.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this@RegistrationActivity) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@RegistrationActivity,
                                "Success", Toast.LENGTH_SHORT).show()
                        sendEmailVerification()
                        finish()
                    }
                    if (!task.isSuccessful) {
                        Toast.makeText(this@RegistrationActivity,
                                "Failed", Toast.LENGTH_SHORT).show()
                    }
                    progressDialog!!.dismiss()
                }
    }

    fun sendEmailVerification() {
        // [START send_email_verification]
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user!!.sendEmailVerification()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                    }
                }
        // [END send_email_verification]
    }


    override fun onClick(view: View) {
        registerClick()
    }

    companion object {

        private val TAG = "RegistrationActivity"
    }

    /*
    public void cancelClick(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }

*/
}

