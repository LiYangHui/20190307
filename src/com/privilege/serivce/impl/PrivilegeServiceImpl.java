package com.privilege.serivce.impl;

import com.privilege.dao.PrivilegesDao;
import com.privilege.dao.impl.PrivilegesDaoImpl;
import com.privilege.po.Privileges;
import com.privilege.serivce.PrivilegeService;

import java.util.List;

public class PrivilegeServiceImpl implements PrivilegeService {
    private PrivilegesDao privilegesDao = new PrivilegesDaoImpl();

    //添加权限
    @Override
    public int addPrivilege(Privileges privileges) {
        int rows = privilegesDao.addPrivilege(privileges);
        return rows;
    }

    //查询权限
    @Override
    public List<Privileges> selectAllPrivilege() {
        List<Privileges> privilegesList = privilegesDao.selectAllPrivilege();
        return privilegesList;
    }

    //授权
    //获取某个人已有的权限
    @Override
    public List<Privileges> getAlreadyPrivilegeList(int roleid) {
        List<Privileges> privilegesList = privilegesDao.getAlreadyPrivilegeList(roleid);
        return privilegesList;
    }

    //获取某个人未有的权限
    @Override
    public List<Privileges> getNotPrivilegeList(int roleid) {
        List<Privileges> privilegesList = privilegesDao.getNotPrivilegeList(roleid);
        return privilegesList;
    }

    //授权 添加或者删除权限数组
    @Override
    public int toAddPrivilege(int roleid, String[] arrAddPrivilege) {
        return privilegesDao.toAddPrivilege(roleid, arrAddPrivilege);
    }

    @Override
    public int toDelPrivilege(int roleid, String[] arrDelPrivilege) {
        return privilegesDao.toDelPrivilege(roleid, arrDelPrivilege);
    }


}
