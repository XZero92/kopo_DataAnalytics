<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.jsp_21_1_ex1_memberex.model.MemberDTO" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 관리 시스템 - 관리자 페이지</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <style>
        body {
            padding-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <header class="pb-3 mb-4 border-bottom d-flex justify-content-between align-items-center">
        <h1 class="display-6">관리자 페이지</h1>
        <a href="<%=request.getContextPath()%>/main.jsp" class="btn btn-outline-secondary">메인으로 돌아가기</a>
    </header>

    <%
        // 관리자 권한 확인 (이미 서블릿에서 체크했지만 추가 보안을 위해)
        session = request.getSession(false);
        String userType = (session != null) ? (String) session.getAttribute("userType") : null;
        String userName = (session != null) ? (String) session.getAttribute("userName") : null;
        
        if (!MemberDTO.USER_TYPE_ADMIN.equals(userType)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
    %>

    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-4">
            <h2 class="display-5 fw-bold">관리자 메뉴</h2>
            <p class="col-md-8 fs-4"><%= userName %> 님, 회원 관리 기능을 사용할 수 있습니다.</p>
            
            <div class="row mt-4">
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <div class="card-body text-center">
                            <h5 class="card-title">회원 관리</h5>
                            <p class="card-text">시스템에 등록된 회원들을 관리합니다.</p>
                            <a href="<%=request.getContextPath()%>/admin/userList" class="btn btn-success">전체 회원 목록</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <div class="card-body text-center">
                            <h5 class="card-title">가입 요청 관리</h5>
                            <p class="card-text">새로운 회원 가입 요청을 처리합니다.</p>
                            <a href="<%=request.getContextPath()%>/admin/userRequestList" class="btn btn-warning">가입 요청 목록</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <div class="card-body text-center">
                            <h5 class="card-title">탈퇴 회원 관리</h5>
                            <p class="card-text">탈퇴한 회원 정보를 관리합니다.</p>
                            <a href="<%=request.getContextPath()%>/admin/userDeletedList" class="btn btn-danger">탈퇴 회원 목록</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>