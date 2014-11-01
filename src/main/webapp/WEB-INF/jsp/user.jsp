<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="user" value="${sessionScope.current}" />

<%-- <c:if test="${user.firstName == null}">
<c:redirect url="/index"/>
</c:if> --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserPage</title>
</head>
<body>
<div align="center" style="margin-top:100px">
	<h1>Hello, <sec:authentication property="principal.username"/>!</h1>
	Click <a href="<c:url value="/logout"/>">to logout</a> 
</div>
</body>
</html>