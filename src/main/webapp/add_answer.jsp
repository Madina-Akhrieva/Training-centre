<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Complete task ♥</title>
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
						<c:choose>
							<c:when test="${not empty sessionScope.wrongLinkAttribute}">
								<h6 style="color:red; text-align: center">${sessionScope['wrongLinkAttribute']}</h6>
								${sessionScope.remove('wrongLinkAttribute')}
							</c:when>
							<c:otherwise>
								<h6 style="color:#1aff00; text-align: center;">${sessionScope.successfulAddMessage}</h6>
								${sessionScope.remove('successfulAddMessage')}

							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<form name="addtask-form"
			      action="<c:url
			      value="/controller?command=send_answer&course_id=${sessionScope['course_id']}&user_id
			=${sessionScope.account.id}&task_id=${sessionScope['task_id']}"/>" method="post" >
				<input name="answer"
				       pattern = "(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})"
				       placeholder="Enter link to answer:" />
				<button type="submit"
				        class="btn">COMPLETE TASK</button>
			</form>
			<c:if test="${not empty sessionScope.isAddedMessage}">
				<p style="color:#ffb507">
						${sessionScope.isAddedMessage}
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
			</div><hr>
			<div>
				${sessionScope['feedback']}
			</div>
		</div>

	</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script scr="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>
</body>
</html>
