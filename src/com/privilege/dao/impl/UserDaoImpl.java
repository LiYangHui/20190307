package com.privilege.dao.impl;

import com.privilege.dao.UserDao;
import com.privilege.po.User;
import com.privilege.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    //获取C3P0的操作对象
    private QueryRunner queryRunner = C3P0Util.getQueryRunner();

    //登录
    @Override
    public int login(String username, String password) {
        String sql = "select * from \"users\" where \"username\"=? and \"password\"=?";
        String count = "";
        try {
            count = queryRunner.query(sql, new ScalarHandler(1), username, password).toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(count);
    }

    //添加用户
    @Override
    public int addUser(User user) {
        String sql = "insert into users value(null,?,?,?)";
        int row = 0;
        try {
            row = queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getNickname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改密码
    @Override
    public int updatePwd(String newpwd, String oldname) {
        String sql = "update users set password=? where password=?";
        int row = 0;
        try {
            row = queryRunner.update(sql, newpwd, oldname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
