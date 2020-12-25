package com.huang.financial.servlet;

import com.huang.financial.domain.User;
import com.huang.financial.service.RegisterService;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.huang.financial.utils.Constant.*;

/**
 *
 *@class_name：RegistServlet
 *@comments: 注册请求处理
 *@param:验证码校验
 *@return: jsp跳转
 */
@WebServlet(name = "/RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    private RegisterService registerService = new RegisterService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = (String) request.getSession().getAttribute("code");
        String inputCode = request.getParameter("code");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");

        User user = new User();
        user.setU_name(username);
        user.setU_password(password);
        user.setU_email(email);
        user.setU_tel(phone);
        user.setU_sex(sex);

        int status = registerService.register(code, inputCode, user);

        if (status == REQUEST_SUCCESS) {
            request.setAttribute("status", REQUEST_SUCCESS);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        /*// 获取session中的验证码
        System.out.println(sessionCode);

        if(sessionCode != null) {
            //  获取页面提交的验证码
            String inputCode = request.getParameter("code");
            System.out.println("页面提交的验证码:" + inputCode);
            if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                System.out.println("页面提交:" + username+password);
                //  验证成功，跳转成功页面
                request.setAttribute("username", username);
                request.getRequestDispatcher("/success.jsp").forward(request, response);
            }else {
                //  验证失败
                request.getRequestDispatcher("fail.jsp").forward(request, response);
            }
        }else {
            //  验证失败
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
        //  移除session中的验证码
        request.removeAttribute("code");*/
    }
}