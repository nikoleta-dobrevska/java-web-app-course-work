/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.course.work;

import com.mycompany.course.work.bean.User;
import com.mycompany.course.work.bean.UserRole;
import com.mycompany.course.work.dao.UserDao;
import com.mycompany.course.work.dao.UserRoleDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        String hashedPassword = null;
        
        try {
            hashedPassword = HashPassword.hashPassword(password);
        } catch (Exception ex) {
            Logger.getLogger(SignInServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        User user = userDao.authenticateUser(email, hashedPassword);       
        
        if (user != null) {

            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession newSession = request.getSession();
            newSession.setAttribute("currentUser", user);

            UserRoleDao userRoleDao = new UserRoleDao();
            UserRole role = userRoleDao.getRole(user.getId());
            
            if (role == null) {
                response.sendRedirect("JSP Pages/sign-in.jsp?role=notfound");
                return;
            }
            
            newSession.setAttribute("role", role.getRoleId());
            
            switch (role.getRoleId()) {
                case 2:
                    response.sendRedirect(request.getContextPath() + "/JSP Pages/user/user-dashboard.jsp");
                    break;
                case 1:
                    newSession.setAttribute("role", role.getRoleId());
                    response.sendRedirect(request.getContextPath() + "/JSP Pages/admin/admin-dashboard.jsp");
                    break;
                default:
                    response.sendRedirect("JSP Pages/sign-in.jsp?role=invalid");
                    break;
            }
        } else {
            response.sendRedirect("JSP Pages/sign-in.jsp?error=invalid");
        }
    }
}