<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.epam.jwd.onlinetraining.dao.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwds" uri="jwd.epam.com" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.manage_courses" var="manageCourses"/>
<fmt:message bundle="${loc}" key="label.link.login" var="login"/>
<fmt:message bundle="${loc}" key="label.link.logout" var="logout"/>
<fmt:message bundle="${loc}" key="label.link.sign_up" var="signUp"/>
<fmt:message bundle="${loc}" key="label.link.watch_profile" var="watchProfile"/>
<fmt:message bundle="${loc}" key="label.message_before_signup" var="messageBeforeSignUp"/>
<fmt:message bundle="${loc}" key="label.mentor_name" var="mentorName"/>
<fmt:message bundle="${loc}" key="label.start_learning_message" var="startLearningMessage"/>
<fmt:message bundle="${loc}" key="label.mentor_position" var="mentorPosition"/>
<fmt:message bundle="${loc}" key="label.work_experience" var="workExperience"/>
<fmt:message bundle="${loc}" key="label.years" var="years"/>
<fmt:message bundle="${loc}" key="label.download_task" var="downloadTask"/>
<fmt:message bundle="${loc}" key="label.check_task" var="checkTask"/>
<fmt:message bundle="${loc}" key="label.complete_task" var="completeTask"/>
<fmt:message bundle="${loc}" key="label.watch_task" var="watchTask"/>
<fmt:message bundle="${loc}" key="label.join_us_message" var="joinUsMessage"/>
<fmt:message bundle="${loc}" key="label.with_love_training_centre_message" var="withLoveTrainingCentreMessage"/>
<fmt:message bundle="${loc}" key="label.rus_lang" var="rusLang"/>
<fmt:message bundle="${loc}" key="label.eng_lang" var="engLang"/>
<fmt:message bundle="${loc}" key="label.check_student" var="check_student"/>
<fmt:message bundle="${loc}" key="label.add_course" var="add_course"/>


<html>
<head>
	<title>${title}</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<link rel="stylesheet" href="../css/main.css">

</head>
<body style="background-color: #ccc">
<nav class="black">
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">

			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<c:if test="${sessionScope.account.role eq Role.ADMIN}">


						<li><a href="<c:url value="/controller?command=manage_courses"/>">${manageCourses}</a></li>
						<li><a href="<c:url value="/controller?command=logout"/>">${logout}</a></li>
					</c:if>
					<c:if test="${sessionScope.account.role eq Role.STUDENT}">
						<li><a
								href="/controller?command=show_profile&&id=${sessionScope.account.id}">
								${watchProfile}</a></li>
						<li><a href="<c:url value="/controller?command=logout"/>">${logout}</a></li>
					</c:if>
					<c:if test="${sessionScope.account.role eq Role.MENTOR}">
						<li><a href="<c:url value="/controller?command=logout"/>">${logout}</a></li>
					</c:if>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="/controller?command=show_login"/>">${login}</a></li>
					<li><a href="<c:url value="/controller?command=show_signup"/>">${signUp}</a></li>
				</c:otherwise>
			</c:choose>
			<li><button style="background-color: #ffb507; color:black; border: 1px solid black; border-radius: 5px;
			height: 30px;"
			            id="set_rus">${rusLang}</button></li>
			<li><button style="background-color: #ffb507; color:black; border: 1px solid black; height: 30px; border-radius: 5px"
			            id="set_eng">${engLang}</button></li>

		</ul>
	</div>
</nav>

<div class="after_nav">
	<div class="typing animate"></div>
	<jwds:welcomeUser text="${startLearningMessage}"/>
</div>

<div>
	<c:forEach var="course" items="${requestScope.courses}">
		<div class="valign-wrapper">
			<div class="row">
				<div>
					<div class="card black darken-1 crad-size">
						<div class="card-content white-text">
							<span class="card-title">${course.title}</span>
							<p>${course.description}</p>
							<br>
							<hr>
							<br>
							<p><span>${mentorName}</span>${course.mentor.pen_name}</p>
							<p><span>${mentorPosition}</span>${course.mentor.position}</p>
							<p>${workExperience}<span>${course.mentor.experience}</span>
								<span>${years}</span></p>

						</div>

						<div class="card-action">
							<c:choose>
								<c:when test="${not empty sessionScope.account}">
									<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">
									</c:if>
									<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.MENTOR}">
										<a href="/controller?command=manage_tasks&id=${course.id}">${downloadTask}</a>
										<a href="<c:url
										value="/controller?command=check_students&id=${course.id}"/>">${check_student}</a>
									</c:if>
									<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.STUDENT}">
										<a href="/controller?command=add_course_to_user&&course_id=${course.id}&&user_id=${sessionScope.account.id}">
											${add_course}
										</a>
									</c:if>
								</c:when>
							</c:choose>
							<a href="/controller?command=watch_tasks&&id=${course.id}">${watchTask}</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<footer class="page-footer black">
	<div class="container">
		<div class="row">
			<div class="col l6 s12">
				<h5 class="orange-text">${joinUsMessage}</h5>
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">
			${withLoveTrainingCentreMessage}

		</div>
	</div>
</footer>
			<script src="../js/main.js"></script>
			<script src="../js/change_lang.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
