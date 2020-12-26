package com.huang.financial.servlet;

import com.huang.financial.dao.Impl.UserDaoImpl;
import com.huang.financial.dao.UserDao;
import com.huang.financial.domain.User;
import com.huang.financial.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "/LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    LoginService loginService = new LoginService();

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

    //跳转页面
    protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        request.getRequestDispatcher(page + ".jsp").forward(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setU_account(username);
        user.setU_password(password);
        user = loginService.checkUser(user);
        if (user != null) {
            request.setAttribute("status", "1");
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("FinancialManagementServlet?method=getAllIncomeExpenseByUserId").
                    forward(request, response);
        }
    }
}
