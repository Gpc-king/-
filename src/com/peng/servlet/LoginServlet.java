package com.peng.servlet;

import com.peng.bean.StuBindID;
import com.peng.util.opDateBase;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Peng
 * @Date 2022/3/29 15:19
 * @Version 1.0
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String qqName = request.getParameter("qqnum1");
        String password = request.getParameter("password");
        StuBindID sBi = new opDateBase().LoginSelect(qqName, password);
        ServletContext application = request.getServletContext();
        if (sBi.getId() != 0 && sBi.getQqnum() != null) {
            application.setAttribute("userID", String.valueOf(sBi.getId()));
            application.setAttribute("username", sBi.getName());
            application.setAttribute("qq", sBi.getQqnum());
            application.setAttribute("type", sBi.getType());
            System.out.println(sBi.getType());
            System.out.println(sBi.toString());
            System.out.println("----------------------------用户:"
                    + application.getAttribute("username") + "  已登入系统----------------------------");
            response.sendRedirect("index.jsp");
        } else {
            String msg = "请检查账号或密码！";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
