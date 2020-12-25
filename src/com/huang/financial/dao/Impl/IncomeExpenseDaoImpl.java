package com.huang.financial.dao.Impl;

import com.huang.financial.dao.IncomeExpenseDao;
import com.huang.financial.domain.IncomeExpense;
import com.huang.financial.web.CriteriaIncomeExpense;
import com.huang.financial.web.Page;

import java.util.List;

public class IncomeExpenseDaoImpl extends BaseDAO<IncomeExpense> implements IncomeExpenseDao {

    @Override
    public List<IncomeExpense> getAllIncomeExpenseByUserId(long userId) {
        String sql = "select * from ie where u_id = ?";
        return queryForList(sql, userId);
    }

    @Override
    public Page<IncomeExpense> getPage(CriteriaIncomeExpense criteriaIncomeExpense) {
        Page<IncomeExpense> page = new Page<>(criteriaIncomeExpense.getPageNo());
        page.setTotalItemNumber(getTotalIncomeExpense(criteriaIncomeExpense));
        //校验Page的合法性
        criteriaIncomeExpense.setPageNo(page.getPageNo());
        page.setList(getPageList(criteriaIncomeExpense, 6));
        return page;
    }

    private List<IncomeExpense> getPageList(CriteriaIncomeExpense criteriaIncomeExpense, int pageSize) {
        String sql = "select ie_id,ie_date,ie_money,ie_remark,u_id,iest_id where " +
                "ie_date like '%?%' and ie_remark like '%?%' limit ?,? and u_id = ?";
        return queryForList(sql, criteriaIncomeExpense.getDate(), criteriaIncomeExpense.getRemark(),
                (criteriaIncomeExpense.getPageNo() - 1) * pageSize, pageSize, criteriaIncomeExpense.getUserId());
    }

    private long getTotalIncomeExpense(CriteriaIncomeExpense criteriaIncomeExpense) {
        String sql = "select count(*) from ie where ie_date like '%?%' and ie_remark like '%?%' and u_id = ?";
        return getSingleVal(sql, criteriaIncomeExpense.getDate(), criteriaIncomeExpense.getRemark(),
                criteriaIncomeExpense.getUserId());
    }
}
