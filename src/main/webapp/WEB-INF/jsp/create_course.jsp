<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 13.12.2021
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Create course</title>
</head>
<body>
<h3>Add course</h3>
<form method="post">
	<label>Name</label><br>
	<input name="name"/><br><br>
	<label>Price</label><br>
	<input name="price" type="number" min="100" /><br><br>
	<input type="submit" value="Save" />
</form>
</body>
</html>
