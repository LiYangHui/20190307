package com.privilege.dao.impl;

import com.privilege.dao.RolesDao;
import com.privilege.po.Roles;
import com.privilege.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class RolesDaoImpl implements RolesDao {

    QueryRunner queryRunner = C3P0Util.getQueryRunner();

    //查看角色
    @Override
    public List<Roles> selectRoles() {
        String sql = "select * from roles ";
        List<Roles> roleslist = null;
        try {
            roleslist = queryRunner.query(sql, new BeanListHandler<Roles>(Roles.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleslist;
    }

    //添加角色
    @Override
    public int addRoles(Roles roles) {
        String sql = "insert into roles value(null,?,?)";
        int row = 0;
        try {
            row = queryRunner.update(sql, roles.getName(), roles.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}
