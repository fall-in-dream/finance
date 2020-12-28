package com.huang.financial.servlet;

import com.google.gson.Gson;
import com.huang.financial.domain.User;
import com.huang.financial.service.UserManageService;
import com.huang.financial.web.CriteriaUser;
import com.huang.financial.web.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "/UserManageServlet", urlPatterns = "/UserManageServlet")
public class UserManageServlet extends HttpServlet {

    private UserManageService userManageService = new UserManageService();

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

    protected void getAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username") == null ? "" : request.getParameter("username");
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String tel = request.getParameter("phone") == null ? "" : request.getParameter("phone");
        String pageNoStr = request.getParameter("pageNo");

        int pageNo;

        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException e) {
            pageNo = 1;
        }
        User findUser = new User();
        findUser.setU_account(username);
        findUser.setU_email(email);
        findUser.setU_tel(tel);
        CriteriaUser criteriaUser = new CriteriaUser(username, email, tel, pageNo);
        Page<User> page = userManageService.getPage(criteriaUser);
        request.setAttribute("userPage", page);
        request.setAttribute("findUser", findUser);
        request.getRequestDispatcher("admin/main.jsp").forward(request, response);
    }

    protected void getUserById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("id"));
        User user = userManageService.getUserById(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(result);
        response.setContentType("text/javascript");
        response.getWriter().print(jsonStr);
    }
}
