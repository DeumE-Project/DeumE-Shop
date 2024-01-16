<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<head>
    <title>판매자 거절 회원 목록</title>
<%--    <script src="https://code.jquery.com/jquery-3.7.1.min.js"--%>
<%--            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>--%>
    <!-- Bootstrap 5 CSS -->
<%--    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">--%>
    <style>
        nav.mh {
            height: 100%;
            width: 15%;
            position: fixed;
            left: 0;
            background-color: #111;
            padding-top: 20px;
            padding-left: 20px;
            color: white;
        }
        .admin-title {
            font-size: 24px; /* Larger font size for Administrator */
            font-weight: bold; /* Optional: Make it bold */
            margin-bottom: 20px; /* Add some space below the Administrator title */
        }

        nav.mh a {
            padding: 10px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
            border-bottom: 1px solid #333;
        }

        nav.mh a:hover {
            background-color: #555;
        }

        .container .mh {
            margin-left: 10%; /* Set the margin in percentage */
            padding: 5%;
        }

        tr{
            font-size: 18px;
            text-align: center;
            vertical-align: middle;
        }
        .th-1{
            width: 9%;
        }
        .th-2{
            width: 35%;
        }
        .th-3{
            width: 15%;
        }
        .th-4{
            width: 12%;
        }
        .th-5{
            width: 22%;
        }
    </style>
</head>
<body>
<nav class="mh">
    <div>
        <div class="admin-title">관리자</div>
        <a href="/admin/manage">판매자 관리</a>
        <a href="/admin/change">판매자 거부 목록</a>
        <a href="/admin/recognize">판매자 가입 승인</a>
        <a href="/notice/">공지사항 관리</a>
    </div>
</nav>

<div class="container">
    <div class="mh">
        <h1>판매자 거절 회원 목록</h1> <br>
        <c:if test="${empty rejectSellerList}">
            <div class="text-center my-5">
                <h3 class="fw-bolder">거절 상태인 판매자가 없습니다.</h3>
            </div>
        </c:if>
        <c:if test="${not empty rejectSellerList}">
            <div>
                <table class="table table-bordered">
                    <thead class="table-dark">
                    <tr>
                        <th class="th-1">아이디</th>
                        <th class="th-1">대표자명</th>
                        <th class="th-3">사업자등록번호</th>
                        <th class="th-2">거절 사유</th>
                        <th class="th-5">전화번호</th>
                        <th class="th-4">승인 변경</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${rejectSellerList}" var="seller">
                        <tr>
                            <td>${seller.sellerId}</td>
                            <td>${seller.sellerName}</td>
                            <td>${seller.sellerTaxId}</td>
                            <td>${seller.sellerRejectReason}</td>
                            <td>${seller.sellerPhone}</td>
                            <td><button class="btn btn-success" onclick="acceptFn('${seller.sellerId}')">승인</button>
                            </td>
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
                                <a class="page-link" href="${pageContext.request.contextPath}/admin/change?page=${paging.page - 1}" aria-label="Previous">이전</a>
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
                                    <a class="page-link" href="${pageContext.request.contextPath}/admin/change?page=${i}">${i}</a>
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
                                <a class="page-link" href="${pageContext.request.contextPath}/admin/change?page=${paging.page + 1}" aria-label="Next">다음</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </c:if>
    </div>
</div>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    const acceptFn = (id) => {
        console.log(id);
        Swal.fire({
            title: '승인하시겠습니까?',
            text: '거절된 판매자를 승인하시겠습니까?',
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
            showCancelButton: true,
            cancelButtonText: '취소',
        }).then((result) => {
            if (result.value) {
                Swal.fire({
                    title: '승인 완료',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '확인',
                }).then((result) => {
                    if (result.value){
                        location.href = "/admin/accept?id=" + id + "&type=2";
                    }
                })
            }
        })
    }
</script>
</body>
</html>
