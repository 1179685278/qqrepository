<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/3
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录页面</h1>
    <form action="/user/login" method="post">
        ${success}<br>
        用户名：<input type="text" name="userName">
        <br>
        密码：<input type="password" name="pswd">
        <br>
        <input type="submit" value="提交"/>
        <a href="/loginadd.jsp"><input type="button" value="注册"></a>
    </form>
</body>
</html>
