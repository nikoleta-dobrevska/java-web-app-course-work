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
    private static int noOfRecords;
    
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
    
    public static List<Flight> getAll(int offset, int noOfRecords) throws SQLException {  
        List<Flight> flights = new ArrayList<>();  
        String query = "SELECT * FROM Flights OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String countQuery = "SELECT COUNT(*) AS total FROM Flights";

        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            PreparedStatement countStatement = conn.prepareStatement(countQuery);) {  
            statement.setInt(1, offset);
            statement.setInt(2, noOfRecords);
            
            ResultSet resultSet = statement.executeQuery();  
            while(resultSet.next()) {  
                Flight f = new Flight();  
                f.id = resultSet.getInt("ID");
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
            
            try (ResultSet countResultSet = countStatement.executeQuery()) {
                if (countResultSet.next()) {
                    FlightDao.noOfRecords = countResultSet.getInt("total");
                }
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
    
    public static Flight getById(int id)    {  
        String query = "SELECT * FROM Flights WHERE ID=?";
        Flight f = null;
        
        try (Connection conn = getConnection();  
            PreparedStatement statement = conn.prepareStatement(query))  {
            statement.setInt(1, id);  
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    f = new Flight(
                        resultSet.getInt("ID"),
                        resultSet.getString("FlightNumber"),
                        resultSet.getString("Origin"),
                        resultSet.getString("Destination"),
                        resultSet.getString("DepartureDate"),
                        resultSet.getString("DepartureTime"),
                        resultSet.getString("ArrivalDate"),
                        resultSet.getString("ArrivalTime"),
                        resultSet.getInt("Price"),
                        resultSet.getInt("Seats")
                    );
                }
            }  
        } catch(Exception e){
            System.out.println(e);
        }  

        return f;  
    }  
    
    public static int update(Flight flight) {
        int status = 0;
        String query = "UPDATE Flights SET FlightNumber=?,Origin=?,Destination=?,DepartureDate=?,DepartureTime=?,ArrivalDate=?,ArrivalTime=?,Price=?,Seats=? WHERE ID=?";
        
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
            statement.setInt(10, flight.getId());
            status = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return status;
    }  

    public static int delete(Flight f) {
        int status = 0;
        String query = "DELETE FROM Flights WHERE ID=?";
        
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setInt(1, f.id);
            status = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

        return status;
    }  
    
    public static List<Flight> searchFlight(String origin, String destination, int maxPrice, int offset, int noOfRecords) throws SQLException, ClassNotFoundException {
        List<Flight> flights = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Flights WHERE 1=1");
        StringBuilder countQueryBuilder = new StringBuilder("SELECT COUNT(*) AS total FROM Flights WHERE 1=1");

        if (!"Anywhere".equals(origin)) {
            queryBuilder.append(" AND Origin = ?");
            countQueryBuilder.append(" AND Origin = ?");
        }
        
        if (!"Anywhere".equals(destination)) {
            queryBuilder.append(" AND Destination = ?");
            countQueryBuilder.append(" AND Destination = ?");
        }
        if (maxPrice >= 0) {
            queryBuilder.append(" AND Price <= ?");
            countQueryBuilder.append(" AND Price <= ?");
        }

        queryBuilder.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        String query = queryBuilder.toString();
        String countQuery = countQueryBuilder.toString();

        try (Connection conn = getConnection(); 
            PreparedStatement statement = conn.prepareStatement(query); 
            PreparedStatement countStatement = conn.prepareStatement(countQuery)) {

            int paramIndex = 1;

            if (!"Anywhere".equals(origin)) {
                statement.setString(paramIndex, origin);
                countStatement.setString(paramIndex, origin);
                paramIndex++;
            }
            
            if (!"Anywhere".equals(destination)) {
                statement.setString(paramIndex, destination);
                countStatement.setString(paramIndex, destination);
                paramIndex++;
            }
            
            if (maxPrice >= 0 ) {
                statement.setInt(paramIndex, maxPrice);
                countStatement.setInt(paramIndex, maxPrice);
                paramIndex++;
            }

            statement.setInt(paramIndex, offset);
            statement.setInt(paramIndex + 1, noOfRecords);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Flight f = new Flight(
                            resultSet.getInt("ID"),
                            resultSet.getString("FlightNumber"),
                            resultSet.getString("Origin"),
                            resultSet.getString("Destination"),
                            resultSet.getString("DepartureDate"),
                            resultSet.getString("DepartureTime"),
                            resultSet.getString("ArrivalDate"),
                            resultSet.getString("ArrivalTime"),
                            resultSet.getInt("Price"),
                            resultSet.getInt("Seats")
                    );
                    flights.add(f);
                }
            }

            try (ResultSet countResultSet = countStatement.executeQuery()) {
                if (countResultSet.next()) {
                    FlightDao.noOfRecords = countResultSet.getInt("total");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during flight search: " + e.getMessage());
            throw e;
        }

        return flights;
    }

    public static int getNoOfRecords() { return noOfRecords; } 
}