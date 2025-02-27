/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.course.work.bean;

/**
 *
 * @author Niki
 */
public class Flight {
    public int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureDate;
    private String departureTime;    
    private String arrivalDate;
    private String arrivalTime;
    private int price;
    private int seats;
    
    public Flight() {}
    
    public Flight(int id, String flightNumber, String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, int price, int seats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.seats = seats;
    }
    
     public Flight(String flightNumber, String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, int price, int seats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.seats = seats;
    }
     
    public Flight(String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, int price, int seats) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.seats = seats;
    }
    
    public int getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    public String getDepartureDate() {
        return departureDate;
    }
        
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    
    public String getArrivalDate() {
        return arrivalDate;
    }
    
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    } 
     public int getSeats() {
        return seats;
    }
    
    public void setSeats(int seats) {
        this.seats = seats;
    }    
}