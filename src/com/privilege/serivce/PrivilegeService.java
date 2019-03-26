package com.privilege.serivce;

import com.privilege.po.Privileges;

import java.util.List;

public interface PrivilegeService {
    //添加权限
    int addPrivilege(Privileges privileges);

    //查看权限
    List<Privileges> selectAllPrivilege();

    //授权
    //获取某个人已有的权限
    List<Privileges> getAlreadyPrivilegeList(int roleid);

    //获取某个人未获得的权限
    List<Privileges> getNotPrivilegeList(int roleid);

    //授权 添加或者删除权限数组
    int toAddPrivilege(int roleid, String[] arrAddPrivilege);

    int toDelPrivilege(int roleid, String[] arrDelPrivilege);
}
