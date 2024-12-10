<%-- 
    Document   : edit-flights
    Created on : Dec 7, 2024, 1:07:09 PM
    Author     : Niki
--%>

<%@page import="com.mycompany.course.work.dao.FlightDao"%>
<%@page import="com.mycompany.course.work.bean.Flight"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Flight</title>
    </head>
    <body>
        <%
            String id = request.getParameter("id");
            Flight f = FlightDao.getById(Integer.parseInt(id));
        %> 
        
        <h3>Edit Flight</h3>
        <form action="${pageContext.request.contextPath}/FlightServlet" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" value="<%= f.getId()%>">
            <label for="flightNumber">Flight Number</label>
            <input type="text" id="flightNumber" name="flightNumber" value="<%= f.getFlightNumber()%>"/><br>
            <label for="origin">Origin</label>
            <select id="origin" name="origin">
                <option value="Sofia">SOFIA (SOF)</option>
                <option value="Varna">VARNA (VAR)</option>
            </select><br>
            <label for="destination">Destination</label>
            <select id="destination" name="destination">
                <option value="Sofia">SOFIA (SOF)</option>
                <option value="Varna">VARNA (VAR)</option>
                <option value="London">LONDON (LHR)</option>
                <option value="Paris">PARIS (CDG)</option>
                <option value="Madrid">MADRID (MAD)</option>
                <option value="Rome">ROME (FCO)</option>
                <option value="Vienna">VIENNA (VIE)</option>
                <option value="Berlin">BERLIN (BER)</option>
                <option value="Reykjavik">REYKJAVIK (RKV)</option>
                <option value="Oslo">OSLO (OSL)</option>
            </select><br>            
            <label for="depDate">Departure Date</label>
            <input type="text" id="depDate" name="depDate" value="<%= f.getDepartureDate()%>"/><br>
            <label for="depTime">Departure Time</label>
            <input type="text" id="depTime" name="depTime" value="<%= f.getDepartureTime()%>"/><br>
            <label for="arrDate">Arrival Date</label>
            <input type="text" id="arrDate" name="arrDate" value="<%= f.getArrivalDate()%>"/><br>
            <label for="arrTime">Arrival Time</label>
            <input type="text" id="arrTime" name="arrTime" value="<%= f.getArrivalTime()%>"/><br>
            <label for="price">Price</label>
            <input type="number" id="price" name="price" value="<%= f.getPrice()%>"/>
            <label for="seats">Seats</label>
            <input type="number" id="seats" name="seats" value="<%= f.getSeats()%>"/>
            <input type="submit" value="Edit"/>
        </form>
    </body>
</html>
