package com.huang.financial.dao;

public interface IncomeExpenseTypeDao {

    /**
     * 根据incomeExpenseTypeName获取incomeExpenseTypeId
     * @param incomeExpenseTypeName
     * @return
     */
    public abstract long getIncomeExpenseTypeIdByName(String incomeExpenseTypeName);

    /**
     * 根据收入支出id获取收入支出类型名
     * @param incomeExpenseSubtypeId
     * @return
     */
    public abstract String getIncomeExpenseTypeNameById(long incomeExpenseSubtypeId);
}
