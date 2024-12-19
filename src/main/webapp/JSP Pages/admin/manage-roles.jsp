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
        <title>Manage Roles</title>
    </head>
    <body>
        <%
            List<UserRole> userRoles;
            UserRoleDao userRoleDao = new UserRoleDao();
            userRoles = userRoleDao.getAll();
            request.setAttribute("userRoles", userRoles);
        %>  
        <h3>Manage Users</h3>
        <p id="message-for-admin"></p>
        <table border="1" width="90%">  
            <tr>
                <th>UserID</th>
                <th>RoleID</th>
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
    <a href="${pageContext.request.contextPath}/JSP Pages/admin/admin-dashboard.jsp">Go Back</a>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Scripts/params-script.js"></script>
    </body>
</html>