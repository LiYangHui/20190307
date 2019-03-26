package com.privilege.utils;

import com.privilege.po.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TestOracle {
    @Test
    public void test() {
        String sql = "select * from \"users\"";
        System.out.println(sql);
        QueryRunner queryRunner = C3P0Util.getQueryRunner();
        System.out.println(queryRunner);
        List<User> userlist = null;
        try {
            userlist = queryRunner.query(sql, new BeanListHandler<User>(User.class));
            for (User user : userlist) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
