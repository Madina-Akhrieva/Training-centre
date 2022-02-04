<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Manage Courses</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/manage_courses.css">
	<link rel="stylesheet" href="../css/main.css">
</head>
<body style="background-color: #ccc">
<nav class="black">
	<div class="nav-wrapper">
		TRAINING CENTRE ♥
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
		<p>${requestScope.DELETED_MESSAGE_ATTRIBUTE}</p>

	</div>
</nav>
<div>

	<div style="width: 1000px; margin: 0 auto">
		<div>
			<table>
				<thead>
				<tr>
					<th>Task title</th>
					<th>Task description</th>
					<th>Link to answer</th>
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
						<button class="waves-effect waves-light btn-small black"  type="submit">
							<a style="color: #ffb507" href="/controller?command=show_add_answer_page&course_id=${requestScope.id}&user_id
			=${sessionScope.account.id}&task_id=${task.id}">
								Add answer ♥
							</a>
						</button>
					</td>
				</tr>
				</tbody>
				</c:forEach>

			</table>
		</div>
		<br>
		<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">
			<a class="waves-effect waves-light btn-small black"
			   href="/controller?command=show_add_task&id=${courseId}">Add task ♥</a>
		</c:if>

	</div>
</div>
<footer class="page-footer black" >
	<h6 style="color: #ffb507; margin-left: 40px">
		With love your training centre ♥
	</h6>
	<div class="container">
		<div class="row">
			<div class="col l6 s12">
				<h5 class="orange-text">${joinUsMessage}</h5>
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">
			${withLoveTrainingCentreMessage}
		</div>
	</div>
</footer>
<script src="../js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>
