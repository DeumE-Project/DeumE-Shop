<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
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
    <h1>한줄 리뷰 게시판</h1>
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
        <c:choose>
            <c:when test="${paging.page<=1}">
                <li class="page-item disabled">
                    <span class="page-link">이전</span>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="/product/comment/paging?page=${paging.page-1}&productIdx=${productIdx}" aria-label="Previous">
                        이전
                    </a>
                </li>
            </c:otherwise>
        </c:choose>

        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
            <c:choose>
                <c:when test="${i eq paging.page}">
                    <li class="page-item active">
                        <span class="page-link">${i}</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="/product/comment/paging?page=${i}&productIdx=${productIdx}">
                                ${i}
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${paging.page>=paging.maxPage}">
                <li class="page-item disabled">
                    <span class="page-link">다음</span>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="/product/comment/paging?page=${paging.page+1}&productIdx=${productIdx}" aria-label="Next">
                        다음
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
</body>
</html>