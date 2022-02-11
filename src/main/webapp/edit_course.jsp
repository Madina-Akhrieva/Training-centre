<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.update_course" var="loc"/>
<fmt:message bundle="${loc}" key="label.update_course_title" var="updateCourseTitle"/>
<fmt:message bundle="${loc}" key="label.title_placeholder" var="title_placeholder"/>
<fmt:message bundle="${loc}" key="label.course_description_placeholder" var="course_description_placeholder"/>
<fmt:message bundle="${loc}" key="label.update_course_button" var="button_update_course"/>
<fmt:message bundle="${loc}" key="label.return_message" var="return_message"/>
<html>
<head>
	<title>${updateCourseTitle}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link rel="stylesheet" href="../css/add_course.css">
	<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
</head>
<div class="main-w3layouts wrapper">
	<h1>${updateCourseTitle}</h1>
	<c:choose>
		<c:when test="${not empty sessionScope.wrongDescriptionAttribute}">
			<p style="color:red; text-align: center">${sessionScope['wrongDescriptionAttribute']}</p>
			${sessionScope.remove('wrongDescriptionAttribute')}
		</c:when>
		<c:when test="${not empty sessionScope.wrongTitleAttribute}">
			<p style="color:red; text-align: center">${sessionScope['wrongTitleAttribute']}</p>
			${sessionScope.remove('wrongTitleAttribute')}
		</c:when>
		<c:otherwise>
			<h6 style="color:#1aff00; text-align: center;">${sessionScope.successfulSignupMessage}</h6>
		</c:otherwise>
	</c:choose>
	<div class="main-agileinfo">
		<div class="agileits-top">
			<form name="course-form"
			      action="/controller?command=submit_edit_course&id=${sessionScope['courseId']}"
			      method="post">
				<input class="text" type="text" name="title" placeholder=${title_placeholder}
				       maxlength="70" minlength="2"
<%--				       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$" --%>
				       required value="${course.title}">
				<br><br>
				<select name="learning_language" id="learning_language" class="text"
				        value="<c:out value='${course.learningLanguage}'/>" required>
					<option value="Java">Java</option>
					<option value="HTML">HTML</option>
					<option value="CSS">CSS</option>
					<option value="JS">JS</option>
					<option value="Python">Python</option>
					<option value="C++">C++</option>
					<option value="C">C</option>
					<option value="Angular">Angular</option>
					<option value="React">React</option>
					<option value="Ruby">Ruby</option>
				</select>
				<br><br>
				<select name="mentor" id="mentor" class="text" value="Dziana Bahdanava" required>
					<option value="Dziana Bahdanava">Dziana Bahdanava</option>
				</select>
				<input class="text w3lpass" type="text" name="description"  placeholder=${course_description_placeholder}
				       maxlength="70" minlength="2"
<%--				       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$" --%>
				       required>
				<input type="submit" value=${button_update_course}>
				<p><a href="/controller?command=manage_courses"> ${return_message}</a></p>
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
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>

<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script type="application/x-javascript"> addEventListener("load", function () {
    setTimeout(hideURLbar, 0);
}, false);
function hideURLbar() {
    window.scrollTo(0, 1);
} </script>
</body>
</html>
