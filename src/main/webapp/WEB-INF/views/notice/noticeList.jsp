<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <h2>Hello Springframework</h2>
    <a href="${pageContext.request.contextPath}/board/save">글작성</a>
    <a href="${pageContext.request.contextPath}/board">글목록</a>
</body>
</html>
