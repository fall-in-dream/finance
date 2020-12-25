package com.huang.financial.service;

import com.huang.financial.dao.Impl.IncomeExpenseDaoImpl;
import com.huang.financial.dao.Impl.IncomeExpenseSubtypeDaoImpl;
import com.huang.financial.dao.IncomeExpenseDao;
import com.huang.financial.dao.IncomeExpenseSubtypeDao;
import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.domain.IncomeExpenseSubtype;
import com.huang.financial.web.CriteriaIncomeExpense;
import com.huang.financial.web.Page;

import java.util.List;

public class FinancialManagementService {
    IncomeExpenseDao incomeExpenseDao = new IncomeExpenseDaoImpl();
    IncomeExpenseSubtypeDao incomeExpenseSubtypeDao = new IncomeExpenseSubtypeDaoImpl();

    public List<IncomeExpense> getAllIncomeExpenseByUserId(long userId) {
        return incomeExpenseDao.getAllIncomeExpenseByUserId(userId);
    }

    public Page<IncomeExpense> getPage(CriteriaIncomeExpense criteriaIncomeExpense) {
        Page<IncomeExpense> page = incomeExpenseDao.getPage(criteriaIncomeExpense);
        for (IncomeExpense incomeExpense: page.getList()) {
            incomeExpense.setIncomeExpenseSubtype();
        }
    }
}
