<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.javabean_0428.memberBean" %>
<html>
<head>
    <title>회원 처리</title>
</head>
<body>
<jsp:useBean id="member" class="com.example.javabean_0428.memberBean" scope="session" />
<jsp:setProperty name="member" property="id" param="id" />
<jsp:setProperty name="member" property="password" param="password" />
<jsp:setProperty name="member" property="name" param="name" />
<jsp:setProperty name="member" property="email" param="email" />

<%
    // 세션에 memberBean 저장
    session.setAttribute("member", member);

    // memberConfirm.jsp로 리다이렉트
    response.sendRedirect("memberConfirm.jsp");
%>
</body>
</html>