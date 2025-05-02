<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.jsp_21_1_ex1_memberex.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>메인 페이지</title>
</head>
<body>
<h1>메인 페이지</h1>
<%
    session = request.getSession(false);
    String userId = (session != null) ? (String) session.getAttribute("userId") : null;

    if (userId == null) {
%>
<a href="join.html">회원가입</a> |
<a href="login.html">로그인</a>
<%
    } else {
        String username = (String) session.getAttribute("username");
%>
<h2>환영합니다, <%= username %> 님!</h2>
<a href="modify.jsp">회원정보 수정</a> |
<a href="logout.jsp">로그아웃</a> <br/>
<%
        String userType = (String) session.getAttribute("userType");
        if (userType == MemberDTO.USER_TYPE_ADMIN) {
            // 관리자 메뉴
            %>
            <h3>관리자 메뉴</h3>
            <a href="adminPage.jsp">관리자 페이지</a> |
            <a href="userList.jsp">회원 목록</a> |
            <a href="userModify.jsp">회원 정보 수정</a> |
            <a href="userDelete.jsp">회원 삭제</a>
            <%
        }
    }
%>
</body>
</html>