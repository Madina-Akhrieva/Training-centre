<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 13.12.2021
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title>Add task form</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link rel="stylesheet" href="../css/add_course.css">
	<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
</head>
<body>
<!-- main -->
<div class="main-w3layouts wrapper">
	<h1>Add Task Form</h1>
	<div class="main-agileinfo">
		<div class="agileits-top">
			<form action="/controller?=command=submit_add_task&id=${course.id}" method="post">
				<input class="text" type="text" name="title" placeholder="Task title" required="">
				<input class="text" type="text" name="description" placeholder="Task description" required="">

				<input type="submit" value="Add task">
				<p> <a href="/controller?command=manage_courses"> Return to the all courses page!</a></p>
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
	</ul>
</div>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</body>
</html>
