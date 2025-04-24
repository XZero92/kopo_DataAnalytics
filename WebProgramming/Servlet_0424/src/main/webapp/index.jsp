<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<form action="FormEx" method="post">
    <label for="userName">이름:</label>
    <input type="text" id="userName" name="userName" required>
    <br>
    <label for="userID">ID:</label>
    <input type="text" id="userID" name="userID" required>
    <br>
    <label for="userPW">PW:</label>
    <input type="password" id="userPW" name="userPW" required>
    <br>
    <button type="submit">가입</button>
</form>
</body>
</html>