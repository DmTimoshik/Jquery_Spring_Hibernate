<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>

<div align="center" style="margin-top:100px">
<c:url value="/j_spring_security_check" var="secureUrl"/>
	<form name='f' method="POST" action="j_spring_security_check">
	<!-- <input type="hidden" name="action" value="login" /> -->
	<table align="center" bgcolor="#cccccc" cellpadding="7" frame="border">	
	<tr><td width=30>
	Login: </td><td><input type=text name="j_username" size=40>
	</td></tr>
	<tr><td width=30>
	Password:</td><td> <input type=password name="j_password" size=40>
	</td></tr>
	<tr><td></td><td align="right">
	<input type="button" value="Registrate "
							onclick="location.href='${app}/reguser'">
	<input type=submit value=" Sign In ">
	</td></tr>
	</table>	
	</form>
	</br>
	
				<c:if test="${wrong != null}">
					<p style="color: red; font-size: 20px;">${wrong}</p>
				</c:if>
<%-- 			<span style="color: black;font-size:18px;">${user}</span><span style="color: red;">${message}</span>
			<p style="color: red;font-size:20px;">${message2}</p> --%>

	</div>
</body>
</html>