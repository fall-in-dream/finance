package com.huang.financial.dao.Impl;

import com.huang.financial.dao.IncomeExpenseSubtypeDao;
import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.domain.IncomeExpenseSubtype;

import java.util.List;

public class IncomeExpenseSubtypeDaoImpl extends BaseDAO<IncomeExpenseSubtype> implements IncomeExpenseSubtypeDao {

    @Override
    public IncomeExpenseSubtype getIncomeExpenseSubtypeById(long IncomeExpenseSubtypeId) {
        String sql = "select iest_id,iest_name,iet_id from ie_subtype where iest_id = ?";
        return query(sql, IncomeExpenseSubtypeId);
    }

    @Override
    public List<IncomeExpenseSubtype> getIncomeExpenseSubtypeByType(String typeName) {
        String sql = "select iest_id,iest_name,ie_subtype.iet_id from ie_subtype,ie_type where ie_subtype.iet_id " +
                "= ie_type.iet_id and iet_name = ?";
        return queryForList(sql, typeName);
    }

    @Override
    public long addIncomeExpenseSubtype(String incomeExpenseSubTypeName, long incomeExpenseTypeId) {
        String sql = "insert into ie_subtype(iest_name,iet_id) values(?,?)";
        return insert(sql, incomeExpenseSubTypeName, incomeExpenseTypeId);
    }

    @Override
    public IncomeExpenseSubtype checkIncomeExpenseType(String incomeExpenseSubTypeName) {
        String sql = "select iest_id,iest_name,iet_id from ie_subtype where iest_name = ?";
        return query(sql, incomeExpenseSubTypeName);
    }

    @Override
    public List<IncomeExpenseSubtype> getAllIncomeExpenseSubtype() {
        String sql = "select iest_id,iest_name,iet_id from ie_subtype";
        return queryForList(sql);
    }


}
