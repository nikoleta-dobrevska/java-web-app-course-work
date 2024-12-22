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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niki
 */
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int page = 1;
            int recordsPerPage = 5;
            
            if (request.getParameter("page") != null) {
                try {
                    page = Integer.parseInt(request.getParameter("page"));
                } catch (NumberFormatException e) {
                    page = 1;
                }
            }
            
            String origin = request.getParameter("origin");
            String destination = request.getParameter("destination");
            int price = Integer.parseInt(request.getParameter("price"));
            
            List<Flight> flights = FlightDao.searchFlight(origin, destination, price, (page - 1) * recordsPerPage, recordsPerPage);

            int noOfRecords = FlightDao.getNoOfRecords();
            int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);
            
            request.setAttribute("origin", origin);
            request.setAttribute("destination", destination);
            request.setAttribute("price", price);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            if (flights.isEmpty()) {
                response.getWriter().println("No flights found.");
            } else {
                request.setAttribute("matchingFlights", flights);
            }
                       
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP Pages/user/search-result.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().println("An error occurred while fetching flights. Please try again later.");
        }
    }
}