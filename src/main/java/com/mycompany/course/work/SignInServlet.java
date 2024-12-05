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
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Niki
 */
public class SignInServlet extends HttpServlet {

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

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CourseWorkDatabase", "se4y", "12345678");

            String email = request.getParameter("email");
            String pwd = request.getParameter("password");
            String hashPassword = HashPassword.hashPassword(pwd);

            statement = conn.prepareStatement("SELECT Email, Password FROM Users WHERE Email = ?");
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("Password");

                    if (hashPassword.equals(hashedPassword)) {
                        HttpSession oldSession = request.getSession(false);                               
                        if (oldSession != null) {
                            oldSession.invalidate();
                        }  
                        
                        HttpSession session = request.getSession(true);  
                        session.setAttribute("email", email);  
                                              
                        response.sendRedirect("JSP Pages/user-dashboard.jsp");
                        return;
                    } else {
                        response.sendRedirect("sign-in.html?error=invalid");
                        return;
                    }
                } else {
                    response.sendRedirect("sign-in.html?error=invalid");
                    return;
                }
            }
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
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