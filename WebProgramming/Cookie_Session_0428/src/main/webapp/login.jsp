<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>로그인</title>
</head>
<body>
<form action="loginOK.jsp" method="post">
  <label for="userId">ID:</label>
  <input type="text" id="userId" name="userId" required>
  <br>
  <label for="password">비밀번호:</label>
  <input type="password" id="password" name="password" required>
  <br>
  <button type="submit">로그인</button>
</form>
</body>
</html>