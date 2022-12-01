package com.peng.servlet;

import com.peng.bean.StuBean;
import com.peng.util.opDateBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Peng
 * @Date 2022/4/1 10:36
 * @Version 1.0
 */

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StuBean stuBean = new opDateBase().searchInfoByID(id);
        request.setAttribute("stuBean", stuBean);
        request.getRequestDispatcher("userUpdate.jsp").forward(request, response);

    }
}
