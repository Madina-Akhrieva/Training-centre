<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 17.11.2021
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>Please log in:</h3>
    <form name="login-form" action="/controller?command=login" method="post">
        <label for="login-input">Login:</label>
        <input id="login-input" type="text" name="login" value=""/>
        <br>
        <label for="password-input">Password:</label>
        <input id="password-input" type="password" name="password" value=""/>
        <br/>
        <c:if test="${not empty requestScope.errorLoginPassMessage}">
            <b>${requestScope.errorLoginPassMessage}</b>
            <br>
        </c:if>
        <input type="submit" value="Log in"/>
    </form>
</body>
</html>