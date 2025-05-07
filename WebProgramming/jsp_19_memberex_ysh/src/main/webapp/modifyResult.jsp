<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원정보 수정 결과</title>
</head>
<body>
<h1>회원정보가 성공적으로 수정되었습니다!</h1>
<p>아이디: ${requestScope.id}</p>
<p>이름: ${requestScope.username}</p>
<p>이메일: ${requestScope.email}</p>
<p>전화번호: ${requestScope.mobile}</p>
<p>성별: ${requestScope.gender}</p>
<a href="index.jsp">메인 페이지로 이동</a>
</body>
</html>