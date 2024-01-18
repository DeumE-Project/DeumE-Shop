<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<head>
    <title>detail</title>
    <style>
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>
</head>
<body class="bg-light">
<div class="contentset">
<div class="container mt-4">
    <table class="table">
        <input type="hidden" value="${productReview.productIdx}">
        <tr>
            <th>리뷰사진</th>
            <td><img src="/review/${productReview.reviewImgSaved}" alt="리뷰 이미지" class="img-fluid"></td>
        </tr>
<%--        <tr>--%>
<%--            <th>번호</th>--%>
<%--            <td>${productReview.reviewIdx}</td>--%>
<%--        </tr>--%>
        <tr>
            <th>작성자</th>
            <td>${productReview.customerId}</td>
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

    <sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="pinfo" />
    <c:if test="${(pinfo.idx eq productReview.customerIdx)}">
        <div>
            <button class="btn btn-warning" onclick="updateFn()">수정</button>
            <button class="btn btn-danger" onclick="deleteFn()">삭제</button>
        </div>
    </c:if>
    </sec:authorize>


</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
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
            location.href = "/product/review/delete?reviewIdx=" + reviewIdx + "&productIdx=" + ${productReview.productIdx};
        } else {
            alert("삭제가 취소되었습니다.");
        }
    };
</script>
</html>