<%-- 
    Document   : manage-users
    Created on : Dec 13, 2024, 6:54:22 PM
    Author     : Niki
--%>

<%@page import="com.mycompany.course.work.dao.UserRoleDao"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.course.work.bean.UserRole"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/manage-roles.css">    
        <title>Manage Roles</title>
    </head>
    <body>
        
        <%
            List<UserRole> userRoles;
            UserRoleDao userRoleDao = new UserRoleDao();
            userRoles = userRoleDao.getAll();
            request.setAttribute("userRoles", userRoles);
        %>  
        <div class="header-wrapper">
            <div class="header">
                <img src="${pageContext.request.contextPath}/Images/header.jpg" alt="header" width="100%" height="255vh">
                <h1>Manage Roles</h1>
            </div>
        </div>
        <div  class="table-wrapper">
            <p id="message-for-admin"></p>
            <table>  
                <tr>
                    <th>UserID</th>
                    <th>RoleID</th>
                    <th id="mng">Manage</th>
                </tr>
                <c:forEach items="${userRoles}" var="u">  
                <tr>
                        <td>${u.getUserId()}</td>  
                        <td>${u.getRoleId()}</td>
                        <td>
                            <c:if test="${u.roleId != 1}">
                                User
                                <form method="post" action="${pageContext.request.contextPath}/EditRoleServlet">
                                    <input type="hidden" name="userId" value="${u.getUserId()}" />
                                    <input type="hidden" name="newRoleId" value="1" />
                                    <button type="submit">Make Admin</button>
                                </form>
                            </c:if>
                            <c:if test="${u.roleId == 1}">
                                Admin
                            </c:if>
                        </td>
                    </tr>  
                </c:forEach>  
        </table>    
     </div>
    <button><a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp">Go Back</a></button>
    <div class="footer">All rights reserved © 2025</div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/params-script.js"></script>
    </body>
</html>