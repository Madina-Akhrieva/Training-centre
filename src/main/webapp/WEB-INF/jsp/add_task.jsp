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
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!-- Custom Theme files -->
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link rel="stylesheet" href="../css/add_course.css">
	<!-- //Custom Theme files -->
	<!-- web font -->
	<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
	<!-- //web font -->
</head>
<body>
<!-- main -->
<div class="main-w3layouts wrapper">
	<h1>Create Task Form</h1>
	<div class="main-agileinfo">
		<div class="agileits-top">
			<form action="#" method="post">
				<input class="text" type="text" name="Username" placeholder="Title" required="">
				<input class="text email" type="text" name="email" placeholder="Task description" required="">
				<input class="text" type="text" name="password" placeholder="Student's answer" required="">
				<input class="text w3lpass" type="text" name="password" placeholder="Feedback" >
				<div class="wthree-text">
					<div class="clear"> </div>
				</div>
				<input type="submit" value="Add task">
				<p> <a href="/controller?command=after_add_task"> Return to the all tasks page!</a></p>
			</form>
		</div>
	</div>
	<!-- copyright -->

	<!-- //copyright -->
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
<!-- //main -->
</body>
</html>