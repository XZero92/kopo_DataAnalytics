<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html data-bs-theme="auto">
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="write_view.do" method="post">
    <button type="submit" class="btn btn-primary">작성 뷰</button>
</form>
<form action="list.do" method="post">
    <button type="submit" class="btn btn-primary">글 목록</button>
</form>
</body>
</html>