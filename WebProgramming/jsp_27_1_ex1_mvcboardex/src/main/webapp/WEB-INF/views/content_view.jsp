<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <c:set var="titleText" value="${content_view.nmTitle}" />
    <c:if test="${fn:length(titleText) > 30}">
        <c:set var="titleText" value="${fn:substring(titleText, 0, 30)}..." />
    </c:if>
    <title>${titleText} - 게시판</title>
    <!-- 나머지 head 내용 -->
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.min.css">
    <style>
        .content-area {
            min-height: 300px;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 5px;
            margin-bottom: 20px;
            white-space: pre-wrap; /* 줄바꿈 보존 */
        }
        .date-info {
            font-size: 0.85rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h2>${content_view.nmTitle}</h2>
        </div>
        <div class="card-body">
            <!-- 게시글 메타 정보 -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <strong>작성자:</strong> ${content_view.nmWriter}
                </div>
                <div class="col-md-6 text-md-end">
                    <strong>조회수:</strong> ${content_view.cnHit}
                </div>
            </div>
            
            <div class="row mb-4">
                <div class="col-md-6 date-info">
                    <strong>작성일:</strong> <fmt:formatDate value="${content_view.daWrite}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </div>
                <div class="col-md-6 text-md-end date-info">
                    <strong>최초작성일:</strong> 
                    <c:choose>
                        <c:when test="${not empty content_view.daFirstDate}">
                            <fmt:formatDate value="${content_view.daFirstDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${content_view.daWrite}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            
            <!-- 게시글 내용 -->
            <div class="content-area">
                ${content_view.nmContent}
                <%-- CLOB 필드를 사용할 경우 아래 주석 해제
                <c:choose>
                    <c:when test="${not empty content_view.cbContent}">
                        ${content_view.cbContent}
                    </c:when>
                    <c:otherwise>
                        ${content_view.nmContent}
                    </c:otherwise>
                </c:choose>
                --%>
            </div>
            
            <!-- 첨부 파일 표시 (있는 경우) -->
            <c:if test="${not empty content_view.idFile}">
                <div class="mt-3">
                    <strong>첨부파일:</strong> 
                    <a href="download.do?fileName=${content_view.idFile}" class="text-decoration-none">
                        ${content_view.idFile}
                    </a>
                </div>
            </c:if>
            
            <!-- 버튼 영역 -->
            <div class="d-flex justify-content-between mt-4">
                <div>
                    <!-- 목록으로 돌아가기 -->
                    <a href="list.do?page=${page}" class="btn btn-secondary">목록</a>
                    
                    <!-- 답글 쓰기 버튼 (선택적) -->
                    <a href="reply_view.do?nbBoard=${content_view.nbBoard}&page=${page}" class="btn btn-info">답글</a>
                </div>
                
                <div>
                    <!-- 수정 및 삭제 버튼 -->
                    <a href="modify_view.do?nbBoard=${content_view.nbBoard}&page=${page}" class="btn btn-warning">수정</a>
                    
                    <form action="delete.do" method="post" style="display:inline;">
                        <input type="hidden" name="nbBoard" value="${content_view.nbBoard}">
                        <input type="hidden" name="page" value="${page}">
                        <button type="submit" class="btn btn-danger" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>