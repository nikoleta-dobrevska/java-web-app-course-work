/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.work.dao;

import com.mycompany.course.work.bean.UserRole;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Niki
 */
public class UserRoleDao {
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        return DriverManager.getConnection("jdbc:derby://localhost:1527/CourseWorkDatabase", "se4y", "12345678");
    }
    
    public boolean setRole (int userId, int roleId) {
        String query = "INSERT INTO UserRole (UserID, RoleID) VALUES (?, ?)";
        
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, roleId);
            
            return statement.executeUpdate() > 0; 
        } catch (Exception e) {
        System.out.println("Error in setRole: " + e.getMessage());
        }
        
        return false;
    }  
    
    public UserRole getRole(int userId) {
        String query = "SELECT * FROM UserRole WHERE UserID = ?";
        try (Connection conn = getConnection(); 
            PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new UserRole(
                        resultSet.getInt("UserID"),
                        resultSet.getInt("RoleID")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}