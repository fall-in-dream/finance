package com.huang.financial.dao;

import com.huang.financial.domain.IncomeExpenseSubtype;

public interface IncomeExpenseSubtypeDao {

    /**
     * 根据IncomeExpenseSubtypeId获取IncomeExpenseSubtype对象
     * @param IncomeExpenseSubtypeId
     * @return
     */
    public abstract IncomeExpenseSubtype getIncomeExpenseSubtypeById(long IncomeExpenseSubtypeId);
}
