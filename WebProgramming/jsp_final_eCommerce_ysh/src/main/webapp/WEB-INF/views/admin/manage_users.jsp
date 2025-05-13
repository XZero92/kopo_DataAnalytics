<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>사용자 관리 - 관리자 페이지</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
</head>
<body>
<jsp:include page="../common/header.jsp" />

<div class="container mt-4">
  <h2 class="mb-4">사용자 관리</h2>

  <!-- 가입 요청 처리 -->
  <div class="card mb-4">
    <div class="card-body">
      <h4>가입 요청</h4>
      <table class="table">
        <thead>
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>가입일</th>
          <th>처리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.pendingUsers}">
          <tr>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.firstDate}</td>
            <td>
              <a href="approve_user.do?userId=${user.userId}" class="btn btn-success btn-sm">승인</a>
              <a href="reject_user.do?userId=${user.userId}" class="btn btn-danger btn-sm">거부</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <!-- 탈퇴 회원 처리 -->
  <div class="card mb-4">
    <div class="card-body">
      <h4>탈퇴 요청</h4>
      <table class="table">
        <thead>
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>탈퇴 요청일</th>
          <th>처리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.deletedUsers}">
          <tr>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.firstDate}</td>
            <td>
              <a href="delete_user.do?userId=${user.userId}" class="btn btn-danger btn-sm">삭제</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <!-- 사용자 정보 조회 및 수정 -->
  <div class="card mb-4">
    <div class="card-body">
      <h4>사용자 목록</h4>
      <table class="table">
        <thead>
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>상태</th>
          <th>권한</th>
          <th>수정</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.allUsers}">
          <tr>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.status}</td>
            <td>${user.userType}</td>
            <td>
              <a href="edit_user.do?userId=${user.userId}" class="btn btn-primary btn-sm">수정</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>