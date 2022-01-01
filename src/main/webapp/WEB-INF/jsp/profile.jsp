<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Profile</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/profile.css">

</head>
<body>
<div class="card">
	<div class="row">
		<div class="col-md-8 cart">
			<div class="title">
				<div class="row">
					<div class="col">
						<h4><b>My courses ♥</b></h4>
					</div>
				</div>
			</div>
			<c:forEach var="course" items="${requestScope.courses}">
				<div class="row border-top border-bottom">
					<div class="row main align-items-center">
						<div class="col">
							<div class="row text-muted">${course.title}</div>
						</div>
						<div
								class="col"><a
								href="/controller?command=complete_task&&id=${course.id}&&uid=${sessionScope.account.id}">Compete
							tasks</a></div>
					</div>
				</div>
			</c:forEach>

			<div
					class="back-to-shop"><a href="/controller?command=main_page">&leftarrow;</a><span
					class="text-muted">Back	to main
				page</span></div>
			<c:if test="${not empty requestScope.isAddedMessage}">

				<p style="color:#ffb507">
						${requestScope.isAddedMessage}
				</p>

			</c:if>
		</div>


		<div class="col-md-4 summary">
			<div>
				<h5><b>My account</b></h5>
			</div>
			<hr>
			<div class="row">
				<div class="col" style="padding-left:0;">
					<span>${requestScope.user.firstName}</span>
				</div>
				<div class="col" style="padding-left:0;">
					<span>${requestScope.user.lastName}</span>
				</div>
				<div class="col" style="padding-left:0;">
					<span>${requestScope.user.phone}</span>
				</div>
			</div>

			<div class="row" style="border-top: 1px solid rgba(0,0,0,.1);color:white; padding: 2vh 0;">
					<button
							class="btn"><a style="color: white; text-decoration: none"
					                       href="/controller?command=logout">LOGOUT</a></button>
			</div>

		</div>
	</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script scr="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>
</html>












