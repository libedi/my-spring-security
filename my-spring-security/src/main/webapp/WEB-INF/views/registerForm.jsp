<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
</head>
<body>
<form action="/register" method="post">
	아이디 : <input type="text" name="userId"><br/>
	비밀번호 : <input type="text" name="password"><br/>
	권한 : <input type="text" name="role" value="USER"><br/>
	닉네임 : <input type="text" name="nick"><br/>
	<button type="submit">등록</button>
	<sec:csrfInput/>
</form>
</body>
</html>