<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <h1>修改密码</h1>
        <form method="post" action="/UserServlet?username=${username}&choose=3">
            旧密码：<input name="oldpwd"><br>
            <br> 新密码:<input name="newpwd"><br>
            <br> <input type="submit" value="提交">
        </form>
    </div>
</section>
<footer class="footer"> 版权归XXXX</footer>
<script src="../js/time.js"></script>
</body>
</html>