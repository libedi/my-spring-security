<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
</head>
<body>
Admin Page<br/>
<sec:authentication property="principal.username"/>님 안녕하세요.<br/>
<sec:authorize access="isAnonymous()">
로그아웃 상태입니다.
</sec:authorize>
<sec:authorize access="isAuthenticated()">
로그인 상태입니다.	
</sec:authorize>
<br/>
<form action="/logout" method="post">
<%-- 	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/> --%>
	<sec:csrfInput/>
	<button type="submit">Logout</button>
</form>
</body>
</html>