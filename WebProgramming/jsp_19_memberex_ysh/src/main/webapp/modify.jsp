<%@ page contentType="text/html; charset=UTF-8" %>
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
    성별:
    <input type="radio" id="genderM" name="gender" value="M" required>
    <label for="genderM">남성</label>
    <input type="radio" id="genderF" name="gender" value="F">
    <label for="genderF">여성</label>
    <input type="radio" id="genderU" name="gender" value="UNK">
    <label for="genderO">비공개</label><br>
    <button type="submit">수정하기</button>
</form>
</body>
</html>