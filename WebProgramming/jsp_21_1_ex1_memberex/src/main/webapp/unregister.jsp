<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 로그인 확인
    String userId = (String) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 탈퇴</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <style>
        body {
            padding-top: 50px;
        }
        .unregister-form {
            max-width: 500px;
            margin: 0 auto;
            padding: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="unregister-form">
        <div class="card">
            <div class="card-header bg-danger text-white">
                <h4 class="mb-0">회원 탈퇴</h4>
            </div>
            <div class="card-body">
                <div class="alert alert-warning">
                    <h5 class="alert-heading">주의!</h5>
                    <p>회원 탈퇴 시 개인정보는 즉시 탈퇴 처리되며, 데이터가 삭제됩니다. 이 작업은 되돌릴 수 없습니다.</p>
                </div>
                <form action="unregisterOk" method="post">
                    <div class="mb-3">
                        <label for="password" class="form-label">비밀번호 확인</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                        <div class="form-text">본인 확인을 위해 비밀번호를 입력해주세요.</div>
                    </div>
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-danger">회원 탈퇴</button>
                        <a href="index.jsp" class="btn btn-secondary">취소</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>