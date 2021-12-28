<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Manage tasks</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/manage_courses.css">
	<link rel="stylesheet" href="../css/main.css">
</head>
<body>


<nav class="black">
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a href="/controller?command=logout ">Logout</a></li>
			<li><a href="/controller?command=manage_courses&&id=${task.id}">Manage courses page</a></li>
		</ul>
	</div>
</nav>
<div style="width: 1000px; margin: 0 auto">

	<div>

		<table>
			<thead>
			<tr>
				<th>Task Title</th>
				<th>Task description</th>
			</tr>
			</thead>

			<tbody>
			<c:forEach var="task" items="${requestScope.tasks}">
				<tr>
					<td>${task.title}</td>
					<td>
						<a href="${task.description}">${task.description}</a>
					</td>
					<td>
						<span class="waves-effect waves-light btn-small black">Follow link ♥</span>
					</td>
				</tr>
			</c:forEach>

			</tbody>

		</table>
	</div>
	<br>
	<a class="waves-effect waves-light btn-small black"
	   href="/controller?command=show_add_task&&id=${courseId}">Add task ♥</a>
</div>
<br>

</body>
</html>
