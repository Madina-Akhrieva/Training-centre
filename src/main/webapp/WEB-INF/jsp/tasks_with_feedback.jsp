<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.check_tasks" var="loc"/>
<fmt:message bundle="${loc}" key="label.logout" var="logout"/>
<fmt:message bundle="${loc}" key="label.main_page" var="main_page"/>
<fmt:message bundle="${loc}" key="label.task_title" var="task_title"/>
<fmt:message bundle="${loc}" key="label.task_description" var="task_description"/>
<fmt:message bundle="${loc}" key="label.link_to_answer" var="link_to_answer"/>
<fmt:message bundle="${loc}" key="label.button_feedback" var="button_feedback"/>
<fmt:message bundle="${loc}" key="label.footer_text" var="footer_text"/>

<html>
<head>
	<title>Tasks</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../css/manage_courses.css">
	<link rel="stylesheet" href="../css/main.css">
</head>
<body>


<nav class="black">
	<div class="nav-wrapper">
		<ul id="nav-mobile" class="right hide-on-med-and-down">


			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<li><a href="<c:url value="/controller?command=logout"/>">${logout}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="/controller?command=show_login"/>">${login}</a></li>
					<li><a href="<c:url value="/controller?command=show_signup"/>">${signUp}</a></li>
				</c:otherwise>
			</c:choose>
					<li><a href="<c:url value="/controller?command=logout"/>">${main_page}</a></li>
		</ul>
	</div>
</nav>
<div>

	<div style="width: 1000px; margin: 0 auto">
		<div>
			<table>
				<thead>
				<tr>
					<th>${task_title}</th>
					<th>${task_description}</th>
					<th>${link_to_answer}</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="task" items="${requestScope.tasks}">
				<tr>
					<td>${task.title}</td>
					<td>
							${task.description}
					</td>
					<td>
						<button class="waves-effect waves-light btn-small black" type="submit">
							<a style="color: #ffb507"
							   href="/controller?command=give_feedback&course_id=${requestScope.id}&user_id
			=${requestScope.uid}&task_id=${task.id}">
									${button_feedback}
							</a>
						</button>
					</td>
				</tr>
				</tbody>
				</c:forEach>


			</table>
		</div>

	</div>
</div>

<footer class="page-footer black">
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
			<script src="../js/main.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		</div>
	</div>
</footer>
</body>
</html>
