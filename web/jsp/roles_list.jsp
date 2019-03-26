<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert title here</title>

</head>
<body>
<!--头部-->
<%@ include file="top.jsp" %>
<!--主体内容-->
<section class="publicMian">
    <%@ include file="left.jsp" %>
    <div class="right">
        <table>
            <tr>
                <td>编号</td>
                <td>角色名称</td>
                <td>描述</td>
                <td>授予权限</td>
            </tr>
            <c:forEach items="${rolesList}" var="role">
                <tr>
                    <td>${role.id}</td>
                    <td>${role.name}</td>
                    <td>${role.description}</td>
                    <td><a href="/PrivilegeServlet?roleid=${role.id}&rolename=${role.name}&choose=3">授权</a></td>
                </tr>


            </c:forEach>

        </table>
    </div>
</section>
<footer class="footer"> 版权归XXXX</footer>
<script src="../js/time.js"></script>
</body>
</html>