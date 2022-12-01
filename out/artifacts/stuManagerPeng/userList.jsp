<%@ page import="java.util.List" %>
<%@ page import="com.peng.bean.StuBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Peng
  Date: 2022/3/31
  Time: 15:19
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
        <p><span id="isWhens"></span><span style="color: #fff21b"> <%=application.getAttribute("username")%></span> , 欢迎你！</p>
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
            <span>用户管理页面</span>
        </div>
        <div class="search">
            <form action="PaginationViewServlet?method=search" method="post">
                <span>用户名：</span>
                <input type="text" name="users" placeholder="请输入用户名"/>
                <input type="submit" value="查询"/>

                <%
                    try {
                        int type = Integer.parseInt(String.valueOf(application.getAttribute("type")));
                        String types = type == 0 ? "管理员" : "用户";
                        if ("管理员".equals(types)) {
                %>
                <a href="userInsert.jsp">添加用户</a>
                <%
                    }
                %>
            </form>
        </div>
        <!--用户-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <%
                List<StuBean> stuBeanList = (List<StuBean>) session.getAttribute("stuBeanList");
                if (stuBeanList.size() != 0) {%>
            <tr class="firstTr">
                <th width="10%">qq账号</th>
                <th width="20%">真实姓名</th>
                <th width="10%">密码</th>
                <th width="10%">性别</th>
                <th width="10%">年龄</th>
                <th width="10%">电话</th>
                <th width="10%">用户类型</th>
                <th width="20%">操作</th>
            </tr>
            <%
                for (StuBean s : stuBeanList) {
            %>
            <tr>
                <td><%=s.getQqNum()%>
                </td>
                <td><%=s.getName()%>
                </td>
                <td>
                    <%

                        String pass = null;
                        if ("0".equals(String.valueOf(application.getAttribute("type")))) {
                            pass = s.getPassword();
                        } else if (String.valueOf(application.getAttribute("username")).equals(s.getName())) {
                            pass = s.getPassword();
                        } else {
                            pass = "******";
                        }
                    %>
                    <%=pass%>
                </td>
                <td><%=s.getSex()%>
                </td>
                <td><%=s.getAge()%>
                </td>
                <td><%=s.getPhone()%>
                </td>
                <td><%=s.getType()%>
                </td>
                <td>
                    <a href="UserListServlet?what=look&id=<%=s.getId()%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <%
                        if (application.getAttribute("username").equals(s.getName()) || "管理员".equals(types)) {
                    %>
                    <a href="UserUpdateServlet?id=<%=s.getId()%>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <%
                        }
                        if ("管理员".equals(types)) {
                    %>
                    <a href="UserListServlet?what=delete&id=<%=s.getId()%>" class="removeUser"><img src="img/schu.png"
                                                                                                    alt="删除"
                                                                                                    title="删除"/></a>
                    <%
                            }
                        }
                    %>
                </td>
            </tr>
            <tr>
                <td colspan="8">

                    <%
                        int n = (int) session.getAttribute("n");
                        if (n > 1) {
                    %>
                    <form action="PaginationViewServlet" method="post">
                        <a href="PaginationViewServlet?page=1">
                            <div>[首页]</div>
                        </a>

                        <c:forEach var="i" begin="1" end="<%=n %>">
                            <a href="PaginationViewServlet?page=${i }">
                                [<c:out value="${i }"/>]
                            </a>
                        </c:forEach>
                        <a href="PaginationViewServlet?page=<%=n %>">
                            <div>[末页]</div>
                        </a>

                        <input type="number" name="page" value="1" min="1" max="<%=n %>"
                               style="width: 50px;height: 30px;"/>
                        <input type="submit" value="跳转"
                               style="width: 70px;height: 30px;font-size: 18px;line-height: 30px">
                    </form>
                    <%
                        }
                    %>
                </td>
            </tr>
            <%

            } else {
            %>
            <tr>
                <td colspan="8">
                    <h1>暂未查询到数据 (⊙ˍ⊙)</h1>
                </td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    response.sendRedirect("error.jsp");
                }
            %>

        </table>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<footer class="footer">
</footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>
<script src="js/when.js"></script>

</body>
</html>