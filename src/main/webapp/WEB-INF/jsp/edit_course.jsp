<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title>Add course form</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link rel="stylesheet" href="../css/add_course.css">
	<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
</head>

<div class="main-w3layouts wrapper">
	<h1>Update Course Form</h1>
	<div class="main-agileinfo">
		<div class="agileits-top">
			<form name="course-form" action="/controller?command=submit_edit_course&&id=${course.id}" method="post">
				<input class="text" type="text" name="title" placeholder="Title" required value="${course.title}">

				<br><br>

				<select name="learning_language" id="learning_language" class="text"
				        value="<c:out value='${course.learningLanguage}'/>" required>
					<option value="Java">Java</option>
					<option value="HTML">HTML</option>
					<option value="CSS">CSS</option>
					<option value="JS">JS</option>
					<option value="Python">Python</option>
					<option value="C++">C++</option>
					<option value="C">C</option>
					<option value="Angular">Angular</option>
					<option value="React">React</option>
					<option value="Ruby">Ruby</option>
				</select>

				<br><br>

				<select name="mentor" id="mentor" class="text" value="Dziana Bahdanava" required>
					<option value="Dziana Bahdanava">Dziana Bahdanava</option>
				</select>

				<input class="text w3lpass" type="text" name="description" placeholder="description" required>

				<input type="submit" value="Update course">
				<p><a href="/controller?command=manage_courses"> Return to the all courses page!</a></p>
			</form>
		</div>
	</div>

	<ul class="colorlib-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script type="application/x-javascript"> addEventListener("load", function () {
    setTimeout(hideURLbar, 0);
}, false);

function hideURLbar() {
    window.scrollTo(0, 1);
} </script>
</body>
</html>
