<%@ page import="com.peng.bean.StuBean" %><%--
  Created by IntelliJ IDEA.
  User: Peng
  Date: 2022/3/31
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学生信息后台管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="script" href="js/when.js">
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>学生信息后台管理系统</h1>

    <div class="publicHeaderR">
        <p><span id="isWhens"></span><span style="color: #fff21b"> Admin</span> , 欢迎你！</p>
        <a href="LogoutServlet">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2022年02月21日 10:21  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="index.jsp">系统主页</a></li>

                <li><a href="userList.jsp">用户管理</a></li>

                <li><a href="LogoutServlet">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span><a href="userList.jsp">用户管理页面</a> >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <%
                try {
                    StuBean stu = (StuBean) session.getAttribute("stuBean");
            %>
            <p><strong>qq账号：</strong><span><%=stu.getQqNum()%></span></p>
            <p><strong>真实姓名：</strong><span><%=stu.getName()%></span></p>
            <%
                String pass = null;
                if ("0".equals(String.valueOf(application.getAttribute("type")))) {
                    pass = stu.getPassword();
                } else if (String.valueOf(application.getAttribute("username")).equals(stu.getName())) {
                    pass = stu.getPassword();
                } else {
                    pass = "******";
                }
            %>
            <p><strong>密码：</strong><span><%=pass%></span></p>
            <p><strong>性别：</strong><span><%=stu.getSex()%></span></p>
            <p><strong>年龄：</strong><span><%=stu.getAge()%></span></p>
            <p><strong>电话：</strong><span><%=stu.getPhone()%></span></p>
            <p><strong>用户类别：</strong><span><%=stu.getType()%></span></p>
            <%
                } catch (Exception e) {
                    response.sendRedirect("error.jsp");
                }
            %>
            <a href="PaginationViewServlet">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>
<script src="js/when.js"></script>

</body>
</html>
