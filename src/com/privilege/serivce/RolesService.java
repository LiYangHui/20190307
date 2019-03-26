package com.privilege.serivce;

import com.privilege.po.Roles;

import java.util.List;

public interface RolesService {
    //查看角色
    List<Roles> selectRoles();

    //添加角色
    int addRoles(Roles roles);
}
