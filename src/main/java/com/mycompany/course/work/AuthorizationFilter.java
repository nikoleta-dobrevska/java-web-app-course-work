/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package com.mycompany.course.work;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.rmi.server.LogStream.log;

/**
 *
 * @author Niki
 */
@WebFilter("/admin/*")
public class AuthorizationFilter implements Filter {
    
    private static final boolean debug = true;
    private ServletContext context;
    private final FilterConfig filterConfig = null;
    
    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
        
        if (filterConfig != null) {
            if (debug) {
                log("AuthenticationFilter:Initializing filter");
            }
        } else {
        }
    }
    
        @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            
            HttpSession session = req.getSession(false);
            
            if (session == null || !"1".equals(session.getAttribute("role").toString())) {
                log("User does not have admin privileges.");
                res.sendRedirect(req.getContextPath() + "/JSP Pages/user/user-dashboard.jsp");
            } else {
                chain.doFilter(request, response);
            }
    }
    
    @Override
    public void destroy() {
    }
}