<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<head>
    <title>판매 관리</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" />
    <style>
        table {
            font-size: 14px;
        }

        .table th,
        .table td {
            text-align: center;
            vertical-align: middle !important;
        }

        .table th {
            font-size: 16px;
        }

        .btn {
            padding: 4px 8px;
            font-size: 12px;
        }

        .btn-outline-secondary:hover,
        .btn-outline-primary:hover {
            color: #fff;
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .btn-outline-primary {
            color: #007bff;
        }

        .btn-outline-primary:hover {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-group-vertical > .btn,
        .btn-group > .btn {
            text-align: center;
        }

        .pagination {
            margin-top: 20px;
        }

        .pagination .page-item.active .page-link {
            z-index: 3;
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
        }

        .pagination .page-link {
            color: #007bff;
            border: 1px solid #007bff;
        }

        .pagination .page-link:hover {
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
        }
    </style>
</head>
<body class="d-flex flex-column">
<main class="flex-shrink-0">
    <section class="bg-light py-5">
        <div class="container px-5 my-5">
            <div class="text-center mb-5">
                <h1 class="fw-bolder" onclick="goToFirstPage()" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">판매관리</h1>
            </div>

            <!-- 검색 폼 -->
            <form id="searchForm" class="row gx-2 justify-content-center mb-4" method="get" action="/seller/manageProduct">
                <input type="hidden" name="sellerIdx" value="${sellerIdx}"/>
                <input type="hidden" name="productIdx" value="${productIdx}"/>
                <div class="col-lg-2">
                    <select class="form-select" name="searchField" id="select-option">
                        <option value="customer">고객명</option>
                    </select>
                </div>
                <div class="col-lg-4">
                    <div class="input-group">
                        <input type="text" id="searchWord" name="searchWord" class="form-control" placeholder="검색어를 입력하세요"/>
                        <button class="btn btn-primary" type="submit" <c:if test="${empty detailMyPd}">disabled</c:if>>검색</button>
                    </div>
                </div>
            </form>

            <c:if test="${empty detailMyPd}">
                <p class="text-center" style="font-size: 20px; font-weight: bold;">주문된 상품이 없습니다.</p>
                <script>
                    function goBack() {
                        window.history.back();
                    }
                </script>
                <button class="btn btn-secondary float-end" onclick="goBack()">뒤로가기</button>
            </c:if>
            <c:if test="${not empty detailMyPd}">



            <table class="table table-bordered">
                <thead class="table-dark">
                <tr>
                    <th style="width: 5%;">번호</th>
                    <th style="width: 10%;">상품명</th>
                    <th style="width: 10%;">고객명</th>
                    <th style="width: 10%;">가격</th>
                    <th style="width: 5%;">개수</th>
                    <th style="width: 10%;">총 가격</th>
                    <th style="width: 15%;">주문날짜</th>
                    <th style="width: 20%;">배송지</th>
                    <th style="width: 10%;">주문 상태</th>
                </tr>
                </thead>
                <c:forEach items="${detailMyPd}" var="sellDetail" varStatus="loop">
                    <tr>
                        <c:choose>
                            <c:when test="${searchWord eq '' || searchWord eq null}">
                                <td>${orderManagePaging.totalCount - ((orderManagePaging.page - 1) * orderManagePaging.pageLimit + loop.index)}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${orderManageSearchPaging.totalCount - ((orderManageSearchPaging.page - 1) * orderManageSearchPaging.pageLimit + loop.index)}</td>
                            </c:otherwise>
                        </c:choose>
                            <td>${sellDetail.productName}</td>
                            <td>${sellDetail.customerName}</td>
                            <td>${sellDetail.productPrice}</td>
                            <td>${sellDetail.productCount}</td>
                            <td>${sellDetail.productTotalPrice}</td>
                            <td>${sellDetail.orderDate}</td>
                            <td>${sellDetail.orderAddress}</td>
                            <td>${sellDetail.orderStatus}</td>
                    </tr>
                </c:forEach>
            </table>

            <!-- 페이지네이션 추가 -->
            <div class="d-flex justify-content-center mt-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${searchWord eq null}">
                            <c:choose>
                                <c:when test="${orderManagePaging.page > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="/seller/manageProduct?page=${orderManagePaging.page - 1}&sellerIdx=${sellerIdx}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo; 이전</span>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <span class="page-link" aria-hidden="true">&laquo; 이전</span>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                            <!-- 페이지 번호 -->
                            <c:forEach begin="${orderManagePaging.startPage}" end="${orderManagePaging.endPage}" var="i" step="1">
                                <li class="page-item">
                                    <c:choose>
                                        <c:when test="${i eq orderManagePaging.page}">
                                            <span class="page-link current">${i}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="page-link" href="/seller/manageProduct?page=${i}&sellerIdx=${sellerIdx}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}">${i}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>

                            <!-- 다음 페이지 -->
                            <c:choose>
                                <c:when test="${orderManagePaging.page < orderManagePaging.maxPage}">
                                    <li class="page-item">
                                        <a class="page-link" href="/seller/manageProduct?page=${orderManagePaging.page + 1}&sellerIdx=${sellerIdx}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Next">
                                            <span aria-hidden="true">다음 &raquo;</span>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <span class="page-link" aria-hidden="true">다음 &raquo;</span>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:if>



                        <c:if test="${searchWord ne null}">
                            <c:choose>
                                <c:when test="${orderManageSearchPaging.page > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="/seller/manageProduct?page=${orderManageSearchPaging.page - 1}&sellerIdx=${sellerIdx}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo; 이전</span>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <span class="page-link" aria-hidden="true">&laquo; 이전</span>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                            <!-- 페이지 번호 -->
                            <c:forEach begin="${orderManageSearchPaging.startPage}" end="${orderManageSearchPaging.endPage}" var="i" step="1">
                                <li class="page-item">
                                    <c:choose>
                                        <c:when test="${i eq orderManageSearchPaging.page}">
                                            <span class="page-link current">${i}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="page-link" href="/seller/manageProduct?page=${i}&sellerIdx=${sellerIdx}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}">${i}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>

                            <!-- 다음 페이지 -->
                            <c:choose>
                                <c:when test="${orderManageSearchPaging.page < orderManageSearchPaging.maxPage}">
                                    <li class="page-item">
                                        <a class="page-link" href="/seller/manageProduct?page=${orderManageSearchPaging.page + 1}&sellerIdx=${sellerIdx}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Next">
                                            <span aria-hidden="true">다음 &raquo;</span>
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <span class="page-link" aria-hidden="true">다음 &raquo;</span>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </section>
</main>
</body>
<script>
    // jQuery를 사용하여 페이지 이동 함수 정의
    function goToFirstPage() {
        window.location.href = "/seller/manageProduct?page=1&sellerIdx=${sellerIdx}&productIdx=${productIdx}";
    }
</script>
</html>