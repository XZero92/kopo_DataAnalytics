<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<%
     Cookie cookie = new Cookie("memberId", "admin");
     response.addCookie(cookie);
%>
<jsp:include page="login.jsp" />
<jsp:include page="printCookies.jsp" />
</body>
</html>