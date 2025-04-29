<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session = request.getSession(false);
    if (session != null) {
        session.invalidate(); // 세션 무효화
    }
    response.sendRedirect("index.jsp");
%>
