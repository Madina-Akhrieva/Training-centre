<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.epam.jwd.onlinetraining.dao.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" />
<%--<fmt:setLocale value="${cookie.lang.value}" />--%>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title" var="title"/>
<fmt:message bundle="${loc}" key="label.manage_courses" var="manageCourses"/>
<fmt:message bundle="${loc}" key="label.link.login" var="login"/>
<fmt:message bundle="${loc}" key="label.link.logout" var="logout"/>
<fmt:message bundle="${loc}" key="label.link.sign_up" var="signUp"/>
<fmt:message bundle="${loc}" key="label.link.watch_profile" var="watchProfile"/>

<html>
<head>
	<title>${title}</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<link rel="stylesheet" href="../css/main.css">

</head>
<body>


<nav class="black">
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">
						<li><a href="<c:url value="/controller?command=manage_courses"/>">${manageCourses}</a></li>
					</c:if>
					<li><a href="/controller?command=logout">${logout}</a></li>
					<li><a href="<c:url value="/controller?command=show_profile"/>">${watchProfile}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="/controller?command=show_login"/>">${login}</a></li>
					<li><a href="<c:url value="/controller?command=show_signup"/>">${signUp}</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>

<div class="after_nav">
	<div class="typing animate"></div>

	<c:choose>
		<c:when test="${not empty sessionScope.account}">
			<c:if test="${not empty sessionScope.account}">
				<p><span>Start learning, </span> ${sessionScope.account.email} <span>!</span></p>

			</c:if>
		</c:when>
		<c:otherwise>
			<em style="color: darkgrey">Для прохождения курсов необходимо зарегистрироваться ♥</em>
		</c:otherwise>
	</c:choose>

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
							<p><span>Курсы ведет ментор: </span>${course.mentor.pen_name}</p>
							<p><span>Позиция ментора:</span>${course.mentor.position}</p>
							<p>Опыт работы: <span>${course.mentor.experience}</span>
								<span>года</span></p>

						</div>

						<div class="card-action">
							<c:choose>
								<c:when test="${not empty sessionScope.account}">
									<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">
										<a href="/controller?command=add_task">Download task</a>
										<a href="/controller?command=check_task">Check task</a>
									</c:if>
									<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.STUDENT}">
										<a href="/controller?command=complete_task&&id=${course.id}">Complete tasks</a>
									</c:if>
								</c:when>

							</c:choose>
							<a href="/controller?command=watch_tasks">Watch task</a>
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
				<h5 class="orange-text">Footer Content</h5>
					content.</p>
			</div>
			<div class="col l4 offset-l2 s12">

			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">
			© 2021 Training centre ♥

				<script src="../js/main.js"></script>
				<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


</body>
</html>
