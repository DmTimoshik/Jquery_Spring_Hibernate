<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="list" required="true" type="java.util.List"%>

<c:if  test="${!empty userList}">
        <c:forEach items="${userList}" var="user">
            <tr id="${user.login}">
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${113-user.birthday.year}</td>
                <td>${user.role.name}</td>
                <td><a href="#" onclick="editShow('${user.login}')">Edit   </a>&nbsp;  
                    <a href="#" onclick="deleteUser('${user.login}')">Delete</a></td>
            </tr>
        </c:forEach>
</c:if>