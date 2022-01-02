<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
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
						<h5><b>Complete task ♥</b></h5>
					</div>
				</div>
			</div>
			<form name="addtask-form"
			      action="<c:url value="/controller?command=send_answer&&course_id=${requestScope.course_id}&&user_id
			=${sessionScope.account.id}&&task_id=${requestScope.task_id}"/>" method="post" >
				<input name="answer"  placeholder="Enter link to answer:" />
				<input type="submit"
				        class="btn">SEND FEEDBACK</input>
			</form>
			<c:if test="${not empty requestScope.isAddedMessage}">
				<p style="color:#ffb507">
						${requestScope.isAddedMessage}
				</p>
			</c:if>
			<div
					class="back-to-shop"><a href="<c:url value="/controller?command=main_page"/>">&leftarrow;</a><span
					class="text-muted">Back	to main
				page</span></div>
		</div>


		<div class="col-md-4 summary">
			<div>
				<h5>Feedback from Dziana Bahdanava ♥</h5>
			</div>
			<hr>
			<form action="<c:url value="/controller?command=send_feedback&&course_id=${requestScope.course_id}&&user_id
			=${sessionScope.account.id}&&task_id=${requestScope.task_id}"/>" method="post">
				<textarea name="feedback" style="margin-left: 20px" placeholder="Feedback" rows="10" cols="30">
				</textarea>
				<input type="submit"
						class="btn">SEND FEEDBACK</input>
			</form>


		</div>
	</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script scr="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>
</body>
</html>
