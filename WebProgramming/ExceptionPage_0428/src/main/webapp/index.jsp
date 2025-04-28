<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="errorpage.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<%
    String value = session.getAttribute("id").toString();
%>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>