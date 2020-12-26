package com.huang.financial.dao;

import com.huang.financial.domain.IncomeExpenseSubtype;

import java.util.List;

public interface IncomeExpenseSubtypeDao {

    /**
     * 根据IncomeExpenseSubtypeId获取IncomeExpenseSubtype对象
     * @param IncomeExpenseSubtypeId
     * @return
     */
    public abstract IncomeExpenseSubtype getIncomeExpenseSubtypeById(long IncomeExpenseSubtypeId);

    /**
     * 根据收入支出类型获取收入支出子类型
     * @return
     */
    public abstract List<IncomeExpenseSubtype> getIncomeExpenseSubtypeByType(String typeName);


    /**
     * 增加收支类型
     * @param incomeExpenseSubTypeName
     * @param incomeExpenseTypeName
     * @return
     */
    public abstract long addIncomeExpenseSubtype(String incomeExpenseSubTypeName, long incomeExpenseTypeName);

    /**
     * 检查该子类型是否存在
     * @param incomeExpenseSubTypeName
     * @return
     */
    public abstract IncomeExpenseSubtype checkIncomeExpenseType(String incomeExpenseSubTypeName);

    /**
     * 获取所有的收支子类型
     * @return
     */
    public abstract List<IncomeExpenseSubtype> getAllIncomeExpenseSubtype();
}
