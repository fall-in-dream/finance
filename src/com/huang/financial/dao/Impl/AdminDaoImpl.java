package com.huang.financial.dao.Impl;

import com.huang.financial.dao.AdminDao;
import com.huang.financial.domain.Admin;

public class AdminDaoImpl extends BaseDAO<Admin> implements AdminDao {

    @Override
    public Admin checkAdmin(Admin admin) {
        String sql = "select * from admin where a_account = ? and a_password = ?";
        return query(sql, admin.getA_account(), admin.getA_password());
    }
}
