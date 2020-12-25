package com.huang.financial.service;

import com.huang.financial.dao.Impl.UserDaoImpl;
import com.huang.financial.dao.UserDao;
import com.huang.financial.domain.User;
import static com.huang.financial.utils.Constant.*;

public class RegisterService {

    private UserDao userDao = new UserDaoImpl();

    public int register(String code, String inputCode, User user) {
        if (code.equals(inputCode) && userDao.insert(user) > 0) {
            return REQUEST_SUCCESS;
        } else {
            return REQUEST_FAILED;
        }
    }
}
