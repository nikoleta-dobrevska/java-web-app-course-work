/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.course.work;

import com.mycompany.course.work.bean.User;
import com.mycompany.course.work.dao.UserDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("country");

        UserDao userDao = new UserDao();
        boolean isRegistered;
        
        if (userDao.checkIfUserExists(email)) {
            response.sendRedirect("JSP Pages/sign-up.jsp?register=invalid");
            return;
        } else {
            String hashedPassword = null;
        
            try {
                hashedPassword = HashPassword.hashPassword(password);
            } catch (Exception ex) {
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            User user = new User(fname, lname, email, hashedPassword, country);
            isRegistered = userDao.registerUser(user);
        }
        
        if (isRegistered) {
            response.sendRedirect("JSP Pages/sign-in.jsp?register=valid");
        } else {
            response.sendRedirect("JSP Pages/sign-up.jsp?register=invalid");
        }
    }
}