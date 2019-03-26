package com.privilege.controller;

import com.privilege.po.User;
import com.privilege.serivce.UserService;
import com.privilege.serivce.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choose = request.getParameter("choose");
        switch (choose) {
            case "1":
                loginUser(request, response);
                break;
            case "2":
                adderUser(request, response);
                break;
            case "3":
                updatePassword(request, response);
                break;
            default:
                System.out.println("错误");
                break;
        }

    }

    //修改密码
    private void updatePassword(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String oldpwd = request.getParameter("oldpwd");
        String newpwd = request.getParameter("newpwd");
        System.out.println(username + "," + oldpwd + "," + newpwd);
        int row = userService.updatePwd(newpwd, oldpwd);
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

    //添加用户
    private void adderUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        User user = new User(0, username, password, nickname);
        int row = userService.addUser(user);
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

    //登录
    private void loginUser(HttpServletRequest request, HttpServletResponse response) {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "," + password);
        //调用方法
        int rows = 0;

        rows = userService.login(username, password);

        if (rows > 0) {
            //存
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            //转发
            try {
                request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //重定向
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
