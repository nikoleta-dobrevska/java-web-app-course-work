/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.work.dao;

import com.mycompany.course.work.bean.Flight;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Niki
 */
public class FlightDao {
    
    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CourseWorkDatabase", "se4y", "12345678");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return conn;
    }
    
    public static List<Flight> getAll() throws SQLException {  
        List<Flight> flights = new ArrayList<>();  
        String query = "SELECT * FROM Flights";
        
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);) {  
            ResultSet resultSet = statement.executeQuery();  
            while(resultSet.next()){  
                Flight f = new Flight();  
                f.setFlightNumber(resultSet.getString("FlightNumber"));
                f.setOrigin(resultSet.getString("Origin"));  
                f.setDestination(resultSet.getString("Destination"));  
                f.setDepartureDate(resultSet.getString("DepartureDate"));
                f.setDepartureTime(resultSet.getString("DepartureTime"));  
                f.setArrivalDate(resultSet.getString("ArrivalDate"));  
                f.setArrivalTime(resultSet.getString("ArrivalTime"));
                f.setPrice(resultSet.getInt("Price"));
                f.setSeats(resultSet.getInt("Seats"));
                flights.add(f);  
            }  
        } catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
          
        return flights;
    }
    
    public boolean addFlight(Flight flight) {
        String query = "INSERT INTO Flights (FlightNumber, Origin, Destination, DepartureDate, DepartureTime, ArrivalDate, ArrivalTime, Price, Seats) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, flight.getFlightNumber());
            statement.setString(2, flight.getOrigin());
            statement.setString(3, flight.getDestination());
            statement.setString(4, flight.getDepartureDate());
            statement.setString(5, flight.getDepartureTime());
            statement.setString(6, flight.getArrivalDate());
            statement.setString(7, flight.getArrivalTime());
            statement.setInt(8, flight.getPrice());
            statement.setInt(9, flight.getSeats());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}