<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Manage Courses</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/manage_courses.css">
	<link rel="stylesheet" href="../css/main.css">
</head>
<body>


<nav class="black">
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">
						<li><a href="/controller?command=show_accounts">Show users</a></li>
						<li><a href="/controller?command=manage_courses">Manage courses</a></li>
					</c:if>
					<li><a href="/controller?command=logout ">Logout</a></li>
					<li><a href="/controller?command=main">Main page</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/controller?command=show_login">Sign in</a></li>

					<li><a href="/controller?command=show_signup">Sign up</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
</nav>
<div style="width: 1000px; margin: 0 auto">

	<div>

		<table >
			<thead>
			<tr>
				<th>Course Title</th>
				<th>Learning language</th>
				<th>Mentor Pen-name</th>
			</tr>
			</thead>

			<tbody>
			<c:forEach var="course" items="${requestScope.courses}">
				<tr>
					<td>${course.title}</td>
					<td>${course.learningLanguage}</td>
					<td>${course.mentor.pen_name}</td>
					<td>
						<a class="waves-effect waves-light btn-small black"
						   href="/controller?command=edit_course&&id=${course.id}">Edit</a>

					</td>
					<td>
						<a class="waves-effect waves-light btn-small black"
						   href="/controller?command=delete_course&&id=${course.id}">Delete
						</a>
					</td>

<%--					<td>--%>
<%--						<a class="waves-effect waves-light btn-small black"--%>
<%--						   href="/controller?command=add_task&&id=${course.id}">Add task--%>
<%--						</a>--%>
<%--					</td>--%>

					<td>
						<a class="waves-effect waves-light btn-small black"
						   href="/controller?command=manage_tasks&&id=${course.id}">Manage tasks
						</a>
					</td>

				</tr>
			</c:forEach>

			</tbody>

		</table>
	</div>
	<br>
	<a class="waves-effect waves-light btn-small black"
	   href="/controller?command=show_add_course">Add course ♥</a>
</div>
<br>

</body>

<script src="../js/manage_courses.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</html>
