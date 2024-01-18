<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<head>
    <title>상품상세페이지</title>

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
</head>

<div class="container-fluid py-5">
    <div class="row px-xl-5 bg-light" style="display: flex; align-items: center;">
        <div class="col-lg-5 pb-5">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner border">
                    <div class="carousel-item active">
                        <img class="w-100 h-100"
                             src="${pageContext.request.contextPath}/product/${productDTO.productImgSaved}" alt="Image">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-7 pb-5">
            <h1 class="font-weight-semi-bold">
                <c:out value="${productDTO.productName}"/>
            </h1>
            <div class="d-flex mb-3">
                <p>판매자: <c:out value="${productDTO.sellerName}"/></p>
            </div>
            <div class="d-flex mb-3">
                <c:set var="star" value=""/>
                <c:if test="${reviewStarSum eq 0}">
                    <c:set var="star" value="☆☆☆☆☆"/>
                </c:if>
                <c:if test="${reviewStarSum eq 1}">
                    <c:set var="star" value="★☆☆☆☆"/>
                </c:if>
                <c:if test="${reviewStarSum eq 2}">
                    <c:set var="star" value="★★☆☆☆"/>
                </c:if>
                <c:if test="${reviewStarSum eq 3}">
                    <c:set var="star" value="★★★☆☆"/>
                </c:if>
                <c:if test="${reviewStarSum eq 4}">
                    <c:set var="star" value="★★★★☆"/>
                </c:if>
                <c:if test="${reviewStarSum eq 5}">
                    <c:set var="star" value="★★★★★"/>
                </c:if>
                <p><span class="text-primary" style="font-weight: bolder">${star} / 5</span>&nbsp;&nbsp;<span>(${reviewCount}개 리뷰)</span></p>
            </div>
            <div>
                판매가 <h2 style="display: inline-block;" class="font-weight-semi-bold mb-4" id="product-price">
                <fmt:formatNumber value="${productDTO.productPrice}" pattern="#,###"/></h2>원
            </div>
            <p class="mb-4">
                <c:out value="${productDTO.productExplain}"/>
            </p>
            <div>
                구매가 <h4 style="display: inline-block;">
                <span id="product-total-price">
                    <fmt:formatNumber value="${productDTO.productPrice}" pattern="#,###"/>
                </span>
            </h4>원
            </div>
            <div class="d-flex align-items-center mb-4 pt-2">
                <div class="input-group quantity mr-3" style="width: 150px;">
                    <div class="input-group-btn" style="margin-right: 10px;">
                        <button class="btn btn-light btn-minus" id="btn-minus">
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                    <input type="text" id="buy-count" class="form-control bg-light text-center" name="count" value="1" style="margin-right: 10px;" disabled>
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-plus" id="btn-plus">
                            <i class="fa fa-plus"></i>
                        </button>
                    </div>
                </div>
                <button class="btn btn-primary px-3" style="margin-left: 10px" id="add-to-cart">
                    <i class="fa fa-shopping-cart mr-1"></i>
                    장바구니에 추가
                </button>
               <%-- <button class="btn btn-link px-3" style="margin-left: 10px" type="button"
                        onclick="location.href='/product/review/paging?productIdx=${productDTO.productIdx}'">
                    <i class="fa fa-star mr-1"></i>
                    사진리뷰보기
                </button>--%>
                <button class="btn btn-link px-3" style="margin-left: 10px" type="button" onclick="loadReviewList(1)">
                    <i class="fa fa-star mr-1"></i>
                    사진리뷰보기
                </button>
<%--                <button class="btn btn-link px-3" style="margin-left: 10px" type="button"
                        onclick="location.href='/product/comment/paging?productIdx=${productDTO.productIdx}'">
                    <i class="fa fa-star mr-1"></i>
                    한줄리뷰보기
                </button>--%>
                <button class="btn btn-link px-3" style="margin-left: 10px" type="button" onclick="loadCommentList(1)">
                    <i class="fa fa-star mr-1"></i>
                    한줄리뷰보기
                </button>
            </div>
        </div>
    </div>
    <div style="display: flex; justify-content: center;">
        <img src="${pageContext.request.contextPath}/product/${productDTO.productDetailSaved}"
             style="max-width: 100%;">
    </div>
</div>
<div id="reviewListContainer"></div>
<script>
    function loadReviewList(page) {
        // Make an AJAX request to the controller with pagination parameters
        $.ajax({
            url: '/product/review/paging',
            type: 'GET',
            data: {
                productIdx: '<c:out value="${productDTO.productIdx}"/>',
                page: page // Add the current page parameter
            },
            success: function (data) {
                // Append the received review list HTML to the container
                $('#reviewListContainer').html(data);
            },
            error: function () {
                // Handle error if needed
                console.error('Failed to fetch review list.');
            }
        });
    }
</script>
<script>
    function loadCommentList(page) {
        // Make an AJAX request to the controller with pagination parameters
        $.ajax({
            url: '/product/comment/paging',
            type: 'GET',
            data: {
                productIdx: '<c:out value="${productDTO.productIdx}"/>',
                page: page // Add the current page parameter
            },
            success: function (data) {
                // Append the received review list HTML to the container
                $('#reviewListContainer').html(data);
            },
            error: function () {
                // Handle error if needed
                console.error('Failed to fetch review list.');
            }
        });
    }
</script>
<script>
    const cartMsg = '<c:out value="${cartMsg}"/>';
    if (cartMsg.trim()) {
        alert(cartMsg);
    }
</script>

<script>
    const productIdx = '<c:out value="${productIdx}"/>';
    const productPrice = '<c:out value="${productDTO.productPrice}"/>';

    const productPriceTag = $('#product-price');
    const buyCountTag = $('#buy-count');
    const productTotalPriceTag = $('#product-total-price');

    $(document).ready(function () {
        let productPriceLocalStr = parseInt(productPrice).toLocaleString('ko-KR');
        productPriceTag.html(productPriceLocalStr);
        productTotalPriceTag.html(productPriceLocalStr);
    });

    $('#btn-minus').on('click', function () {
        const buyCount = buyCountTag.val();
        if (buyCount <= 1) {
            alert('구매 수량은 최소 한 개부터 가능합니다');
            buyCountTag.val(1);
            calculatePrice();
            return;
        }
        buyCountTag.val(buyCount - 1);
        calculatePrice();
    });

    $('#btn-plus').on('click', function () {
        const buyCount = buyCountTag.val();
        buyCountTag.val(parseInt(buyCount) + 1);
        calculatePrice();
    });

    const calculatePrice = () => {
        productTotalPriceTag.html((productPrice * buyCountTag.val()).toLocaleString('ko-KR'));
    }

    $('#add-to-cart').on('click', function () {
        const formTag = document.createElement('form');
        formTag.method = 'post';
        formTag.action = '${pageContext.request.contextPath}' + '/cart/add-cart';

        const productIdxTagForm = document.createElement('input');
        productIdxTagForm.setAttribute('name', 'productIdx');
        productIdxTagForm.setAttribute('value', productIdx);
        formTag.appendChild(productIdxTagForm);

        const buyCountTagForm = document.createElement('input');
        buyCountTagForm.setAttribute('name', 'buyCount');
        buyCountTagForm.setAttribute('value', buyCountTag.val());
        formTag.appendChild(buyCountTagForm);

        document.body.appendChild(formTag).submit();
    });
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
