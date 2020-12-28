package com.huang.financial.service;

import com.huang.financial.dao.AdminDao;
import com.huang.financial.dao.Impl.AdminDaoImpl;
import com.huang.financial.domain.Admin;

public class AdminLoginService {

    AdminDao adminDao = new AdminDaoImpl();

    public Admin checkAdmin(Admin admin) {
        return adminDao.checkAdmin(admin);
    }

}
