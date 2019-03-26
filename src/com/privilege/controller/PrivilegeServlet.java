package com.privilege.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.privilege.po.Privileges;
import com.privilege.serivce.PrivilegeService;
import com.privilege.serivce.impl.PrivilegeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 权限
 */
@WebServlet("/PrivilegeServlet")
public class PrivilegeServlet extends HttpServlet {
    private PrivilegeService privilegeService = new PrivilegeServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //获取参数choose
        String choose = request.getParameter("choose");
        System.out.println(choose);
        switch (choose) {
            case "1":
                addPrivilegeA(request, response);
                break;
            case "2":
                selectPrivilege(request, response);
                break;
            case "3":
                //授权 查询出已有的权限和未有的权限
                selectEveryPrivilege(request, response);
                break;
            case "4":
                System.out.println("错误!");
                addDeletePrivilege(request, response);
                break;
            default:
                System.out.println("错误!");
                break;
        }

    }

    //授权 添加或者删除权限数组
    private void addDeletePrivilege(HttpServletRequest request, HttpServletResponse response) {
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        String[] priaddsId = request.getParameter("priaddsId").split(",");
        String[] pridelsId = request.getParameter("pridelsId").split(",");

        int delrow = privilegeService.toDelPrivilege(roleid, pridelsId);
        int addrow = privilegeService.toAddPrivilege(roleid, priaddsId);
        if (addrow > 0) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", 1);
                String jsonString = JSON.toJSONString(jsonObject);
                PrintWriter pw = response.getWriter();
                pw.write(jsonString);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //授权 查询出已有的权限和未有的权限
    private void selectEveryPrivilege(HttpServletRequest request, HttpServletResponse response) {
        int roleid = Integer.parseInt(request.getParameter("roleid"));
        String rolename = request.getParameter("rolename");
        System.out.println(rolename);
        //已有权限
        List<Privileges> alreadyPrivilegeList = privilegeService.getAlreadyPrivilegeList(roleid);
        //未有权限
        List<Privileges> notPrivilegeList = privilegeService.getNotPrivilegeList(roleid);
        //所有权限
        List<Privileges> privilegesList = privilegeService.selectAllPrivilege();
        //if(alreadyPrivilegeList.size()>0){
        try {
            HttpSession session = request.getSession();
            session.setAttribute("roleid", roleid);
            session.setAttribute("rolename", rolename);
            session.setAttribute("alreadyPrivilegeList", alreadyPrivilegeList);
            session.setAttribute("notPrivilegeList", notPrivilegeList);
            session.setAttribute("privilegesList", privilegesList);
            request.getRequestDispatcher("/jsp/grant_privilege.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // }

    }

    //添加权限
    private void addPrivilegeA(HttpServletRequest request, HttpServletResponse response) {
        String p_name = request.getParameter("p_name");
        String p_url = request.getParameter("p_url");
        String p_description = request.getParameter("p_description");
        Privileges privileges = new Privileges(0, p_name, p_url, p_description);
        int row = privilegeService.addPrivilege(privileges);
        if (row > 0) {
            try {
                request.getRequestDispatcher("a.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(row);
        }
    }

    //查看权限
    private void selectPrivilege(HttpServletRequest request, HttpServletResponse response) {
        List<Privileges> privilegesList = privilegeService.selectAllPrivilege();
        if (privilegesList.size() > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("privilegesList", privilegesList);
            try {
                request.getRequestDispatcher("/jsp/privilege_list.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
