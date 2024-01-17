<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

    <title>판매자 관리 페이지</title>
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
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
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
            margin-left: 10%;
            padding: 5%;
        }
        tr{
            font-size: 18px;
            text-align: center;
            vertical-align: middle;
        }
        a.mh-none {
            color: black;
            text-decoration: none;
        }
        .th-1{
            width: 20%;
        }
        .th-2{
            width: 30%;
        }
    </style>
</head>
<body>

<nav class="mh">
    <div>
        <div class="admin-title">관리자</div>
        <a class="mh" href="/admin/manage">판매자 관리</a>
        <a class="mh" href="/admin/change">판매자 거부 목록</a>
        <a class="mh" href="/admin/recognize">판매자 가입 승인</a>
        <a class="mh" href="/notice/">공지사항 관리</a>
    </div>
</nav>

<div class="container">
    <div class="mh">
        <h1>판매자 관리 페이지</h1> <br>
        <c:if test="${empty recognizedSellerList}">
            <div class="text-center my-5">
                <h3 class="fw-bolder">등록된 판매자가 없습니다.</h3>
            </div>
        </c:if>
        <c:if test="${not empty recognizedSellerList}">
            <div>
                <table class="table table-bordered">
                    <thead class="table-dark">
                    <tr>
                        <th class="th-1">아이디</th>
                        <th class="th-1">대표자명</th>
                        <th class="th-2">사업자등록번호</th>
                        <th class="th-2">가입 날짜</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${recognizedSellerList}" var="seller">
                        <tr>
                            <td>
                                <a class="mh-none"
                                   href="/admin/info?id=${seller.sellerId}&page=${paging.page}">
                                        ${seller.sellerId}
                                </a>
                            </td>
                            <td>${seller.sellerName}</td>
                            <td>${seller.sellerTaxId}</td>
                            <td>${seller.sellerJoinedDateStr}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="d-flex justify-content-center mt-3">
                <ul class="pagination">

                    <c:choose>
                        <c:when test="${paging.page <= 1}">
                            <li class="page-item disabled">
                                <span class="page-link">이전</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/admin/manage?page=${paging.page - 1}"
                                   aria-label="Previous">이전</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
                        <c:choose>
                            <c:when test="${i eq paging.page}">
                                <li class="page-item active" aria-current="page">
                                    <span class="page-link">${i}</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/admin/manage?page=${i}">${i}</a>
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
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/admin/manage?page=${paging.page + 1}"
                                   aria-label="Next">다음</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </c:if>
    </div>
</div>

<jsp:include page="sellerBanModal.jsp" flush="true"/>

</body>
</html>
