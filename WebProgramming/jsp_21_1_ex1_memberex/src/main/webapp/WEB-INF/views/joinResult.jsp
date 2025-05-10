<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>가입 완료</title>
  <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
  <style>
    body {
      padding-top: 50px;
    }
    .result-container {
      max-width: 500px;
      margin: 0 auto;
      padding: 15px;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="result-container">
    <div class="card">
      <div class="card-header bg-success text-white">
        <h4 class="mb-0">회원가입 신청 완료</h4>
      </div>
      <div class="card-body">
        <div class="alert alert-success">
          <h5 class="alert-heading">환영합니다, ${requestScope.userName}님!</h5>
          <p>회원가입 신청이 완료되었습니다. 관리자 승인 후 서비스를 이용하실 수 있습니다.</p>
        </div>
        <div class="mb-3">
          <p><strong>아이디:</strong> ${requestScope.userId}</p>
          <p><strong>이름:</strong> ${requestScope.userName}</p>
          <p><strong>이메일:</strong> ${requestScope.email}</p>
          <p><strong>전화번호:</strong> ${requestScope.mobileNo}</p>
        </div>
        <div class="d-grid gap-2">
          <a href="index.jsp" class="btn btn-primary">메인으로</a>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>