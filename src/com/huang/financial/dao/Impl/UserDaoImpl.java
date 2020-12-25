package com.huang.financial.dao.Impl;

import com.huang.financial.dao.UserDao;
import com.huang.financial.domain.User;

import javax.management.Query;

public class UserDaoImpl extends BaseDAO<User> implements UserDao {
    @Override
    public long insert(User user) {
        String sql = "insert into user(u_name,u_sex,u_email,u_password,u_tel) values " +
                "(?,?,?,?,?)";
        return insert(sql, user.getU_name(),user.getU_sex(),user.getU_email()
        ,user.getU_password(),user.getU_tel());
    }

    @Override
    public User checkUser(User user) {
        String sql = "select * from user where u_account = ? and u_password = ?";
        return query(sql, user.getU_account(), user.getU_password());
    }
}
