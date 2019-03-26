package com.privilege.serivce.impl;

import com.privilege.dao.impl.UserDaoImpl;
import com.privilege.po.User;
import com.privilege.serivce.UserService;
import com.privilege.utils.MD5Util;

public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    //登录
    @Override
    public int login(String username, String password) {
        //返回行数
        int rows = userDao.login(username, password);
        //加密
        password = MD5Util.MD5Encoding(password);
        return rows;
    }

    //添加用户
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    //更新密码
    @Override
    public int updatePwd(String newpwd, String oldname) {
        return userDao.updatePwd(newpwd, oldname);
    }
}
