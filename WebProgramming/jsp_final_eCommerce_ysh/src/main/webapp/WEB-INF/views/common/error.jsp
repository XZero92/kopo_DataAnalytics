<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>오류 발생 - 쇼핑몰</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootswatch 테마 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/flatly/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card border-danger">
                    <div class="card-header bg-danger text-white">
                        <h4 class="mb-0">오류가 발생했습니다</h4>
                    </div>
                    <div class="card-body">
                        <p class="card-text">
                            <% 
                            String errorMsg = (String)request.getAttribute("errorMessage");
                            if (errorMsg != null && !errorMsg.isEmpty()) {
                                out.println(errorMsg);
                            } else {
                                out.println("알 수 없는 오류가 발생했습니다. 나중에 다시 시도해주세요.");
                            }
                            %>
                        </p>
                        <hr>
                        <a href="main.do" class="btn btn-primary">홈으로 돌아가기</a>
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