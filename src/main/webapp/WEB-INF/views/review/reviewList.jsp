<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <table class="table table-striped">
        <thead>
        <tr>
            <th>번호</th>
 <%--           <th>작성자</th>--%>
            <th>내용</th>
            <th>별점</th>
            <th>파일이름</th>
            <th>작성시간</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviewList}" var="review">
            <tr>
                <td>${review.reviewIdx}</td>
<%--                <td>${review.customerId}</td>--%>
                <td>${review.reviewContent}</td>
                <td>${review.reviewStar}</td>
                <td>${review.reviewImgSaved}</td>
                <td>${review.reviewDate}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>