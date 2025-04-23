<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>폼 테스트</title>
</head>
<body>
<h1>폼 테스트</h1>
<form action="testServlet" method="post">
    <label for="name">이름:</label>
    <input type="text" id="name" name="name" required>
    <br>
    <label for="age">나이:</label>
    <input type="number" id="age" name="age" required>
    <br>
    <button type="submit">전송</button>
</form>
</body>
</html>