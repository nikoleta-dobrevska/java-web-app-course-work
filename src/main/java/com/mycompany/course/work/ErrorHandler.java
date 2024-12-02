package com.mycompany.course.work;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;
import static jakarta.servlet.RequestDispatcher.ERROR_MESSAGE;
import static jakarta.servlet.RequestDispatcher.ERROR_REQUEST_URI;
import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Niki
 */
@WebServlet(name = "ErrorHandler", urlPatterns = {"/ErrorHandler"})
public class ErrorHandler extends HttpServlet {
       private static final long serialVersionUID = 1L;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                response.setContentType("text/html;charset=utf-8");
                
                try (PrintWriter writer = response.getWriter()) {
                    Integer statusCode = (Integer) request.getAttribute(ERROR_STATUS_CODE);
                    String requestedUri = (String) request.getAttribute(ERROR_REQUEST_URI);
                    String message = (String) request.getAttribute(ERROR_MESSAGE);
                    Throwable throwable = (Throwable) request.getAttribute(ERROR_EXCEPTION);
                    
                    writer.write("<!DOCTYPE html>");
                    writer.write("<html><head><title>Error/Exception Page</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "</head><body>");
                    writer.write("<h2>Error/Exception Description:</h2>");
                    writer.write("<ul>");
                    
                    if (statusCode != null) {
                        writer.println("<li><b>Status code:</b> " + statusCode + "</li>");
                        writer.write("<li><b>Error message:</b> " + message + "</li>");
                        writer.write("<li><b>Requested URI:</b> " + requestedUri + "</li>");
                    }
                    
                    if (throwable != null) {
                        writer.write("<li><b>Exception type:</b> " + throwable.getClass().getName() + "</li>");
                        writer.write("<li><b>Exception message:</b> " + throwable.getMessage() + "</li>");
                    } 
                    
                    if (statusCode == null && throwable == null) {
                        writer.write("<p>No error/exception info at the moment</p>");
                    }
                    
                    writer.write("</ul>");
                    writer.write("</body></html>");
        }
    }
}
