<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>계정 삭제</title>
</head>
<body>
<h1>계정 삭제</h1>
<form action="unregisterOk" method="post">
    <label>
        비밀번호:
        <input type="password" name="password" required>
    </label><br>
    <button type="submit">계정 삭제</button>
</form>
</body>
</html>