<%-- 
    Document   : sign-in
    Created on : Dec 5, 2024, 8:43:30 PM
    Author     : Niki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Sign In</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../Styles/sign-in-style.css">    
    </head>
    <body>
        <div class="wrapper">
            <img src="../Images/header.jpg" alt="header" width="100%" height="255vh">
            <h1>SIGN IN</h1>
            <div class="form-wrapper">
                <form name="sign-in-form" action="${pageContext.request.contextPath}/SignInServlet" method="post">
                    <p id="message-for-user"></p>
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required><br>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required><br>
                    <button>SIGN IN</button>
                </form>                                  
            </div>
            <p><a href="index.jsp">GO BACK</a></p>    
            <p><a id="sign-in-link" href="sign-up.jsp">Haven't signed up yet? Click here to get started!</a></p>         
        </div> 
        <div class="footer">All rights reserved © 2025</div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/params-script.js"></script>
    </body>
</html>