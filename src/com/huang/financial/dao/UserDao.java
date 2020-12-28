package com.huang.financial.dao;

import com.huang.financial.domain.User;
import com.huang.financial.web.CriteriaUser;
import com.huang.financial.web.Page;

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

    /**
     * 检查用户名是否存在
     * @return
     */
    public abstract User checkUserByName(String username);

    /**
     * 获取包装了User的Page对象
     * @param criteriaUser
     * @return
     */
    public abstract Page<User> getPage(CriteriaUser criteriaUser);

    /**
     * 通过userId获取User对象
     * @param userId
     * @return
     */
    public abstract User getUserById(long userId);
}
