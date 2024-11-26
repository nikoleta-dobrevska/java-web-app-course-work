/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.course.work;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Niki
 */
public class SignUpServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CourseWorkDatabase", "se4y", "12345678");

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String country = request.getParameter("country");

            statement = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE Email = ?");
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                if (resultSet.getInt(1) > 0) {
                    response.sendRedirect("sign-up.html?register=invalid");
                    return;
                }
            }

            String hashedPassword = HashPassword.hashPassword(password);

            statement2 = conn.prepareStatement(
                "INSERT INTO Users (FirstName, LastName, Email, Password, Country) VALUES (?, ?, ?, ?, ?)"
            );
            
            statement2.setString(1, fname);
            statement2.setString(2, lname);
            statement2.setString(3, email);
            statement2.setString(4, hashedPassword);
            statement2.setString(5, country);

            int result2 = statement2.executeUpdate();

            if (result2 > 0) {
                response.sendRedirect("sign-in.html?register=valid");
            }
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (statement2 != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                response.getWriter().println("Error: " + e.getMessage());
            }
        }
    }
}