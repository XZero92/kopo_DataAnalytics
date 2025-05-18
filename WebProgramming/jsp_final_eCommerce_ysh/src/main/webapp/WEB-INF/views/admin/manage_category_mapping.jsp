<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>카테고리-상품 매핑 관리 - 쇼핑몰</title>
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
        .product-card {
            border: 1px solid #dee2e6;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
        }
        .product-card img {
            max-width: 100px;
            height: auto;
        }
        .category-list {
            max-height: 600px;
            overflow-y: auto;
        }
        .product-list {
            max-height: 600px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" />

<div class="container mt-4">
    <h2 class="mb-4">관리자 페이지 - 카테고리-상품 매핑 관리</h2>

    <div class="row">
        <!-- 왼쪽 메뉴 -->
        <div class="col-md-3 admin-menu">
            <div class="d-flex flex-column">
                <div class="p-3 bg-light">
                    <h5>관리자님</h5>
                    <p class="mb-0 text-muted">관리자 전용 페이지</p>
                </div>
                <div class="nav flex-column nav-pills">
                    <a class="nav-link" href="adminPage.do">관리자 홈</a>
                    <a class="nav-link" href="manage_users.do">전체 사용자 관리</a>
                    <a class="nav-link" href="manage_categories.do">상품 카테고리 관리</a>
                    <a class="nav-link" href="manage_products.do">상품 관리</a>
                    <a class="nav-link active" href="manage_category_mapping.do">카테고리-상품 매핑</a>
                    <a class="nav-link disabled" href="#">주문 관리</a>
                </div>
            </div>
        </div>

        <!-- 오른쪽 콘텐츠 -->
        <div class="col-md-9">
            <!-- 알림 메시지 표시 -->
            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${sessionScope.message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <c:remove var="message" scope="session" />
            </c:if>
            <c:if test="${not empty sessionScope.errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${sessionScope.errorMessage}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <c:remove var="errorMessage" scope="session" />
            </c:if>

            <!-- 매핑 관리 섹션 -->
            <div class="row">
                <!-- 카테고리 목록 -->
                <div class="col-md-5">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="card-title mb-0">카테고리 목록</h5>
                        </div>
                        <div class="card-body category-list">
                            <div class="list-group">
                                <c:forEach var="category" items="${categories}">
                                    <button type="button" class="list-group-item list-group-item-action"
                                            onclick="loadCategoryProducts(${category.categoryNo})">
                                        ${category.fullCategoryName}
                                    </button>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 선택된 카테고리의 상품 목록 -->
                <div class="col-md-7">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h5 class="card-title mb-0">연결된 상품 목록</h5>
                            <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#addProductsModal">
                                상품 추가
                            </button>
                        </div>
                        <div class="card-body product-list" id="mappedProductsList">
                            <!-- 선택된 카테고리의 상품들이 여기에 동적으로 로드됨 -->
                            <div class="text-center text-muted">
                                카테고리를 선택하면 연결된 상품이 표시됩니다.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 상품 추가 모달 -->
<div class="modal fade" id="addProductsModal" tabindex="-1" aria-labelledby="addProductsModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductsModalLabel">카테고리에 상품 추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <input type="text" class="form-control" id="productSearch" placeholder="상품명으로 검색">
                </div>
                <div id="productSearchResults" style="max-height: 400px; overflow-y: auto;">
                    <!-- 검색된 상품 목록이 여기에 표시됨 -->
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" onclick="addSelectedProducts()">선택한 상품 추가</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 의존성 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<script>
let selectedCategoryNo = null;

function loadCategoryProducts(categoryNo) {
    selectedCategoryNo = categoryNo;
    fetch('get_category_products.do?categoryNo=' + categoryNo)
        .then(response => response.json())
        .then(data => {
            const productsList = document.getElementById('mappedProductsList');
            productsList.innerHTML = '';

            data.forEach(product => {
                const productCard = document.createElement('div');
                productCard.className = 'product-card d-flex align-items-center';
                productCard.innerHTML = `
                    <img src="getImage.do?fileId=${product.fileId}" class="me-3" alt="${product.productName}">
                    <div class="flex-grow-1">
                        <h6 class="mb-1">${product.productName}</h6>
                        <p class="mb-1">판매가: ￦${product.salePrice.toLocaleString()}</p>
                    </div>
                    <button class="btn btn-sm btn-outline-danger" onclick="removeProduct('${product.productNo}')">
                        제거
                    </button>
                `;
                productsList.appendChild(productCard);
            });
        })
        .catch(error => console.error('Error:', error));
}

function removeProduct(productNo) {
    if (!selectedCategoryNo) return;

    fetch('remove_category_product.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `categoryNo=${selectedCategoryNo}&productNo=${productNo}`
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            loadCategoryProducts(selectedCategoryNo);
        } else {
            alert('상품 제거 중 오류가 발생했습니다.');
        }
    })
    .catch(error => console.error('Error:', error));
}

let searchTimeout;
const productSearch = document.getElementById('productSearch');
productSearch.addEventListener('input', function() {
    clearTimeout(searchTimeout);
    searchTimeout = setTimeout(() => {
        const searchTerm = this.value.trim();
        if (searchTerm.length >= 2) {
            searchProducts(searchTerm);
        }
    }, 300);
});

function searchProducts(searchTerm) {
    fetch('search_products.do?term=' + encodeURIComponent(searchTerm))
        .then(response => response.json())
        .then(data => {
            const resultsDiv = document.getElementById('productSearchResults');
            resultsDiv.innerHTML = '';

            data.forEach(product => {
                const productDiv = document.createElement('div');
                productDiv.className = 'form-check product-card';
                productDiv.innerHTML = `
                    <input class="form-check-input" type="checkbox" value="${product.productNo}" id="product${product.productNo}">
                    <label class="form-check-label" for="product${product.productNo}">
                        ${product.productName} - ￦${product.salePrice.toLocaleString()}
                    </label>
                `;
                resultsDiv.appendChild(productDiv);
            });
        })
        .catch(error => console.error('Error:', error));
}

function addSelectedProducts() {
    if (!selectedCategoryNo) {
        alert('카테고리를 먼저 선택해주세요.');
        return;
    }

    const selectedProducts = Array.from(document.querySelectorAll('#productSearchResults input:checked'))
        .map(input => input.value);

    if (selectedProducts.length === 0) {
        alert('추가할 상품을 선택해주세요.');
        return;
    }

    fetch('add_category_products.do', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            categoryNo: selectedCategoryNo,
            productNos: selectedProducts
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            loadCategoryProducts(selectedCategoryNo);
            const modal = bootstrap.Modal.getInstance(document.getElementById('addProductsModal'));
            modal.hide();
        } else {
            alert('상품 추가 중 오류가 발생했습니다.');
        }
    })
    .catch(error => console.error('Error:', error));
}
</script>
</body>
</html>
