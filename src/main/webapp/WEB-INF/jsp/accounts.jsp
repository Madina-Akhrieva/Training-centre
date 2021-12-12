<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Users</title>
</head>
<body>
<c:forEach var="account" items="${requestScope.accounts}">
	<table>
		<tr>
			<td>${account.email}</td>
			<td>${account.id}</td>
		</tr>
	</table>
</c:forEach>
</body>
</html>
