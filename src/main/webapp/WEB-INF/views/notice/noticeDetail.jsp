<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>detail.jsp</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Optional custom styles */
        body {
            padding-top: 20px;
        }
        /* Style for table headers */
        th {
            width: 120px; /* Adjust as needed */
            text-align: center;
            vertical-align: middle;
            font-size: x-large;
        }
        td{
            font-size: x-large;
        }
        .center-buttons {
            text-align: center;
        }
        /* Set minimum height for content */
        .content-section {
            font-size: x-large;
            height: 300px;
            overflow: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>공지사항 상세보기</h1><br>
    <table class="table table-bordered">
        <tr>
            <th class="table-dark">제목</th>
            <td><c:out value="${notice.noticeTitle}"/></td>
        </tr>
        <tr>
            <th class="table-dark">작성일</th>
            <td>${notice.noticeDateStr}</td>
        </tr>
        <tr>
            <th class="table-secondary">내용</th>
            <td colspan="3" class="content-section"><c:out value="${notice.noticeContent}"/></td>
        </tr>
    </table>
    <div class="center-buttons">
        <button onclick="listFn()" class="btn btn-primary">목록</button>
        <button onclick="updateFn()" class="btn btn-secondary">수정</button>
        <button onclick="deleteFn()" class="btn btn-danger">삭제</button>
    </div>
</div>
<!-- Bootstrap 5 JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>
<script>
    const listFn = () => {
        const page = '${page}';
        location.href = "/notice/?page=" + page;
    }
    const updateFn = () => {
        const idx = '${notice.noticeIdx}';
        location.href = "/notice/update?idx=" + idx;
    }
    const deleteFn = () => {
        const idx = '${notice.noticeIdx}';
        location.href = "/notice/delete?idx=" + idx;
    }
</script>
</body>
</html>