<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="app" value="${pageContext.request.contextPath}" />

<c:set var="user" value="${currentUser}" />

	<div align="center" style="padding-left: 300px;" id="editUserShow">
		<div align="right" style="margin-right: 20px">
			Admin  
			<sec:authentication property="principal.username"/>
			&nbsp; (<a href="<c:url value="/logout"/>">Logout</a>)
		</div>
		<div align="left" style="margin-left: 30px">
			<h1>Edit user</h1>
		</div>
		<div align="left" style="margin-top: 20px" >
		<form:form  method="get" id="editformUser" modelAttribute="template">
				<table >
					<tr>
						<td>Login</td>
						<td>
							<form:input path="login" type="text" size="30"  readonly="true" style="background-color: #cccfac;"/>
							</td>
						<td><font color="#CC0000"> * </font></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:input path="password" size="30" type="password" id="passwordEdit"/></td>
						<td><font color="#CC0000"> * </font><span id="passwordErrorEdit"></span></td>
					</tr>
					<tr>
						<td>Password again</td>
						<td><form:input path="passwordConfirm" size="30" type="password" id="passwordConfirmEdit"/></td>
						<td><font color="#CC0000"> * </font><span id="passwordConfErrorEdit"></span></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><form:input path="email" size="30" type="text" id="emailEdit"/></td>
						<td><font color="#CC0000"> * </font><span id="emailErrorEdit"></span></td>
					</tr>
					<tr>
						<td>First name</td>
						<td><form:input path="firstName" size="30" type="text" id="firstNameEdit"/></td>
						<td><font color="#CC0000"> * </font><span id="firstNameErrorEdit"></span></td>
					</tr>
					<tr>
						<td>Last name</td>
						<td><form:input path="lastName" size="30" type="text" id="lastNameEdit"/></td>
						<td><font color="#CC0000"> * </font><span id="lastNameErrorEdit"></span></td>
					</tr>
					<tr>
						<td>Birthday(yyyy-mm-dd)</td>
						<td><form:input path="birthday" size="30" type="text" id="birthdayEdit"/></td>
						<td><font color="#CC0000"> * </font><span id="birthdayErrorEdit"></span></td>
					</tr>
					<tr>
						<td>Role</td>
						<td>
							<form:select path="role" id="role">
								<form:option value="User"/>
								<form:option value="Admin"/>
							</form:select></td>
					</tr>
					<tr>
						<td align="left">
						<input type="button" value="OK" onclick="editUser();">
						<input type="button" value=" Cancel "
							onclick="adminShow();"></td>
					</tr>
				</table>

				<c:if test="${wrong != null}">
					<p style="color: red; font-size: 20px;">Error - ${wrong}</p>
				</c:if>
			</form:form>

		</div>
	</div>
