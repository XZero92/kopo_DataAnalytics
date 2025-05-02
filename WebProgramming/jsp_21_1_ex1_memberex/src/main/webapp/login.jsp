<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
<form action="loginOk" method="post">
    <label>
        아이디:
        <input type="text" name="userId" required>
    </label><br>
    <label>
        비밀번호:
        <input type="password" name="password" required>
    </label><br>
    <button type="submit">로그인</button>
</form>
</body>
</html>