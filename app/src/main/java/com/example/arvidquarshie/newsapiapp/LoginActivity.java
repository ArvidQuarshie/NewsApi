package com.example.arvidquarshie.newsapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText username;
    private TextView forgot;
    private TextInputLayout username_input_layout, password_input_layout;
    //regex used  to validate the email pattern.
    public static final Pattern email_regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        login = (Button) findViewById(R.id.login);


        username_input_layout = (TextInputLayout) findViewById(R.id.usernameInputLayout);
        password_input_layout = (TextInputLayout) findViewById(R.id.passwordInputLayout);
        username = (EditText) findViewById(R.id.username);
        forgot = (TextView) findViewById(R.id.forgot);

        if (validate(username.getText().toString()) == false) {
            username_input_layout.requestFocus();
//            username_input_layout.setErrorEnabled(true);
            username_input_layout.setError("e.g johndoe@gmail.com");

        } else {
            username_input_layout.setError("");
        }

        if(username.getText().toString().isEmpty()){
            Toast.makeText(getBaseContext(), "Hi User," + "Password should not be empty.", Toast.LENGTH_LONG).show();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //At this point we perform gmail Api authentication
//Had problems with keytool command therefore not able to generate sha1 key

                // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                        .requestEmail()
//                        .build();

                // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
//                mGoogleApiClient = new GoogleApiClient.Builder(this)
//                        .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                        .build();


                if (username.getText().toString().isEmpty()) {

                } else {
                    Intent intent = new Intent(LoginActivity.this, LauncherActivity.class);
                    startActivity(intent);
                }

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Forgot Password? " + "Use your official gmail account to recover.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * function that validates the email address format
     *
     * @param email
     * @return
     */
    public static boolean validate(String email) {
        Matcher matcher = email_regex.matcher(email);
        return matcher.find();
    }

    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * check if is empty utility function
     *
     * @param text
     * @return
     */
    public static boolean isEmptyString(String text) {
        boolean isEmpty = false;
        try {
            isEmpty = (text == null || text.trim().equals("null") || text.trim().equals(""));
        } catch (Exception ex) {
            isEmpty = false;
        }
        return isEmpty;
    }
}
