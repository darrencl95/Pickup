package com.pickup.pickup.controller;

import android.widget.Toast;

/**
 * Created by zachschlesinger on 3/4/17.
 */

public class CredentialVerification {

    // passwords require uppercase, lowercase, and numbers. minimum of 8 characters
    // emails require string + @ + string + . + string

    private static String verifyPassword(String password) {
        String message;
        boolean length = password.length() >= 8;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.matches(".*\\d+.*");

        message = (!length) ? "Password is too short" : null;
        message = (!hasUppercase) ? "Password does not contain an uppercase" : null;
        message = (!hasLowercase) ? "Password does not contain an owercase" : null;
        message = (!hasNumber) ? "Password does not contain a number" : null;


        return message;
    }

}
