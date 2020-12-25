package com.huang.financial.dao;

import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.web.CriteriaIncomeExpense;
import com.huang.financial.web.Page;

import java.util.List;

public interface IncomeExpenseDao {

    /**
     * 获取数据库中所有的IncomeExpense对象
     * @return
     */
    public abstract List<IncomeExpense> getAllIncomeExpenseByUserId(long UserId);

    /**
     * 根据查询条件获取page对象
     * @param criteriaIncomeExpense
     * @return
     */
    public abstract Page<IncomeExpense> getPage(CriteriaIncomeExpense criteriaIncomeExpense);

}
