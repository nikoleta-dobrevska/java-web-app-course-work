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
            <form action="${pageContext.request.contextPath}/SignOutServlet" method="POST">
                <input type="submit" value="Sign Out">
            </form> 
                
            <%
                List<Flight> allFlights = FlightDao.getAll();
                request.setAttribute("allFlights", allFlights);
            %>  

            <h3>Flights</h3>
            <p id="message-for-user"></p>
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
                    <td>${f.getFlightNumber()}</td>  
                    <td>${f.getOrigin()}</td>
                    <td>${f.getDestination()}</td>
                    <td>${f.getDepartureDate()}</td>  
                    <td>${f.getDepartureTime()}</td>
                    <td>${f.getArrivalDate()}</td>
                    <td>${f.getArrivalTime()}</td>
                    <td>${f.getPrice()}</td>
                    <td><a href="edit-flights.jsp?id=${f.id}">Edit</a></td>
                <form action="${pageContext.request.contextPath}/FlightServlet" method="POST" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${f.id}">
                    <button type="submit" onclick="return confirm('Are you sure you want to delete this flight?');">Delete</button>
                </form>
                </tr>  
                </c:forEach>  
            </table>     
            <a href="${pageContext.request.contextPath}/JSP Pages/admin/add-flight.jsp">Add</a>
            <a href="${pageContext.request.contextPath}/JSP Pages/admin/manage-roles.jsp">Manage Roles</a>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/params-script.js"></script>
    </body>
</html>