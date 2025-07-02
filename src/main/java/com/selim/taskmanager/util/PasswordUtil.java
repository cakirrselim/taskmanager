package com.selim.taskmanager.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String hashPassword(String plainTextPassword) {
        return encoder.encode(plainTextPassword);
    }
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return encoder.matches(plainTextPassword, hashedPassword);
    }
}
