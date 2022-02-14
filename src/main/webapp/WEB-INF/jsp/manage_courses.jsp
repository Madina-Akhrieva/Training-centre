<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.manage_courses" var="loc"/>
<fmt:message bundle="${loc}" key="label.logout" var="logoOut"/>
<fmt:message bundle="${loc}" key="label.main_page" var="mainPage"/>
<fmt:message bundle="${loc}" key="label.add_course" var="addCourse"/>
<fmt:message bundle="${loc}" key="label.edit" var="Edit"/>
<fmt:message bundle="${loc}" key="label.delete" var="Delete"/>
<fmt:message bundle="${loc}" key="label.manage_tasks" var="manageTasks"/>
<fmt:message bundle="${loc}" key="label.footer_text" var="footerText"/>
<fmt:message bundle="${loc}" key="label.course_title" var="courseTitle"/>
<fmt:message bundle="${loc}" key="label.learning_language" var="learningLanguage"/>
<fmt:message bundle="${loc}" key="label.mentor_pen_name" var="mentorPenName"/>
<html>
<head>
	<title>Manage Courses</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/manage_courses.css">
	<link rel="stylesheet" href="../css/main.css">
</head>
<body style="background-color: #ccc">
<nav class="black">
	<div class="nav-wrapper">
		TRAINING CENTRE ♥
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li style=" color: #1aff00; margin-right: 100px">
					<c:choose>
						<c:when test="${not empty sessionScope.successfulDeleteMessage}">
							<p style="color:#1aff00; text-align: center">${sessionScope['successfulDeleteMessage']}</p>
							${sessionScope.remove('successfulDeleteMessage')}
						</c:when>
						<c:when test="${not empty sessionScope.successfulEditMessage}">
							<h6 style="color:#1aff00; text-align: center">${sessionScope['successfulEditMessage']}</h6>
							${sessionScope.remove('successfulEditMessage')}
						</c:when>
						<c:otherwise>
							<h6 style="color:#1aff00; text-align: center;">${sessionScope.successfulAddMessage}</h6>
							${sessionScope.remove('successfulAddMessage')}

						</c:otherwise>
					</c:choose>
			</li>
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">
						<li><a href="/controller?command=show_accounts">Show users</a></li>
						<li><a href="/controller?command=manage_courses">Manage courses</a></li>
					</c:if>
					<li><a href="/controller?command=logout ">${logoOut}</a></li>
					<li><a href="/controller?command=main">${mainPage}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/controller?command=show_login">Sign in</a></li>
					<li><a href="/controller?command=show_signup">Sign up</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
		<p>${requestScope.DELETED_MESSAGE_ATTRIBUTE}</p>

	</div>
</nav>
<div style="width: 1000px; margin: 0 auto">

	<div>

		<table>
			<thead>
				<tr>
					<th>${courseTitle}</th>
					<th>${learningLanguage}</th>
					<th>${mentorPenName}</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="course" items="${requestScope.courses}">
				<tr>
					<td>${course.title}</td>
					<td>${course.learningLanguage}</td>
					<td>${course.mentor.pen_name}</td>
					<td>
						<a class="waves-effect waves-light btn-small black"
						   href="/controller?command=edit_course&&id=${course.id}">${Edit}</a>
					</td>
					<td>
						<a class="waves-effect waves-light btn-small black"
						   href="/controller?command=delete_course&&id=${course.id}">${Delete}
						</a>
					</td>
					<td>
						<a class="waves-effect waves-light btn-small black"
						   href="/controller?command=manage_tasks&&id=${course.id}">${manageTasks}
						</a>
					</td>
				</tr>
			</c:forEach>

			</tbody>

		</table>
	</div>
	<br>
	<a class="waves-effect waves-light btn-small black"
	   href="/controller?command=show_add_course">${addCourse}</a>
</div>
<footer class="page-footer black">
	<h6 style="color: #ffb507; margin-left: 40px">
		${footer_text}
	</h6>
	<div class="container">
		<div class="row">
			<div class="col l6 s12">
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">
			<h5 class="orange-text">${footerText}</h5>
		</div>
	</div>
</footer>

</body>

<script src="../js/manage_courses.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</html>
