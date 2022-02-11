<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.watch_tasks" var="loc"/>
<fmt:message bundle="${loc}" key="label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="label.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="label.task_link" var="task_link"/>
<fmt:message bundle="${loc}" key="label.task_card_title" var="task_card_title"/>
<fmt:message bundle="${loc}" key="label.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="label.sign_up" var="sign_up"/>
<fmt:message bundle="${loc}" key="label.footer_text" var="footer_text"/>
<fmt:message bundle="${loc}" key="label.manage_courses" var="manage_courses"/>
<fmt:message bundle="${loc}" key="label.add_task" var="add_task"/>
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
				${sessionScope['successfulSignupMessage']}
				${sessionScope.remove('successfulSignupMessage')}

			</li>
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">

						<li><a href="/controller?command=show_accounts">Show users</a></li>
						<li><a href="/controller?command=manage_courses">${manage_courses}</a></li>
					</c:if>
					<li><a href="/controller?command=logout ">${logout}</a></li>
					<li><a href="/controller?command=main">${main_page}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/controller?command=show_login">${sign_in}</a></li>

					<li><a href="/controller?command=show_signup">${sign_up}</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
		<p>${requestScope.DELETED_MESSAGE_ATTRIBUTE}</p>

	</div>
</nav>
<div style="width: 500px; margin: 0 auto">
	<div style="width: 500px; margin: 0 auto">
		<div>
			<c:forEach var="task" items="${requestScope.tasks}">
				<div class="col s12 m7">
					<h6 class="header">${task_card_title}</h6>
					<div class="card horizontal">

						<div class="card-stacked">
							<div class="card-content">
								<p>${task.title}</p>
							</div>
							<div class="card-action">
								<a href="${task.description}">${task_link}</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<a class="waves-effect waves-light btn-small black"
			   href="/controller?command=show_add_task&&id=${courseId}">${add_task}</a>
		</div>
		<br>
	</div>

</div>

<footer class="page-footer black">
<%--	<h6 style="color: #ffb507; margin-right: 40px">--%>
<%--		${footer_text}--%>
<%--	</h6>--%>
	<div class="container">
		<div class="row">
			<div class="col l6 s12">
				<h5 class="orange-text">${joinUsMessage}</h5>
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">
			${footer_text}

		</div>
	</div>
</footer>
<script src="../js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
