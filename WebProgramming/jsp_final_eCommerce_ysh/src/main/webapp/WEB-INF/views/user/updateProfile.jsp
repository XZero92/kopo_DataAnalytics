<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>개인정보 수정 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Flatly 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <style>
        .mypage-menu {
            border-right: 1px solid #eee;
        }
        .mypage-menu .nav-link {
            color: #555;
            padding: 0.8rem 1rem;
            border-radius: 0;
        }
        .mypage-menu .nav-link.active {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #2C3E50;
            border-left: 3px solid #18BC9C;
        }
        .mypage-menu .nav-link:hover:not(.active) {
            background-color: #f8f9fa;
        }
        .section-title {
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    
    <div class="container mt-4">
        <h2 class="mb-4">마이페이지</h2>
        
        <div class="row">
            <!-- 왼쪽 메뉴 -->
            <div class="col-md-3 mypage-menu">
                <div class="d-flex flex-column">
                    <div class="p-3 bg-light">
                        <h5>${user.userName}님</h5>
                        <p class="mb-0 text-muted">${user.email}</p>
                    </div>
                    <div class="nav flex-column nav-pills">
                        <a class="nav-link" href="mypage.do">마이페이지 홈</a>
                        <a class="nav-link active" href="update_profile.do">개인정보 수정</a>
                        <a class="nav-link" href="change_password.do">비밀번호 변경</a>
                        <a class="nav-link" href="order_history.do">주문 내역</a>
                        <a class="nav-link text-danger" href="withdraw.do">회원 탈퇴</a>
                    </div>
                </div>
            </div>
            
            <!-- 오른쪽 콘텐츠 -->
            <div class="col-md-9">
                <div class="card">
                    <div class="card-body">
                        <h4 class="section-title">개인정보 수정</h4>
                        
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                                ${successMessage}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                ${errorMessage}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        
                        <form action="update_profile.do" method="post" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="userId" class="form-label">아이디(이메일)</label>
                                <input type="email" class="form-control" id="userId" name="userId" value="${user.userId}" readonly>
                                <small class="form-text text-muted">아이디는 변경할 수 없습니다.</small>
                            </div>
                            
                            <div class="mb-3">
                                <label for="userName" class="form-label">이름</label>
                                <input type="text" class="form-control" id="userName" name="userName" value="${user.userName}" required>
                                <div class="invalid-feedback">이름을 입력해주세요.</div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="mobileNo" class="form-label">휴대폰 번호</label>
                                <input type="text" class="form-control" id="mobileNo" name="mobileNo" value="${user.mobileNo}" placeholder="010-0000-0000" required>
                                <div class="invalid-feedback">휴대폰 번호를 입력해주세요.</div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="currentPassword" class="form-label">현재 비밀번호</label>
                                <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                                <div class="invalid-feedback">정보 수정을 위해 현재 비밀번호를 입력해주세요.</div>
                                <small class="form-text text-muted">보안을 위해 현재 비밀번호를 입력해주세요.</small>
                            </div>
                            
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <button type="submit" class="btn btn-primary">정보 수정</button>
                                <a href="mypage.do" class="btn btn-outline-secondary">취소</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS 및 의존성 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    
    <script>
    // 폼 유효성 검증
    (function () {
      'use strict'
      
      // 폼 유효성 검증 스크립트
      var forms = document.querySelectorAll('.needs-validation')
      
      Array.prototype.slice.call(forms)
        .forEach(function (form) {
          form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
              event.preventDefault()
              event.stopPropagation()
            }
            
            form.classList.add('was-validated')
          }, false)
        })
    })()
    </script>
</body>
</html>