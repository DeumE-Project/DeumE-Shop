<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>공지사항</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Optional custom styles */
        body {
            padding-top: 20px;
        }
        .wider-column {
            width: 70%; /* Adjust the width as needed */
        }
        .float-end {
            float: right;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>공지사항</h2><br>

    <div class="d-flex justify-content-end mb-3">
        <a href="${pageContext.request.contextPath}/notice/save" class="btn btn-primary float-end">글작성</a>
    </div>

    <table class="table table-bordered">
        <thead>
        <tr align="center">
            <th>idx</th>
            <th class="wider-column">제목</th>
            <th>작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${noticeList}" var="notice">
            <tr>
                <td align="center">${notice.noticeIdx}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/notice?idx=${notice.noticeIdx}" class="text-decoration-none">${notice.noticeTitle}</a>
                </td>
                <td align="center">${notice.noticeDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Bootstrap 5 JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>