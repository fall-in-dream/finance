package com.huang.financial.dao;

public interface IncomeExpenseTypeDao {

    /**
     * 根据incomeExpenseTypeName获取incomeExpenseTypeId
     * @param incomeExpenseTypeName
     * @return
     */
    public abstract long getIncomeExpenseTypeIdByName(String incomeExpenseTypeName);
}
