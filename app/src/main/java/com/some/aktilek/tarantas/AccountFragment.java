package com.some.aktilek.tarantas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AccountFragment extends Fragment {
    private Button logoutButton;
    private TextView userEmail;

    public AccountFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // extract args
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        logoutButton = view.findViewById(R.id.logoutButton);
        userEmail = view.findViewById(R.id.userEmail);
        logoutButton.setOnClickListener(this.handleLoginButton());
        this.pushToLogin();
        return view;
    }

    private View.OnClickListener handleLoginButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        };
    }

    private void logout() {
        AuthUtils.logout(this.getContext());
        this.userEmail.setText("");
        this.pushToLogin();
    }

    private void pushToLogin() {
        if (AuthUtils.isUserAuthenticated(this.getContext())) {

            this.bindView();
            return;
        }

        Intent intent = new Intent(this.getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void bindView() {
        String email = AuthUtils.getEmail(this.getContext());
        this.userEmail.setText(email);
    }
}
