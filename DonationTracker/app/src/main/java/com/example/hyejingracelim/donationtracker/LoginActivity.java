package com.example.hyejingracelim.donationtracker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.os.*;


import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.AuthResult;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.util.ArrayList;
import java.util.List;
import android.view.View;


import static android.Manifest.permission.READ_CONTACTS;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.app.TaskStackBuilder;
import android.app.PendingIntent;

/**
 * A login screen that offers login via email/password.
 *
 * Warnings:
 *
 * Android: Lint: Performance: Static Field Leaks:
 *  Is dependent on class LoginActivity and cannot rework logic
 */

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    private static final String TAG = "LoginActivity";
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.

     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    @Nullable
    private UserLoginTask mAuthTask;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private boolean accessGranted; // goes true after correct info entering
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private int counter = 2;

    private int numberOfCalls;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        //fb stuff
        //FacebookSDK.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton) findViewById(R.id.login_button); //facebook
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        goMainScreen();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this,
                                "Canceled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this,
                                "Error", Toast.LENGTH_SHORT).show();
                    }
                });


        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if ((id == EditorInfo.IME_ACTION_DONE) || (id == EditorInfo.IME_NULL)) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

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

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, LocationsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
    These two methods are listeners for the cancel and login button clicking
    *This way of handling clicks is not the best way, so it's temporary now
    *
    * This maybe developed later to save what needs to be saved before
    * calling System.exit()
     *
     * @param view screen where button is
    */
    public void cancelClick(View view){
        finish();
        //System.exit(0);
    }

    /**
     * @param view screen where button is
     *
     */
    public void loginClick(View view){
        attemptLogin();
        numberOfCalls=0;
        if(accessGranted) {
            accessGranted = false;
           // Intent i = new Intent(this, LocationsActivity.class);//startActivity(i);
        }

    }

    public void forgotPasswordClick(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        String email = mEmailView.getText().toString();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
    }
    /**
     * @param view screen where button is
     *
     */
    public void registerClick(View view){
            Intent i2 = new Intent(this, RegistrationActivity.class);
            startActivity(i2);
    }

    public void guestClick(View view){
        Intent i3 = new Intent(this, LocationsActivity.class);
        startActivity(i3);
    }

//    public void setAccessGranted(){
//        accessGranted = false;
//    }

    /**
     * Warnings:
     * Code style issues: Chained method calls:
     *  Chained method call 'initLoader()' cannot be reworked because it is amongst 12 other chained
     *  method calls
     */
    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }
    /**
     * Warnings:
     * Code style issues: Chained method calls:
     *  Chained method call 'setAction()' cannot be reworked because it is amongst 12 other chained
     *  method calls
     */

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if ((grantResults.length == 1)
                    && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     *
     * Warnings:
     *  Code style issues: Chained method calls:
     *      Chained method call 'initLoader()' cannot be reworked because it is amongst 12 other
     *      chained method calls
     *
     *      Chained method call 'addOnCompleteListener()' cannot be reworked because it is amongst
     *      12 other chained method calls
     *
     *      Chained method call 'show()' cannot be reworked because it is amongst
     *      12 other chained method calls
     *
     */



    @TargetApi(26)
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        final Button logButton = findViewById(R.id.email_sign_in_button);

        boolean cancel = false;
        View focusView = null;

        // Check if user entered a password.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Your password is required");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
       if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
           mPasswordView.setError(getString(R.string.error_invalid_password));
           focusView = mPasswordView;
           cancel = true;
       }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if (task.isSuccessful()){
                            numberOfCalls++;
                            if(numberOfCalls==1) {
                                //finish();
                                Log.d("Moose", "How many times ***");

                                int NOTIFICATION_ID = 234;

                                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                                String CHANNEL_ID = "my_channel_01";
                                CharSequence name = "my_channel";
                                String Description = "This is my channel";
                                int importance = NotificationManager.IMPORTANCE_HIGH;
                                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                                mChannel.setDescription(Description);
                                mChannel.enableLights(true);
                                mChannel.setLightColor(Color.RED);
                                mChannel.enableVibration(true);
                                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                                mChannel.setShowBadge(false);
                                notificationManager.createNotificationChannel(mChannel);


                                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Donation Tracker")
                                        .setContentText("You have successfully logged in");

                                Intent resultIntent = new Intent(getApplicationContext(),
                                        LocationsActivity.class);
                                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                                stackBuilder.addParentStack(LoginActivity.class);
                                stackBuilder.addNextIntent(resultIntent);
                                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                                builder.setContentIntent(resultPendingIntent);

                                notificationManager.notify(NOTIFICATION_ID, builder.build());

                                startActivity(new Intent(getApplicationContext(),
                                        LocationsActivity.class));
                            }
                        } else if (!task.isSuccessful() && counter == 0){
                                logButton.setEnabled(false);
                                Toast alert = Toast.makeText(LoginActivity.this, "Login Disabled for 5 mins", Toast.LENGTH_SHORT);
                                alert.show();
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable()
                                {   @Override
                                public void run()
                                {   logButton.setEnabled(true);
                                    counter = 2;
                                }
                                }, 300000);
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        "Wrong password or email", Toast.LENGTH_SHORT).show();
                                counter--;
                            }

                    }
                });


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
    }


    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(CharSequence password) {

        return password.length() > 4;
    }


    /**
     *
     * Shows the progress UI and hides the login form.
     * Warnings:
     *  Code style issues: Chained method calls:
     *      Chained method call 'initLoader()' cannot be reworked because it is amongst 12 other
     *
     *
     *      Chained method call 'setDuration()' cannot be reworked because it is amongst 12 other
     *      chained method calls
     *
     *      Chained method call 'alpha()' cannot be reworked because it is amongst 12 other
     *      chained method calls
     *
     *      Chained method call 'setListener()' cannot be reworked because it is amongst 12 other
     *      chained method calls
     *
     *      Chained method call 'setDuration()' cannot be reworked because it is amongst 12 other
     *      chained method calls
     */



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress() {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(View.VISIBLE);
            }
        });

        mProgressView.setVisibility(View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
//        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     *
     * Warnings:
     *
     * Android: Lint: Performance: Static Field Leaks:
     *      Is dependent on class LoginActivity and cannot rework logic
     *      Cannot make class static because non-static fields mAuthTask and mPasswordView cannot be
     *      referenced by static content
     *
     *  Java: Abstraction issues: Magic number: number is set from template, cannot be changed
     *
     *  Java: Declaration redundancy: Unused declaration:
     *      Although UserLoginTask constructor is never used, code breaks if commented out
     */
    class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {


            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }


            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress();

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress();
        }
    }
}

