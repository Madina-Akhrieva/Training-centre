<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.login" var="loc"/>
<fmt:message bundle="${loc}" key="label.sign_in_title" var="signInTitle"/>
<fmt:message bundle="${loc}" key="label.left.create_account" var="createAccount"/>
<fmt:message bundle="${loc}" key="label.left.mail_placeholder" var="mailPlaceholder"/>
<fmt:message bundle="${loc}" key="label.left.password_placeholder" var="passwordPlaceholder"/>
<fmt:message bundle="${loc}" key="label.left.firstname_placeholder" var="firstnamePlaceholder"/>
<fmt:message bundle="${loc}" key="label.left.lastname_placeholder" var="lastnamePlaceholder"/>
<fmt:message bundle="${loc}" key="label.left.phone_placeholder" var="phonePlaceholder"/>
<fmt:message bundle="${loc}" key="label.left.button.sign_up" var="signUpButton"/>
<fmt:message bundle="${loc}" key="label.right.welcome_back_title" var="welcomeBackTitle"/>
<fmt:message bundle="${loc}" key="label.right.text" var="text"/>
<fmt:message bundle="${loc}" key="label.right.button.sign_in" var="signInButton"/>
<fmt:message bundle="${loc}" key="label.hello_friend_message" var="helloFriendTitle"/>
<fmt:message bundle="${loc}" key="label.hello_text" var="welcomeText"/>
<fmt:message bundle="${loc}" key="label.sign_title" var="signTitle"/>



<html>
<head>
	<title>Register ♥</title>
	<link rel="stylesheet" href="../css/login_signup.css">
</head>
<body>
<h2> ${signInTitle}</h2>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form name="login-form" action="/controller?command=login" method="post">
			<h1>${signTitle}</h1>

			<input type="email" placeholder=${mailPlaceholder} name="email"
<%--			       maxlength="50"--%>
<%--			       minlength="7" required=""--%>
			/>
			<input type="password" placeholder=${passwordPlaceholder} name="password"
<%--			       pattern="^[a-zA-Z0-9]+$"--%>
<%--			       maxlength="17" minlength="7" required=""--%>
			/>
			<button type="submit">${signInButton}</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form name="signup-form" action="/controller?command=signup" method="post">
			<h1>${createAccount}</h1>
			<c:choose>
				<c:when test="${not empty sessionScope.errorLoginPassMessage}">
					<p style="color:red">${sessionScope['errorLoginPassMessage']}</p>
					${sessionScope.remove('errorLoginPassMessage')}
				</c:when>
				<c:when test="${not empty sessionScope.emptyInputsMessage}">
					<p style="color:red">${sessionScope['emptyInputsMessage']}</p>
					${sessionScope.remove('emptyInputsMessage')}
				</c:when>
				<c:when test="${not empty sessionScope.wrongPasswordMessage}">
					<p style="color:red">${sessionScope['wrongPasswordMessage']}</p>
					${sessionScope.remove('wrongPasswordMessage')}
				</c:when>
				<c:when test="${not empty sessionScope.wrongMailExceptionMessage}">
					<p style="color:red">${sessionScope['wrongMailExceptionMessage']}</p>
					${sessionScope.remove('wrongMailExceptionMessage')}
				</c:when>
				<c:when test="${not empty sessionScope.accountExistsExceptionMessage}">
					<p style="color:red">${sessionScope['accountExistsExceptionMessage']}</p>
					${sessionScope.remove('accountExistsExceptionMessage')}

				</c:when>
				<c:when test="${not empty sessionScope.wrongPhoneAttribute}">
					<p style="color:red">${sessionScope['wrongPhoneAttribute']}</p>
					${sessionScope.remove('wrongPhoneAttribute')}

				</c:when>
				<c:when test="${not empty sessionScope.wrongFirstNameAttribute}">
					<p style="color:red">${sessionScope['wrongFirstNameAttribute']}</p>
					${sessionScope.remove('wrongFirstNameAttribute')}

				</c:when>
				<c:when test="${not empty sessionScope.wrongLastNameAttribute}">
					<p style="color:red">${sessionScope['wrongLastNameAttribute']}</p>
					${sessionScope.remove('wrongLastNameAttribute')}
				</c:when>
				<c:when test="${not empty sessionScope.errorLoginPassMessage}">
					<p style="color:red">${sessionScope['errorLoginPassMessage']}</p>
					${sessionScope.remove('errorLoginPassMessage')}
				</c:when>

				<c:otherwise>
					<p style="color:#1aff00">${sessionScope['successfulSignupMessage']}</p>
				</c:otherwise>
			</c:choose>
			<input id="signup-input" type="email" placeholder=${mailPlaceholder}
			       maxlength="50" minlength="7" name="email" />
			<input id="password-input" type="password"
<%--			       maxlength="17" minlength="7"--%>
<%--			       required=""--%>
<%--			       pattern="^[a-zA-Z0-9]+$"--%>
			       placeholder=${passwordPlaceholder}
			       name="password"/>
			<input id="signup-input" type="text" placeholder=${firstnamePlaceholder}
			       name="firstname"
<%--			       maxlength="50" minlength="2"--%>
<%--			       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$"--%>
<%--			       required=""--%>
			/>
			<input id="signup-input" type="text" placeholder=${lastnamePlaceholder}
			       name="lastname"
<%--			       maxlength="50" minlength="2"--%>
<%--			       pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$"--%>
<%--			       required=""--%>
			/>
			<input id="signup-input" type="tel" name="phone" placeholder=${phonePlaceholder}
			       name="phone"
<%--			       maxlength="13" minlength="13"--%>
<%--			       pattern="[\+]375\d{2}\d{3}\d{2}\d{2}"--%>
<%--			       required=""--%>
			/>
			<button type="submit">${signUpButton}</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel  overlay-left">
				<h1>${helloFriendTitle}</h1>
				<p>${text}</p>
				<button class="ghost" id="signIn">${signUpButton}</button>
			</div>
			<div class="overlay-panel  overlay-right">
				<h1>${welcomeBackTitle}</h1>
				<p>${welcomeText}</p>
				<button class="ghost" id="signUp">${signInButton}</button>
			</div>
		</div>
	</div>
</div>
<script src="../js/login_signup.js"></script>
</body>
</html>
