package com.huang.financial.servlet;

import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.domain.User;
import com.huang.financial.service.FinancialManagementService;
import com.huang.financial.web.CriteriaIncomeExpense;
import com.huang.financial.web.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "/FinancialManagementServlet", urlPatterns = "/FinancialManagementServlet")
public class FinancialManagementServlet extends HttpServlet {
    FinancialManagementService financialManagementService = new FinancialManagementService();

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

    protected void getAllIncomeExpenseByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateStr = request.getParameter("date");
        String remarkStr = request.getParameter("remark");
        String pageNoStr = request.getParameter("pageNo");
        User user = (User)request.getSession().getAttribute("user");

        long userId = user.getU_id();
        String date = "";
        int pageNo = 0;
        String remark = "";

        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException e) {}

        try {
            remark = String.valueOf(remarkStr);
        } catch (NumberFormatException e) {}

        try {
            date = String.valueOf(dateStr);
        } catch (NumberFormatException e) {}


        CriteriaIncomeExpense criteriaIncomeExpense = new CriteriaIncomeExpense(userId, date, remark, pageNo);
        criteriaIncomeExpense.setUserId(userId);
        Page<IncomeExpense> page = financialManagementService.getPage(criteriaIncomeExpense);
        request.setAttribute("IncomeExpensePage", page);
        List<IncomeExpense> incomeExpenseList = financialManagementService.getAllIncomeExpenseByUserId(user.getU_id());
        request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
    }
}
