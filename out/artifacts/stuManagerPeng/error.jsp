<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Peng
  Date: 2022/3/29
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1 style="background-color: #0D2D54;color: #FFFFFF;margin-top: 100px;font-size: 35px;" align="center">
    您还未登录！没有权限，请登录或请联系管理员.</h1>
<h1 align="center">You haven't logged in yet! No permission, please log in or contact the administrator.</h1>
<h4 align="center">系统将在5s后自动跳转。</h4>
<h4 align="center">The system will jump automatically after 5S.</h4>
<%
    response.setHeader("refresh", "5;url=login.jsp");
%>
</body>
</html>
