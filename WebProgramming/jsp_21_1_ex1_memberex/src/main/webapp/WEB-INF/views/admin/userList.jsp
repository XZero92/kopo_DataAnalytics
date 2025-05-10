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

    // 페이징 처리
    int currentPage = 1;
    int recordsPerPage = 10;

    String pageStr = request.getParameter("page");
    if (pageStr != null && !pageStr.isEmpty()) {
        currentPage = Integer.parseInt(pageStr);
    }

    // 검색 조건
    String searchType = request.getParameter("searchType");
    String searchKeyword = request.getParameter("searchKeyword");

    // DAO 인스턴스 생성
    MemberDAO dao = new MemberDAO();

    // 전체 레코드 수 조회 (검색 조건 적용)
    int totalRecords = dao.countMembers(searchType, searchKeyword);
    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

    // 회원 목록 조회 (페이징 및 검색 조건 적용)
    List<MemberDTO> members = dao.getMembers(currentPage, recordsPerPage, searchType, searchKeyword);

    // 이전 페이지와 다음 페이지 계산
    int prevPage = (currentPage > 1) ? currentPage - 1 : 1;
    int nextPage = (currentPage < totalPages) ? currentPage + 1 : totalPages;
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 목록</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        body {
            padding-top: 20px;
        }
        .status-active {
            background-color: #d1e7dd;
        }
        .status-inactive {
            background-color: #fff3cd;
        }
        .status-deleted {
            background-color: #f8d7da;
        }
        .status-suspended {
            background-color: #dee2e6;
        }
        
        /* 드롭다운 메뉴 스크롤바 문제 해결 */
        .dropdown-menu {
            position: fixed !important;
            z-index: 1050 !important;
        }
        
        .table-responsive {
            overflow: visible !important;
        }

    </style>
</head>
<body>
<div class="container">
    <h1 class="mb-4">회원 목록</h1>

    <div class="row mb-3">
        <div class="col-md-6">
                <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-primary">메인으로</a>
                <a href="<%=request.getContextPath()%>/admin/userRequestList" class="btn btn-warning">가입 요청 목록</a>
                <a href="<%=request.getContextPath()%>/admin/userDeletedList" class="btn btn-danger">탈퇴 회원 목록</a>
        </div>
        <div class="col-md-6">
            <form action="<%=request.getContextPath()%>/admin/userList" method="get" class="d-flex">
                <select name="searchType" class="form-select me-2" style="width: 120px;">
                    <option value="userId" <%= "userId".equals(searchType) ? "selected" : "" %>>아이디</option>
                    <option value="userName" <%= "userName".equals(searchType) ? "selected" : "" %>>이름</option>
                    <option value="mobileNo" <%= "mobileNo".equals(searchType) ? "selected" : "" %>>전화번호</option>
                </select>
                <input type="text" name="searchKeyword" class="form-control me-2" placeholder="검색어"
                       value="<%= searchKeyword != null ? searchKeyword : "" %>">
                <button type="submit" class="btn btn-outline-primary">
                    <i class="bi bi-search"></i>
                </button>
            </form>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header d-flex justify-content-between align-items-center">
            <span>전체 회원 수: <%= totalRecords %>명</span>
            <div class="d-flex align-items-center">
                <div class="me-3">
                    <span class="badge rounded-pill bg-success">정상</span> 활성 회원
                </div>
                <div class="me-3">
                    <span class="badge rounded-pill bg-warning">대기</span> 승인 대기 회원
                </div>
                <div class="me-3">
                    <span class="badge rounded-pill bg-secondary">정지</span> 일시 정지 회원
                </div>
                <div>
                    <span class="badge rounded-pill bg-danger">탈퇴</span> 탈퇴 회원
                </div>
            </div>
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover mb-0">
                    <thead>
                    <tr>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>상태</th>
                        <th>권한</th>
                        <th>가입일</th>
                        <th>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (MemberDTO member : members) { %>
                    <tr class="<%= getStatusClass(member.getStatus()) %>">
                        <td><%= member.getUserId() %></td>
                        <td><%= member.getUserName() %></td>
                        <td><%= member.getMobileNo() %></td>
                        <td><%= getStatusBadge(member.getStatus()) %></td>
                        <td><%= getUserTypeBadge(member.getUserType()) %></td>
                        <td><%= member.getFirstDate() != null ? member.getFirstDate() : "-" %></td>
                        <td>
                            <div class="dropdown">
                                <button class="btn btn-sm btn-outline-secondary dropdown-toggle" type="button"
                                        id="dropdownMenuButton<%= member.getUserId().hashCode() %>"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    관리
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton<%= member.getUserId().hashCode() %>">
                                    <% if (MemberDTO.STATUS_INACTIVE.equals(member.getStatus())) { %>
                                    <li>
                                        <form action="<%=request.getContextPath()%>/admin/updateMemberStatus" method="post" class="dropdown-item">
                                            <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                                            <input type="hidden" name="status" value="<%= MemberDTO.STATUS_ACTIVE %>">
                                            <button type="submit" class="btn btn-sm text-success p-0">
                                                <i class="bi bi-check-circle"></i> 승인하기
                                            </button>
                                        </form>
                                    </li>
                                    <% } %>

                                    <% if (MemberDTO.STATUS_SUSPENDED.equals(member.getStatus())) { %>
                                    <li>
                                        <form action="<%=request.getContextPath()%>/admin/updateMemberStatus" method="post" class="dropdown-item">
                                            <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                                            <input type="hidden" name="status" value="<%= MemberDTO.STATUS_ACTIVE %>">
                                            <button type="submit" class="btn btn-sm text-success p-0">
                                                <i class="bi bi-play-circle"></i> 정지해제
                                            </button>
                                        </form>
                                    </li>
                                    <% } %>

                                    <% if (MemberDTO.STATUS_ACTIVE.equals(member.getStatus())) { %>
                                    <li>
                                        <form action="<%=request.getContextPath()%>/admin/updateMemberStatus" method="post" class="dropdown-item">
                                            <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                                            <input type="hidden" name="status" value="<%= MemberDTO.STATUS_SUSPENDED %>">
                                            <button type="submit" class="btn btn-sm text-warning p-0">
                                                <i class="bi bi-pause-circle"></i> 일시정지
                                            </button>
                                        </form>
                                    </li>
                                    <li>
                                        <form action="<%=request.getContextPath()%>/admin/updateMemberStatus" method="post" class="dropdown-item">
                                            <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                                            <input type="hidden" name="status" value="<%= MemberDTO.STATUS_DELETED %>">
                                            <button type="submit" class="btn btn-sm text-danger p-0">
                                                <i class="bi bi-x-circle"></i> 탈퇴처리
                                            </button>
                                        </form>
                                    </li>

                                    <!-- 관리자 권한 부여/해제 옵션 -->
                                    <li><hr class="dropdown-divider"></li>
                                    <li>
                                        <% if (!MemberDTO.USER_TYPE_ADMIN.equals(member.getUserType())) { %>
                                            <form action="<%=request.getContextPath()%>/admin/updateMemberType" method="post" class="dropdown-item">
                                                <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                                                <input type="hidden" name="userType" value="<%= MemberDTO.USER_TYPE_ADMIN %>">
                                                <button type="submit" class="btn btn-sm text-primary p-0">
                                                    <i class="bi bi-shield-fill"></i> 관리자 권한 부여
                                                </button>
                                            </form>
                                        <% } else if (!member.getUserId().equals(userId)) { %>
                                            <form action="<%=request.getContextPath()%>/admin/updateMemberType" method="post" class="dropdown-item">
                                                <input type="hidden" name="userId" value="<%= member.getUserId() %>">
                                                <input type="hidden" name="userType" value="<%= MemberDTO.USER_TYPE_MEMBER %>">
                                                <button type="submit" class="btn btn-sm text-secondary p-0">
                                                    <i class="bi bi-shield"></i> 관리자 권한 해제
                                                </button>
                                            </form>
                                        <% } else { %>
                                            <span class="dropdown-item text-muted">
                                                <i class="bi bi-shield-fill"></i> 현재 로그인한 관리자
                                            </span>
                                        <% } %>
                                    </li>

                                    <% } %>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <% if (members.isEmpty()) { %>
            <div class="alert alert-info m-3">회원 정보가 없습니다.</div>
            <% } %>
        </div>

        <!-- 페이징 처리 -->
        <div class="card-footer">
            <nav aria-label="회원 목록 페이지">
                <ul class="pagination justify-content-center mb-0">
                    <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                        <a class="page-link" href="<%=request.getContextPath()%>/admin/userList?page=1<%= getSearchQueryString(searchType, searchKeyword) %>">
                            <i class="bi bi-chevron-double-left"></i>
                        </a>
                    </li>
                    <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                        <a class="page-link" href="<%=request.getContextPath()%>/admin/userList?page=<%= prevPage %><%= getSearchQueryString(searchType, searchKeyword) %>">
                            <i class="bi bi-chevron-left"></i>
                        </a>
                    </li>

                    <%
                        // 페이지 숫자 표시 범위 계산
                        int startPage = Math.max(1, currentPage - 2);
                        int endPage = Math.min(totalPages, currentPage + 2);

                        for (int i = startPage; i <= endPage; i++) {
                    %>
                    <li class="page-item <%= i == currentPage ? "active" : "" %>">
                        <a class="page-link" href="<%=request.getContextPath()%>/admin/userList?page=<%= i %><%= getSearchQueryString(searchType, searchKeyword) %>">
                            <%= i %>
                        </a>
                    </li>
                    <% } %>

                    <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                        <a class="page-link" href="<%=request.getContextPath()%>/admin/userList?page=<%= nextPage %><%= getSearchQueryString(searchType, searchKeyword) %>">
                            <i class="bi bi-chevron-right"></i>
                        </a>
                    </li>
                    <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                        <a class="page-link" href="<%=request.getContextPath()%>/admin/userList?page=<%= totalPages %><%= getSearchQueryString(searchType, searchKeyword) %>">
                            <i class="bi bi-chevron-double-right"></i>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<%!
    // 상태에 따른 CSS 클래스 반환
    private String getStatusClass(String status) {
        if (MemberDTO.STATUS_ACTIVE.equals(status)) {
            return "status-active";
        } else if (MemberDTO.STATUS_INACTIVE.equals(status)) {
            return "status-inactive";
        } else if (MemberDTO.STATUS_DELETED.equals(status)) {
            return "status-deleted";
        } else if (MemberDTO.STATUS_SUSPENDED.equals(status)) {
            return "status-suspended";
        }
        return "";
    }

    // 상태에 따른 뱃지 HTML 반환
    private String getStatusBadge(String status) {
        if (MemberDTO.STATUS_ACTIVE.equals(status)) {
            return "<span class='badge bg-success'>정상</span>";
        } else if (MemberDTO.STATUS_INACTIVE.equals(status)) {
            return "<span class='badge bg-warning'>대기</span>";
        } else if (MemberDTO.STATUS_DELETED.equals(status)) {
            return "<span class='badge bg-danger'>탈퇴</span>";
        } else if (MemberDTO.STATUS_SUSPENDED.equals(status)) {
            return "<span class='badge bg-secondary'>정지</span>";
        }
        return "<span class='badge bg-secondary'>알 수 없음</span>";
    }

    // 사용자 권한에 따른 뱃지 HTML 반환
    private String getUserTypeBadge(String userType) {
        if (MemberDTO.USER_TYPE_ADMIN.equals(userType)) {
            return "<span class='badge bg-primary'>관리자</span>";
        } else {
            return "<span class='badge bg-info'>일반</span>";
        }
    }

    // 검색 조건 쿼리스트링 생성
    private String getSearchQueryString(String searchType, String searchKeyword) {
        if (searchType != null && !searchType.isEmpty() && searchKeyword != null && !searchKeyword.isEmpty()) {
            return "&searchType=" + searchType + "&searchKeyword=" + searchKeyword;
        }
        return "";
    }
%>
</body>
</html>