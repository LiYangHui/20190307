package com.privilege.serivce.impl;

import com.privilege.dao.RolesDao;
import com.privilege.dao.impl.RolesDaoImpl;
import com.privilege.po.Roles;
import com.privilege.serivce.RolesService;

import java.util.List;

public class RolesServiceImpl implements RolesService {
    private RolesDao rolesDao = new RolesDaoImpl();

    //查看角色
    @Override
    public List<Roles> selectRoles() {
        List<Roles> rolesList = rolesDao.selectRoles();
        return rolesList;
    }

    //添加角色
    @Override
    public int addRoles(Roles roles) {
        int row = rolesDao.addRoles(roles);
        return row;
    }

}
