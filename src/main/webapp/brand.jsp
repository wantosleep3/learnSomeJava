<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--一次对话的两次请求之间共享数据 需要把数据存在ck 或者ssn里面--%>
<h1>${user.username},欢迎你</h1>

<%--添加id = “add” 跳转到新增的表单--%>
<input type="button" value="新增" id="add"><br>
<hr>
<table border="1" cellspacing="0" width="80%">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>

    </tr>

    <%--用varstatus生成序号   index代表从零开始  count代表从1开始--%>
    <c:forEach items="${brands}" var="brand" varStatus="status">
        <tr align="center">
            <%--<td>${brand.id}</td>--%>
            <td>${status.count}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.description}</td>
                <%--<td>${brand.status}</td>--%>

            <c:if test="${brand.status==1}">
                <td>启用</td>
            </c:if>
            <c:if test="${brand.status!=1}">
                <td>禁用</td>

            </c:if>
            <td><a href="/brand-demo/selectByIdServlet?id=${brand.id}">修改</a> <a href="/brand-demo/deleteByIdServlet?id=${brand.id}">删除</a></td>
        </tr>
    </c:forEach>

</table>

<script>
    document.getElementById("add").onclick=function(){
        location.href="/brand-demo/addBrand.jsp";
    }
</script>

</body>
</html>
