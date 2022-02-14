<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.add_task" var="loc"/>
<fmt:message bundle="${loc}" key="label.add_task_title" var="add_task_title"/>
<fmt:message bundle="${loc}" key="label.title_placeholder" var="title_placeholder"/>
<fmt:message bundle="${loc}" key="label.task_link_placeholder" var="task_link_placeholder"/>
<fmt:message bundle="${loc}" key="label.button_add_task" var="button_add_task"/>
<fmt:message bundle="${loc}" key="label.return_message" var="return_message"/>
<html>
<head>
	<title>${add_task_title}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link rel="stylesheet" href="../css/add_course.css">
	<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
</head>
<body>
<!-- main -->
<div class="main-w3layouts wrapper">
	<h1>${add_task_title}</h1>
	<c:choose>
		<c:when test="${not empty sessionScope.emptyInputsMessage}">
			<p style="color:red; text-align: center">${sessionScope['emptyInputsMessage']}</p>
			${sessionScope.remove('emptyInputsMessage')}
		</c:when>
		<c:when test="${not empty sessionScope.wrongTitleAttribute}">
			<h6 style="color:red; text-align: center">${sessionScope['wrongTitleAttribute']}</h6>
			${sessionScope.remove('wrongTitleAttribute')}
		</c:when>
		<c:when test="${not empty sessionScope.wrongLinkAttribute}">
			<h6 style="color:red; text-align: center">${sessionScope['wrongLinkAttribute']}</h6>
			${sessionScope.remove('wrongLinkAttribute')}
		</c:when>
		<c:otherwise>
			<h6 style="color:#1aff00; text-align: center;">${sessionScope.successfulAddMessage}</h6>
			${sessionScope.remove('successfulAddMessage')}
		</c:otherwise>
	</c:choose>
	<div class="main-agileinfo">
		<div class="agileits-top">
			<form action="/controller?command=add_task&id=${courseId}" method="post">
				<input class="text" type="text" name="title"
				       maxlength=70 minlength=2
				       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$"
				       required

				       placeholder=${title_placeholder}
				>
				<br><br>
				<input  class="text" type="text" name="description"
						        pattern = "(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})"
								       required
                       placeholder=${task_link_placeholder}

				>
				<br><br>

				<input type="submit" value=${button_add_task}>
				<p>
					<a href="/controller?command=manage_courses">
					${return_message}
					</a></p>
			</form>
		</div>
	</div>

	<ul class="colorlib-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</body>
</html>
