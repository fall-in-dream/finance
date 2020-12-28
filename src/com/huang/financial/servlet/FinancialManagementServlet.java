package com.huang.financial.servlet;

import com.google.gson.Gson;
import com.huang.financial.dao.IncomeExpenseSubtypeDao;
import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.domain.IncomeExpenseSubtype;
import com.huang.financial.domain.User;
import com.huang.financial.service.FinancialManagementService;
import com.huang.financial.utils.Constant;
import com.huang.financial.web.CriteriaIncomeExpense;
import com.huang.financial.web.Page;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String date = request.getParameter("date") == null ? "": request.getParameter("date");
        String remark = request.getParameter("remark") == null ? "": request.getParameter("remark");
        String pageNoStr = request.getParameter("pageNo");
        User user = (User)request.getSession().getAttribute("user");

        long userId = user.getU_id();
        int pageNo;

        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException e) {
            pageNo = 1;
        }

        CriteriaIncomeExpense criteriaIncomeExpense = new CriteriaIncomeExpense(userId, date, remark, pageNo);
        criteriaIncomeExpense.setUserId(userId);
        Page<IncomeExpense> page = financialManagementService.getPage(criteriaIncomeExpense);
        List<IncomeExpenseSubtype> incomeSubtypes = financialManagementService.getAllIncomeSubtype();
        List<IncomeExpenseSubtype> expenseSubtypes = financialManagementService.getAllExpenseSubtype();
        request.setAttribute("date", date);
        request.setAttribute("remark", remark);
        request.setAttribute("incomes", incomeSubtypes);
        request.setAttribute("expenses", expenseSubtypes);
        request.setAttribute("IncomeExpensePage", page);
        request.getRequestDispatcher("jsp/main.jsp").forward(request, response);
    }

    protected void addIncomeExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String remark = request.getParameter("szr_remark");
        int money =  Integer.parseInt(request.getParameter("szr_money"));
        String date = request.getParameter("szr_date");
        long incomeExpenseSubtypeId = Long.parseLong(request.getParameter("lest_id"));
        User user = (User)request.getSession().getAttribute("user");
        if (request.getParameter("isExpense") != null) {
            money = -1 * money;
        }
        IncomeExpense incomeExpense = new IncomeExpense();
        incomeExpense.setIe_remark(remark);
        incomeExpense.setIe_money(money);
        incomeExpense.setIe_date(date);
        incomeExpense.setU_id(user.getU_id());
        incomeExpense.setIest_id(incomeExpenseSubtypeId);
        long flag = financialManagementService.addIncomeExpense(incomeExpense);
        if (flag > 0) {
            request.setAttribute("isKeepAccount", Constant.REQUEST_SUCCESS);

        }
        getAllIncomeExpenseByUserId(request, response);
    }

    protected void addIncomeExpenseSubtype(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 传回json数据
        String incomeExpenseSubTypeName = request.getParameter("son_category");
        String incomeExpenseTypeName = request.getParameter("parent_category");
        String status = financialManagementService.addIncomeExpenseSubtype(incomeExpenseSubTypeName, incomeExpenseTypeName);
        Map<String, Object> result = new HashMap<>();
        result.put("status", status);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(result);
        response.setContentType("text/javascript");
        response.getWriter().print(jsonStr);
    }

    protected void checkIncomeExpenseSubtype(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String incomeExpenseSubTypeName = request.getParameter("son_category");
        String name = financialManagementService.checkIncomeExpenseSubtype(incomeExpenseSubTypeName);
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(result);
        response.setContentType("text/javascript");
        response.getWriter().print(jsonStr);
    }

    protected void getIncomeExpenseById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long incomeExpenseId = Integer.parseInt(request.getParameter("id"));
        IncomeExpense incomeExpense = financialManagementService.getIncomeExpenseById(incomeExpenseId);
        List<IncomeExpenseSubtype> incomeExpenseSubtypes = financialManagementService.getAllIncomeExpenseSubtype();
        Map<String, Object> result = new HashMap<>();
        result.put("incomeExpense", incomeExpense);
        result.put("incomeExpenseSubtypes", incomeExpenseSubtypes);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(result);
        response.setContentType("text/javascript");
        response.getWriter().print(jsonStr);
    }

    protected void alterIncomeExpenseRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String remark = request.getParameter("szr_remark");
        int money = Integer.parseInt(request.getParameter("szr_money"));
        long incomeExpenseId = Long.parseLong(request.getParameter("szr_id"));
        String date = request.getParameter("szr_date");
        long incomeExpenseSubtypeId = Long.parseLong(request.getParameter("szr_type"));
        IncomeExpense incomeExpense = new IncomeExpense();
        incomeExpense.setIe_id(incomeExpenseId);
        incomeExpense.setIe_remark(remark);
        incomeExpense.setIe_date(date);
        incomeExpense.setIest_id(incomeExpenseSubtypeId);
        if (financialManagementService.getIncomeExpenseTypeName(incomeExpenseSubtypeId).equals("支出")) {
            money = -1 * money;
        }
        incomeExpense.setIe_money(money);
        financialManagementService.alterIncomeExpense(incomeExpense);
        getAllIncomeExpenseByUserId(request, response);
    }

    protected void deleteIncomeExpense(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter("id"));
        financialManagementService.deleteIncomeExpenseById(id);
    }

    protected void batchDeleteIncomeExpense(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("id");
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        for(long id: idList) {
            financialManagementService.deleteIncomeExpenseById(id);
        }
    }
}
