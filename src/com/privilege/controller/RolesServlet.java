package com.privilege.controller;

import com.privilege.po.Roles;
import com.privilege.serivce.RolesService;
import com.privilege.serivce.impl.RolesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 角色
 */
@WebServlet("/RolesServlet")
public class RolesServlet extends HttpServlet {
    private RolesService rolesService = new RolesServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choose = request.getParameter("choose");
        switch (choose) {
            case "1":
                selectRolesList(request, response);
                break;
            case "2":
                addRoles(request, response);
                break;
        }
    }

    //添加角色
    private void addRoles(HttpServletRequest request, HttpServletResponse response) {
        String role_name = request.getParameter("role_name");
        String role_description = request.getParameter("role_description");
        Roles roles = new Roles(0, role_name, role_description);
        int row = rolesService.addRoles(roles);
        if (row > 0) {
            try {
                request.getRequestDispatcher("a.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //查询角色
    private void selectRolesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Roles> rolesList = rolesService.selectRoles();
        if (rolesList.size() > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("rolesList", rolesList);
            request.getRequestDispatcher("/jsp/roles_list.jsp").forward(request, response);
        } else {
            response.sendRedirect("/jsp/welcome.jsp");
        }
    }
}
