package com.huang.financial.service;

import com.huang.financial.dao.Impl.UserDaoImpl;
import com.huang.financial.dao.UserDao;
import com.huang.financial.domain.User;
import com.huang.financial.web.CriteriaUser;
import com.huang.financial.web.Page;

public class UserManageService {

    private UserDao userDao = new UserDaoImpl();

    public Page<User> getPage(CriteriaUser criteriaUser) {
        return userDao.getPage(criteriaUser);
    }

    public User getUserById(long userId) {
        return userDao.getUserById(userId);
    }
}
