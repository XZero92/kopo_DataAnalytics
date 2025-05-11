<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${not empty modify}">게시글 수정</c:when>
            <c:when test="${not empty reply}">답글 작성</c:when>
            <c:otherwise>게시글 작성</c:otherwise>
        </c:choose>
    </title>
    <!-- Bootswatch Zephyr Theme CSS CDN 링크 -->
    <link href="https://bootswatch.com/5/zephyr/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>
            <c:choose>
                <c:when test="${not empty modify}">게시글 수정</c:when>
                <c:when test="${not empty reply}">답글 작성</c:when>
                <c:otherwise>게시글 작성</c:otherwise>
            </c:choose>
        </h2>
        
        <!-- form action 설정 -->
        <c:set var="formAction">
            <c:choose>
                <c:when test="${not empty modify}">modify.do</c:when>
                <c:when test="${not empty reply}">reply.do</c:when>
                <c:otherwise>write.do</c:otherwise>
            </c:choose>
        </c:set>
        
        <form action="${formAction}" method="post">
            <!-- 수정 또는 답글 작성 시 필요한 hidden 필드 추가 -->
            <c:if test="${not empty modify || not empty reply}">
                <input type="hidden" name="nbBoard" value="${content_view.nbBoard}">
                <input type="hidden" name="page" value="${page}">
            </c:if>
            
            <div class="mb-3">
                <label for="author" class="form-label">작성자:</label>
                <c:choose>
                    <c:when test="${not empty modify}">
                        <input type="text" class="form-control" id="author" name="author" 
                               value="${content_view.nmWriter}" readonly required>
                    </c:when>
                    <c:when test="${not empty reply}">
                        <input type="text" class="form-control" id="author" name="author" 
                               value="" required>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="form-control" id="author" name="author" 
                               value="${content_view.nmWriter}" required>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <div class="mb-3">
                <label for="title" class="form-label">제목:</label>
                <c:choose>
                    <c:when test="${not empty reply}">
                        <input type="text" class="form-control" id="title" name="title" 
                               value="RE: ${content_view.nmTitle}" required>
                    </c:when>
                    <c:otherwise>
                        <input type="text" class="form-control" id="title" name="title" 
                               value="${content_view.nmTitle}" required>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <div class="mb-3">
                <label for="content" class="form-label">내용:</label>
                <c:choose>
                    <c:when test="${not empty reply}">
                        <c:set var="quoteText">

--------------------------------------------------
[원본 글]
작성자: ${content_view.nmWriter}
제목: ${content_view.nmTitle}
--------------------------------------------------
${content_view.nmContent}
--------------------------------------------------

</c:set>
                        <textarea class="form-control" id="content" name="content" 
                              rows="15" required>${quoteText}</textarea>
                    </c:when>
                    <c:otherwise>
                        <textarea class="form-control" id="content" name="content" 
                              rows="10" required>${content_view.nmContent}</textarea>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <!-- 원본 글 표시 영역 제거 -->
            
            <!-- 버튼 영역 -->
            <div class="text-end">
                <button type="submit" class="btn btn-primary">
                    <c:choose>
                        <c:when test="${not empty modify}">수정</c:when>
                        <c:when test="${not empty reply}">답글 등록</c:when>
                        <c:otherwise>등록</c:otherwise>
                    </c:choose>
                </button>
                
                <!-- 취소 버튼의 링크 설정 -->
                <c:choose>
                    <c:when test="${empty modify && empty reply}">
                        <a href="list.do" class="btn btn-secondary">취소</a>
                    </c:when>
                    <c:otherwise>
                        <a href="content_view.do?nbBoard=${content_view.nbBoard}&page=${page}" class="btn btn-secondary">취소</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>