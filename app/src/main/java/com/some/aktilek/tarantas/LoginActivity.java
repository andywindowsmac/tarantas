package com.some.aktilek.tarantas;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private boolean isErrorExist = false;

    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button signButton;
    private Button signUpSuggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        this.initView();
        this.setListeners();
    }

    /* Views */
    public void initView() {
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        signButton = findViewById(R.id.email_sign_in_button);
        signUpSuggestion = findViewById(R.id.email_sign_up_suggestion);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        emailLayout = findViewById(R.id.emailLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
    }

    private void setEmailErrors() {
        emailLayout.setError(getResources().getString(R.string.error_invalid_email));
        isErrorExist = true;
    }

    private void setPasswordErrors() {
        passwordLayout.setError(getResources().getString(R.string.error_invalid_password));
        isErrorExist = true;
    }

    private void clearErrors() {
        if (!isErrorExist) return;
        emailLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);
    }

    /* Listeners */
    private void setListeners() {
        mEmailView.setOnFocusChangeListener(this.handleInputFocusChange());
        signButton.setOnClickListener(this.handleSignButtonPress());
        signUpSuggestion.setOnClickListener(this.handleSignUpSuggestionPress());
    }

    private View.OnFocusChangeListener handleInputFocusChange() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    clearErrors();
                }
            }
        };
    }

    private OnClickListener handleSignUpSuggestionPress() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                pushToSignUp();
            }
        };
    }

    private OnClickListener handleSignButtonPress() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        };
    }

    /* Additional */
    private void login() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (!AuthUtils.isEmailValid(email)) {
            this.setEmailErrors();
        }

        if (!AuthUtils.isPasswordValid(password)) {
            this.setPasswordErrors();
            return;
        }
    }

    private void pushToSignUp() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}

