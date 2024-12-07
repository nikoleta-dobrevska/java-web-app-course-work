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

    public static List<Flight> getAll() throws SQLException{  
        List<Flight> list = new ArrayList<Flight>();  
        Connection conn = null;
        PreparedStatement statement = null;

        try{  
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CourseWorkDatabase", "se4y", "12345678");        
            statement = conn.prepareStatement("SELECT * from Flights");  
            ResultSet resultSet = statement.executeQuery();  
            while(resultSet.next()){  
                Flight f = new Flight();  
                f.setId(resultSet.getInt("ID"));  
                f.setOrigin(resultSet.getString("Origin"));  
                f.setFlightNumber(resultSet.getString("FlightNumber"));
                f.setDestination(resultSet.getString("Destination"));  
                f.setDepartureDate(resultSet.getString("DepartureDate"));
                f.setDepartureTime(resultSet.getString("DepartureTime"));  
                f.setArrivalDate(resultSet.getString("ArrivalDate"));  
                f.setArrivalTime(resultSet.getString("ArrivalTime"));
                f.setPrice(resultSet.getInt("Price"));
                f.setSeats(resultSet.getInt("Seats"));
                list.add(f);  
            }  
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                
                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e) {
                    System.out.println(e);
            }
        }  
        
        return list;
    }
}