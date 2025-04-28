<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.javabean_0428.memberBean" %>
<html>
<head>
    <title>회원 확인</title>
</head>
<body>
<jsp:useBean id="member" class="com.example.javabean_0428.memberBean" scope="session" />
<%
    if (member != null) {
%>
<h1>회원 정보 확인</h1>
<p>ID: <jsp:getProperty name="member" property="id" /></p>
<p>Name: <jsp:getProperty name="member" property="name" /></p>
<p>Email: <jsp:getProperty name="member" property="email" /></p>
<%
} else {
%>
<p>회원 정보가 없습니다.</p>
<%
    }
%>
</body>
</html>