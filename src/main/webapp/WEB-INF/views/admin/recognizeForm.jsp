<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<head>
    <title>판매자 가입 승인 페이지</title>
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
        .th-1{
            width: 11%;
        }
        .th-2{
            width: 40%;
        }
        .th-3{
            width: 15%;
        }
        .th-4{
            width: 12%;
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
        <h1>판매자 가입 승인 페이지</h1> <br>

        <c:if test="${empty notRecognizedList}">
            <div class="text-center my-5">
                <h3 class="fw-bolder">가입 신청한 판매자가 없습니다</h3>
            </div>
        </c:if>
        <c:if test="${not empty notRecognizedList}">
            <div>
                <table class="table table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th class="th-1">아이디</th>
                            <th class="th-1">대표자명</th>
                            <th class="th-3">사업자등록번호</th>
                            <th class="th-2">사업장소재지</th>
                            <th class="th-4">승인 여부</th>
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
                                    <a class="btn btn-danger" data-bs-toggle="modal" data-sellerid="${seller.sellerId}"
                                       href="#rejectModal" role="button" >거절</a>
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
        </c:if>
    </div>
</div>

<jsp:include page="rejectModal.jsp" flush="true"/>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    var RID="";
    var rejectReason="";
    $(document).ready(function() {
        $('#rejectModal').on('show.bs.modal', function(event) {
            RID = $(event.relatedTarget).data('sellerid');
        });
    });

    const acceptFn = (id) => {
        console.log(id);
        Swal.fire({
            title: '승인하시겠습니까?',
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
                        location.href = "/admin/accept?id=" + id + "&type=1";
                    }
                })
            }
        })
    }
    const rejectFn = () => {
        console.log(RID);
        if (rejectReason==0){
            Swal.fire({
                title: '거절 이유를 선택해 주세요',
                icon: 'info',
                confirmButtonText: '확인',
            });
            return false;
        }
        Swal.fire({
            title: '거절하시겠습니까?',
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
            showCancelButton: true,
            cancelButtonText: '취소',
        }).then((result) => {
            if (result.value) {
                Swal.fire({
                    title: '거절 완료',
                    icon: 'error',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '확인',
                }).then((result) => {
                    if (result.value){
                        location.href = "/admin/reject?id=" + RID + "&rejectReason="+ rejectReason + "&type=1";
                    }
                })
            }
        })
    }

    function changeFn(){
        rejectReason  = document.getElementById("rejectReason");
        var value = (rejectReason.options[rejectReason.selectedIndex].value);
        rejectReason = value;
    }
</script>
</body>
</html>