<%@ page import="com.peng.bean.StuBean" %><%--
  Created by IntelliJ IDEA.
  User: Peng
  Date: 2022/4/1
  Time: 9:40
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
    <span id="time">2022年02月21日 08:31  星期一</span>
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
            <span><a href="userList.jsp">用户管理页面</a> >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <%
                try {
                    StuBean s = (StuBean) request.getAttribute("stuBean");
                    int type = Integer.parseInt(String.valueOf(application.getAttribute("type")));
                    String types = type == 0 ? "管理员" : "用户";
                    String hidden = "";
                    String readonly = "";
                    if ("管理员".equals(types)) {
                        hidden = "hidden";
                    } else {
                        readonly = "readonly";
                    }

            %>
            <h2 style="color:#f00;">${msg}</h2>
            <form action="UserListServlet?what=update&id=<%=s.getId()%>" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="qqnum">QQ号：</label>
                    <input <%=readonly%> type="text" name="qqnum" id="qqnum" value="<%=s.getQqNum()%>"/>
                    <span>*</span><span <%=hidden%>>如需修改请联系管理员！</span>
                </div>


                <div>
                    <label for="username">真实姓名：</label>
                    <input type="text" name="username" id="username" required value="<%=s.getName()%>"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="password">密码：</label>
                    <input type="text" name="password" id="password" required maxlength="9"
                           value="<%=s.getPassword()%>"/>
                    <span>*</span>
                </div>
                <div>
                    <label>性别：</label>
                    <%
                        String isMale = "";
                        String isFemale = "";
                        if ("男".equals(s.getSex())) {
                            isMale = "checked";
                        } else {
                            isFemale = "checked";
                        }
                    %>
                    男<input type="radio" value="男" name="sex" id="sex1" required <%=isMale%>>
                    女<input type="radio" value="女" name="sex" id="sex2" required <%=isFemale%>>
                    <span>*</span>
                </div>

                <div>
                    <label for="birth">出生日期：</label>
                    <input type="date" name="birth" id="birth" required value="<%=s.getBirth()%>"/>
                    <span>*</span>
                </div>

                <div>
                    <label for="tel">电话：</label>
                    <input type="tel" name="tel" id="tel" maxlength="11" minlength="11" required
                           value="<%=s.getPhone()%>"/>
                    <span>*</span>
                </div>

                <div>
                    <label>用户类型：</label>
                    <%
                        if ("管理员".equals(types)) {
                    %>
                    管理员<input type="radio" value="管理员" name="type" id="type1" checked>
                    用户<input type="radio" value="用户" name="type" id="type2">
                    <span>*</span>
                    <%
                    } else {
                    %>
                    <input <%=hidden%> readonly type="text" name="types" id="types" value="<%=s.getType()%>"/>
                    <span>如需修改请联系管理员！</span>
                    <%
                            }
                        } catch (Exception e) {
                            response.sendRedirect("error.jsp");
                        }
                    %>
                </div>

                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>
<script src="js/when.js"></script>

</body>
</html>