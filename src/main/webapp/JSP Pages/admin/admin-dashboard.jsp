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
                        <td>${f.flightNumber}</td>
                        <td>${f.origin}</td>
                        <td>${f.destination}</td>
                        <td>${f.departureDate}</td>
                        <td>${f.departureTime}</td>
                        <td>${f.arrivalDate}</td>
                        <td>${f.arrivalTime}</td>
                        <td>${f.price}</td>
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
            
            <!-- Previous Button -->
            <c:if test="${currentPage != 1}"> 
                <a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp?page=${currentPage - 1}">Previous</a> 
            </c:if> 

            <!-- Pagination -->
            <table border="1" cellpadding="5" cellspacing="5"> 
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

            <!-- Next Button -->
            <c:if test="${currentPage < noOfPages}"> 
                <a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp?page=${currentPage + 1}">Next</a> 
            </c:if> 
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/params-script.js"></script>
    </body>
</html>