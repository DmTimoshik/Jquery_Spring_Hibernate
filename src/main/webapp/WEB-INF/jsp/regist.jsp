<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <c:set var="user" value="${current}" /> --%>
<c:set var="userAdmin" value="${sessionScope.current}" />
<c:set var="app" value="${pageContext.request.contextPath}" />
<%-- <c:if test="${user.firstName == null}">
<c:redirect url="index.jsp"/>
</c:if> --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add users</title>



</head>
<body>
	<div align="center" style="padding-left: 300px;">

		<div align="left">
			<h1>New user registration:</h1>
		</div>
		<div align="left" style="margin-top: 20px">
			<form:form method="post" action="${app}/regserv"
				modelAttribute="template">
				<%-- form method=post action="${app}/addUser" --%>
				<input type="hidden" name="action" value="add" />
				<table >
					<tr>
						<td>Login</td>
						<td><form:input path="login" size="30" type="text" /></td>
						<td><font color="#CC0000"> * </font><form:errors path="login" style="color:red" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:input path="password" size="30" type="password" /></td>
						<td><font color="#CC0000"> * </font><form:errors path="password" style="color:red" /></td>
					</tr>
					<tr>
						<td>Password again</td>
						<td><form:input path="passwordConfirm" size="30"
								type="password" /></td>
						<td><font color="#CC0000"> * </font><form:errors path="passwordConfirm" style="color:red" /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><form:input path="email" size="30" type="text" /></td>
						<td><font color="#CC0000"> * </font><form:errors path="email" style="color:red" /></td>
					</tr>
					<tr>
						<td>First name</td>
						<td><form:input path="firstName" size="30" type="text" /></td>
						<td><font color="#CC0000"> * </font><form:errors path="firstName" style="color:red" /></td>
					</tr>
					<tr>
						<td>Last name</td>
						<td><form:input path="lastName" size="30" type="text" /></td>
						<td><font color="#CC0000"> * </font><form:errors path="lastName" style="color:red" /></td>
					</tr>
					<tr>
						<td>Birthday(yyyy-mm-dd)</td>
						<!-- required="required" value="yyyy-mm-dd"-->
						<td><form:input path="birthday" size="30" type="text" /></td>
						<td><font color="#CC0000"> * </font><form:errors path="birthday" style="color:red" /></td>
					</tr>
					<tr>
					<td colspan='3'>
						<script type="text/javascript"
							src="http://api.recaptcha.net/challenge?k=<6LftC-YSAAAAABDdxcCPOyEkJ4Mb4i4uE6rrSUah>">
                    </script>
						<noscript>
							<iframe
								src="http://api.recaptcha.net/noscript?k=<6LftC-YSAAAAABDdxcCPOyEkJ4Mb4i4uE6rrSUah>"
								height="300" width="500" frameborder="0"></iframe>
							</br> <input type="text" name="recaptcha_challenge_field"
								rows="3" cols="40">
							</textarea>
							<input type="hidden" name="recaptcha_response_field"
								value="manual_challenge">
						</noscript>
						</td>
					</tr>
					<tr>
						<td align="left"><input type=submit name="Ok" value=" Ok ">
							<input type="button" value=" Cancel "
							onclick="location.href='${app}/index'"></td>
					</tr>
				</table>
				<c:if test="${errorCaptcha != null}">
					<p style="color: red; font-size: 20px;">Error - ${errorCaptcha}</p>
				</c:if>

			</form:form>
		</div>
	</div>
</body>
</html>