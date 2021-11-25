<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/login_signup.css">
</head>
<body>
<%--    <h3>Please log in:</h3>--%>
<%--    <form name="login-form" action="/controller?command=login" method="post">--%>
<%--        <label for="login-input">Login:</label>--%>
<%--        <input id="login-input" type="text" name="login" value=""/>--%>
<%--        <br>--%>
<%--        <label for="password-input">Password:</label>--%>
<%--        <input id="password-input" type="password" name="password" value=""/>--%>
<%--        <br/>--%>
<%--        <c:if test="${not empty requestScope.errorLoginPassMessage}">--%>
<%--            <b>${requestScope.errorLoginPassMessage}</b>--%>
<%--            <br>--%>
<%--        </c:if>--%>
<%--        <input type="submit" value="Log in"/>--%>
<%--    </form>--%>


    <h2> Sign in/up Form</h2>
    <div class="container" id="container">
        <div class="form-container sign-up-container">
            <form name="login-form" action="/controller?command=login" method="post">
                <h1>Create Account</h1>
                <input type="email" placeholder="Email"  name="login"  />
                <input type="password" placeholder="Password" name="password"/>
                <button>Sign Up</button>
            </form>
        </div>

        <div class="form-container sign-in-container">
            <form action="#">
                <h1>Sign in</h1>
                <input type="email" placeholder="Email" />
                <input type="password" placeholder="Password" />
                <button>Sign In</button>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>Welcome Back!</h1>
                    <p>To keep connected with us please login with your personal info ♥</p>
                    <button class="ghost" id="signIn">Sign In</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>Hello, Friend!</h1>
                    <p>Enter your personal details and start studying journey with us ♥</p>
                    <button class="ghost" id="signUp">Sign Up</button>
                </div>
            </div>
        </div>
    </div>
    <script src="../js/login_signup.js"></script>
</body>
</html>
