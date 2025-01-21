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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/add-edit-flight.css">    
        <title>Edit Flight</title>
    </head>
    <body>
        <div class="wrapper">
            <img src="${pageContext.request.contextPath}/Images/header.jpg" alt="header" width="100%" height="255vh">
            <h1>Edit Flight</h1>
            <div class="form-wrapper">
                <%
                    String id = request.getParameter("id");
                    Flight f = FlightDao.getById(Integer.parseInt(id));
                %> 
                <form action="${pageContext.request.contextPath}/FlightServlet" method="post">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="<%= f.getId()%>">
                    <label for="flightNumber">Flight Number</label>
                    <input type="text" id="flightNumber" name="flightNumber" title="Please enter the flight number in the correct format"
                           pattern="[A-Z]{2}[1-9]{3,4}" required value="<%= f.getFlightNumber()%>"/><br>
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
                    <input type="text" id="depDate" name="depDate" title="Please enter the date in the correct format" required  
                           pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})" value="<%= f.getDepartureDate()%>"/><br>
                    <label for="depTime">Departure Time</label>
                    <input type="text" id="depTime" name="depTime" title="Please enter a time in the 24-hour format (14:30)" required 
                           pattern="([01][0-9]|2[0-3]):[0-5][0-9]" value="<%= f.getDepartureTime()%>"/><br>
                    <label for="arrDate">Arrival Date</label>
                    <input type="text" id="arrDate" name="arrDate" title="Please enter the date in the correct format" required 
                           pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})" value="<%= f.getArrivalDate()%>"/><br>
                    <label for="arrTime">Arrival Time</label>
                    <input type="text" id="arrTime" name="arrTime" title="Please enter a time in the 24-hour format (14:30)" required 
                           pattern="([01][0-9]|2[0-3]):[0-5][0-9]" value="<%= f.getArrivalTime()%>"/><br>
                    <label for="price">Price</label>
                    <input type="number" id="price" name="price" title="Please enter the price" required value="<%= f.getPrice()%>"/>
                    <label for="seats">Seats</label>
                    <input type="number" id="seats" name="seats" title="Please enter the number of seats" required value="<%= f.getSeats()%>"/>
                    <input class="btn" type="submit" value="Edit"/>        
                </form>
             </div>
        <a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp">Go Back</a>
        </div>
        <div class="footer">All rights reserved © 2025</div>
    </body>
</html>
