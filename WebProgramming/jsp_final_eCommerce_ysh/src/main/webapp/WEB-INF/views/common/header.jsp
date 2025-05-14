<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="py-3 border-bottom">
    <div class="container d-flex justify-content-between align-items-center">
        <!-- 좌측: 로고 영역 -->
        <div class="logo">
            <a href="index.do" class="text-decoration-none">
                <!-- 이미지 로고 (주석 처리) -->
                <%-- <img src="assets/images/logo.png" alt="쇼핑몰 로고" height="40"> --%>
                
                <!-- 텍스트 로고 -->
                <h1 class="fs-3 fw-bold text-primary m-0">쇼핑몰</h1>
            </a>
        </div>
        
        <!-- 우측: 사용자 계정 메뉴 -->
        <div class="user-menu">
            <!-- 로그인 상태에 따라 다른 메뉴 표시 -->
            <c:choose>
                <c:when test="${empty sessionScope.loginUser}">
                    <!-- 비로그인 상태: 로그인, 회원가입 버튼 -->
                    <a href="login.do" class="btn btn-outline-primary me-2">로그인</a>
                    <a href="register.do" class="btn btn-primary">회원가입</a>
                </c:when>
                <c:otherwise>
                    <!-- 로그인 상태: 사용자 이름, 마이페이지, 로그아웃 버튼 -->
                    <span class="me-3">${sessionScope.loginUser.userName}님 환영합니다</span>
                    <a href="mypage.do" class="btn btn-outline-primary me-2">마이페이지</a>
                    <c:if test="${sessionScope.loginUser.userType == '20'}">
                        <a href="adminPage.do" class="btn btn-warning me-2">관리자 메뉴</a>
                    </c:if>
                    <a href="logout.do" class="btn btn-primary">로그아웃</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>