<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <title>detail</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <jsp:include page="#"/>

</head>
<body class="bg-light">
<div class="container mt-4">
    <table class="table">
        <input type="hidden" value="${productReview.productIdx}">
        <tr>
            <th>리뷰사진</th>
            <td><img src="/review/${productReview.reviewImgSaved}" alt="리뷰 이미지" class="img-fluid"></td>
        </tr>
        <tr>
            <th>번호</th>
            <td>${productReview.reviewIdx}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${productReview.customerIdx}</td>
        </tr>
        <tr>
            <th>리뷰내용</th>
            <td><c:out value="${productReview.reviewContent}"/></td>
        </tr>
        <tr>
            <th>리뷰별점</th>
            <td>${productReview.reviewStar}</td>
        </tr>
        <tr>
            <th>작성시간</th>
            <td>${productReview.reviewDateStr}</td>
        </tr>
    </table>

    <div class="mb-3">
        <button class="btn btn-secondary" onclick="listFn()">목록</button>
        <button class="btn btn-warning" onclick="updateFn()">수정</button>
        <button class="btn btn-danger" onclick="deleteFn()">삭제</button>
    </div>

</div>

</body>
<script>
    const listFn = () => {
        const page = '${page}';
        location.href = "/product/review/paging?page=" + page;
    }
    const updateFn = () => {
        const reviewIdx = '${productReview.reviewIdx}';
        location.href = "/product/review/update?reviewIdx=" + reviewIdx;
    }
    const deleteFn = () => {
        const reviewIdx = `${productReview.reviewIdx}`;

        const isConfirmed = window.confirm("정말 삭제하시겠습니까?");

        if (isConfirmed) {
            alert("삭제 되었습니다.");
            location.href = "/product/review/delete?reviewIdx=" + reviewIdx;
        } else {
            alert("삭제가 취소되었습니다.");
        }
    };
</script>
</html>