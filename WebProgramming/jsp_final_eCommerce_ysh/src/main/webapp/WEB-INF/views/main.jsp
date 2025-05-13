<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>쇼핑몰 - 메인</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Flatly 테마 CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
  <style>
    .product-card {
      height: 100%;
      transition: transform 0.3s;
    }
    .product-card:hover {
      transform: translateY(-5px);
    }
  </style>
</head>
<body>
<jsp:include page="./common/header.jsp" />


<!-- Bootstrap JS 및 의존성 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<script>
</script>
</body>
</html>