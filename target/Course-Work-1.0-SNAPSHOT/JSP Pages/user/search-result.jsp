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
        
        <c:if test="${currentPage != 1}"> 
        <td><a href="/Course-Work/SearchServlet?page=${currentPage - 1}">Previous</a></td> 
        </c:if> 
        
        <table border="1" cellpadding="5" cellspacing="5"> 
            <tr> 
                <c:forEach begin="1" end="${noOfPages}" var="i"> 
                    <c:choose> 
                        <c:when test="${currentPage eq i}"> 
                            <td>${i}</td> 
                        </c:when> 
                        <c:otherwise> 
                            <td><a href="/Course-Work/SearchServlet?page=${i}">${i}</a></td> 
                            </c:otherwise> 
                        </c:choose> 
                    </c:forEach> 
            </tr> 
        </table> 
     
        <c:if test="${currentPage lt noOfPages}"> 
            <td><a href="/Course-Work/SearchServlet?page=${currentPage + 1}">Next</a></td> 
        </c:if> 
            
        <a href="${pageContext.request.contextPath}/JSP Pages/user/user-dashboard.jsp">Go Back</a>
    </body>
</html>