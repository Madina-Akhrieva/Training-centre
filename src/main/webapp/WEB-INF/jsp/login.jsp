<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Register ♥</title>
	<link rel="stylesheet" href="../css/login_signup.css">
</head>
<body>


<h2> Sign in/up Form</h2>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form name="login-form" action="/controller?command=login" method="post">
			<h1>Sign in</h1>
			<c:if test="${not empty requestScope.errorLoginPassMessage}">
				<p style="color:red">${requestScope.errorLoginPassMessage}</p>
			</c:if>
			<input type="email" placeholder="Email (from 7-50 symbols available)" name="email" maxlength="50"
			       minlength="7"  required=""/>
			<input type="password"  placeholder="Password symbols: a-z,A-Z,0-9" name="password" pattern="^[a-zA-Z0-9]+$"
			       maxlength="17" minlength="7" required=""/>
			<button type="submit">Sign In</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form name="signup-form" action="/controller?command=signup" method="post">
			<h1>Create Account</h1>
			<c:choose>
				<c:when test="${not empty requestScope.wrongPasswordMessage}">
					<p style="color:red">${requestScope.wrongPasswordMessage}</p>
				</c:when>
				<c:when test="${not empty requestScope.wrongMailExceptionMessage}">
					<p style="color:red">${requestScope.wrongMailExceptionMessage}</p>
				</c:when>
				<c:when test="${not empty requestScope.accountExistsExceptionMessage}">
					<p style="color:red">${requestScope.accountExistsExceptionMessage}</p>
				</c:when>
				<c:when test="${not empty requestScope.wrongPhoneAttribute}">
					<p style="color:red">${requestScope.wrongPhoneAttribute}</p>
				</c:when>
				<c:when test="${not empty requestScope.wrongFirstNameAttribute}">
					<p style="color:red">${requestScope.wrongFirstNameAttribute}</p>
				</c:when>
				<c:when test="${not empty requestScope.wrongLastNameAttribute}">
					<p style="color:red">${requestScope.wrongLastNameAttribute}</p>
				</c:when>
				<c:when test="${not empty requestScope.errorLoginPassMessage}">
					<p style="color:red">${requestScope.errorLoginPassMessage}</p>
				</c:when>
				<c:otherwise>
					<p style="color:#1aff00">${requestScope.successfulSignupMessage}</p>
				</c:otherwise>
			</c:choose>
			<input id="signup-input" type="email" placeholder="Email (from 7-50 symbols available)" name="email"
			       maxlength="50" minlength="7"  required=""/>
			<input id="password-input" type="password" maxlength="17" minlength="7" required=""
			       placeholder="Password symbols: a-z,A-Z,0-9" name="password" pattern="^[a-zA-Z0-9]+$"/>
			<input id="signup-input" type="text" placeholder="Firstname (from 2-50 symbols)"
			       name="firstname" maxlength="50" minlength="2" pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$" required=""/>
			<input id="signup-input" type="text" placeholder="Lastname (from 2-50 symbols)"
			       name="lastname" maxlength="50" minlength="2" pattern="^[a-zA-Z][0-9a-zA-Z .,'-]*$" required=""/>
			<input id="signup-input" type="tel" name="phone" placeholder="Use format: +375333333333"
			       name="phone" maxlength="13" minlength="13" pattern="[\+]375\d{2}\d{3}\d{2}\d{2}"
			       required=""/>
			<button type="submit">Sign Up</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel  overlay-left">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start studying journey with us ♥</p>
				<button class="ghost" id="signIn">Sign Up</button>
			</div>
			<div class="overlay-panel  overlay-right">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info ♥</p>
				<button class="ghost" id="signUp">Sign In</button>
			</div>
		</div>
	</div>
</div>
<script src="../js/login_signup.js"></script>
</body>
</html>
