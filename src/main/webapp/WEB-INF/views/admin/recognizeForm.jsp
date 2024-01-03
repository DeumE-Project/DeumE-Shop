<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Seller registration approval page</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Optional custom styles */
        body {
            padding-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>판매자 가입 승인 <페이지></페이지></h2>
    <div>
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>이메일</th>
                <th>전화번호</th>
                <th>승인 여부</th>
<%--                <th>Reject</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${notRecognizedList}" var="seller">
                <tr>
                    <td>${seller.sellerId}</td>
                    <td>${seller.sellerName}</td>
                    <td>${seller.sellerEmail}</td>
                    <td>${seller.sellerPhone}</td>
                    <td><button class="btn btn-success" onclick="acceptFn('${seller.sellerId}')">승인</button>&nbsp<button class="btn btn-danger" onclick="rejectFn('${seller.sellerId}')">거절</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Bootstrap 5 JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>
<script>
    const acceptFn = (id) => {
        console.log(id);
        location.href = "/admin/accept?id=" + id;
    }
    const rejectFn = (id) => {
        console.log(id);
        location.href = "/admin/reject?id=" + id;
    }
</script>
</body>
</html>