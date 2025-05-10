<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.jsp_21_1_ex1_memberex.model.MemberDTO" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 관리 시스템 - 메인</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <style>
        body {
            padding-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <header class="pb-3 mb-4 border-bottom">
        <h1 class="display-6">회원 관리 시스템</h1>
    </header>

    <%
        // 세션 확인
        session = request.getSession(false);
        String userId = (session != null) ? (String) session.getAttribute("userId") : null;
        String userName = (session != null) ? (String) session.getAttribute("userName") : null;
        String userType = (session != null) ? (String) session.getAttribute("userType") : null;
        
        // 로그인되지 않은 상태라면 index.jsp로 리다이렉트
        if (userId == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>

    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-4">
            <h2 class="display-5 fw-bold">안녕하세요, <%= userName %> 님!</h2>
            <p class="col-md-8 fs-4">회원 관리 시스템에 로그인되었습니다.</p>
            <div class="d-flex flex-wrap gap-2">
                <a href="modify.jsp" class="btn btn-primary">회원정보 수정</a>
                <a href="unregister.jsp" class="btn btn-outline-danger">회원 탈퇴</a>
                <a href="logout.jsp" class="btn btn-secondary">로그아웃</a>
                
                <% if (MemberDTO.USER_TYPE_ADMIN.equals(userType)) { %>
                <a href="admin/adminPage" class="btn btn-warning">관리자 페이지</a>
                <% } %>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>