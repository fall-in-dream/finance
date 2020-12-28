package com.huang.financial.service;

import com.huang.financial.dao.Impl.IncomeExpenseDaoImpl;
import com.huang.financial.dao.Impl.IncomeExpenseSubtypeDaoImpl;
import com.huang.financial.dao.Impl.IncomeExpenseTypeDaoImpl;
import com.huang.financial.dao.IncomeExpenseDao;
import com.huang.financial.dao.IncomeExpenseSubtypeDao;
import com.huang.financial.dao.IncomeExpenseTypeDao;
import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.domain.IncomeExpenseSubtype;
import com.huang.financial.web.CriteriaIncomeExpense;
import com.huang.financial.web.Page;

import java.util.List;

public class FinancialManagementService {

    IncomeExpenseDao incomeExpenseDao = new IncomeExpenseDaoImpl();
    IncomeExpenseSubtypeDao incomeExpenseSubtypeDao = new IncomeExpenseSubtypeDaoImpl();
    IncomeExpenseTypeDao incomeExpenseTypeDao = new IncomeExpenseTypeDaoImpl();

    public List<IncomeExpense> getAllIncomeExpenseByUserId(long userId) {
        return incomeExpenseDao.getAllIncomeExpenseByUserId(userId);
    }

    public Page<IncomeExpense> getPage(CriteriaIncomeExpense criteriaIncomeExpense) {
        Page<IncomeExpense> page = incomeExpenseDao.getPage(criteriaIncomeExpense);
        for (IncomeExpense incomeExpense: page.getList()) {
            incomeExpense.setIncomeExpenseSubtype(incomeExpenseSubtypeDao.
                    getIncomeExpenseSubtypeById(incomeExpense.getIest_id()));
        }
        return page;
    }

    public List<IncomeExpenseSubtype> getAllIncomeSubtype() {
        return incomeExpenseSubtypeDao.getIncomeExpenseSubtypeByType("收入");
    }

    public List<IncomeExpenseSubtype> getAllExpenseSubtype() {
        return incomeExpenseSubtypeDao.getIncomeExpenseSubtypeByType("支出");
    }

    public long addIncomeExpense(IncomeExpense incomeExpense) {
        return incomeExpenseDao.insertIncomeExpense(incomeExpense);
    }

    public String addIncomeExpenseSubtype(String incomeExpenseSubtypeName, String incomeExpenseTypeName) {
        long incomeExpenseTypeId = incomeExpenseTypeDao.getIncomeExpenseTypeIdByName(incomeExpenseTypeName);
        incomeExpenseSubtypeDao.addIncomeExpenseSubtype(incomeExpenseSubtypeName, incomeExpenseTypeId);
        return "ok";
    }

    public String checkIncomeExpenseSubtype(String incomeExpenseSubtypeName) {
        IncomeExpenseSubtype incomeExpenseSubtype = incomeExpenseSubtypeDao.checkIncomeExpenseType(incomeExpenseSubtypeName);
        if (incomeExpenseSubtype == null) {
            return "ok";
        } else {
            return "exit";
        }
    }

    public IncomeExpense getIncomeExpenseById(long incomeExpenseId) {
        return incomeExpenseDao.getIncomeExpenseById(incomeExpenseId);
    }

    public List<IncomeExpenseSubtype> getAllIncomeExpenseSubtype() {
        return incomeExpenseSubtypeDao.getAllIncomeExpenseSubtype();
    }

    public void alterIncomeExpense(IncomeExpense incomeExpense) {
        incomeExpenseDao.alterIncomeExpense(incomeExpense);
    }

    public void deleteIncomeExpenseById(long id) {
        incomeExpenseDao.deleteIncomeExpenseById(id);
    }

    public String getIncomeExpenseTypeName(long id) {
        return incomeExpenseTypeDao.getIncomeExpenseTypeNameById(id);
    }
}
