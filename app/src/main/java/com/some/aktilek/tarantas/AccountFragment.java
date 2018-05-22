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

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        // Add an args
        fragment.setArguments(args);
        return fragment;
    }

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
        this.pushToLogin();
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        logoutButton = view.findViewById(R.id.logoutButton);
        userEmail = view.findViewById(R.id.userEmail);
        return view;
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
        String userEmail = AuthUtils.getEmail(this.getContext());
        Log.d("LoginActivity", userEmail);
        this.userEmail.setText(userEmail);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            this.pushToLogin();
        }
    }
}
