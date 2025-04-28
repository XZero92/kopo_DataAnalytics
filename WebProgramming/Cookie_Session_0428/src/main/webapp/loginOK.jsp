<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String userId = request.getParameter("userId");
  String password = request.getParameter("password");

  // 간단한 인증 로직
  if ("admin".equals(userId) && "1234".equals(password)) {
    // 세션 생성 및 사용자 정보 저장
    session = request.getSession();
    session.setAttribute("userId", userId);
    session.setAttribute("name", "관리자");
    session.setAttribute("permission", "admin");

    // 로그인 성공 시 welcome.jsp로 이동
    response.sendRedirect("welcome.jsp");
  } else {
    response.sendRedirect("login.jsp");
  }
%>