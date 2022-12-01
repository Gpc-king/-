package com.peng.servlet;

import com.peng.filter.LoginFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Peng
 * @Date 2022/3/31 22:28
 * @Version 1.0
 */

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = request.getServletContext();
        System.out.println("----------------------------用户:" + application.getAttribute("username")+ "  已退出系统----------------------------");
        HttpSession session = request.getSession();
        application.removeAttribute("userID");
        application.removeAttribute("qq");
        application.removeAttribute("type");
        application.removeAttribute("username");
        session.invalidate();

        response.sendRedirect("login.jsp");
    }
}
