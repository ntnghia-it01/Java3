<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Java3/register" method="GET">
		<input disabled type="text" name="username" placeholder="Tên tài khoản"/>
		<input readonly type="password" name="password" placeholder="Mật khẩu"/>
		<input type="text" name="name" placeholder="Họ và tên"/>
		<button type="submit">Đăng ký</button>
	</form>
</body>
</html>