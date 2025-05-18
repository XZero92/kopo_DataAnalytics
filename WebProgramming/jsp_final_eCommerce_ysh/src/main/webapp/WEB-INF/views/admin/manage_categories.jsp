<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>카테고리 관리 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Flatly 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
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
        .sortable-ghost {
            background-color: #f8f9fa;
            opacity: 0.5;
        }
        .sortable-drag {
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .sort-handle {
            cursor: move;
            margin-right: 10px;
            color: #dee2e6;
        }
        .sort-handle:hover {
            color: #adb5bd;
        }
    </style>
    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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
                        <div class="ms-auto">
                            <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateOrderModal">
                                카테고리 순서 변경
                            </button>
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCategoryModal">
                                <i class="bi bi-plus"></i> 새 카테고리 추가
                            </button>
                        </div>
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

<!-- 카테고리 순서 변경 모달 -->
<div class="modal fade" id="updateOrderModal" tabindex="-1" aria-labelledby="updateOrderModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form id="orderForm" action="update_category_order.do" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateOrderModalLabel">카테고리 순서 변경</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="alert alert-info">
                        <i class="bi bi-info-circle"></i> 화살표 버튼을 사용하여 같은 레벨 내에서만 순서를 변경할 수 있습니다.
                    </div>
                    <table id="sortableCategories" class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 10%">순서</th>
                                <th style="width: 40%">카테고리 이름</th>
                                <th style="width: 30%">설명</th>
                                <th style="width: 10%">활성화</th>
                                <th style="width: 10%">작업</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- 대분류 -->
                            <c:forEach var="cat1" items="${categories}">
                                <c:if test="${cat1.level == 1}">
                                    <tr data-level="${cat1.level}" data-parent="0">
                                        <td>
                                            <input type="hidden" name="categoryId" value="${cat1.categoryNo}">
                                            <input type="hidden" name="order" value="${cat1.order}">
                                            ${cat1.order}
                                        </td>
                                        <td>${cat1.categoryName}</td>
                                        <td>${cat1.explain}</td>
                                        <td>${cat1.useYn == 'Y' ? '활성화' : '비활성화'}</td>
                                        <td>
                                            <div class="btn-group btn-group-sm">
                                                <button type="button" class="btn btn-outline-primary" onclick="moveUp(this.closest('tr'))">
                                                    <i class="bi bi-arrow-up"></i>
                                                </button>
                                                <button type="button" class="btn btn-outline-primary" onclick="moveDown(this.closest('tr'))">
                                                    <i class="bi bi-arrow-down"></i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                    <!-- 중분류 -->
                                    <c:forEach var="cat2" items="${categories}">
                                        <c:if test="${cat2.parentCategoryNo == cat1.categoryNo}">
                                            <tr data-level="${cat2.level}" data-parent="${cat1.categoryNo}">
                                                <td>
                                                    <input type="hidden" name="categoryId" value="${cat2.categoryNo}">
                                                    <input type="hidden" name="order" value="${cat2.order}">
                                                    ${cat2.order}
                                                </td>
                                                <td style="padding-left: 20px">${cat2.categoryName}</td>
                                                <td>${cat2.explain}</td>
                                                <td>${cat2.useYn == 'Y' ? '활성화' : '비활성화'}</td>
                                                <td>
                                                    <div class="btn-group btn-group-sm">
                                                        <button type="button" class="btn btn-outline-primary" onclick="moveUp(this.closest('tr'))">
                                                            <i class="bi bi-arrow-up"></i>
                                                        </button>
                                                        <button type="button" class="btn btn-outline-primary" onclick="moveDown(this.closest('tr'))">
                                                            <i class="bi bi-arrow-down"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            <!-- 소분류 -->
                                            <c:forEach var="cat3" items="${categories}">
                                                <c:if test="${cat3.parentCategoryNo == cat2.categoryNo}">
                                                    <tr data-level="${cat3.level}" data-parent="${cat2.categoryNo}">
                                                        <td>
                                                            <input type="hidden" name="categoryId" value="${cat3.categoryNo}">
                                                            <input type="hidden" name="order" value="${cat3.order}">
                                                            ${cat3.order}
                                                        </td>
                                                        <td style="padding-left: 40px">${cat3.categoryName}</td>
                                                        <td>${cat3.explain}</td>
                                                        <td>${cat3.useYn == 'Y' ? '활성화' : '비활성화'}</td>
                                                        <td>
                                                            <div class="btn-group btn-group-sm">
                                                                <button type="button" class="btn btn-outline-primary" onclick="moveUp(this.closest('tr'))">
                                                                    <i class="bi bi-arrow-up"></i>
                                                                </button>
                                                                <button type="button" class="btn btn-outline-primary" onclick="moveDown(this.closest('tr'))">
                                                                    <i class="bi bi-arrow-down"></i>
                                                                </button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">순서 저장</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function editCategory(categoryId, categoryName, explanation, useYn) {
        document.getElementById('editCategoryId').value = categoryId;
        document.getElementById('editCategoryName').value = categoryName;
        document.getElementById('editExplanation').value = explanation;
        document.getElementById('editUseYn').checked = useYn === 'Y';
        var editModal = new bootstrap.Modal(document.getElementById('editCategoryModal'));
        editModal.show();
    }

    function deleteCategory(categoryId, categoryName) {
        if (confirm(`카테고리 "${categoryName}"를 삭제하시겠습니까?`)) {
            location.href = `delete_category.do?categoryId=${categoryId}`;
        }
    }

    function moveUp(row) {
        var prev = findPreviousSameLevel(row);
        if (prev) {
            swapRows(row, prev);
        }
    }

    function moveDown(row) {
        var next = findNextSameLevel(row);
        if (next) {
            swapRows(row, next);
        }
    }

    function findPreviousSameLevel(row) {
        var level = row.getAttribute('data-level');
        var parent = row.getAttribute('data-parent');
        var prev = row.previousElementSibling;

        while (prev) {
            if (prev.getAttribute('data-level') === level &&
                prev.getAttribute('data-parent') === parent) {
                return prev;
            }
            prev = prev.previousElementSibling;
        }
        return null;
    }

    function findNextSameLevel(row) {
        var level = row.getAttribute('data-level');
        var parent = row.getAttribute('data-parent');
        var next = row.nextElementSibling;

        while (next) {
            if (next.getAttribute('data-level') === level &&
                next.getAttribute('data-parent') === parent) {
                return next;
            }
            next = next.nextElementSibling;
        }
        return null;
    }

    // 부모 카테고리를 찾는 함수
    function findParentCategory(row) {
        const parentId = row.getAttribute('data-parent');
        if (parentId === '0') return null;

        const rows = Array.from(row.parentNode.children);
        return rows.find(r => r.querySelector('input[name="categoryId"]')?.value === parentId);
    }

    // 계층형 순서 번호를 생성하는 함수
    function formatOrderNumber(row) {
        const level = parseInt(row.getAttribute('data-level'));
        const orderNumber = row.querySelector('input[name="order"]').value;

        if (level === 1) {
            return orderNumber;
        } else {
            let parent = findParentCategory(row);
            let prefix = [];
            while (parent) {
                prefix.unshift(parent.querySelector('input[name="order"]').value);
                parent = findParentCategory(parent);
            }
            return prefix.join('.') + '.' + orderNumber;
        }
    }

    // 모든 행의 순서 번호를 업데이트하는 함수
    function updateAllOrderNumbers() {
        const tbody = document.querySelector('#sortableCategories tbody');
        const rows = Array.from(tbody.children);

        rows.forEach(row => {
            const orderCell = row.cells[0];
            const formattedNumber = formatOrderNumber(row);
            // hidden input은 그대로 두고 표시되는 텍스트만 변경
            orderCell.lastChild.textContent = formattedNumber;
        });
    }

    function swapRows(row1, row2) {
        // 이동할 행들의 범위와 모든 하위 항목 찾기
        var parent = row1.parentNode;
        var rows = Array.from(parent.children);

        // 각 행과 그 하위 항목들을 포함하는 배열 생성
        var group1 = getRowGroupWithChildren(row1, rows);
        var group2 = getRowGroupWithChildren(row2, rows);

        // 순서값 교환 (부모 행만)
        var order1 = row1.querySelector('input[name="order"]').value;
        var order2 = row2.querySelector('input[name="order"]').value;
        row1.querySelector('input[name="order"]').value = order2;
        row2.querySelector('input[name="order"]').value = order1;

        // 표시되는 순서값 교환 (부모 행만)
        var text1 = row1.cells[0].lastChild.textContent.trim();
        var text2 = row2.cells[0].lastChild.textContent.trim();
        row1.cells[0].lastChild.textContent = text2;
        row2.cells[0].lastChild.textContent = text1;

        // row1이 row2보다 앞에 있는 경우
        if (rows.indexOf(row1) < rows.indexOf(row2)) {
            // group2의 마지막 요소 다음에 group1의 모든 요소를 삽입
            var insertPoint = group2[group2.length - 1].nextElementSibling;
            group1.forEach(row => parent.insertBefore(row, insertPoint));

            // group1의 원래 위치에 group2의 모든 요소를 삽입
            insertPoint = group1[0];
            group2.forEach(row => parent.insertBefore(row, insertPoint));
        } else {
            // group1의 마지막 요소 다음에 group2의 모든 요소를 삽입
            var insertPoint = group1[group1.length - 1].nextElementSibling;
            group2.forEach(row => parent.insertBefore(row, insertPoint));

            // group2의 원래 위치에 group1의 모든 요소를 삽입
            insertPoint = group2[0];
            group1.forEach(row => parent.insertBefore(row, insertPoint));
        }

        // 모든 행의 순서 번호 업데이트
        updateAllOrderNumbers();
    }

    // 특정 행과 그 모든 하위 항목을 포함하는 배열을 반환하는 함수
    function getRowGroupWithChildren(row, allRows) {
        var result = [row];
        var level = parseInt(row.getAttribute('data-level'));
        var startIndex = allRows.indexOf(row) + 1;

        // 현재 행 이후의 모든 행을 검사
        for (var i = startIndex; i < allRows.length; i++) {
            var currentRow = allRows[i];
            var currentLevel = parseInt(currentRow.getAttribute('data-level'));

            // 현재 레벨보다 높은 레벨(하위 항목)인 경우에만 추가
            if (currentLevel > level) {
                result.push(currentRow);
            } else {
                // 같거나 낮은 레벨을 만나면 중단
                break;
            }
        }

        return result;
    }

    document.getElementById('orderForm').addEventListener('submit', function(e) {
        e.preventDefault();

        // 모든 input 요소를 배열로 변환하여 처리
        var rows = document.querySelectorAll('#sortableCategories tr');
        var formData = new FormData();

        rows.forEach(function(row, index) {
            // 기존 데이터
            formData.append('categoryIds[]', row.querySelector("input[name='categoryId']").value);
            formData.append('orders[]', row.querySelector("input[name='order']")?.value || (index + 1) * 10);

            // 계층 구조 데이터 추가
            formData.append('levels[]', row.getAttribute('data-level'));
            formData.append('parents[]', row.getAttribute('data-parent'));
        });

        // AJAX 요청
        fetch('update_category_order.do', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('순서 변경 중 오류가 발생했습니다.');
            }
            return response;
        })
        .then(() => {
            location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
            alert(error.message);
        });
    });

    // 페이지 로드 시 초기 순서 번호 포맷팅
    document.addEventListener('DOMContentLoaded', function() {
        updateAllOrderNumbers();
    });
</script>
</body>
</html>

