<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <title>list</title>
    <jsp:include page="#"/>
</head>

<body class="bg-light">
<div class="container mt-3">
    <table class="table table-striped"  id="comment-list">
        <tr>
            <th>번호</th>
            <th>작성자</th>
            <th>내용</th>
            <th>작성시간</th>
        </tr>
        <c:forEach items="${commentList}" var="comment">
            <tr>
                <td>${comment.commentIdx}</td>
                <td><c:out value="${comment.commentWriter}"/></td>
                <td><c:out value="${comment.commentContents}"/><</td>
                <td>${comment.commentCreatedTimeStr}</td>
            </tr>
        </c:forEach>
    </table>
    <button class="btn btn-warning" onclick="savefn()">리뷰 등록</button>
</div>
<script>
    const savefn = () => {
        location.href = "/product/comment/save";
    }
</script>
</body>
</html>
