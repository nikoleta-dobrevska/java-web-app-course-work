<%-- 
    Document   : search-result
    Created on : Dec 19, 2024, 11:04:03 AM
    Author     : Niki
--%>

<%@page import="com.mycompany.course.work.dao.FlightDao"%>
<%@page import="com.mycompany.course.work.SearchServlet"%>
<%@page import="com.mycompany.course.work.bean.Flight" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flights</title>
    </head>
    <body>
        <h3>Flights</h3>
        
        <c:if test="${empty matchingFlights}">
            <td>No flights found</td>
        </c:if>
            
        <table>
            <tr>
                <th>Flight Number</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Departure Date</th>
                <th>Departure Time</th>
                <th>Arrival Date</th>
                <th>Arrival Time</th>
                <th>Price</th>
            </tr>
            <c:forEach items="${matchingFlights}" var="f">  
                <tr>
                    <td>${f.getFlightNumber()}</td>  
                    <td>${f.getOrigin()}</td>
                    <td>${f.getDestination()}</td>
                    <td>${f.getDepartureDate()}</td>  
                    <td>${f.getDepartureTime()}</td>
                    <td>${f.getArrivalDate()}</td>
                    <td>${f.getArrivalTime()}</td>
                    <td>${f.getPrice()}</td>
                </tr>  
            </c:forEach>  
        </table>
        
        <c:if test="${currentPage > 1}">
            <a href="/Course-Work/SearchServlet?origin=${origin}&destination=${destination}&price=${price}&page=${currentPage - 1}">Previous</a>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <span>${i}</span> <!-- Current page, no link -->
                </c:when>
                <c:otherwise>
                    <a href="/Course-Work/SearchServlet?origin=${origin}&destination=${destination}&price=${price}&page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage < noOfPages}">
            <a href="/Course-Work/SearchServlet?origin=${origin}&destination=${destination}&price=${price}&page=${currentPage + 1}">Next</a>
        </c:if>
            
        <a href="${pageContext.request.contextPath}/JSP Pages/user/user-dashboard.jsp">Go Back</a>
    </body>
</html>