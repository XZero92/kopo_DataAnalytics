<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>카테고리 관리 - 쇼핑몰</title>
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
        .category-tree-item {
            margin: 5px 0;
            padding: 8px;
            border-radius: 4px;
            background-color: #f8f9fa;
        }
        .category-tree-item:hover {
            background-color: #e9ecef;
        }
        .level-1 { margin-left: 0; }
        .level-2 { margin-left: 20px; }
        .level-3 { margin-left: 40px; }
        .level-4 { margin-left: 60px; }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp" />

<div class="container mt-4">
    <h2 class="mb-4">관리자 페이지 - 카테고리 관리</h2>

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
                    <a class="nav-link active" href="manage_categories.do">상품 카테고리 관리</a>
                    <a class="nav-link" href="manage_products.do">상품 관리</a>
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

            <!-- 카테고리 관리 섹션 -->
            <div class="card mb-4">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="section-title">카테고리 목록</h4>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCategoryModal">
                            <i class="bi bi-plus"></i> 새 카테고리 추가
                        </button>
                    </div>
                    
                    <!-- 카테고리 트리 목록 -->
                    <div class="category-tree">
                        <!-- 레벨 1 카테고리 (최상위 카테고리) -->
                        <c:forEach var="category" items="${categories}">
                            <c:if test="${category.level == 1}">
                                <div class="category-tree-item level-1">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <span>
                                            <strong>${category.categoryName}</strong>
                                            <c:if test="${not empty category.explain}">
                                                <span class="text-muted"> - ${category.explain}</span>
                                            </c:if>
                                        </span>
                                        <div class="btn-group">
                                            <button class="btn btn-sm btn-outline-primary" 
                                                onclick="editCategory(${category.categoryNo}, '${category.categoryName}', '${category.explain}', '${category.useYn}')">
                                                수정
                                            </button>
                                            <button class="btn btn-sm btn-outline-danger" 
                                                onclick="deleteCategory(${category.categoryNo}, '${category.categoryName}')">
                                                삭제
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- 레벨 2 카테고리 (하위 카테고리) -->
                                <c:forEach var="subCategory" items="${categories}">
                                    <c:if test="${subCategory.parentCategoryNo == category.categoryNo}">
                                        <div class="category-tree-item level-2">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <span>
                                                    <strong>${subCategory.categoryName}</strong>
                                                    <c:if test="${not empty subCategory.explain}">
                                                        <span class="text-muted"> - ${subCategory.explain}</span>
                                                    </c:if>
                                                </span>
                                                <div class="btn-group">
                                                    <button class="btn btn-sm btn-outline-primary" 
                                                        onclick="editCategory(${subCategory.categoryNo}, '${subCategory.categoryName}', '${subCategory.explain}', '${subCategory.useYn}')">
                                                        수정
                                                    </button>
                                                    <button class="btn btn-sm btn-outline-danger" 
                                                        onclick="deleteCategory(${subCategory.categoryNo}, '${subCategory.categoryName}')">
                                                        삭제
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <!-- 레벨 3 카테고리 -->
                                        <c:forEach var="level3Category" items="${categories}">
                                            <c:if test="${level3Category.parentCategoryNo == subCategory.categoryNo}">
                                                <div class="category-tree-item level-3">
                                                    <div class="d-flex justify-content-between align-items-center">
                                                        <span>
                                                            <strong>${level3Category.categoryName}</strong>
                                                            <c:if test="${not empty level3Category.explain}">
                                                                <span class="text-muted"> - ${level3Category.explain}</span>
                                                            </c:if>
                                                        </span>
                                                        <div class="btn-group">
                                                            <button class="btn btn-sm btn-outline-primary" 
                                                                onclick="editCategory(${level3Category.categoryNo}, '${level3Category.categoryName}', '${level3Category.explain}', '${level3Category.useYn}')">
                                                                수정
                                                            </button>
                                                            <button class="btn btn-sm btn-outline-danger" 
                                                                onclick="deleteCategory(${level3Category.categoryNo}, '${level3Category.categoryName}')">
                                                                삭제
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 카테고리 추가 모달 -->
<div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addCategoryModalLabel">새 카테고리 추가</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="add_category.do" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="parentCategoryId" class="form-label">상위 카테고리</label>
                        <select class="form-select" id="parentCategoryId" name="parentCategoryId">
                            <option value="0">최상위 카테고리</option>
                            <c:forEach var="category" items="${categories}">
                                <c:if test="${category.level <= 2}"> <!-- 최대 3단계까지만 허용 -->
                                    <option value="${category.categoryNo}">${category.fullCategoryName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="categoryName" class="form-label">카테고리 이름</label>
                        <input type="text" class="form-control" id="categoryName" name="categoryName" required>
                    </div>
                    <div class="mb-3">
                        <label for="explanation" class="form-label">설명</label>
                        <textarea class="form-control" id="explanation" name="explanation" rows="3"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">카테고리 추가</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 카테고리 수정 모달 -->
<div class="modal fade" id="editCategoryModal" tabindex="-1" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editCategoryModalLabel">카테고리 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="update_category.do" method="post">
                <div class="modal-body">
                    <input type="hidden" id="editCategoryId" name="categoryId">
                    <div class="mb-3">
                        <label for="editCategoryName" class="form-label">카테고리 이름</label>
                        <input type="text" class="form-control" id="editCategoryName" name="categoryName" required>
                    </div>
                    <div class="mb-3">
                        <label for="editExplanation" class="form-label">설명</label>
                        <textarea class="form-control" id="editExplanation" name="explanation" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="editUseYn" name="useYn" value="Y">
                            <label class="form-check-label" for="editUseYn">
                                카테고리 활성화
                            </label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">카테고리 수정</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 카테고리 삭제 확인 모달 -->
<div class="modal fade" id="deleteCategoryModal" tabindex="-1" aria-labelledby="deleteCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteCategoryModalLabel">카테고리 삭제 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>카테고리 "<span id="deleteCategoryName"></span>"를 삭제하시겠습니까?</p>
                <p class="text-danger">주의: 이 작업은 되돌릴 수 없으며, 하위 카테고리에도 영향을 줄 수 있습니다.</p>
            </div>
            <div class="modal-footer">
                <form action="delete_category.do" method="post">
                    <input type="hidden" id="deleteCategoryId" name="categoryId">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-danger">카테고리 삭제</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 의존성 -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

<script>
    function editCategory(categoryId, categoryName, explanation, useYn) {
        document.getElementById('editCategoryId').value = categoryId;
        document.getElementById('editCategoryName').value = categoryName;
        document.getElementById('editExplanation').value = explanation || '';
        document.getElementById('editUseYn').checked = useYn === 'Y';
        
        // 모달 열기
        new bootstrap.Modal(document.getElementById('editCategoryModal')).show();
    }
    
    function deleteCategory(categoryId, categoryName) {
        document.getElementById('deleteCategoryId').value = categoryId;
        document.getElementById('deleteCategoryName').textContent = categoryName;
        
        // 모달 열기
        new bootstrap.Modal(document.getElementById('deleteCategoryModal')).show();
    }
</script>
</body>
</html>