<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>상품 관리 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Flatly 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <!-- Bootstrap 아이콘 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
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
        .product-img-thumb {
            max-width: 80px;
            max-height: 80px;
            object-fit: contain;
        }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" />

<div class="container mt-4">
    <h2 class="mb-4">관리자 페이지 - 상품 관리</h2>

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
                    <a class="nav-link active" href="manage_products.do">상품 관리</a>
                    <a class="nav-link disabled" href="#">전시 상품 관리</a>
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

            <!-- 상품 관리 섹션 -->
            <div class="card mb-4">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="section-title">상품 목록</h4>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addProductModal">
                            <i class="bi bi-plus"></i> 새 상품 추가
                        </button>
                    </div>
                    
                    <!-- 상품 테이블 -->
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>상품번호</th>
                                    <th>이미지</th>
                                    <th>상품명</th>
                                    <th>판매가</th>
                                    <th>재고</th>
                                    <th>판매기간</th>
                                    <th>관리</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.productNo}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${not empty product.fileId}">
                                                    <img src="getImage.do?fileId=${product.fileId}" alt="${product.productName}" class="product-img-thumb">
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="text-center bg-light" style="width: 80px; height: 80px; display: flex; align-items: center; justify-content: center;">
                                                        <i class="bi bi-image text-muted" style="font-size: 2rem;"></i>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${product.productName}</td>
                                        <td><fmt:formatNumber value="${product.salePrice}" type="currency" currencySymbol="₩" maxFractionDigits="0" /></td>
                                        <td>${product.stockQuantity}개</td>
                                        <td>
                                            <c:if test="${not empty product.startDate}">
                                                ${product.startDate} ~ 
                                            </c:if>
                                            <c:if test="${not empty product.endDate}">
                                                ${product.endDate}
                                            </c:if>
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <button class="btn btn-sm btn-outline-primary" onclick="viewProduct('${product.productNo}')">
                                                    보기
                                                </button>
                                                <!-- edit 버튼에 data 속성으로 상품 정보를 전달 -->
                                                <button type="button" class="btn btn-sm btn-primary"
                                                        onclick="editProduct(this)"
                                                        data-product-no="${product.productNo}"
                                                        data-product-name="${product.productName}"
                                                        data-sale-price="${product.salePrice}"
                                                        data-stock-quantity="${product.stockQuantity}"
                                                        data-customer-quantity="${product.customerQuantity}"
                                                        data-delivery-fee="${product.deliveryFee}"
                                                        data-start-date="${product.startDate}"
                                                        data-end-date="${product.endDate}"
                                                        data-detail-explain="${product.detailExplain}"
                                                        data-file-id="${product.fileId}">
                                                    수정
                                                <button class="btn btn-sm btn-outline-danger" onclick="deleteProduct('${product.productNo}', '${product.productName}')">
                                                    삭제
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 상품 추가 모달 -->
<div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">새 상품 추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="add_product.do" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="productName" class="form-label">상품명</label>
                                <input type="text" class="form-control" id="productName" name="productName" required>
                            </div>
                            <div class="mb-3">
                                <label for="customerQuantity" class="form-label">소비자 가격</label>
                                <div class="input-group">
                                    <span class="input-group-text">₩</span>
                                    <input type="number" class="form-control" id="customerQuantity" name="customerQuantity" min="0" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="salePrice" class="form-label">판매 가격</label>
                                <div class="input-group">
                                    <span class="input-group-text">₩</span>
                                    <input type="number" class="form-control" id="salePrice" name="salePrice" min="0" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="stockQuantity" class="form-label">재고 수량</label>
                                <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" min="0" required>
                            </div>
                            <div class="mb-3">
                                <label for="deliveryFee" class="form-label">배송비</label>
                                <div class="input-group">
                                    <span class="input-group-text">₩</span>
                                    <input type="number" class="form-control" id="deliveryFee" name="deliveryFee" min="0" value="0">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="startDate" class="form-label">판매 시작일</label>
                                <input type="date" class="form-control" id="startDate" name="startDate">
                            </div>
                            <div class="mb-3">
                                <label for="endDate" class="form-label">판매 종료일</label>
                                <input type="date" class="form-control" id="endDate" name="endDate">
                            </div>
                            <div class="mb-3">
                                <label for="productImage" class="form-label">상품 이미지</label>
                                <input type="file" class="form-control" id="productImage" name="productImage">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">카테고리</label>
                                <div class="border p-3" style="max-height: 150px; overflow-y: auto;">
                                    <c:forEach var="category" items="${categories}">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" name="categoryIds"
                                                   value="${category.categoryNo}" id="category${category.categoryNo}">
                                            <label class="form-check-label" for="category${category.categoryNo}">
                                                <c:forEach begin="1" end="${category.level - 1}">
                                                    &nbsp;&nbsp;&nbsp;
                                                </c:forEach>
                                                    ${category.fullCategoryName}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="detailExplain" class="form-label">상품 상세 설명</label>
                        <textarea class="form-control" id="detailExplain" name="detailExplain" rows="5"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">상품 추가</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 상품 수정 모달 -->
<div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="editProductModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editProductModalLabel">상품 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="update_product.do" method="post" enctype="multipart/form-data">
                <input type="hidden" id="editProductNo" name="productNo">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="editProductName" class="form-label">상품명</label>
                                <input type="text" class="form-control" id="editProductName" name="productName" required>
                            </div>
                            <div class="mb-3">
                                <label for="customerQuantity" class="form-label">소비자 가격</label>
                                <div class="input-group">
                                    <span class="input-group-text">₩</span>
                                    <input type="number" class="form-control" id="customerQuantity" name="customerQuantity" min="0" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="editSalePrice" class="form-label">판매가</label>
                                <div class="input-group">
                                    <span class="input-group-text">₩</span>
                                    <input type="number" class="form-control" id="editSalePrice" name="salePrice" min="0" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="editStockQuantity" class="form-label">재고 수량</label>
                                <input type="number" class="form-control" id="editStockQuantity" name="stockQuantity" min="0" required>
                            </div>
                            <div class="mb-3">
                                <label for="editDeliveryFee" class="form-label">배송비</label>
                                <div class="input-group">
                                    <span class="input-group-text">₩</span>
                                    <input type="number" class="form-control" id="editDeliveryFee" name="deliveryFee" min="0" value="0">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="editStartDate" class="form-label">판매 시작일</label>
                                <input type="date" class="form-control" id="editStartDate" name="startDate">
                            </div>
                            <div class="mb-3">
                                <label for="editEndDate" class="form-label">판매 종료일</label>
                                <input type="date" class="form-control" id="editEndDate" name="endDate">
                            </div>
                            <div class="mb-3">
                                <label for="editProductImage" class="form-label">상품 이미지</label>
                                <div id="currentImagePreview" class="mb-2"></div>
                                <input type="file" class="form-control" id="editProductImage" name="productImage">
                                <small class="text-muted">이미지를 변경하지 않으려면 비워두세요.</small>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">카테고리</label>
                                <div class="border p-3" style="max-height: 150px; overflow-y: auto;" id="editCategoryCheckboxes">
                                    <c:forEach var="category" items="${categories}">
                                        <div class="form-check">
                                            <input class="form-check-input edit-category-checkbox" type="checkbox" name="categoryIds" value="${category.categoryNo}" id="editCategory${category.categoryNo}">
                                            <label class="form-check-label" for="editCategory${category.categoryNo}">
                                                ${category.fullCategoryName}
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="editDetailExplain" class="form-label">상품 상세 설명</label>
                        <textarea class="form-control" id="editDetailExplain" name="detailExplain" rows="5"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">상품 수정</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 상품 삭제 확인 모달 -->
<div class="modal fade" id="deleteProductModal" tabindex="-1" aria-labelledby="deleteProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteProductModalLabel">상품 삭제 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>상품 "<span id="deleteProductName"></span>"을(를) 삭제하시겠습니까?</p>
                <p class="text-danger">주의: 이 작업은 되돌릴 수 없습니다.</p>
            </div>
            <div class="modal-footer">
                <form action="delete_product.do" method="post">
                    <input type="hidden" id="deleteProductNo" name="productNo">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-danger">상품 삭제</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 의존성 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<script>
    // 상품 상세 보기
    function viewProduct(productNo) {
        // 상품 상세 페이지로 이동
        window.location.href = "view_product.do?productNo=" + productNo;
    }

    // 상품 수정 모달 열기: 클릭한 버튼의 data 속성을 읽어 모달 폼에 채움.
    function editProduct(button) {
        document.getElementById('editProductNo').value = button.getAttribute('data-product-no');
        document.getElementById('editProductName').value = button.getAttribute('data-product-name');
        document.getElementById('editSalePrice').value = button.getAttribute('data-sale-price');
        document.getElementById('editStockQuantity').value = button.getAttribute('data-stock-quantity');
        document.getElementById('editCustomerQuantity').value = button.getAttribute('data-customer-quantity');
        document.getElementById('editDeliveryFee').value = button.getAttribute('data-delivery-fee');
        document.getElementById('editStartDate').value = button.getAttribute('data-start-date') || '';
        document.getElementById('editEndDate').value = button.getAttribute('data-end-date') || '';
        document.getElementById('editDetailExplain').value = button.getAttribute('data-detail-explain') || '';

        // 이미지 미리보기 처리
        var fileId = button.getAttribute('data-file-id');
        var previewDiv = document.getElementById('currentImagePreview');
        if (fileId) {
            previewDiv.innerHTML = '<img src="getImage.do?fileId=' + fileId + '" alt="' + button.getAttribute('data-product-name') + '" class="img-thumbnail" style="max-height: 100px;">';
        } else {
            previewDiv.innerHTML = '';
        }

        new bootstrap.Modal(document.getElementById('editProductModal')).show();
    }
    
    // 상품 삭제 모달 열기
    function deleteProduct(productNo, productName) {
        document.getElementById('deleteProductNo').value = productNo;
        document.getElementById('deleteProductName').textContent = productName;
        
        // 모달 열기
        new bootstrap.Modal(document.getElementById('deleteProductModal')).show();
    }
</script>
</body>
</html>