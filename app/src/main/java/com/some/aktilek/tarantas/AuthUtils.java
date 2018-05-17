package com.some.aktilek.tarantas;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthUtils {
    private String AUTH_PREFERENCE = "AUTH_PREFERENCE";
    private String JWT_TOKEN = "JWT_TOKEN";

    /* Token utils */
    public boolean setToken(String token, Context  context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putString(JWT_TOKEN, token);
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getToken(Context  context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String token = sh.getString(JWT_TOKEN, null);
            editor.commit();
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean clearToken(Context context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.clear();
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUserAuthenticated(Context context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String token = sh.getString(JWT_TOKEN, null);
            if (token.isEmpty()) throw new Error("User does not exist");
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void login(String email, String password) {

    }

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
