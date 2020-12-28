package com.huang.financial.servlet;

import com.huang.financial.domain.Admin;
import com.huang.financial.service.AdminLoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@WebServlet(name = "/AdminLoginServlet", urlPatterns = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    AdminLoginService adminLoginService = new AdminLoginService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        try {
            // 利用反射获取方法
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            // 执行相应的方法
            method.setAccessible(true);
            method.invoke(this, request,response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminName = request.getParameter("adminname");
        String password = request.getParameter("password");

        Admin admin = new Admin();
        admin.setA_account(adminName);
        admin.setA_password(password);

        if (adminLoginService.checkAdmin(admin) != null) {
            request.setAttribute("status", "1");
            request.getSession().setAttribute("admin", admin);
            request.getRequestDispatcher("UserManageServlet?method=getAllUser").forward(request, response);
        } else {
            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('账号或密码输入错误！');");
            out.println("history.back();");
            out.println("</script>");
        }
    }


}
