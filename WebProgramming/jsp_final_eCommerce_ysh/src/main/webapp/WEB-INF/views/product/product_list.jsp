<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>상품 목록</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <style>
        .card-deck {
            display: flex;
            flex-wrap: wrap;
            gap: 1rem;
            justify-content: start;
        }
        .product-card {
            flex: 1 1 calc(25% - 1rem);
            min-width: 250px;
        }
        .dummy-category {
            border: 1px solid #eee;
            padding: 1rem;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="container mt-4">
    <h2 class="mb-4">전체 상품 목록</h2>

    <!-- 더미 카테고리 영역 -->
    <div class="dummy-category">
        <h4>카테고리 분류 (더미)</h4>
        <p>카테고리 기능은 준비 중입니다.</p>
    </div>

    <!-- 상품 카드 목록 -->
    <div class="card-deck">
        <c:forEach var="product" items="${products}">
            <div class="card product-card">
                <c:if test="${not empty product.fileId}">
                    <img src="getImage.do?fileId=${product.fileId}" class="card-img-top" alt="${product.productName}">
                </c:if>
                <div class="card-body">
                    <h5 class="card-title">${product.productName}</h5>
                    <p class="card-text">${product.detailExplain}</p>
                    <p class="card-text"><strong>판매가:</strong> ₩<fmt:formatNumber value="${product.salePrice}" type="number"/></p>
                </div>
                <div class="card-footer">
                    <button type="button" class="btn btn-primary" onclick="window.location.href='view_product.do?productNo=${product.productNo}'">
                        상세보기
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>