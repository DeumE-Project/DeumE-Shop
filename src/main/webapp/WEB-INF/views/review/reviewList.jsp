<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <script>
        const savefn = () => {
            const productIdx = '${productReview.productIdx}';
            location.href = "/product/review/save";
        }
    </script>
    <title>list</title>
    <jsp:include page="#"/>
</head>

<body class="bg-light">
<div class="container mt-3">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>번호</th>
            <th>리뷰사진</th>
            <th>작성자</th>
            <th>내용</th>
            <th>별점</th>
            <th>작성시간</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviewList}" var="review">
            <tr>
                <td><a href="/product/review?reviewIdx=${review.reviewIdx}">${review.reviewIdx}</a></td>
                <c:set var="reviewDate" value="${fn:substring(review.reviewDate, 0, 10)}" />
                <td><a href="#"><img src="/review/${reviewDate}/${review.reviewThumbSaved}" alt="리뷰 이미지" class="img-fluid"></a></td>
                <td>${review.customerIdx}</td>
                <td>
                    <c:choose>
                        <c:when test="${fn:length(review.reviewContent) gt 50}">
                            <c:out value="${fn:substring(review.reviewContent, 0, 50)}....."/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${review.reviewContent}"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${review.reviewStar}</td>
                <td>${review.reviewDateStr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-warning" onclick="savefn()">리뷰 등록</button>
</div>
</body>
</html>