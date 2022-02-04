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


<nav class="black" >
	TRAINING CENTRE ♥
	<div class="nav-wrapper" style="color: white">
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li><a  href="<c:url value="/controller?command=logout"/>">Main page</a></li>

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
							${task.description}
					</td>
					<td>
						<button class="waves-effect waves-light btn-small black"  type="submit">
							<a style="color: #ffb507" href="/controller?command=give_feedback&course_id=${requestScope.id}&user_id
			=${requestScope.uid}&task_id=${task.id}">
								Give feedback ♥
							</a>
						</button>
					</td>
				</tr>
				</tbody>
				</c:forEach>


			</table>
		</div>

	</div>
</div>

<footer class="page-footer black">
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
			<script src="../js/main.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		</div>
	</div>
</footer>
</body>
</html>
