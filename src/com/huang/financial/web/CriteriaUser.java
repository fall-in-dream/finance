package com.huang.financial.web;

public class CriteriaUser {
    //封装查询条件的CriteriaUser类
    private String username;
    private String email;
    private String tel;
    private int pageNo;

    public CriteriaUser(String username, String email, String tel, int pageNo) {
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.pageNo = pageNo;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
