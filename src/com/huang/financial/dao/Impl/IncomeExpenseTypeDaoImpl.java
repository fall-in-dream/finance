package com.huang.financial.dao.Impl;

import com.huang.financial.dao.IncomeExpenseDao;
import com.huang.financial.dao.IncomeExpenseTypeDao;
import com.huang.financial.domain.IncomeExpenseType;

public class IncomeExpenseTypeDaoImpl extends BaseDAO<IncomeExpenseType> implements IncomeExpenseTypeDao {


    @Override
    public long getIncomeExpenseTypeIdByName(String incomeExpenseTypeName) {
        String sql = "select iet_id from ie_type where iet_name = ?";
        return Long.parseLong(getSingleVal(sql, incomeExpenseTypeName).toString());
    }

    @Override
    public String getIncomeExpenseTypeNameById(long incomeExpenseSubtypeId) {
        String sql = "select iet_name from ie_type,ie_subtype where ie_type.iet_id = ie_subtype.iet_id and iest_id = ?";
        return getSingleVal(sql, incomeExpenseSubtypeId);
    }
}
