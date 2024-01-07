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
                    <a href="${pageContext.request.contextPath}/notice?idx=${notice.noticeIdx}&page=${paging.page}"
                       class="text-decoration-none">${notice.noticeTitle}</a>
                </td>
                <td align="center">${notice.noticeDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-flex justify-content-center mt-3">
        <ul class="pagination">

            <c:choose>
                <%-- If the current page is page 1, only the previous text is shown --%>
                <c:when test="${paging.page <= 1}">
                    <li class="page-item disabled">
                        <span class="page-link">이전</span>
                    </li>
                </c:when>
                <%-- If it is not page 1, clicking [Previous] will request a page 1 smaller than the current page --%>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/notice/?page=${paging.page - 1}" aria-label="Previous">이전</a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
                <c:choose>
                    <%-- If it is on the requested page, the current page number will only show text --%>
                    <c:when test="${i eq paging.page}">
                        <li class="page-item active" aria-current="page">
                            <span class="page-link">${i}</span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/notice/?page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${paging.page >= paging.maxPage}">
                    <li class="page-item disabled">
                        <span class="page-link">다음</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/notice/?page=${paging.page + 1}" aria-label="Next">다음</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

<!-- Bootstrap 5 JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>