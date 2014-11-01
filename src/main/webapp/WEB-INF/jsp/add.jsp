<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	
<c:set var="app" value="${pageContext.request.contextPath}" />


	<div align="center" style="padding-left: 300px;" id="addUserShow">
		<div align="right" style="margin-right: 20px"> 
			Admin 
			<sec:authentication property="principal.username"/>
			&nbsp;(<a href="<c:url value="/logout"/>">Logout</a>)
		</div>
		<div align="left" style="margin-left: 30px">
			<h1>Add user</h1>
		</div>
	<div align="left" style="margin-top: 20px">
	
			<form:form  method="get" id="addformUser" modelAttribute="template">
			
 				<table>
					<tr>
						<td>Login</td>
						<td><form:input path="login" size="30" type="text"  id="login"/></td>
						<td><font color="#CC0000"> * </font><span id="loginError"></span></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:input path="password" size="30" type="password" id="password"/></td>
						<td><font color="#CC0000"> * </font><span id="passwordError"></span></td>
					</tr>
					<tr>
						<td>Password again</td>
						<td><form:input path="passwordConfirm" size="30" type="password" id="passwordConfirm"/></td>
						<td><font color="#CC0000"> * </font><span id="passwordConfError"></span></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><form:input path="email" size="30" type="text" id="email"/></td>
						<td><font color="#CC0000"> * </font><span id="emailError"></span></td>
					</tr>
					<tr>
						<td>First name</td>
						<td><form:input path="firstName" size="30" type="text" id="firstName"/></td>
						<td><font color="#CC0000"> * </font><span id="firstNameError"></span> </td>
					</tr>
					<tr>
						<td>Last name</td>
						<td><form:input path="lastName" size="30" type="text" id="lastName"/></td>
						<td><font color="#CC0000"> * </font><span id="lastNameError"></span></td>
					</tr>
					<tr>
						<td>Birthday</td>
						<td><form:input path="birthday" size="30" type="text" id="birthday"/></td>
						<td><font color="#CC0000"> * </font><span id="birthdayError"></span></td>
					</tr>
					<tr>
						<td>Role</td>
						<td><form:select path="role" id="role">
								<form:option value="Admin" />
								<form:option value="User" />
							</form:select></td>
						<td><font color="#CC0000"> * </font></td>
					</tr> 
					<tr>
						<td align="left">
						<input  type="button" value="OK" onclick="addUser();">
							<input type="button" value=" Cancel "
							onclick="adminShow();"></td>
					</tr>
				</table>
				<c:if test="${wrong != null}">
					<p style="color: red; font-size: 20px;">Error - ${wrong}</p>
				</c:if>

			</form:form>
		</div>
		<div id="result"></div>
	</div>
