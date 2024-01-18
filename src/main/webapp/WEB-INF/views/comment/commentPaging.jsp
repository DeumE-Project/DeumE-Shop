<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <title>paging</title>
    <jsp:include page="#"/>

</head>
<body class="bg-light">
<div class="container mt-3">
    <h1>한줄 리뷰 목록</h1>
    <table class="table table-striped"  id="comment-list">
        <tr>
            <th>작성자</th>
            <th>내용</th>
            <th>작성시간</th>
        </tr>
        <c:set var="productIdx" value=""/>
        <c:forEach items="${pagingList}" var="comment">
            <c:set var="productIdx" value="${comment.productIdx}"/>
            <tr>
                <td>${comment.commentWriter}</td>
                <td>${comment.commentContents}</td>
                <td>${comment.commentCreatedTimeStr}</td>
            </tr>
        </c:forEach>
    </table>

</div>


<div class="container mt-3">

<ul class="pagination">

    <li class="page-item ${paging.page <= 1 ? 'disabled' : ''}">
        <a class="page-link" href="javascript:void(0);" onclick="loadCommentList(${paging.page-1})" aria-label="Previous">
            이전
        </a>
    </li>

    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <li class="page-item ${paging.page eq i ? 'active' : ''}">
            <a class="page-link" href="javascript:void(0);" onclick="loadCommentList(${i})">${i}</a>
        </li>
    </c:forEach>


    <li class="page-item ${paging.page >= paging.maxPage ? 'disabled' : ''}">
        <a class="page-link" href="javascript:void(0);" onclick="loadCommentList(${paging.page+1})" aria-label="Next">
            다음
        </a>
    </li>
</ul>
</div>
</body>
</html>