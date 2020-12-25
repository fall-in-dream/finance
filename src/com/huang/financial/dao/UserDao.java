package com.huang.financial.dao;

import com.huang.financial.domain.User;

public interface UserDao {

    /**
     * 创建user对象
     * @param user
     * @return
     */
    public abstract long insert(User user);

    /**
     * 检查账号密码是否正确
     * @param user
     * @return
     */
    public abstract User checkUser(User user);
}
