<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InfoSec: 登录</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <%
        response.sendRedirect("index.jsp");
    %>

</sec:authorize>

<h3>用户登录</h3>
<form name='f' action='/j_spring_security_check' method='POST'>
    <table>
        <tr><td>User:</td><td><input type='text' name='j_username' value=''></td></tr>
        <tr><td>Password:</td><td><input type='password' name='j_password'/></td></tr>
        <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
    </table>
</form>
</body>
</html>