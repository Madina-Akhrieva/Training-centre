<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Feedback page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/profile.css">
</head>
<body>

<div class="card">
	<div class="row">
		<div class="col-md-8 cart">
			<div>
				<h5>Feedback from Dziana Bahdanava ♥</h5>
				<c:choose>
					<c:when test="${not empty requestScope.wrongFeedbackAttribute}">
						<h6 style="color:red; text-align: center;">${requestScope.wrongFeedbackAttribute}</h6>
					</c:when>
					<c:otherwise>
						<h6 style="color:#1aff00; text-align: center;">${requestScope.successfulAddMessage}</h6>
					</c:otherwise>
				</c:choose>
			</div>
			<hr>
			<form action="<c:url value="/controller?command=send_feedback&&course_id=${requestScope.course_id}&&user_id
			=${requestScope.user_id}&&task_id=${requestScope.task_id}"/>" method="post">
				<textarea name="feedback" style="width: 100%; text-align: left; height: 45%">
					Enter feedback, please.
				</textarea>
				<button type="submit" class="btn">SEND FEEDBACK</button>
			</form>


			<div
					class="back-to-shop"><a href="<c:url value="/controller?command=main_page"/>">&leftarrow;</a><span
					class="text-muted">Back	to main
				page</span></div>
		</div>


		<div class="col-md-4 summary">
			<div class="title">
				<div class="row">
					<div class="col">
						<h5><b>Task answer is below ♥</b></h5>
						<h6>${requestScope.task.taskAnswer}</h6>

					</div>
				</div>
			</div>


		</div>
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script scr="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>

</html>
