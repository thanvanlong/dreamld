package com.example.dreamtale.utils;

import com.example.dreamtale.network.dto.AuthRequestBody;

public class AuthUtils {

    public static boolean validatePhone(String phoneNumber) {
        String patternPhone = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        String patternPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)";

        return phoneNumber.matches(patternPhone);
    }

    public static boolean validatePass(String pass) {
        String patternPhone = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        String patternPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)";

        return pass.matches(patternPass);
    }

    public static boolean validatePhoneAndPass(AuthRequestBody authBody) {
        String patternPhone = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        String patternPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$)";

        return authBody.getPassword().matches(patternPass) && authBody.getPhone().matches(patternPhone);
    }


}
