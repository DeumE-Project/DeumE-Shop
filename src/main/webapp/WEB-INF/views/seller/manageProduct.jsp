<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>판매 관리</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"--%>
<%--            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"--%>
<%--            crossorigin="anonymous"></script>--%>


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
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>
</head>
<body class="d-flex flex-column">
<div class="contentset">
<main class="flex-shrink-0">
    <section class="bg-light py-5">
        <div class="container px-5 my-5">
            <div class="text-center mb-5">
                <h1 class="fw-bolder" onclick="goToFirstPage()" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">판매관리</h1>
            </div>

            <!-- 검색 폼 -->
            <form id="searchForm" class="row gx-2 justify-content-center mb-4" method="get" action="/seller/manageProduct">
<%--                <input type="hidden" name="sellerIdx" value="${sellerIdx}"/>--%>
                <input type="hidden" name="productIdx" value="${productIdx}"/>
                <div class="col-lg-2">
                    <select class="form-select" name="searchField" id="select-option">
                        <option value="customer">고객명</option>
                    </select>
                </div>
                <div class="col-lg-4">
                    <div class="input-group">
                        <input type="text" id="searchWord" name="searchWord" class="form-control" placeholder="검색어를 입력하세요"/>
                        <input type="text" name="page" value="1" style="display: none;"/> <!-- Add this line -->
                        <button class="btn btn-primary" type="submit" <c:if test="${empty detailMyPd}">disabled</c:if>>검색</button> <%--리스트가 없을 경우 검색 x--%>
                    </div>
                </div>
            </form>

            <%--리스트가 없을 경우 문구--%>
            <c:if test="${empty detailMyPd}">
                <p class="text-center" style="font-size: 20px; font-weight: bold;">주문된 상품이 없습니다.</p>
                <script>
                    function goBack() {
                        window.history.back();
                    }
                </script>
                <button class="btn btn-secondary float-end" onclick="goBack()">뒤로가기</button>
            </c:if>


            <%--리스트가 있을 경우--%>
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
                    <th style="width: 10%;">배송상태</th>
                </tr>
                </thead>
                <c:forEach items="${detailMyPd}" var="sellDetail" varStatus="loop">
                    <tr>
                        <%--검색 값 유무에 따라 구분해서 번호 계산--%>
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
                        <td><c:out value='${sellDetail.orderDate.toString().replaceAll("T", " ")}'/></td>
                        <td>${sellDetail.orderAddress}</td>
                        <td>
                                <%--                                <a class="btn btn-danger" data-bs-toggle="modal" data-orderidx="${sellDetail.orderIdx}"--%>
                                <%--                                   href="#updateModal" role="button" onclick="updateOrderIdx('${sellDetail.orderIdx}')">${sellDetail.orderStatus}</a>--%>
                            <button type="button" class="btn btn-${sellDetail.orderStatus eq '배송완료' ? 'success' : 'danger'}" data-bs-toggle="modal" data-bs-target="#updateModal" onclick="updateOrderIdx('${sellDetail.orderIdx}')">
                                    ${sellDetail.orderStatus}
                            </button>
                            <%--배송상태 수정을 위한 모달버튼--%>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <!-- 페이지네이션 추가 -->
            <div class="d-flex justify-content-center mt-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination">

                        <%--검색단어가 없을 경우 페이징--%>
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
<%--                                        <a class="page-link" href="/seller/manageProduct?page=${orderManagePaging.page + 1}&sellerIdx=${sellerIdx}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Next">--%>
                                        <a class="page-link" href="/seller/manageProduct?page=${orderManagePaging.page + 1}&productIdx=${productIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Next">
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


                        <%--검색단어가 있을 경우 페이징--%>
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
</div>
<script>
    var sellerIdx = ${sellerIdx};
    var productIdx = ${productIdx};
    var searchField = "${param.searchField}";
    var searchWord = "${param.searchWord}";
</script>

<%--모달창 include--%>
<jsp:include page="updateModal.jsp" flush="true"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<%--모달창에서 데이터 처리--%>
<script>
    var RID="";
    var updateStatus="";

    var orderIdx = "";

    $(document).ready(function() {
        $('#updateModal').on('show.bs.modal', function(event) {
            RID = $(event.relatedTarget).data('orderidx');
        });
    });

    const updateFn = () => {
        console.log(RID);
        if (updateStatus==0){
            Swal.fire({
                title: '배송상태를 선택해주세요',
                icon: 'info',
                confirmButtonText: '확인',
            });
            return false;
        }
        Swal.fire({
            title: '수정하시겠습니까?',
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
            showCancelButton: true,
            cancelButtonText: '취소',
        }).then((result) => {
            if (result.value) {
                Swal.fire({
                    title: '수정 완료',
                    icon: 'success',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '확인',
                }).then((result) => {
                    if (result.value){
                        location.href = "/seller/updateStatus?updateStatus="+ updateStatus + "&productIdx=" + ${param.productIdx} + "&orderIdx=" + orderIdx + "&page=" + ${page} + "&searchWord=" + '${param.searchWord}';
                    }
                })
            }
        })
    }

    function changeFn(){
        updateStatus  = document.getElementById("updateStatus");
        var value = (updateStatus.options[updateStatus.selectedIndex].value);
        // alert("value = "+value);
        updateStatus = value;
        console.log(updateStatus);
    }


    function updateOrderIdx(order_idx) {
        orderIdx = order_idx;
    }
</script>

<script>
    // jQuery를 사용하여 페이지 이동 함수 정의
    function goToFirstPage() {
        window.location.href = "/seller/manageProduct?page=1&productIdx=${productIdx}";

    }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>


</html>