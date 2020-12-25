package com.huang.financial.dao.Impl;

import com.huang.financial.dao.IncomeExpenseSubtypeDao;
import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.domain.IncomeExpenseSubtype;

public class IncomeExpenseSubtypeDaoImpl extends BaseDAO<IncomeExpenseSubtype> implements IncomeExpenseSubtypeDao {

    @Override
    public IncomeExpenseSubtype getIncomeExpenseSubtypeById(long IncomeExpenseSubtypeId) {
        String sql = "select iest_id,iest_name,iet_id from ie_subtype where iest_id = ?";
        return query(sql, IncomeExpenseSubtypeId);
    }
}
