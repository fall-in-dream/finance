package com.huang.financial.service;

import com.huang.financial.dao.Impl.UserDaoImpl;
import com.huang.financial.dao.UserDao;
import com.huang.financial.domain.User;

public class LoginService {

    private UserDao userDao = new UserDaoImpl();

    public User checkUser(User user) {
        return userDao.checkUser(user);
    }
}
