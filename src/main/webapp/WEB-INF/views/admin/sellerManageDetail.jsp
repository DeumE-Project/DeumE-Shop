<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<head>
    <title>판매자 '${seller.sellerId}' 정보 </title>
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

        th {
            width: 120px;
            height: 100px;
            text-align: center;
            vertical-align: middle;
            font-size: x-large;
        }
        td{
            font-size: 18px;
            vertical-align: middle;
            padding-left: 30px;
        }
        .center-buttons {
            text-align: center;
            margin-top: 20px;
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
        <h1 class="mt-3">판매자 '${seller.sellerId}' 정보</h1>

        <table class="table table-bordered mt-4">
            <tr>
                <th class="table-dark">아이디</th>
                <td>${seller.sellerId}</td>
                <th class="table-dark">대표자명</th>
                <td>${seller.sellerName}</td>
                <th class="table-dark">사업자<br>등록번호</th>
                <td>${seller.sellerTaxId}</td>
            </tr>
            <tr>
                <th class="table-dark">이메일</th>
                <td >${seller.sellerEmail}</td>
                <th class="table-dark">전화번호</th>
                <td>${seller.sellerPhone}</td>
                <th class="table-dark">수익</th>
                <td>${seller.sellerIncome}</td>
            </tr>
            <tr>
                <th class="table-dark">가입날짜</th>
                <td>${seller.sellerJoinedDateStr}</td>
                <th class="table-dark">사업장<br>소재지</th>
                <td class="no-center" colspan="3">
                    [${seller.sellerZipcode}]<br>
                    ${seller.sellerAddress1}<br>
                    ${seller.sellerAddress2}
                </td>

            </tr>
        </table>

        <div class="center-buttons">
            <button onclick="listFn()" class="btn btn-primary">목록</button>
            <a class="btn btn-danger" data-bs-toggle="modal" data-sellerid="${seller.sellerId}"
               href="#banSellerModal" role="button" >정지</a>
        </div>
    </div>
</div>

<jsp:include page="sellerBanModal.jsp" flush="true"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    var BID="";
    var banReason="";
    $(document).ready(function() {
        $('#banSellerModal').on('show.bs.modal', function(event) {
            BID = $(event.relatedTarget).data('sellerid');
        });
    });

    const listFn = () => {
        const page = '${page}';
        location.href = "/admin/manage?page=" + page;
    }

    const banSellerFn = () => {
        if (banReason==0){
            Swal.fire({
                title: '정지 사유를 선택해 주세요',
                icon: 'info',
                confirmButtonText: '확인',
            });
            return false;
        }
        Swal.fire({
            title: '정지하시겠습니까?',
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
            showCancelButton: true,
            cancelButtonText: '취소',
        }).then((result) => {
            if (result.value) {
                Swal.fire({
                    title: '정지 완료',
                    icon: 'error',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: '확인',
                }).then((result) => {
                    if (result.value){
                        location.href = "/admin/reject?id=" + BID + "&rejectReason=" + banReason + "&type=2";
                    }
                })
            }
        })
    }

    function changeFn(){
        banReason  = document.getElementById("banReason");
        var value = (banReason.options[banReason.selectedIndex].value);
        banReason = value;
    }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>