/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.work.dao;

import com.mycompany.course.work.bean.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Niki
 */
public class UserDao {
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection("jdbc:derby://localhost:1527/CourseWorkDatabase", "se4y", "12345678");
    }

    public boolean checkIfUserExists(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean registerUser(User user) {
        String query = "INSERT INTO Users (FirstName, LastName, Email, Password, Country) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getCountry());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public User authenticateUser(String email, String password) {
        String query = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("Country")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}