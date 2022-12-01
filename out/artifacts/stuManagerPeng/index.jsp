<%--
  Created by IntelliJ IDEA.
  User: Peng
  Date: 2022/3/29
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学生信息后台管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body background="img/bg.jpg">
<!--头部-->
<header class="publicHeader">
    <h1>学生信息后台管理系统</h1>
    <div class="publicHeaderR">
        <p><span id="isWhens"></span><span style="color: #fff21b"> <%=application.getAttribute("username")%></span> , 欢迎你！</p>
        <a href="LogoutServlet">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2022年02月21日 08:31  星期一</span> <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="PaginationViewServlet">用户管理</a></li>
                <li><a href="LogoutServlet">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <img class="wColck" src="img/clock.jpg" alt=""/>
        <div class="wFont">
            <%
                try {
                    int type = Integer.parseInt(String.valueOf(application.getAttribute("type")));
                    String types = type == 0 ? "管理员" : "用户";
            %>
            <h2><span id="isWhen">您好！</span>&nbsp;&nbsp;&nbsp;<%=types%>,<%=application.getAttribute("username")%>
            </h2>
            <%
                if ("管理员".equals(types)) {
            %>
            <h3>您可进行的操作</h3>
            <ol style="line-height: 20px;margin: 15px;font-size: 10px" type="1">
                <li>查看所有学生详细信息</li>
                <li>修改所有学生详细信息</li>
                <li>删除学生信息</li>
                <li></li>
            </ol>
            <%
            } else {

            %>
            <h3>您可进行的操作</h3>
            <ol style="line-height: 20px;margin: 15px;font-size: 10px" type="1">
                <li>查看他人信息</li>
                <li>修改个人详细信息</li>
                <li></li>
            </ol>
            <%
                    }
                } catch (Exception e) {
                    response.sendRedirect("error.jsp");
                }
            %>
            <p>欢迎来到学生信息后台管理系统!</p>
        </div>
    </div>
</section>
<footer class="footer"></footer>
<script src="js/time.js"></script>
<script src="js/when.js"></script>
</body>
</html>
