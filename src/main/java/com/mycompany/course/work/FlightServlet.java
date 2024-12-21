/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.course.work;

import com.mycompany.course.work.bean.Flight;
import com.mycompany.course.work.dao.FlightDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
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
            List<Flight> flights = FlightDao.getAll();
            
            request.setAttribute("allFlights", flights);

            request.getRequestDispatcher("/JSP Pages/user/user-dashboard.jsp").forward(request, response);
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
        String action = request.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            addFlight(request, response);
        } else if ("edit".equalsIgnoreCase(action)) {
            editFlight(request, response);
        } else if ("delete".equalsIgnoreCase(action)){
            deleteFlight(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
    
    private void addFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            response.sendRedirect(request.getContextPath() + "/JSP Pages/admin/admin-dashboard.jsp?add=success");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error adding flight!");
        }
    }
    
    private void editFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String flightNumber = request.getParameter("flightNumber");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String depDate = request.getParameter("depDate");
        String depTime = request.getParameter("depTime");
        String arrDate = request.getParameter("arrDate");
        String arrTime = request.getParameter("arrTime");
        int price = Integer.parseInt(request.getParameter("price"));
        int seats = Integer.parseInt(request.getParameter("seats"));

        Flight flight = new Flight(id, flightNumber, origin, destination, depDate, depTime, arrDate, arrTime, price, seats);
        var isEdited = FlightDao.update(flight);

        if (isEdited > 0) {
            response.sendRedirect(request.getContextPath() + "/JSP Pages/admin/admin-dashboard.jsp?edit=success");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error editing flight!");
        }
    }
    
    private void deleteFlight (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        if (id != 0) {
            try {
                Flight flight = new Flight();
                flight.id = id;
                int status = FlightDao.delete(flight);
                
                if (status > 0) {
                    response.sendRedirect(request.getContextPath() + "/JSP Pages/admin/admin-dashboard.jsp?delete=success");
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error deleting flight!");
                }
            } catch(IOException e) {
                System.out.println(e);
            }
        }
    }
}