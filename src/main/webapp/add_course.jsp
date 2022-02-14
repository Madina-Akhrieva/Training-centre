<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.update_course" var="loc"/>
<fmt:message bundle="${loc}" key="label.update_course_title" var="updateCourseTitle"/>
<fmt:message bundle="${loc}" key="label.title_add_course" var="addCourseTitle"/>
<fmt:message bundle="${loc}" key="label.title_placeholder" var="title_placeholder"/>
<fmt:message bundle="${loc}" key="label.course_description_placeholder" var="course_description_placeholder"/>
<fmt:message bundle="${loc}" key="label.update_course_button" var="button_update_course"/>
<fmt:message bundle="${loc}" key="label.return_message" var="return_message"/>
<fmt:message bundle="${loc}" key="label.learning_language" var="learning_language"/>
<fmt:message bundle="${loc}" key="label.mentor_name" var="mentor_name"/>
<fmt:message bundle="${loc}" key="label.add_course_button" var="add_course_button"/>
<html>
<head>
	<title>${addCourseTitle}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link rel="stylesheet" href="../css/add_course.css">
	<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
</head>


</div>
<div class="main-w3layouts wrapper">
	<h1>${addCourseTitle}</h1>
	<c:choose>
		<c:when test="${not empty sessionScope.emptyInputsMessage}">
			<p style="color:red; text-align: center">${sessionScope['emptyInputsMessage']}</p>
			${sessionScope.remove('emptyInputsMessage')}
		</c:when>
		<c:when test="${not empty sessionScope.wrongDescriptionAttribute}">
			<h6 style="color:red; text-align: center">${sessionScope['wrongDescriptionAttribute']}</h6>
			${sessionScope.remove('wrongDescriptionAttribute')}
		</c:when>
		<c:when test="${not empty sessionScope.wrongTitleAttribute}">
			<h6 style="color:red; text-align: center">${sessionScope['wrongTitleAttribute']}</h6>
			${sessionScope.remove('wrongTitleAttribute')}
		</c:when>

		<c:otherwise>
			<h6 style="color:#1aff00; text-align: center;">${sessionScope.successfulSignupMessage}</h6>
		</c:otherwise>
	</c:choose>
	<div class="main-agileinfo">
		<div class="agileits-top">
			<form name="course-form" action="/controller?command=add_course" method="post">
				<input class="text" type="text" name="title"
				       maxlength="70" minlength="2"
				       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$"
				       required
				       placeholder=${title_placeholder}
				>

				<br><br>

				<select name="learning_language" id="learning_language" class="text" required>
					<option value="">${learning_language}</option>
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

				<select  name="mentor" id="mentor" class="text" required>
					<option value="">${mentor_name}</option>
					<option value="Dziana Bahdanava">Dziana Bahdanava</option>
				</select>

				<input class="text w3lpass" type="text" name="description"
				       maxlength="70" minlength="2"
				       required
				       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$"
				       placeholder=${course_description_placeholder}
				       >

				<input type="submit" value${add_course_button}>
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
</body>
</html>
