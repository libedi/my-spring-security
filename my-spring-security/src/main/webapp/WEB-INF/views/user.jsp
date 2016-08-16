<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Page</title>
</head>
<body>
User Page<br/>
<sec:authentication property="principal.username"/>님 안녕하세요.<br/>
<sec:authentication property="principal.nick"/>님 안녕하세요.<br/>
<br/>
<form action="/logout" method="post">
<%-- 	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/> --%>
	<sec:csrfInput/>
	<button type="submit">Logout</button>
</form>
</body>
</html>