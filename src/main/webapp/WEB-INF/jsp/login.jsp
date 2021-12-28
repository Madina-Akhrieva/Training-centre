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
            <form name="signup-form" action="/controller?command=signup" method="post">
                <h1>Create Account</h1>
                <input id="signup-input" type="email" placeholder="Email (from 7-50 symbols available)"  name="email"  maxlength="50" minlength="7"
                       required=""/>
                <input id="password-input" type="password" placeholder="Password (from 7-50 symbols available)" name="password"  maxlength="50"
                       minlength="7"  required=""/>

                <input id="signup-input" type="text" placeholder="Firstname (from 2-50 symbols available)"
                       name="firstname"  maxlength="50"
                       minlength="2"  required=""/>

                <input id="signup-input" type="text" placeholder="Lastname (from 2-50 symbols available)"
                       name="lastname"  maxlength="50"
                       minlength="2"  required=""/>

                <input id="signup-input" type="phone" placeholder="Phone example: +375XXXXXXXXX"
                       name="phone"  maxlength="12"
                       minlength="12"  required=""/>
                <c:if test="${not empty requestScope.errorLoginPassMessage}">

                    <p style="color:red">
                            ${requestScope.errorLoginPassMessage}
                    </p>

                </c:if>
                <button type="submit">Sign Up</button>
            </form>
        </div>



        <div class="form-container sign-in-container">
            <form name="login-form" action="/controller?command=login" method="post">
                <h1>Sign in</h1>
                <input type="email" placeholder="Email (from 7-50 symbols available)" name="email" maxlength="50"
                       minlength="7" required=""/>
                <input type="password" placeholder="Password (from 7-50 symbols available)" name="password" maxlength="50" minlength="7"
                       required="" />
                <c:if test="${not empty requestScope.errorLoginPassMessage}">

                        <p style="color:red">
                          ${requestScope.errorLoginPassMessage}
                        </p>

                </c:if>

                <button type="submit">Sign In</button>
            </form>
        </div>


        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel  overlay-left">
                    <h1>Welcome Back!</h1>
                    <p>To keep connected with us please login with your personal info ♥</p>
                    <button class="ghost" id="signIn">Sign In</button>
                </div>
                <div class="overlay-panel  overlay-right">
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
