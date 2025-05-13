<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>마이페이지 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Flatly 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
    <style>
        .mypage-menu {
            border-right: 1px solid #eee;
        }
        .mypage-menu .nav-link {
            color: #555;
            padding: 0.8rem 1rem;
            border-radius: 0;
        }
        .mypage-menu .nav-link.active {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #2C3E50;
            border-left: 3px solid #18BC9C;
        }
        .mypage-menu .nav-link:hover:not(.active) {
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
        <h2 class="mb-4">마이페이지</h2>
        
        <div class="row">
            <!-- 왼쪽 메뉴 -->
            <div class="col-md-3 mypage-menu">
                <div class="d-flex flex-column">
                    <div class="p-3 bg-light">
                        <h5>${user.userName}님</h5>
                        <p class="mb-0 text-muted">${user.email}</p>
                    </div>
                    <div class="nav flex-column nav-pills">
                        <a class="nav-link active" href="mypage.do">마이페이지 홈</a>
                        <a class="nav-link" href="update_profile.do">개인정보 수정</a>
                        <a class="nav-link" href="change_password.do">비밀번호 변경</a>
                        <a class="nav-link" href="order_history.do">주문 내역</a>
                        <a class="nav-link text-danger" href="unregister.do">회원 탈퇴</a>
                    </div>
                </div>
            </div>
            
            <!-- 오른쪽 콘텐츠 -->
            <div class="col-md-9">
                <!-- 사용자 정보 요약 -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h4 class="section-title">내 정보</h4>
                        <div class="row">
                            <div class="col-md-6">
                                <p><strong>이름:</strong> ${user.userName}</p>
                                <p><strong>이메일:</strong> ${user.email}</p>
                            </div>
                            <div class="col-md-6">
                                <p><strong>휴대폰 번호:</strong> ${user.mobileNo}</p>
                                <p><strong>회원 상태:</strong> 
                                    <span class="badge bg-success">활성</span>
                                </p>
                            </div>
                        </div>
                        <div class="mt-3">
                            <a href="update_profile.do" class="btn btn-outline-primary btn-sm">정보 수정</a>
                        </div>
                    </div>
                </div>
                
                <!-- 최근 주문 내역 -->
                <div class="card">
                    <div class="card-body">
                        <h4 class="section-title">최근 주문 내역</h4>
                        
                        <c:if test="${empty recentOrders}">
                            <div class="alert alert-info">
                                최근 주문 내역이 없습니다.
                            </div>
                        </c:if>
                        
                        <c:if test="${not empty recentOrders}">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>주문번호</th>
                                            <th>주문일자</th>
                                            <th>상품</th>
                                            <th>결제금액</th>
                                            <th>상태</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="order" items="${recentOrders}">
                                            <tr>
                                                <td>${order.orderId}</td>
                                                <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></td>
                                                <td>${order.itemSummary}</td>
                                                <td><fmt:formatNumber value="${order.totalAmount}" pattern="#,###" />원</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${order.status eq 'PENDING'}">
                                                            <span class="badge bg-warning">상품준비중</span>
                                                        </c:when>
                                                        <c:when test="${order.status eq 'SHIPPING'}">
                                                            <span class="badge bg-info">배송중</span>
                                                        </c:when>
                                                        <c:when test="${order.status eq 'COMPLETED'}">
                                                            <span class="badge bg-success">배송완료</span>
                                                        </c:when>
                                                        <c:when test="${order.status eq 'CANCELLED'}">
                                                            <span class="badge bg-danger">주문취소</span>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a href="order_detail.do?orderId=${order.orderId}" class="btn btn-sm btn-outline-secondary">상세보기</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="text-end">
                                <a href="order_history.do" class="btn btn-link">전체 주문내역 보기</a>
                            </div>
                        </c:if>
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