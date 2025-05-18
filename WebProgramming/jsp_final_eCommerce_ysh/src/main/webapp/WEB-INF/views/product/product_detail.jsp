<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
  boolean isIncluded = (request.getAttribute("javax.servlet.include.request_uri") != null);
%>
<html>
<head>
  <title>상품 상세 페이지</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
</head>
<body>
<%
  if (!isIncluded) {
%>
<jsp:include page="../common/header.jsp" />
<%
  }
%>
<div class="container mt-4">
  <div class="row">
    <div class="col-md-6">
      <c:if test="${not empty product.fileId}">
        <img src="getImage.do?fileId=${product.fileId}" alt="${product.productName}" class="img-fluid">
      </c:if>
    </div>
    <div class="col-md-6">
      <h2>${product.productName}</h2>
      <p>${product.detailExplain}</p>
      <p><strong>판매가:</strong> ₩<fmt:formatNumber value="${product.salePrice}" type="number"/></p>
      <p>
        <strong>판매 기간:</strong>
        <c:if test="${not empty product.startDate and not empty product.endDate}">
          <fmt:formatDate value="${product.startDate}" pattern="yyyy-MM-dd" /> ~
          <fmt:formatDate value="${product.endDate}" pattern="yyyy-MM-dd" />
        </c:if>
      </p>
      <form action="#" method="post" id="purchaseForm">
        <div class="mb-3">
          <label for="productQuantity" class="form-label">수량</label>
          <input type="number" class="form-control" id="productQuantity" name="quantity" value="1" min="1">
        </div>
        <div class="d-flex">
          <button type="button" class="btn btn-primary me-2" onclick="alert('장바구니 기능은 준비 중입니다.')">
            장바구니 담기
          </button>
          <button type="button" class="btn btn-success" onclick="alert('즉시 구매 기능은 준비 중입니다.')">
            즉시 구매
          </button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>