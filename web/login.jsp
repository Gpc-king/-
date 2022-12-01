<%--
  Created by IntelliJ IDEA.
  User: Peng
  Date: 2022/3/29
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>学生信息管理系统后台登录</title>
    <link rel="stylesheet" type="text/css" href="css/login-style.css" />
    <link rel="stylesheet" type="text/css" href="css/login-body.css"/>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="LoginServlet" method="post">
            <h1>学生信息后台登录</h1>
            <div>
                <input type="text" placeholder="账号" id="username" name="qqnum1" required/>
            </div>
            <div>
                <input type="password" placeholder="密码" id="password"  name="password" required/>
            </div>
            <br>
            <h5 style="color: #f00">${msg}</h5>
            <div>
                <input type="submit" value="登录" class="btn btn-primary"/>
                <input type="button" value="返回" class="btn btn-primary"/>
            </div>
        </form>
        <div class="button">
            <h3>学生信息管理系统后台登录</h3>
        </div>
    </section>
</div>
</body>
</html>
<!--<c:if test="${not empty tips }">
    <script type="text/javascript">
    alert("${tips}")
    </script>
</c:if>-->
