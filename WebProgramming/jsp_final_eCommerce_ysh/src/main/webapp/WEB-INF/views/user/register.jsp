<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootswatch 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <style>
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
        }
        .required-label::after {
            content: " *";
            color: red;
        }
    </style>
</head>
<body>
    <jsp:include page="../common/header.jsp" />
    
    <div class="container mt-5">
        <div class="form-container">
            <h2 class="text-center mb-4">회원가입</h2>
            
            <% 
            // 에러 메시지가 있고 POST 요청인 경우에만 표시
            String errorMsg = (String)request.getAttribute("errorMessage");
            String method = request.getMethod();
            if (errorMsg != null && !errorMsg.isEmpty() && "POST".equalsIgnoreCase(method)) {
            %>
            <div class="alert alert-danger" role="alert">
                <%= errorMsg %>
            </div>
            <% } %>
            
            <form action="register.do" method="post" id="registerForm" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label for="userId" class="form-label required-label">아이디(이메일)</label>
                    <input type="email" class="form-control" id="userId" name="userId"
                           value="${requestScope.userId != null ? requestScope.userId : ''}" required>
                    <div class="invalid-feedback">
                        유효한 이메일 주소를 입력해주세요.
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="userName" class="form-label required-label">이름</label>
                    <input type="text" class="form-control" id="userName" name="userName" 
                           value="${requestScope.userName != null ? requestScope.userName : ''}" required>
                    <div class="invalid-feedback">
                        이름을 입력해주세요.
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="password" class="form-label required-label">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <div class="invalid-feedback">
                        비밀번호를 입력해주세요.
                    </div>
                    <small class="form-text text-muted">
                        5~15자리의 영문자(대문자/소문자 포함)와 숫자를 포함해야 합니다.
                    </small>
                </div>
                
                <div class="mb-3">
                    <label for="confirmPassword" class="form-label required-label">비밀번호 확인</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    <div class="invalid-feedback">
                        비밀번호가 일치하지 않습니다.
                    </div>
                </div>
                
                <div class="mb-3">
                    <label for="mobileNo" class="form-label required-label">휴대폰 번호</label>
                    <input type="text" class="form-control" id="mobileNo" name="mobileNo" placeholder="010-0000-0000" 
                           value="${requestScope.mobileNo != null ? requestScope.mobileNo : ''}" required>
                    <div class="invalid-feedback">
                        휴대폰 번호를 입력해주세요.
                    </div>
                </div>
                
                <div class="d-grid gap-2">
                    <button type="submit" class="btn btn-primary">가입하기</button>
                    <a href="login.do" class="btn btn-outline-secondary">이미 회원이신가요? 로그인</a>
                </div>
            </form>
        </div>
    </div>
    
    <!-- Bootstrap JS 및 의존성 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

    <script>
        (function () {
            'use strict'

            // 유효성 검사 함수
            function validateField(field) {
                const id = field.id;
                const value = field.value;
                let isValid = true;

                if (id === 'userId') {
                    const userIdRegex = /^[A-Za-z0-9+_.-]{5,15}@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
                    isValid = userIdRegex.test(value);
                    field.setCustomValidity(isValid ? '' : '5~15자리의 유효한 이메일 주소를 입력해주세요.');
                } else if (id === 'password') {
                    const passwordRegex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9]{5,15}$/;
                    isValid = passwordRegex.test(value);
                    field.setCustomValidity(isValid ? '' : '5~15자리의 영문자(대문자/소문자 포함)와 숫자를 포함해야 합니다.');
                } else if (id === 'confirmPassword') {
                    const password = document.getElementById('password').value;
                    isValid = value === password;
                    field.setCustomValidity(isValid ? '' : '비밀번호가 일치하지 않습니다.');
                } else if (id === 'mobileNo') {
                    const mobileNoRegex = /^01[0-9]-\d{3,4}-\d{4}$/;
                    isValid = mobileNoRegex.test(value);
                    field.setCustomValidity(isValid ? '' : '유효한 휴대폰 번호 형식이 아닙니다. (예: 010-1234-5678)');
                }

                field.classList.toggle('is-invalid', !isValid);
                field.classList.toggle('is-valid', isValid);
            }

            // 모든 입력 필드에 blur 이벤트 리스너 추가
            const fields = document.querySelectorAll('#registerForm input');
            fields.forEach(field => {
                field.addEventListener('blur', function () {
                    validateField(field);
                });
            });
        })();
    </script>
</body>
</html>