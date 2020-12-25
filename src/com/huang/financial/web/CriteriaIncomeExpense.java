package com.huang.financial.web;

import java.sql.Date;

public class CriteriaIncomeExpense {
    //封装查询条件的CriteriaIncomeExpense类
    private String date;
    private String remark;
    private long userId;
    private int pageNo;

    public CriteriaIncomeExpense(long userId, String date, String remark, int pageNo) {
        this.userId = userId;
        this.date = date;
        this.remark = remark;
        this.pageNo = pageNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


}
