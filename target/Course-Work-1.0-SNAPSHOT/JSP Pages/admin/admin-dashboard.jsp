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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/admin-style.css">    
        <title>Flights</title>
    </head>
    <body>
        <div class="header-wrapper">
            <div class="header">
                <img src="${pageContext.request.contextPath}/Images/header.jpg" alt="header" width="100%" height="255vh">
                <h1>Flights</h1>
            </div>
        </div>
        <div class="menu-wrapper">
            <div class="user-wrapper">
                <img src="${pageContext.request.contextPath}/Images/profile.jpg" alt="profile picture" width="100px" height="100px">
                <form action="${pageContext.request.contextPath}/SignOutServlet" method="POST">
                    <input class="btn" type="submit" value="Sign Out">
                    <a class="btn" href="${pageContext.request.contextPath}/JSP Pages/admin/add-flight.jsp">Add Flight</a>
                    <a class="btn" href="${pageContext.request.contextPath}/JSP Pages/admin/manage-roles.jsp">Manage Roles</a>
                </form>                  
            </div> 
        </div>
 
            <%
                int currentPage = 1;
                int recordsPerPage = 5;

                if (request.getParameter("page") != null) {
                    currentPage = Integer.parseInt(request.getParameter("page"));
                }

                List<Flight> flights = FlightDao.getAll((currentPage - 1) * recordsPerPage, recordsPerPage);
                int noOfRecords = FlightDao.getNoOfRecords();
                int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("allFlights", flights);
            %>
            <div class="table-wrapper">
              <p id="message-for-admin"></p>
              <table>  
                  <tr>
                      <th>Flight Number</th>
                      <th>Origin</th>
                      <th>Destination</th>
                      <th>Departure Date</th>  
                      <th>Departure Time</th>
                      <th>Arrival Date</th>
                      <th>Arrival Time</th>
                      <th>Price (BGN)</th>
                      <th class="act">Edit</<th>
                      <th class="act">Delete</th>
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
                          <td><a class="btn" href="edit-flights.jsp?id=${f.id}">Edit Flight</a></td>
                          <form action="${pageContext.request.contextPath}/FlightServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${f.id}">
                            <td><button class="btn del" type="submit" onclick="return confirm('Are you sure you want to delete this flight?');">Delete Flight</button></td>
                          </form>
                      </tr>  
                  </c:forEach>  
              </table>       
            </div>         
            <div class="pagination-wrapper">
                <c:if test="${currentPage != 1}"> 
                    <a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp?page=${currentPage - 1}">Previous</a> 
                </c:if> 
                <table> 
                    <tr> 
                        <c:forEach begin="1" end="${noOfPages}" var="i"> 
                            <c:choose> 
                                <c:when test="${currentPage == i}"> 
                                    <td>${i}</td> 
                                </c:when> 
                                <c:otherwise> 
                                    <td><a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp?page=${i}">${i}</a></td> 
                                    </c:otherwise> 
                                </c:choose> 
                            </c:forEach> 
                    </tr> 
                </table> 
                <c:if test="${currentPage < noOfPages}"> 
                    <a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp?page=${currentPage + 1}">Next</a> 
                </c:if> 
        </div>
        <div class="footer">All rights reserved © 2025</div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/params-script.js"></script>
    </body>
</html>