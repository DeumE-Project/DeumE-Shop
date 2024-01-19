<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <title>상품리뷰리스트</title>
    <script>
        const savefn = () => {
            const productIdx = '${productReview.productIdx}';
            location.href = "/product/review/save";
        }
    </script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1>사진 리뷰 목록</h1>
    <table class="table table-striped">
        <thead>
        <tr>
<%--            <th>번호</th>--%>
            <th>리뷰사진</th>
            <th>작성자</th>
            <th>내용</th>
            <th>별점</th>
            <th>작성시간</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="productIdx" value=""/>
        <c:forEach items="${pagingList}" var="review">
            <tr onclick="location.href='/product/review?reviewIdx=${review.reviewIdx}'" style="cursor: pointer;">
                <c:set var="productIdx" value="${review.productIdx}"/>
<%--                <td>${review.reviewIdx}</td>--%>
                <c:set var="reviewDate" value="${fn:substring(review.reviewDate, 0, 10)}" />
                <td><img src="/review/${reviewDate}/${review.reviewThumbSaved}" alt="리뷰 이미지" class="img-fluid"></td>
                <td>${review.customerId}</td>
                <td>
                    <c:choose>
                        <c:when test="${fn:length(review.reviewContent) gt 50}">
                            <%--데이터 출력시 c:out을 통해 문자그대로 출력 (html태그 등등 이용방지)--%>
                            <c:out value="${fn:substring(review.reviewContent, 0, 50)}....."/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${review.reviewContent}"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:forEach var="i" begin="1" end="5">
                    <c:choose>
                        <c:when test="${i <= review.reviewStar}">
                            ★
                        </c:when>
                        <c:otherwise>
                            ☆
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </td>
                <td>${review.reviewDateStr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container mt-3">

    <ul class="pagination">

        <li class="page-item ${paging.page <= 1 ? 'disabled' : ''}">
            <a class="page-link" href="javascript:void(0);" onclick="loadReviewList(${paging.page-1})" aria-label="Previous">
                이전
            </a>
        </li>


        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
            <li class="page-item ${paging.page eq i ? 'active' : ''}">
                <a class="page-link" href="javascript:void(0);" onclick="loadReviewList(${i})">${i}</a>
            </li>
        </c:forEach>


        <li class="page-item ${paging.page >= paging.maxPage ? 'disabled' : ''}">
            <a class="page-link" href="javascript:void(0);" onclick="loadReviewList(${paging.page+1})" aria-label="Next">
                다음
            </a>
        </li>
    </ul>
</div>
</body>
</html>