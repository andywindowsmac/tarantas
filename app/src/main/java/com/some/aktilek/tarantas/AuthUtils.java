package com.some.aktilek.tarantas;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Response;

public class AuthUtils {
    private static String AUTH_PREFERENCE = "AUTH_PREFERENCE";
    private static String JWT_TOKEN = "JWT_TOKEN";
    private static String USER_EMAIL = "USER_EMAIL";
    private static String USER_ID = "USER_ID";
    private static String USER_TYPE = "USER_TYPE";

    /* Token utils */
    public static boolean setUserData(String token, String email, String userId, String userType, Context  context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putString(JWT_TOKEN, token);
            editor.putString(USER_EMAIL, email);
            editor.putString(USER_ID, userId);
            editor.putString(USER_TYPE, userType);
            editor.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getToken(Context  context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String token = sh.getString(JWT_TOKEN, "");
            if (token == null) {
                throw new Exception("empty");
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getEmail(Context  context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String token = sh.getString(USER_EMAIL, "");
            if (token == null) {
                throw new Exception("empty");
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getUserId(Context  context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String token = sh.getString(USER_ID, "");
            if (token == null) {
                throw new Exception("empty");
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getUserType(Context  context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String token = sh.getString(USER_TYPE, "");
            if (token == null) {
                throw new Exception("empty");
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean logout(Context context) {
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

    public static boolean isUserAuthenticated(Context context) {
        try {
            SharedPreferences sh = context.getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String token = sh.getString(JWT_TOKEN, "");
            if (token == null) {
                throw new Exception("empty");
            }
            return !token.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void login(Context context, String email, String password, Response.Listener response, Response.ErrorListener error, boolean isEntityUser) {
        if (isEntityUser) {
            API.loginEntity(context, email, password, response, error);
            return;
        }
        API.loginIndividual(context, email, password, response, error);
    }

    public static void signUpIndividual(Context context, String email, String password, String phoneNumber, Response.Listener response, Response.ErrorListener error, boolean isEntityUser) {
        API.signUpIndividual(context, phoneNumber, email, password, response, error);
    }

    public static void signUpEntity(Context context, String email, String password, String companyName, int locationId, String name, String surname, String number, String address, Response.Listener response,Response.ErrorListener error) {
        API.signUpEntity(context,email,password,companyName,locationId,name, surname,number,address,response,error);
    }

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
