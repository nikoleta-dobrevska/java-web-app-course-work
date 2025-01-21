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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/user-dashboard-style.css">
        <title>Flights</title>
    </head>
    <body>
        <div class="header-wrapper"> 
            <div class="header">
                <img src="${pageContext.request.contextPath}/Images/header.jpg" alt="header" width="100%" height="255vh">
                <h1>Flights</h1>
            </div>            
        </div>
        <button id="go-back-btn"><a href="${pageContext.request.contextPath}/JSP Pages/user/user-dashboard.jsp">Go Back</a></button>
        <div class="table-wrapper">
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
                <c:if test="${empty matchingFlights}">
                    <td style="border: unset; text-align: center;">No flights found!</td>
                </c:if>
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
        </div>
        <div class="pagination-wrapper">
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
        </div>
        <div class="footer">All rights reserved © 2025</div>
    </body>
</html>