<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>관리자 페이지 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Flatly 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <style>
        .admin-menu {
            border-right: 1px solid #eee;
        }
        .admin-menu .nav-link {
            color: #555;
            padding: 0.8rem 1rem;
            border-radius: 0;
        }
        .admin-menu .nav-link.active {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #2C3E50;
            border-left: 3px solid #18BC9C;
        }
        .admin-menu .nav-link:hover:not(.active) {
            background-color: #f8f9fa;
        }
        .section-title {
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" />

<div class="container mt-4">
    <h2 class="mb-4">관리자 페이지</h2>

    <div class="row">
        <!-- 왼쪽 메뉴 -->
        <div class="col-md-3 admin-menu">
            <div class="d-flex flex-column">
                <div class="p-3 bg-light">
                    <h5>관리자님</h5>
                    <p class="mb-0 text-muted">관리자 전용 페이지</p>
                </div>
                <div class="nav flex-column nav-pills">
                    <a class="nav-link active" href="adminPage.do">관리자 홈</a>
                    <a class="nav-link" href="manage_users.do">전체 사용자 관리</a>
                    <a class="nav-link" href="manage_categories.do">상품 카테고리 관리</a>
                    <a class="nav-link" href="manage_products.do">상품 관리</a>
                    <a class="nav-link" href="manage_category_mapping.do">전시 상품 관리</a>
                    <a class="nav-link disabled" href="#">주문 관리</a>
                </div>
            </div>
        </div>

        <!-- 오른쪽 콘텐츠 -->
        <div class="col-md-9">
            <!-- 관리자 정보 요약 -->
            <div class="card mb-4">
                <div class="card-body">
                    <h4 class="section-title">관리자 정보</h4>
                    <p>관리자 전용 페이지에 오신 것을 환영합니다.</p>
                    <p>왼쪽 메뉴를 통해 관리 기능에 접근할 수 있습니다.</p>
                </div>
            </div>

            <!-- 사용자 관리 -->
            <div class="card">
                <div class="card-body">
                    <h4 class="section-title">전체 사용자 관리</h4>
                    <p>사용자 관리 페이지로 이동하여 전체 사용자 정보를 확인하고 관리할 수 있습니다.</p>
                    <a href="manage_users.do" class="btn btn-primary">사용자 관리 페이지로 이동</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 의존성 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>