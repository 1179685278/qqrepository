<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/4
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>注册页面</h1>
    <form action="/user/loginadd" method="post">
        ${success}<br>
        用户名：<input type="text" name="userName">
        <br>
        密码：<input type="password" name="pswd">
        <br>
        <input type="submit" value="确认注册"/>
        <a href="/login.jsp"><input type="button" value="登录"></a>
    </form>
</body>
</html>
