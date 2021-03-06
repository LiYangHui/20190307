package com.privilege.dao;

import com.privilege.po.User;

import java.sql.SQLException;

public interface UserDao {
    //登录
    int login(String username, String password);

    //添加用户
    int addUser(User user);

    //修改密码
    int updatePwd(String newpwd, String oldname);
}
