<%-- 
    Document   : add-flight
    Created on : Dec 7, 2024, 1:10:24 PM
    Author     : Niki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/add-edit-flight.css">    
        <title>Add Flight</title>
    </head>
    <body>
        <div class="wrapper">
            <img src="${pageContext.request.contextPath}/Images/header.jpg" alt="header" width="100%" height="255vh">
            <h1>Add Flight</h1>
            <div class="form-wrapper">
                <form action="${pageContext.request.contextPath}/FlightServlet" method="post">
                    <input type="hidden" name="action" value="add">
                    <label for="flightNumber">Flight Number</label>
                    <input type="text" id="flightNumber" placeholder="FN1234" pattern="[A-Z]{2}[1-9]{3,4}" name="flightNumber" 
                           title="Please enter the flight number in the correct format" required/><br>
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
                    <input type="text" id="depDate" placeholder="DD/MM/YYYY" pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})"  
                           name="depDate" title="Please enter the date in the correct format" required/><br>
                    <label for="depTime">Departure Time</label>
                    <input type="text" id="depTime" placeholder="00:00" pattern="([01][0-9]|2[0-3]):[0-5][0-9]"  
                           name="depTime" title="Please enter a time in the 24-hour format (14:30)" required/><br>
                    <label for="arrDate">Arrival Date</label>
                    <input type="text" id="arrDate" placeholder="DD/MM/YYYY" pattern="(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})"
                           name="arrDate" title="Please enter the date in the correct format" required/><br>
                    <label for="arrTime">Arrival Time</label>
                    <input type="text" id="arrTime" placeholder="00:00" pattern="([01][0-9]|2[0-3]):[0-5][0-9]" 
                           name="arrTime" title="Please enter a time in the 24-hour format (14:30)" required/><br>
                    <label for="price">Price</label>
                    <input type="number" id="price" name="price" title="Please enter the price" required/>
                    <label for="seats">Seats</label>
                    <input type="number" id="seats" name="seats" title="Please enter the number of seats" required/>
                    <input class="btn" type="submit" value="Add"/>
                </form>
            </div>
        <a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp">Go Back</a>
        </div>
        <div class="footer">All rights reserved © 2025</div>
    </body>
</html>
