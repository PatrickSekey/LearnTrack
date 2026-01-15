package com.airtribe.learntrack.util;

public class InputValidator {
    
    // Add 'static' keyword to all methods!
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }
    
    public static boolean isPositiveNumber(int number) {
        return number > 0;
    }
}