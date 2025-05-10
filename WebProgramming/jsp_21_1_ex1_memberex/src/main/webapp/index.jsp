<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 관리 시스템</title>
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
        session = request.getSession(false);
        String userId = (session != null) ? (String) session.getAttribute("userId") : null;
        
        // 로그인 상태인 경우 main.jsp로 리다이렉트
        if (userId != null) {
            response.sendRedirect("main.jsp");
            return;
        }
    %>
    
    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-5">
            <h2 class="display-5 fw-bold">환영합니다</h2>
            <p class="col-md-8 fs-4">회원 관리 시스템에 오신 것을 환영합니다. 서비스를 이용하려면 로그인하거나 회원가입을 해주세요.</p>
            <a href="login.jsp" class="btn btn-primary btn-lg me-2">로그인</a>
            <a href="join.jsp" class="btn btn-secondary btn-lg">회원가입</a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>