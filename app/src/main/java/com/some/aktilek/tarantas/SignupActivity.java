package com.some.aktilek.tarantas;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class SignupActivity extends AppCompatActivity {
    private boolean isErrorExist = false;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mPhoneNumberView;
    private TextInputLayout emailLayout;
    private TextInputLayout passwordLayout;

    private EditText companyName;
    private EditText firstName;
    private EditText lastName;
    private EditText city;
    private EditText address;

    private View mProgressView;
    private View mLoginFormView;
    private CheckBox checkBox;
    private LinearLayout entityFields;

    private Boolean isEntityUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPhoneNumberView = findViewById(R.id.phoneNumber);
        mEmailView.setOnFocusChangeListener(this.handleInputFocusChange());
        emailLayout = findViewById(R.id.email_text_input);
        passwordLayout = findViewById(R.id.password_text_input);
        checkBox = findViewById(R.id.isEntityUser);
        entityFields = findViewById(R.id.entityFields);

        companyName = findViewById(R.id.companyName);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        city = findViewById(R.id.city);
        address = findViewById(R.id.address);

        checkBox.setOnClickListener(this.handleIsEntityUser());
        Button mEmailSignUpButton = findViewById(R.id.email_sign_up_button);
        mEmailSignUpButton.setOnClickListener(this.handleSignUpButton());
        Button mSignInSuggestionButton = findViewById(R.id.email_sign_in_suggestion);
        mSignInSuggestionButton.setOnClickListener(this.handleSignInSuggestionButton());

        mLoginFormView = findViewById(R.id.signup_form);
        mProgressView = findViewById(R.id.signup_progress);
    }

    private OnClickListener handleIsEntityUser() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                isEntityUser = !isEntityUser;
                changeView();
            }
        };
    }

    private void changeView() {
        entityFields.setVisibility(isEntityUser ? View.VISIBLE : View.GONE);
    }

    private OnClickListener handleSignInSuggestionButton() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
    }

    private OnClickListener handleSignUpButton() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        };
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

    private void clearErrors() {
        if (!isErrorExist) return;
        emailLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);
    }

    private void setEmailErrors() {
        emailLayout.setError(getResources().getString(R.string.error_invalid_email));
        isErrorExist = true;
    }

    private void setPasswordErrors() {
        passwordLayout.setError(getResources().getString(R.string.error_invalid_password));
        isErrorExist = true;
    }

    /* Additional */
    private void signUp() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String phoneNumber = mPhoneNumberView.getText().toString();

        if (!AuthUtils.isEmailValid(email)) {
            this.setEmailErrors();
        }

        if (!AuthUtils.isPasswordValid(password)) {
            this.setPasswordErrors();
            return;
        }

        if (phoneNumber.isEmpty()) {
            return;
        }

        mProgressView.setVisibility(View.VISIBLE);
        if (this.isEntityUser) {
            String companyNameString = companyName.getText().toString();
            if(companyNameString.isEmpty()) {
                return;
            }

            String firstNameString = firstName.getText().toString();
            if(firstNameString.isEmpty()) {
                return;
            }

            String lastNameString = lastName.getText().toString();
            if(lastNameString.isEmpty()) {
                return;
            }

            String cityString = city.getText().toString();
            if(cityString.isEmpty()) {
                return;
            }

            String addressString = address.getText().toString();
            if(addressString.isEmpty()) {
                return;
            }

            AuthUtils.signUpEntity(this,email, password, companyNameString, 0, firstNameString, lastNameString, phoneNumber, addressString, this.handleUserSignUp(), this.handleUserSignUpError());
            return;
        }
        AuthUtils.signUpIndividual(this,phoneNumber, email, password,this.handleUserSignUp(), this.handleUserSignUpError(), isEntityUser);
    }

    private Response.ErrorListener handleUserSignUpError() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                displayError();
                mProgressView.setVisibility(View.GONE);
            }
        };
    }

    private void displayError() {
        Toast.makeText(this, "Sign up fail", Toast.LENGTH_LONG).show();
    }

    private Response.Listener<String> handleUserSignUp() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.contentEquals("error")) {
                        String message = jsonObject.getString("message");
                        Log.d("LoginActivity", message);
                        mProgressView.setVisibility(View.GONE);
                        return;
                    }
                    Log.d("SignupActivity", status);
                    String userType = jsonObject.getString("userType");
                    String token = jsonObject.getString("token");
                    String userId = jsonObject.getString("id");
                    String email = jsonObject.getString("email");
                    saveUserData(token, userType, userId, email);
                    mProgressView.setVisibility(View.GONE);
                } catch (Exception e) {
                    mProgressView.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        };
    }

    private void saveUserData(String token,String userType,String userId,String email) {
        AuthUtils.setUserData(token, email, userId, userType, this);
        Toast.makeText(this, "Sign up succeed", Toast.LENGTH_LONG).show();
        this.finish();
    }
}
