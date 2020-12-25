package com.huang.financial.domain;

import java.sql.Date;

public class IncomeExpense {
    private long ie_id;
    private String ie_date;
    private int ie_money;
    private String ie_remark;
    private int u_id;
    private int iest_id;
    private IncomeExpenseSubtype incomeExpenseSubtype;

    public long getIe_id() {
        return ie_id;
    }

    public void setIe_id(long ie_id) {
        this.ie_id = ie_id;
    }

    public String getIe_date() {
        return ie_date;
    }

    public void setIe_date(String ie_date) {
        this.ie_date = ie_date;
    }

    public int getIe_money() {
        return ie_money;
    }

    public void setIe_money(int ie_money) {
        this.ie_money = ie_money;
    }

    public String getIe_remark() {
        return ie_remark;
    }

    public void setIe_remark(String ie_remark) {
        this.ie_remark = ie_remark;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getIest_id() {
        return iest_id;
    }

    public void setIest_id(int iest_id) {
        this.iest_id = iest_id;
    }

    public IncomeExpenseSubtype getIncomeExpenseSubtype() {
        return incomeExpenseSubtype;
    }

    public void setIncomeExpenseSubtype(IncomeExpenseSubtype incomeExpenseSubtype) {
        this.incomeExpenseSubtype = incomeExpenseSubtype;
    }
}
