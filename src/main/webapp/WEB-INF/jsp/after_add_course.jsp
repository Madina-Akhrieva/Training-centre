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
		<th>Course Title</th>
		<th>Learning language</th>
		<th>Mentor Penname</th>
	</tr>
	</thead>

	<tbody>
	<c:forEach var="course" items="${requestScope.courses}">
		<tr>
			<td>${course.title}</td>
			<td>${course.learningLanguage}</td>
			<td>${course.mentor.pen_name}</td>
			<td>
				<a class="waves-effect waves-light btn-small black" href="/controller?command=after_edit_course">Edit</a>

			</td>
			<td>
				<a class="waves-effect waves-light btn-small black" href="/controller?command=after_delete_course">Delete
				</a>
			</td>
		</tr>
	</c:forEach>

	<tr>
		<td>Become the best Java developer!</td>
		<td>Java</td>
		<td>Dziana Bahdanava</td>
		<td>
			<a class="waves-effect waves-light btn-small black" href="/controller?command=edit_course">Edit</a>

		</td>
		<td>
			<a class="waves-effect waves-light btn-small black" href="/controller?command=after_delete_course">Delete
			</a>
		</td>
	</tr>
	</tbody>
</table>
<br>
<a class="waves-effect waves-light btn-large black" href="/controller?command=add_course">Add course</a>



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
