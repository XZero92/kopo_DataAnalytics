<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인 페이지</title>
</head>
<body>
<h1>메인 페이지</h1>
<%--<c:catch var="err">
    <%
        int result=10/0;
    %>
</c:catch>

<c:if test="${not empty err}">
    예외 발생: <c:out value="${err}"/>
</c:if>
<c:set var="userN" value="아무개" />
<p>이름: <c:out value="${userN}"/></p>

<c:choose>
    <c:when test="${userN == '홍길동'}">
        <p>홍길동입니다.</p>
    </c:when>
    <c:when test="${userN == '아무개'}">
        <p>아무개입니다.</p>
    </c:when>
    <c:otherwise>
        <p>다른사람입니다.</p>
    </c:otherwise>
</c:choose>
<c:set var="sports" value="${[['축구', '농구', '야구'], ['자유형', '평형', '접영']]}" />
<ul>
<c:forEach var="sport" items="${sports}">
    <c:forEach var="s" items="${sport}">
        <li>${s}</li>
    </c:forEach>
</c:forEach>
</ul>
<c:redirect url="redirectTest.jsp">
    <c:param name="directName" value="홍길동"/>
</c:redirect>--%>
<%--<c:url var="toModifyResult" value="/modifyResult.jsp">
    <c:param name="id" value="honggildong"/>
    <c:param name="username" value="홍길동"/>
    <c:param name="email" value="hong@example.com"/>
    <c:param name="mobile" value="01000001111"/>
    <c:param name="gender" value="M"/>
</c:url>--%>
<a href="${toModifyResult}">회원정보 수정 결과</a>
<%
    session = request.getSession(false);
    String userId = (session != null) ? (String) session.getAttribute("userId") : null;

    if (userId == null) {
%>
<a href="join.html">회원가입</a> |
<a href="login.html">로그인</a>
<%
} else {
%>
<h2>환영합니다, ${sessionScope.username} 님!</h2>
<a href="modify.jsp">회원정보 수정</a> |
<a href="logout.jsp">로그아웃</a>
<%
    }
%>
</body>
</html>