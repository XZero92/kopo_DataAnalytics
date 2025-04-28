<%--
  Created by IntelliJ IDEA.
  User: kopo
  Date: 25. 4. 28.
  Time: 오전 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Cookie[] cookies = request.getCookies();

  for(int i =0; i < cookies.length; i++){
    out.println(cookies[i].getName() + " : " + cookies[i].getValue() + "<br>");
  }
%>