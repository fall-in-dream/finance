package com.huang.financial.dao;

import com.huang.financial.domain.Admin;

public interface AdminDao {

    /**
     * 检查管理员账号密码是否正确
     * @param admin
     * @return
     */
    public abstract Admin checkAdmin(Admin admin);
}
