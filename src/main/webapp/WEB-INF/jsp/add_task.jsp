<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:choose>

		<c:when test="${not empty requestScope.wrongLinkAttribute}">
			<h6 style="color:red; text-align: center;">${requestScope.wrongLinkAttribute}</h6>
		</c:when>
		<c:when test="${not empty requestScope.wrongTitleAttribute}">
			<h6 style="color:red; text-align: center;">${requestScope.wrongTitleAttribute}</h6>
		</c:when>
		<c:otherwise>
			<h6 style="color:#1aff00; text-align: center;">${requestScope.successfulAddMessage}</h6>
		</c:otherwise>
	</c:choose>
	<div class="main-agileinfo">
		<div class="agileits-top">
			<form action="/controller?command=add_task&id=${courseId}" method="post">
				<input class="text" type="text" name="title"  placeholder="Title (2-70 symbols [0-9a-zA-Z .,'-])"
				       maxlength="70" minlength="2"
				       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$"
				       required="">
				<br><br>
				<input class="text" type="text" name="description" placeholder="Task link"
				       pattern = "(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})"
				       required="">

				<input type="submit" value="Add task">
				<p>
					<a href="/controller?command=manage_courses"> Return to the all courses
						page!</a></p>
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
