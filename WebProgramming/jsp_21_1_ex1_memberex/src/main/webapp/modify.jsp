
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원정보 수정</title>
</head>
<body>
<h1>회원정보 수정</h1>
<form action="modifyOk" method="post">
    <label>
        이름:
        <input type="text" name="username" required>
    </label><br>
    <label>
        이메일:
        <input type="email" name="email" required>
    </label><br>
    <label>
        전화번호:
        <input type="tel" name="mobile" required>
    </label><br>
    <button type="submit">수정하기</button>
</form>
</body>
</html>