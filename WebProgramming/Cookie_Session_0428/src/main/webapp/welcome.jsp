<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  session = request.getSession(false); // 기존 세션 가져오기
  if (session == null || session.getAttribute("userId") == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  String userId = (String) session.getAttribute("userId");
  String name = (String) session.getAttribute("name");
  String permission = (String) session.getAttribute("permission");
%>
<html>
<head>
  <title>환영합니다</title>
</head>
<body>
<h1>환영합니다, <%= name %>님! (권한: <%= permission %>)</h1>
<a href="logout.jsp">로그아웃</a>
</body>
</html>