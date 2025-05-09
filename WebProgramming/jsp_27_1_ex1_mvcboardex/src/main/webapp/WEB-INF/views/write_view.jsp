<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 작성</title>
    <!-- Bootswatch Zephyr Theme CSS CDN 링크 -->
    <link href="https://bootswatch.com/5/zephyr/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>게시글 작성</h2>
        <form action="write.do" method="post">
            <div class="mb-3">
                <label for="author" class="form-label">작성자:</label>
                <input type="text" class="form-control" id="author" name="author" required>
            </div>
            <div class="mb-3">
                <label for="title" class="form-label">제목:</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용:</label>
                <textarea class="form-control" id="content" name="content" rows="10" required></textarea>
            </div>
            <div class="text-end">
                <button type="submit" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>

    <!-- Bootstrap JS Bundle (Popper 포함) - 선택 사항, 일부 컴포넌트에 필요 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>