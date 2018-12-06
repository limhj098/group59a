package com.example.hyejingracelim.donationtracker

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar

import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks

import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask

import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.os.*


import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.AuthResult

import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton


import java.util.ArrayList


import android.Manifest.permission.READ_CONTACTS

import android.app.Notification
import android.app.NotificationManager
import android.app.NotificationChannel

import android.content.Context
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.app.TaskStackBuilder
import android.app.PendingIntent

/**
 * A login screen that offers login via email/password.
 *
 * Warnings:
 *
 * Android: Lint: Performance: Static Field Leaks:
 * Is dependent on class LoginActivity and cannot rework logic
 */

class LoginActivity : AppCompatActivity(), LoaderCallbacks<Cursor> {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private var mAuthTask: UserLoginTask? = null

    // UI references.
    private var mEmailView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null
    private var mProgressView: View? = null
    private var mLoginFormView: View? = null
    private var accessGranted: Boolean = false // goes true after correct info entering
    private var loginButton: LoginButton? = null
    private var callbackManager: CallbackManager? = null
    private var counter = 2

    private var numberOfCalls: Int = 0

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()

        //fb stuff
        //FacebookSDK.sdkInitialize(getApplicationContext());
        loginButton = findViewById<View>(R.id.login_button) as LoginButton //facebook
        callbackManager = CallbackManager.Factory.create()

        loginButton!!.registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        goMainScreen()
                    }

                    override fun onCancel() {
                        Toast.makeText(this@LoginActivity,
                                "Canceled", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(exception: FacebookException) {
                        Toast.makeText(this@LoginActivity,
                                "Error", Toast.LENGTH_SHORT).show()
                    }
                })


        // Set up the login form.
        mEmailView = findViewById(R.id.email)
        populateAutoComplete()

        mPasswordView = findViewById(R.id.password)
        mPasswordView!!.setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        //        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        //        ProgressDialog progressDialog = new ProgressDialog(this);

        /* mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
               // Intent i = new Intent(this,LocationsActivity.class);
               // startActivity(i);
            }
        });
        */

        mLoginFormView = findViewById(R.id.login_form)
        mProgressView = findViewById(R.id.login_progress)
    }

    private fun goMainScreen() {
        val intent = Intent(this, LocationsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * These two methods are listeners for the cancel and login button clicking
     * This way of handling clicks is not the best way, so it's temporary now
     *
     * This maybe developed later to save what needs to be saved before
     * calling System.exit()
     *
     * @param view screen where button is
     */
    fun cancelClick(view: View) {
        finish()
        //System.exit(0);
    }

    /**
     * @param view screen where button is
     */
    fun loginClick(view: View) {
        attemptLogin()
        numberOfCalls = 0
        if (accessGranted) {
            accessGranted = false
            // Intent i = new Intent(this, LocationsActivity.class);//startActivity(i);
        }

    }

    fun forgotPasswordClick(view: View) {
        val auth = FirebaseAuth.getInstance()

        val email = mEmailView!!.text.toString()

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                    }
                }
    }

    /**
     * @param view screen where button is
     */
    fun registerClick(view: View) {
        val i2 = Intent(this, RegistrationActivity::class.java)
        startActivity(i2)
    }

    fun guestClick(view: View) {
        val i3 = Intent(this, LocationsActivity::class.java)
        startActivity(i3)
    }

    //    public void setAccessGranted(){
    //        accessGranted = false;
    //    }

    /**
     * Warnings:
     * Code style issues: Chained method calls:
     * Chained method call 'initLoader()' cannot be reworked because it is amongst 12 other chained
     * method calls
     */
    private fun populateAutoComplete() {
        if (!mayRequestContacts()) {
            return
        }

        loaderManager.initLoader(0, null, this)
    }

    /**
     * Warnings:
     * Code style issues: Chained method calls:
     * Chained method call 'setAction()' cannot be reworked because it is amongst 12 other chained
     * method calls
     */

    private fun mayRequestContacts(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView!!, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) { requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS) }
        } else {
            requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS)
        }
        return false
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete()
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     *
     * Warnings:
     * Code style issues: Chained method calls:
     * Chained method call 'initLoader()' cannot be reworked because it is amongst 12 other
     * chained method calls
     *
     * Chained method call 'addOnCompleteListener()' cannot be reworked because it is amongst
     * 12 other chained method calls
     *
     * Chained method call 'show()' cannot be reworked because it is amongst
     * 12 other chained method calls
     *
     */


    @TargetApi(26)
    private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        mEmailView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        val email = mEmailView!!.text.toString()
        val password = mPasswordView!!.text.toString()

        val logButton = findViewById<Button>(R.id.email_sign_in_button)

        var cancel = false
        var focusView: View? = null

        // Check if user entered a password.
        if (TextUtils.isEmpty(password)) {
            mPasswordView!!.error = "Your password is required"
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView!!.error = getString(R.string.error_field_required)
            focusView = mEmailView
            cancel = true
        } else if (!isEmailValid(email)) {
            mEmailView!!.error = getString(R.string.error_invalid_email)
            focusView = mEmailView
            cancel = true
        }

        firebaseAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        numberOfCalls++
                        if (numberOfCalls == 1) {
                            //finish();
                            Log.d("Moose", "How many times ***")

                            val NOTIFICATION_ID = 234

                            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                            val CHANNEL_ID = "my_channel_01"
                            val name = "my_channel"
                            val Description = "This is my channel"
                            val importance = NotificationManager.IMPORTANCE_HIGH
                            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
                            mChannel.description = Description
                            mChannel.enableLights(true)
                            mChannel.lightColor = Color.RED
                            mChannel.enableVibration(true)
                            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                            mChannel.setShowBadge(false)
                            notificationManager.createNotificationChannel(mChannel)


                            val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("Donation Tracker")
                                    .setContentText("You have successfully logged in")

                            val resultIntent = Intent(applicationContext,
                                    LocationsActivity::class.java)
                            val stackBuilder = TaskStackBuilder.create(applicationContext)
                            stackBuilder.addParentStack(LoginActivity::class.java)
                            stackBuilder.addNextIntent(resultIntent)
                            val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

                            builder.setContentIntent(resultPendingIntent)

                            notificationManager.notify(NOTIFICATION_ID, builder.build())

                            startActivity(Intent(applicationContext,
                                    LocationsActivity::class.java))
                        }
                    } else if (!task.isSuccessful && counter == 0) {
                        logButton.isEnabled = false
                        val alert = Toast.makeText(this@LoginActivity, "Login Disabled for 5 mins", Toast.LENGTH_SHORT)
                        alert.show()
                        val handler = Handler()
                        handler.postDelayed({
                            logButton.isEnabled = true
                            counter = 2
                        }, 300000)
                    } else {
                        Toast.makeText(this@LoginActivity,
                                "Wrong password or email", Toast.LENGTH_SHORT).show()
                        counter--
                    }
                }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        }
    }


    private fun isEmailValid(email: String): Boolean {

        return email.contains("@")
    }

    private fun isPasswordValid(password: CharSequence): Boolean {

        return password.length > 4
    }


    /**
     *
     * Shows the progress UI and hides the login form.
     * Warnings:
     * Code style issues: Chained method calls:
     * Chained method call 'initLoader()' cannot be reworked because it is amongst 12 other
     *
     *
     * Chained method call 'setDuration()' cannot be reworked because it is amongst 12 other
     * chained method calls
     *
     * Chained method call 'alpha()' cannot be reworked because it is amongst 12 other
     * chained method calls
     *
     * Chained method call 'setListener()' cannot be reworked because it is amongst 12 other
     * chained method calls
     *
     * Chained method call 'setDuration()' cannot be reworked because it is amongst 12 other
     * chained method calls
     */


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress() {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)

        mLoginFormView!!.visibility = View.VISIBLE
        mLoginFormView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                1f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mLoginFormView!!.visibility = View.VISIBLE
            }
        })

        mProgressView!!.visibility = View.GONE
        mProgressView!!.animate().setDuration(shortAnimTime.toLong()).alpha(
                0f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mProgressView!!.visibility = View.GONE
            }
        })
    }

    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<Cursor> {
        return CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE + " = ?", arrayOf(ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE),

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC")
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        val emails = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS))
            cursor.moveToNext()
        }

        addEmailsToAutoComplete(emails)
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {

    }

    private fun addEmailsToAutoComplete(emailAddressCollection: List<String>) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        val adapter = ArrayAdapter(this@LoginActivity,
                android.R.layout.simple_dropdown_item_1line, emailAddressCollection)

        mEmailView!!.setAdapter(adapter)
    }


    private interface ProfileQuery {
        companion object {
            val PROJECTION = arrayOf(ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract.CommonDataKinds.Email.IS_PRIMARY)

            val ADDRESS = 0
        }
        //        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     *
     * Warnings:
     *
     * Android: Lint: Performance: Static Field Leaks:
     * Is dependent on class LoginActivity and cannot rework logic
     * Cannot make class static because non-static fields mAuthTask and mPasswordView cannot be
     * referenced by static content
     *
     * Java: Abstraction issues: Magic number: number is set from template, cannot be changed
     *
     * Java: Declaration redundancy: Unused declaration:
     * Although UserLoginTask constructor is never used, code breaks if commented out
     */
    internal inner class UserLoginTask(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {


            try {
                // Simulate network access.
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                return false
            }

            for (credential in DUMMY_CREDENTIALS) {
                val pieces = credential.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (pieces[0] == mEmail) {
                    // Account exists, return true if the password matches.
                    return pieces[1] == mPassword
                }
            }


            return true
        }

        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress()

            if (success!!) {
                finish()
            } else {
                mPasswordView!!.error = getString(R.string.error_incorrect_password)
                mPasswordView!!.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress()
        }
    }

    companion object {

        private val TAG = "LoginActivity"
        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         *
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}

