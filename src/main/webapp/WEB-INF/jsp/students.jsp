<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.check_students" var="loc"/>
<fmt:message bundle="${loc}" key="label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="label.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="label.link_tasks" var="link_tasks"/>
<fmt:message bundle="${loc}" key="label.student_card" var="student_card"/>
<fmt:message bundle="${loc}" key="label.first_nem" var="first_nem"/>
<fmt:message bundle="${loc}" key="label.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="label.footer_text" var="footer_text"/>
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
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<c:if test="${not empty sessionScope.account && sessionScope.account.role eq Role.ADMIN}">

						<li><a href="/controller?command=show_accounts">Show users</a></li>
						<li><a href="/controller?command=manage_courses">Manage courses</a></li>
					</c:if>
					<li><a href="/controller?command=logout ">${logout}</a></li>
					<li><a href="/controller?command=main">${main_page}</a></li>
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

<div style="width: 500px; margin: 0 auto">
	<div>
		<c:forEach  var="student" items="${requestScope.students}">
			<div class="col s12 m7">
				<h6 class="header">${student_card}</h6>
				<div class="card horizontal">

					<div class="card-stacked">
						<div class="card-content">
							<p>${task.title}</p><br>
							<p>
								<span>${first_nem}</span>
								<span>${student.firstName}</span>
							</p>
							<p>
								<span>${last_name}</span>
								<span>${student.lastName}</span>
							</p>

						</div>
						<div class="card-action">
							<a
									href="${pageContext.request.contextPath}/controller
								?command=show_check_tasks_page
								&uid=${student.id}
								&id=${student.course.id}">${link_tasks}
							</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>


	</div>
	<br>
</div>

<footer class="page-footer black" >
	<h6 style="color: #ffb507; margin-left: 40px">
		${footer_text}
	</h6>
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


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script scr="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>

</html>
