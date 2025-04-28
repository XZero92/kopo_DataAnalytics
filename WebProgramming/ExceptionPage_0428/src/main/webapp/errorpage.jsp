<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%
    response.setStatus(404);
%>

<p>
    <%=exception.getMessage()%>
</p>

<a href="front.jsp">Get Back</a>