package com.privilege.dao.impl;

import com.privilege.dao.PrivilegesDao;
import com.privilege.po.Privileges;
import com.privilege.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PrivilegesDaoImpl implements PrivilegesDao {
    private QueryRunner queryRunner = C3P0Util.getQueryRunner();

    //添加权限
    @Override
    public int addPrivilege(Privileges privileges) {
        String sql = "insert into privileges value(null,?,?,?)";
        int rows = 0;
        try {
            rows = queryRunner.update(sql, privileges.getName(), privileges.getFnpath(), privileges.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Privileges> selectAllPrivilege() {
        String sql = "select * from privileges";
        List<Privileges> privilegesList = null;
        try {
            privilegesList = queryRunner.query(sql, new BeanListHandler<Privileges>(Privileges.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privilegesList;
    }

    //授权
    //获取已有的权限
    @Override
    public List<Privileges> getAlreadyPrivilegeList(int roleid) {
        String sql = "select * from privileges where id in(select privilege_id from roleprivilege where role_id=?)";
        List<Privileges> privilegesList = null;
        try {
            privilegesList = queryRunner.query(sql, new BeanListHandler<Privileges>(Privileges.class), roleid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privilegesList;
    }

    //授权
    //获取未获得的权限
    @Override
    public List<Privileges> getNotPrivilegeList(int roleid) {
        String sql = "select * from privileges where id not in(select privilege_id from roleprivilege where role_id=?)";
        List<Privileges> privilegesList = null;
        try {
            privilegesList = queryRunner.query(sql, new BeanListHandler<Privileges>(Privileges.class), roleid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privilegesList;
    }

    //授权 添加或者删除权限数组
    @Override
    public int toAddPrivilege(int roleid, String[] arrAddPrivilege) {
        String sql = "insert into roleprivilege value(?,?)";
        int row = 0;
        try {
            for (int i = 0; i < arrAddPrivilege.length; i++) {
                row = queryRunner.update(sql, arrAddPrivilege[i], roleid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    @Override
    public int toDelPrivilege(int roleid, String[] arrDelPrivilege) {
        String sql = "delete from roleprivilege where role_id=?";
        int row = 0;
        try {
            row = queryRunner.update(sql, roleid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }


}
