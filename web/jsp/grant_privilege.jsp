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

        <table border="1" style="text-align: center;">
            <tr>
                <td>角色名称</td>
                <td>${rolename}</td>
            </tr>
            <tr>
                <td>描述</td>
                <td>已具有权限： <c:forEach items="${alreadyPrivilegeList}" var="privileges">
                    ${privileges.name}<input type="checkbox" disabled name="privilegedelete" value="${privileges.id}"
                                             checked="checked">&nbsp;
                </c:forEach>
                </td>
            </tr>

            <tr>
                <td>系统所有权限列表</td>
                <td><c:forEach items="${alreadyPrivilegeList}" var="privileges">
                    ${privileges.name}<input type="checkbox" name="privilegeadd" value="${privileges.id}"
                                             onchange="changestatus(this,this.checked)">&nbsp;
                </c:forEach>
                    <c:forEach items="${notPrivilegeList}" var="privileges">
                        ${privileges.name}<input type="checkbox" name="privilegeadd" value="${privileges.id}"
                                                 onchange="changestatus(this,this.checked)">&nbsp;
                    </c:forEach></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="hidden" name="roleID" id="roleid" value="${roleid}">
                    <input type="hidden" name="choose" value="3">
                    <input type="button" value="授权" onclick="shouquan()"></td>

            </tr>

        </table>

    </div>
</section>
<footer class="footer"> 版权归XXXX</footer>
<script src="../js/time.js"></script>

</body>
<script src="../js/jquery.js"></script>
<script>
    function changestatus(th, check) {
        if (check) {
            $(th).attr("checked", "checked");
        } else {
            $(th).removeAttr("checked");
        }
    }

    function shouquan() {
        var privilegeadd = $("input[name='privilegeadd']");
        var privilegedelete = $("input[name='privilegedelete']");
        var priadds = "";
        var pridels = "";
        $(privilegeadd).each(function (i) {
            if (this.checked == true) {
                priadds += this.value + ",";
            }
        });
        $(privilegedelete).each(function (i) {
            pridels += this.value + ",";
        });
        priadds = priadds.substring(0, priadds.length - 1);
        pridels = pridels.substring(0, pridels.length - 1);
        $.ajax({
            url: "PrivilegeServlet",
            type: "post",
            dataType: "json",
            data: {
                roleid: rp = $("#roleid").val(),
                priaddsId: priadds,
                pridelsId: pridels,
                choose: 4
            },
            success: function (dd) {
                if (dd.result == 1) {
                    alert("授权成功");
                    window.location = "/RolesServlet?choose=1";
                }
            }
        });
    }
</script>
</html>