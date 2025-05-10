<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <style>
        body {
            padding-top: 50px;
        }
        .login-form {
            max-width: 400px;
            margin: 0 auto;
            padding: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-form">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">로그인</h4>
                </div>
                <div class="card-body">
                    <form action="loginOk" method="post">
                        <div class="mb-3">
                            <label for="userId" class="form-label">아이디(이메일)</label>
                            <input type="email" class="form-control" id="userId" name="userId" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">로그인</button>
                        </div>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <p class="mb-0">아직 계정이 없으신가요? <a href="join.jsp">회원가입</a></p>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>