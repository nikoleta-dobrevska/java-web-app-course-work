/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.course.work;

import com.mycompany.course.work.bean.Flight;
import com.mycompany.course.work.dao.FlightDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author Niki
 */
public class FlightServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
            // Fetch flights from the DAO
            List<Flight> flights = FlightDao.getAll();
            
            // Set the flights as a request attribute
           request.setAttribute("allFlights", flights);
           
            // Forward the request to the JSP
            response.sendRedirect(request.getContextPath() + "/JSP Pages/user/user-dashboard.jsp");
            } catch (SQLException e) {
                response.getWriter().println("Error: " + e.getMessage());
        }    
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flightNumber = request.getParameter("flightNumber");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String depDate = request.getParameter("depDate");
        String depTime = request.getParameter("depTime");
        String arrDate = request.getParameter("arrDate");
        String arrTime = request.getParameter("arrTime");
        int price = Integer.parseInt(request.getParameter("price"));
        int seats = Integer.parseInt(request.getParameter("seats"));
        
        FlightDao flightDao = new FlightDao();
        
        Flight flight = new Flight(flightNumber, origin, destination, depDate, depTime, arrDate, arrTime, price, seats);
        boolean isAdded = flightDao.addFlight(flight);   

        if (isAdded) {
            response.sendRedirect(request.getContextPath() + "/JSP Pages/admin/admin-dashboard.jsp");
        } else {
            response.getWriter().println("Error!");
        }
    }
}
