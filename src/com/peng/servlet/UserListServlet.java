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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Peng
 * @Date 2022/3/31 15:21
 * @Version 1.0
 */

@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String what = request.getParameter("what");
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        if ("look".equals(what)) {
            StuBean stuBean = new opDateBase().searchInfoByID(id);
            session.setAttribute("stuBean", stuBean);
            request.getRequestDispatcher("userView.jsp").forward(request, response);
        } else if ("update".equals(what)) {
            StuBean stuBean = new StuBean();
            stuBean.setId(id);
            stuBean.setQqNum(request.getParameter("qqnum"));
            stuBean.setName(request.getParameter("username"));
            stuBean.setPassword(request.getParameter("password"));
            stuBean.setSex(request.getParameter("sex"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birth = sdf.parse(request.getParameter("birth"));
                stuBean.setBirth(birth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            stuBean.setPhone(request.getParameter("tel"));
            int type = 1;
            if ("管理员".equals(request.getParameter("type")))
                type = 0;
            stuBean.setType(type);
            System.out.println("---->" + stuBean.toString());
            int influenceLine = new opDateBase().updateByID(stuBean);
            if (influenceLine > 0 ){
                request.getRequestDispatcher("UserListServlet?what=look&id="+stuBean.getId()).forward(request, response);
            }else if(influenceLine == -1){
                String msg = "qq号已存在！";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
            }

        } else if ("delete".equals(what)) {
            int influenceLine = new opDateBase().deleteUser(id);
            if (influenceLine > 0)
                request.getRequestDispatcher("PaginationViewServlet").forward(request, response);
        } else if ("insert".equals(what)) {
            StuBean stu = new StuBean();
            stu.setQqNum(request.getParameter("qqnum"));
            stu.setName(request.getParameter("username"));
            stu.setPassword(request.getParameter("password"));
            stu.setSex(request.getParameter("sex"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birth = sdf.parse(request.getParameter("birth"));
                stu.setBirth(birth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            stu.setPhone(request.getParameter("tel"));
            stu.setType(Integer.parseInt(request.getParameter("type")));
            int influenceLine = new opDateBase().insertUser(stu);
            if (influenceLine == -1){
                String msg = "qq号已存在！";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("userInsert.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("PaginationViewServlet").forward(request, response);
            }
        }
    }
}
