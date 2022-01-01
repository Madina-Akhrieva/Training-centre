<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Watch tasks</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/manage_courses.css">
	<link rel="stylesheet" href="../css/main.css">
</head>
<body style="background-color: #ccc">

<nav class="black">
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<li><a href="<c:url value="/controller?command=logout"/>">${logout}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="/controller?command=show_login"/>">${login}</a></li>
					<li><a href="<c:url value="/controller?command=show_signup"/>">${signUp}</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>
<div style="width: 1000px; margin: 0 auto">
	<div>
		<table style="width: 600px; margin: 0 auto; ">
			<thead>
			<tr>
				<th>Task title</th>
				<th>Task link</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="task" items="${requestScope.tasks}">
				<tr>
					<td>${task.title}</td>
					<td>
						<a href="${task.description}">${task.description}</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<br>
</div>
</body>
</html>
