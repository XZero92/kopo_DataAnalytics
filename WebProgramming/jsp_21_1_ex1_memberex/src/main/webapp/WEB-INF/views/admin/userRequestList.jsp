<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.jsp_21_1_ex1_memberex.model.MemberDTO" %>
<%@ page import="com.example.jsp_21_1_ex1_memberex.repository.MemberDAO" %>
<%@ page import="java.util.List" %>
<%
  // 로그인 및 권한 체크
  String userId = (String) session.getAttribute("userId");
  String userType = (String) session.getAttribute("userType");

  if (userId == null || !MemberDTO.USER_TYPE_ADMIN.equals(userType)) {
    response.sendRedirect("login.jsp");
    return;
  }

  // 가입 요청 상태인 회원만 조회
  MemberDAO dao = new MemberDAO();
  List<MemberDTO> requestMembers = dao.getMembersByStatus(MemberDTO.STATUS_INACTIVE);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>가입 요청 목록</title>
  <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
  <style>
    body {
      padding-top: 20px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1 class="mb-4">가입 요청 목록</h1>

  <div class="mb-3">
    <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-primary">메인으로</a>
    <a href="<%=request.getContextPath()%>/admin/userList" class="btn btn-success">전체 회원 목록</a>
    <a href="<%=request.getContextPath()%>/admin/userDeletedList" class="btn btn-danger">탈퇴 회원 목록</a>
  </div>

  <div class="card">
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
          <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>전화번호</th>
            <th>상태</th>
            <th>가입일</th>
            <th>관리</th>
          </tr>
          </thead>
          <tbody>
          <% for (MemberDTO member : requestMembers) { %>
          <tr>
            <td><%= member.getUserId() %></td>
            <td><%= member.getUserName() %></td>
            <td><%= member.getMobileNo() %></td>
            <td><span class="badge bg-warning">가입요청</span></td>
            <td><%= member.getFirstDate() %></td>
            <td>
              <form action="admin/updateMemberStatus" method="post" class="d-inline">
                <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                <input type="hidden" name="status" value="<%= MemberDTO.STATUS_ACTIVE %>">
                <button type="submit" class="btn btn-sm btn-success">승인</button>
              </form>
              <form action="admin/updateMemberStatus" method="post" class="d-inline">
                <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                <input type="hidden" name="status" value="<%= MemberDTO.STATUS_DELETED %>">
                <button type="submit" class="btn btn-sm btn-danger">거부</button>
              </form>
            </td>
          </tr>
          <% } %>
          </tbody>
        </table>
      </div>

      <% if (requestMembers.isEmpty()) { %>
      <div class="alert alert-info">가입 요청 중인 회원이 없습니다.</div>
      <% } %>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>