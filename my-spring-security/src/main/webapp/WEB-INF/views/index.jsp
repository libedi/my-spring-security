<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
Welcome to Spring Security!<br/>
<a href="/admin">어드민 페이지로 이동</a><br/>
<a href="/user">유저 페이지로 이동</a><br/>
<sec:authorize access="isAnonymous()">
로그아웃 상태입니다.
</sec:authorize>
<sec:authorize access="isAuthenticated()">
로그인 상태입니다.	
</sec:authorize>
</body>
</html>