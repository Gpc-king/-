package com.peng.servlet;

import com.peng.bean.StuBean;
import com.peng.util.opDateBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Peng
 * @Date 2022/3/31 20:29
 * @Version 1.0
 */

@WebServlet("/PaginationViewServlet")
public class PaginationViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //总行数：连接数据库获取
        int countLine = 0;
        //当前页数：默认第一页
        int currentPage = 1;
        //每页显示数：可指定
        int countSize = 5;
        //总共多少页：需计算
        int countPage = 0;

        List<StuBean> stuBeanList = new LinkedList<>();
        //如果是查询，那就是按条件查询。如果是查看所有，那就是直接查询。
        //两种的不同方式的sql语句同，可选择重写查询所有的方法
        if ("search".equals(request.getParameter("method"))){
            String users = request.getParameter("users");
            if("".equals(users) || " ".equals(users))
                users = "%";
            countLine = new opDateBase().getCountLine(users);
        }else{
            countLine = new opDateBase().getCountLine();
        }
        //计算总页数：整除正好，有余则需多加一页
        if (countLine % countSize == 0)
            countPage = countLine / countSize;
        else
            countPage = (countLine / countSize) + 1;
        //获取当前页
        String page = request.getParameter("page");
        if (page != null)
            currentPage = Integer.parseInt(page);
        //如果是查询，那就是按条件查询。如果是查看所有，那就是直接查询。
        //两种的不同方式的sql语句同，可选择重写查询所有的方法
        if ("search".equals(request.getParameter("method"))){
            String users = request.getParameter("users");
            if("".equals(users) || " ".equals(users))
                users = "%";
            //利用sql中的LIMIT语句LIMIT X,Y 从第X条开始，要Y条
            stuBeanList = new opDateBase().searchAllUser(users, (currentPage - 1) * countSize, countSize);
        }else{
            stuBeanList = new opDateBase().searchAllUser((currentPage - 1) * countSize, countSize);
        }
        HttpSession session = request.getSession();
        //如果存在则先移除
        if(session.getAttribute("stuBeanList") != null){
            session.removeAttribute("stuBeanList");
        }
        //如果存在则先移除
        if(session.getAttribute("n") != null){
            session.removeAttribute("n");
        }
        session.setAttribute("stuBeanList", stuBeanList);
        session.setAttribute("n", countPage);
        request.getRequestDispatcher("userList.jsp").forward(request, response);

    }
}
