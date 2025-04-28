<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP Session Example</title>
</head>
<body>
<h1>Session Example</h1>

<%
  // 세션 가져오기
  session = request.getSession();

  // 세션 속성 설정
  session.setAttribute("memberid", "admin");

  // 세션 유효 시간 설정 (초 단위, 5분)
  session.setMaxInactiveInterval(300);

  // 세션 속성 가져오기
  String memid = (String) session.getAttribute("memberid");

  // 세션 속성 이름 출력
  out.println("<h3>Session Attributes:</h3>");
  java.util.Enumeration<String> attributeNames = session.getAttributeNames();
  while (attributeNames.hasMoreElements()) {
    String attributeName = attributeNames.nextElement();
    out.println("<p>" + attributeName + ": " + session.getAttribute(attributeName) + "</p>");
  }

  // 특정 속성 제거
  session.removeAttribute("email");

  // 속성 제거 후 확인
  out.println("<h3>After Removing 'email' Attribute:</h3>");
  attributeNames = session.getAttributeNames();
  while (attributeNames.hasMoreElements()) {
    String attributeName = attributeNames.nextElement();
    out.println("<p>" + attributeName + ": " + session.getAttribute(attributeName) + "</p>");
  }
%>

<br/>
<a href="session.jsp">Refresh Page</a>
</body>
</html>