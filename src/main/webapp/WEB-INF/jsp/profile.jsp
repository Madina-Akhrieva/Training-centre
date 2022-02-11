<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.profile" var="loc"/>
<fmt:message bundle="${loc}" key="label.my_course_title" var="my_course_title"/>
<fmt:message bundle="${loc}" key="label.complete_tasks_link" var="complete_tasks_link"/>
<fmt:message bundle="${loc}" key="label.back_message" var="back_message"/>
<fmt:message bundle="${loc}" key="label.my_account_title" var="my_account_title"/>
<fmt:message bundle="${loc}" key="label.logout_message" var="logout_message"/>
<fmt:message bundle="${loc}" key="label.first_nem" var="first_nem"/>
<fmt:message bundle="${loc}" key="label.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="label.phone_number" var="phone_number"/>
<html>
<head>
	<title>Profile</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="../css/profile.css">

</head>

<body>
<div class="card">
	<div class="row">
		<div class="col-md-8 cart">
			<div class="title">
				<div class="row">
					<div class="col">
						<h4><b>${my_course_title}</b></h4>
					</div>
				</div>
			</div>
			<c:forEach var="course" items="${requestScope.courses}">

				<div class="row border-top border-bottom">
					<div class="row main align-items-center">
						<div class="col">
							<div class="row text-muted">${course.title}</div>
						</div>
						<div class="col">
							<a href="/controller?command=complete_task&&id=${course.id}&&uid=${sessionScope.account.id}">
							${complete_tasks_link}
							</a>
						</div>
					</div>
				</div>
			</c:forEach>

			<div
					class="back-to-shop"><a href="/controller?command=main_page">&leftarrow;</a><span
					class="text-muted">${back_message}</span></div>
			<c:if test="${not empty requestScope.isAddedMessage}">

				<p style="color:#ffb507">
						${requestScope.isAddedMessage}
				</p>

			</c:if>
		</div>


		<div class="col-md-4 summary">
			<div>
				<h5><b>${my_account_title}</b></h5>
			</div>
			<hr>
			<div class="row">
				<div class="col" style="padding-left:0;">
					<span>${first_nem}</span>
				</div>
				<div class="col" style="padding-left:0;">
					<span>${requestScope.user.firstName}</span>
				</div>

			</div>
			<hr>
			<div class="row">

				<div class="col" style="padding-left:0;">
					<span>${last_name}</span>
				</div>
				<div class="col" style="padding-left:0;">
					<span>${requestScope.user.lastName}</span>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col" style="padding-left:0;">
					<span>${phone_number}</span>
				</div>
				<div class="col" style="padding-left:0;">
					<span>${requestScope.user.phone}</span>
				</div>
			</div>

			<div class="row" style="border-top: 1px solid rgba(0,0,0,.1);color:white; padding: 2vh 0;">
					<button
							class="btn"><a style="color: white; text-decoration: none"
					                       href="/controller?command=logout">${logout_message}</a></button>
			</div>

		</div>
	</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script scr="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

</body>
</html>












