package com.peng.filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @Author Peng
 * @Date 2022/3/29 15:28
 * @Version 1.0
 */

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        ServletContext application = req.getServletContext();
        String userID = String.valueOf(application.getAttribute("userID"));
        String path = request.getServletPath();
        if (!"null".equals(userID) || "/LoginServlet".equals(path) || "//login.jsp".equals(path)
                || path.endsWith(".jar") || path.endsWith(".css") || path.endsWith(".js")
                || path.endsWith(".png") || path.endsWith(".jpg") || "/login.jsp".equals(path)){
            chain.doFilter(req, resp);
        }else{
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
