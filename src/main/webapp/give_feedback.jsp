<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwds" uri="jwd.epam.com" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.give_feedback" var="loc"/>
<fmt:message bundle="${loc}" key="label.feedback_title" var="feedback_title"/>
<fmt:message bundle="${loc}" key="label.feedback_placeholder" var="feedback_placeholder"/>
<fmt:message bundle="${loc}" key="label.send_feedback_button" var="send_feedback_button"/>
<fmt:message bundle="${loc}" key="label.answer_title" var="answer_title"/>
<fmt:message bundle="${loc}" key="label.footer_text" var="footer_text"/>
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
				<h5>${feedback_title}</h5>
				<c:choose>

					<c:when test="${not empty sessionScope.emptyInputsMessage}">
						<p style="color:red; text-align: center">${sessionScope['emptyInputsMessage']}</p>
						${sessionScope.remove('emptyInputsMessage')}
					</c:when>
					<c:when test="${not empty sessionScope.wrongFeedbackAttribute}">
						<h6 style="color:red; text-align: center">${sessionScope['wrongFeedbackAttribute']}</h6>
						${sessionScope.remove('wrongFeedbackAttribute')}
					</c:when>
					<c:otherwise>
						<h6 style="color:#1aff00; text-align: center;">${sessionScope.successfulAddMessage}</h6>
						${sessionScope.remove('successfulAddMessage')}

					</c:otherwise>
				</c:choose>
			</div>
			<hr>
			<form action="<c:url
			value="/controller?command=send_feedback&&course_id=${sessionScope['course_id']}&&user_id
			=${sessionScope['user_id']}&&task_id=${sessionScope['task_id']}"/>" method="post">

				<input name="feedback" style="width: 100%; text-align: left; height: 45%"
				       required
				       placeholder=${feedback_placeholder}

				/>


				<button type="submit" class="btn">${send_feedback_button}</button>
			</form>


			<div
					class="back-to-shop"><a href="<c:url value="/controller?command=main_page"/>">&leftarrow;</a><span
					class="text-muted">
				${footer_text}
			</span></div>
		</div>


		<div class="col-md-4 summary">
			<div class="title">
				<div class="row">
					<div class="col">
						<h5>${answer_title}</h5>
						<hr>
						<h6>${sessionScope.task.taskAnswer}</h6>

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
