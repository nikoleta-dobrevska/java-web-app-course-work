package com.mycompany.course.work;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Niki
 */
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class HashPassword {

    // Method to hash the password using SHA-256
    public static String hashPassword(String password) throws Exception {
        // Create MessageDigest instance for SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Perform the hash calculation
        byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        // Convert the byte array into a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashedBytes) {
            hexString.append(String.format("%02x", b));
        }

        // Return the hashed password as a string
        return hexString.toString();
    }
}