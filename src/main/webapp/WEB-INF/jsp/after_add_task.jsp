<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>Manage Courses</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/manage_courses">
</head>
<body>


<table>
	<thead>
	<tr>
		<th>Номер задания</th>
		<th>Название задания</th>
		<th>Условие задания</th>
	</tr>
	</thead>

	<tbody>


	<tr>
		<td>Задание 1</td>
		<td>Java основы</td>
		<td>Разработать консольное приложение для проверки числа на то, является ли оно полиндромом или нет.</td>
	</tr>
	<tr>
		<td>Задание 2</td>
		<td>Java</td>
		<td>Разработать консольное ООП приложение, используя основные принципы ооп и соблюдая code conventions.</td>
	</tr>
	<tr>
		<td>Задание 3</td>
		<td>Java</td>
		<td>Разработать простое crud приложение.</td>
	</tr>
	<tr>
		<td>Задание 4</td>
		<td>Java</td>
		<td>Разработать сложное crud приложение.</td>
	</tr>

	</tbody>
</table>
<br>
<a class="waves-effect waves-light btn-large black" href="/controller?command=add_task">Add task</a>



<%--	<table>--%>
<%--		<thead>--%>
<%--		<tr>--%>
<%--			<th>Course title</th>--%>
<%--			<th>Learning language</th>--%>
<%--			<th>Mentor name</th>--%>
<%--		</tr>--%>
<%--		</thead>--%>

<%--		<tbody>--%>

<%--		<c:forEach var="course" items="${requestScope.courses}">--%>
<%--			<tr>--%>
<%--				<td>${course.title}</td>--%>
<%--				<td>${course.learning_language}</td>--%>
<%--				<td>${course.mentor.pen_name}</td>--%>
<%--			</tr>--%>
<%--		</c:forEach>--%>


<%--		</tbody>--%>
<%--	</table>--%>
<%--	<c:forEach var="course" items="${requestScope.courses}">--%>
<%--		<div class="valign-wrapper">--%>
<%--			<div class="row">--%>
<%--				<div>--%>
<%--					<div class="card black darken-1 crad-size">--%>
<%--						<div class="card-content white-text">--%>
<%--							<span class="card-title">${course.title}</span>--%>
<%--							<p>${course.description}</p>--%>

<%--							<br>--%>
<%--							<hr>--%>
<%--							<br>--%>
<%--							<p><span>Курсы ведет ментор: </span>${course.mentor.pen_name}</p>--%>
<%--							<p><span>Позиция ментора:</span>${course.mentor.position}</p>--%>
<%--							<p>Опыт работы:  <span>${course.mentor.experience}</span>--%>
<%--								<span>года</span></p>--%>

<%--						</div>--%>
<%--						<div class="card-action">--%>
<%--							<a href="#">Register</a>--%>
<%--							<a href="#">Execute</a>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--	</c:forEach>--%>

</body>

<script src="../js/manage_courses.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</html>
