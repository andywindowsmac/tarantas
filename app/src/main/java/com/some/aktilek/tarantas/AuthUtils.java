package com.some.aktilek.tarantas;

public class AuthUtils {
    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
