<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Flatly 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <style>
        .login-container {
            max-width: 450px;
            margin: 0 auto;
            padding: 20px;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    
    <div class="container mt-5">
        <div class="login-container">
            <!-- 회원가입 성공 메시지 -->
            <c:if test="${param.registered == 'true'}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>회원가입 요청 완료.</strong> 관리자가 가입을 승인한 뒤에 로그인하실 수 있습니다.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            
            <!-- 로그인 에러 메시지 -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${errorMessage}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            
            <div class="card">
                <div class="card-header bg-primary text-white text-center py-3">
                    <h4 class="mb-0">로그인</h4>
                </div>
                <div class="card-body p-4">
                    <form action="login.do" method="post">
                        <div class="mb-3">
                            <label for="userId" class="form-label">아이디(이메일)</label>
                            <input type="email" class="form-control" id="userId" name="userId" placeholder="name@example.com" required>
                        </div>
                        
                        <div class="mb-3">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">로그인</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer bg-light py-3">
                    <div class="text-center">
                        <div class="mb-2">
                            <a href="register.do" class="text-decoration-none">회원가입</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap JS 및 의존성 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>