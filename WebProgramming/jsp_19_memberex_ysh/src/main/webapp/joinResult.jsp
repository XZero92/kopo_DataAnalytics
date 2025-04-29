<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 결과</title>
</head>
<body>
<h1>회원가입이 완료되었습니다!</h1>
<p>아이디: <%= request.getAttribute("id") %></p>
<p>이름: <%= request.getAttribute("username") %></p>
<p>이메일: <%= request.getAttribute("email") %></p>
<p>전화번호: <%= request.getAttribute("mobile") %></p>
<p>성별: <%= request.getAttribute("gender") %></p>
<a href="index.jsp">메인 페이지로 이동</a>
</body>
</html>