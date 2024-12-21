<%-- 
    Document   : user-dashboard
    Created on : Dec 4, 2024, 3:26:16 PM
    Author     : Niki
--%>

<%@page import="com.mycompany.course.work.dao.FlightDao"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.course.work.SignOutServlet"%>
<%@page import="com.mycompany.course.work.bean.Flight"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flights</title>
    </head>
    <body>
        <div>
            <jsp:include page="search-form.html"></jsp:include>  
        </div>
        <div> 
            <form action="${pageContext.request.contextPath}/SignOutServlet" method="POST">
                <input type="submit" value="Sign Out">
            </form>
                
        <%
            List<Flight> flights = FlightDao.getAll();
            request.setAttribute("allFlights", flights);
        %>
                
            <h3>Flights</h3>
            <table border="1" width="90%">  
                <tr>
                    <th>Flight Number</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Departure Date</th>  
                    <th>Departure Time</th>
                    <th>Arrival Date</th>
                    <th>Arrival Time</th>
                    <th>Price (BGN)</th>
                </tr>
                <c:forEach items="${allFlights}" var="f">  
                <tr>
                    <td>${f.flightNumber}</td>
                    <td>${f.origin}</td>
                    <td>${f.destination}</td>
                    <td>${f.departureDate}</td>
                    <td>${f.departureTime}</td>
                    <td>${f.arrivalDate}</td>
                    <td>${f.arrivalTime}</td>
                    <td>${f.price}</td>
                </tr>  
                </c:forEach>  
            </table>   
        </div>
    </body>
</html>