<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.jsp_21_1_ex1_memberex.model.MemberDTO" %>
<%@ page import="com.example.jsp_21_1_ex1_memberex.repository.MemberDAO" %>
<%
    // 로그인 확인
    String userId = (String) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // 회원 정보 조회
    MemberDAO dao = new MemberDAO();
    MemberDTO member = dao.getMemberById(userId);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보 수정</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <style>
        body {
            padding-top: 50px;
        }
        .modify-form {
            max-width: 500px;
            margin: 0 auto;
            padding: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="modify-form">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">회원정보 수정</h4>
            </div>
            <div class="card-body">
                <form action="modifyOk" method="post" onsubmit="return validateForm();">
                    <div class="mb-3">
                        <label for="userId" class="form-label">아이디(이메일)</label>
                        <input type="email" class="form-control" id="userId" name="userId" value="<%= member.getUserId() %>" readonly>
                        <div class="form-text">아이디는 변경할 수 없습니다.</div>
                    </div>
                    <div class="mb-3">
                        <label for="userName" class="form-label">이름</label>
                        <input type="text" class="form-control" id="userName" name="userName" value="<%= member.getUserName() %>" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">새 비밀번호</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                        <div id="passwordHelpBlock" class="form-text">
                            비밀번호는 최소 4자 이상의 영문자와 숫자 조합이어야 합니다.
                        </div>
                        <div id="passwordFormatFeedback" class="invalid-feedback">
                            비밀번호는 최소 4자 이상의 영문자와 숫자 조합이어야 합니다.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">비밀번호 확인</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                        <div id="passwordFeedback" class="invalid-feedback">
                            비밀번호가 일치하지 않습니다.
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="mobileNo" class="form-label">전화번호</label>
                        <input type="tel" class="form-control" id="mobileNo" name="mobileNo" value="<%= member.getMobileNo() %>" required>
                    </div>
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary">수정하기</button>
                        <a href="index.jsp" class="btn btn-secondary">취소</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // 비밀번호 형식 확인
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');
    const passwordFeedback = document.getElementById('passwordFeedback');
    const passwordFormatFeedback = document.getElementById('passwordFormatFeedback');

    // 비밀번호 형식 검증 (최소 4글자, 영문자+숫자 조합)
    function isValidPassword(pwd) {
        // 영문자와 숫자 조합으로 최소 4글자 이상
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,}$/;
        return passwordRegex.test(pwd);
    }

    password.addEventListener('input', function() {
        if (!isValidPassword(this.value)) {
            this.classList.add('is-invalid');
            passwordFormatFeedback.style.display = 'block';
        } else {
            this.classList.remove('is-invalid');
            passwordFormatFeedback.style.display = 'none';
        }
    });

    // 비밀번호 일치 확인
    confirmPassword.addEventListener('input', function() {
        if (this.value !== password.value) {
            this.classList.add('is-invalid');
            passwordFeedback.style.display = 'block';
        } else {
            this.classList.remove('is-invalid');
            passwordFeedback.style.display = 'none';
        }
    });

    function validateForm() {
        const pw = document.getElementById('password').value;
        const pw2 = document.getElementById('confirmPassword').value;
        
        // 비밀번호 형식 검증
        if (!isValidPassword(pw)) {
            password.classList.add('is-invalid');
            passwordFormatFeedback.style.display = 'block';
            return false;
        }
        
        // 비밀번호 일치 검증
        if (pw !== pw2) {
            confirmPassword.classList.add('is-invalid');
            passwordFeedback.style.display = 'block';
            return false;
        }
        
        return true;
    }
</script>
</body>
</html>