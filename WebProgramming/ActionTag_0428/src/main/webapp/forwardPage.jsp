<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 25. 4. 28.
  Time: 오전 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    String id, paswd;
%>
<%
    id = request.getParameter("id");
    paswd = request.getParameter("paswd");
%>
  <h1>forwardPage 입니다.</h1>
    <h1>id : <%= id %></h1>
    <h1>paswd : <%= paswd %></h1>
<jsp:include page="includePage.jsp" flush="true"/>
  <h1>다시 forwardPage 입니다.</h1>
</body>
</html>
