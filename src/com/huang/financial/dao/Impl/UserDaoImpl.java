package com.huang.financial.dao.Impl;

import com.huang.financial.dao.UserDao;
import com.huang.financial.domain.User;
import com.huang.financial.web.CriteriaUser;
import com.huang.financial.web.Page;

import javax.management.Query;
import java.util.List;

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

    @Override
    public User checkUserByName(String username) {
        String sql = "select * from user where u_account = ?";
        return query(sql, username);
    }

    @Override
    public Page<User> getPage(CriteriaUser criteriaUser) {
        Page<User> page = new Page<>(criteriaUser.getPageNo());
        page.setTotalItemNumber(getTotalUser(criteriaUser));
        //校验page的合法性
        criteriaUser.setPageNo(page.getPageNo());
        page.setList(getPageList(criteriaUser, 6));
        return page;
    }

    @Override
    public User getUserById(long userId) {
        String sql = "select * from user where u_id = ?";
        return query(sql, userId);
    }

    private List<User> getPageList(CriteriaUser criteriaUser, int pageSize) {
        String sql = "select * from user where u_name like ? and u_email like ? and " +
                "u_tel like ? limit ?, ?";
        return queryForList(sql, "%" + criteriaUser.getUsername() + "%", "%" + criteriaUser.getEmail()
                + "%", "%" + criteriaUser.getTel() + "%",(criteriaUser.getPageNo() - 1) * pageSize, pageSize);
    }

    private long getTotalUser(CriteriaUser criteriaUser) {
        String sql = "select count(*) from user where u_name like ? and u_email like ? and " +
                "u_tel like ?";
        return getSingleVal(sql, "%" + criteriaUser.getUsername() + "%",
                "%" + criteriaUser.getEmail() + "%", "%" + criteriaUser.getTel() + "%");
    }
}
