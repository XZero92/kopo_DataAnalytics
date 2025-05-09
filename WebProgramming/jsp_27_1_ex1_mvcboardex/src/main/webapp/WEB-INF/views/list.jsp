<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>게시판 목록</title>
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <style>
        /* 들여쓰기를 위한 스타일 */
        .indent {
            display: inline-block;
            width: 20px;
            height: 16px;
            background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16"><path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/></svg>') no-repeat center center;
            vertical-align: middle;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">게시판</h2>
    <!-- 게시글 목록 테이블 -->
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col" style="width: 10%">번호</th>
            <th scope="col" style="width: 45%">제목</th>
            <th scope="col" style="width: 15%">작성자</th>
            <th scope="col" style="width: 20%">작성일</th>
            <th scope="col" style="width: 10%">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty boardList}">
                <tr>
                    <td colspan="5" class="text-center">등록된 게시글이 없습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="board" items="${boardList}">
                    <tr>
                        <th scope="row">${board.nbBoard}</th>
                        <td>
                            <!-- 들여쓰기 처리 -->
                            <c:if test="${board.nbIndent > 0}">
                                <c:forEach begin="1" end="${board.nbIndent}">
                                    <span class="indent"></span>
                                </c:forEach>
                                <span class="badge bg-secondary">RE:</span>
                            </c:if>
                            <!-- 제목 링크 -->
                            <a href="content_view.do?nbBoard=${board.nbBoard}&page=${currentPage}" class="text-decoration-none">
                                ${board.nmTitle}
                            </a>
                        </td>
                        <td>${board.nmWriter}</td>
                        <td>
                            <fmt:formatDate value="${board.daWrite}" pattern="yyyy-MM-dd HH:mm"/>
                        </td>
                        <td>${board.cnHit}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <!-- 하단 영역: 검색, 페이지네이션, 글쓰기 버튼 -->
    <div class="row mt-3 align-items-center">
        <!-- 검색 영역 (좌측) -->
        <div class="col-md-4">
            <!-- 검색 폼은 추후 구현 예정 -->
        </div>
        
        <!-- 페이지네이션 영역 (중앙) -->
        <div class="col-md-4">
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center mb-0">
                    <!-- 이전 페이지 블록 -->
                    <c:if test="${startPage > pageBlock}">
                        <li class="page-item">
                            <a class="page-link" href="list.do?page=${startPage - pageBlock}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    
                    <!-- 이전 페이지 -->
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="list.do?page=${currentPage - 1}">이전</a>
                        </li>
                    </c:if>
                    
                    <!-- 페이지 번호 -->
                    <c:forEach var="i" begin="${startPage}" end="${endPage}">
                        <c:choose>
                            <c:when test="${currentPage == i}">
                                <li class="page-item active" aria-current="page">
                                    <a class="page-link" href="#">${i}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="list.do?page=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    
                    <!-- 다음 페이지 -->
                    <c:if test="${currentPage < totalPage}">
                        <li class="page-item">
                            <a class="page-link" href="list.do?page=${currentPage + 1}">다음</a>
                        </li>
                    </c:if>
                    
                    <!-- 다음 페이지 블록 -->
                    <c:if test="${endPage < totalPage}">
                        <li class="page-item">
                            <a class="page-link" href="list.do?page=${startPage + pageBlock}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
        
        <!-- 글쓰기 버튼 영역 (우측) -->
        <div class="col-md-4 text-end">
            <form action="write_view.do" method="get" class="mb-0">
                <button type="submit" class="btn btn-primary">글쓰기</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>