package com.privilege.serivce;

import com.privilege.po.User;

public interface UserService {
    //登录
    int login(String username, String password);

    //添加用户
    int addUser(User user);

    //修改密码
    int updatePwd(String newpwd, String oldname);
}
