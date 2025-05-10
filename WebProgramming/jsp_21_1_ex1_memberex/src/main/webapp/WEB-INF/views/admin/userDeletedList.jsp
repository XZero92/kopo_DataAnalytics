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

  // 탈퇴 상태인 회원만 조회
  MemberDAO dao = new MemberDAO();
  List<MemberDTO> deletedMembers = dao.getMembersByStatus(MemberDTO.STATUS_DELETED);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>탈퇴 회원 목록</title>
  <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
  <style>
    body {
      padding-top: 20px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1 class="mb-4">탈퇴 회원 목록</h1>

  <div class="mb-3">
    <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-primary">메인으로</a>
    <a href="<%=request.getContextPath()%>/admin/userList" class="btn btn-success">전체 회원 목록</a>
    <a href="<%=request.getContextPath()%>/admin/userRequestList" class="btn btn-warning">가입 요청 목록</a>
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
            <th>가입일</th>
            <th>탈퇴일</th>
            <th>관리</th>
          </tr>
          </thead>
          <tbody>
          <% for (MemberDTO member : deletedMembers) { %>
          <tr>
            <td><%= member.getUserId() %></td>
            <td><%= member.getUserName() %></td>
            <td><%= member.getMobileNo() %></td>
            <td><%= member.getFirstDate() %></td>
            <td>
              <form action="admin/updateMemberStatus" method="post" class="d-inline">
                <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                <input type="hidden" name="status" value="<%= MemberDTO.STATUS_ACTIVE %>">
                <button type="submit" class="btn btn-sm btn-success">복구</button>
              </form>
              <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal<%= member.getUserId().hashCode() %>">
                영구삭제
              </button>
            </td>
          </tr>

          <!-- 영구 삭제 확인 모달 -->
          <div class="modal fade" id="deleteConfirmModal<%= member.getUserId().hashCode() %>" tabindex="-1" aria-labelledby="deleteConfirmModalLabel<%= member.getUserId().hashCode() %>" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="deleteConfirmModalLabel<%= member.getUserId().hashCode() %>">회원 영구 삭제 확인</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <p class="text-danger fw-bold">주의: 이 작업은 되돌릴 수 없습니다!</p>
                  <p><%= member.getUserId() %> 회원을 데이터베이스에서 영구적으로 삭제하시겠습니까?</p>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                  <form action="admin/deleteMember" method="post">
                    <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                    <button type="submit" class="btn btn-danger">영구 삭제</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <% } %>
          </tbody>
        </table>
      </div>

      <% if (deletedMembers.isEmpty()) { %>
      <div class="alert alert-info">탈퇴한 회원이 없습니다.</div>
      <% } %>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>