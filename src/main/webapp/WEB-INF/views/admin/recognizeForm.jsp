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
        tr{
            font-size: x-large;
            text-align: center;
            vertical-align: middle;
        }
        .th-1{
            width: 9%;
        }
        .th-2{
            width: 50%;
        }
        .th-3{
            width: 15%;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>판매자 가입 승인 페이지</h1> <br>
    <div>
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th class="th-1">아이디</th>
                <th class="th-1">대표자명</th>
                <th class="th-3">사업자등록번호</th>
                <th class="th-2">사업장소재지</th>
                <th class="th-3">승인 여부</th>
<%--                <th>Reject</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${notRecognizedList}" var="seller">
                <tr>
                    <td>${seller.sellerId}</td>
                    <td>${seller.sellerName}</td>
                    <td>${seller.sellerTaxId}</td>
                    <td>${seller.sellerAddress1}</td>
                    <td><button class="btn btn-success" onclick="acceptFn('${seller.sellerId}')">승인</button>
                        <button class="btn btn-danger" onclick="rejectFn('${seller.sellerId}')">거절</button>
                        <a class="btn btn-danger" data-bs-toggle="modal" href="#exampleModalToggle" role="button">모달 거절</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

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
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/recognize?page=${paging.page - 1}" aria-label="Previous">이전</a>
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
                            <a class="page-link" href="${pageContext.request.contextPath}/admin/recognize?page=${i}">${i}</a>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/recognize?page=${paging.page + 1}" aria-label="Next">다음</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>

<div class="modal fade" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalToggleLabel">Modal 1</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Show a second modal and hide this one with the button below.
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal">Open second modal</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalToggle2" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalToggleLabel2">Modal 2</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Hide this modal and show the first with the button below.
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-bs-target="#exampleModalToggle" data-bs-toggle="modal">Back to first</button>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap 5 JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>
<script>
    const acceptFn = (id) => {
        console.log(id);
        const page = '${paging.page}';
        location.href = "/admin/accept?id=" + id +"&page=" + page;
    }
    const rejectFn = (id) => {
        console.log(id);
        location.href = "/admin/reject?id=" + id;
    }
</script>
</body>
</html>