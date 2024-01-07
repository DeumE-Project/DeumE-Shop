<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품상세페이지</title>

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/common/styles.css" rel="stylesheet">
    <!-- jQuery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid py-5">
    <div class="row px-xl-5 bg-light" style="display: flex; align-items: center;">
        <div class="col-lg-5 pb-5">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner border">
                    <div class="carousel-item active">
                        <img class="w-100 h-100"
                             src="${pageContext.request.contextPath}/resources/common/cart/product_main_img_sample.jpg" alt="Image">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-7 pb-5">
            <h1 class="font-weight-semi-bold">[상품명]</h1>
            <div class="d-flex mb-3">
                <p><span class="text-primary">[4] / 5</span>&nbsp;&nbsp;<span>([50] 리뷰)</span></p>
            </div>
            <div>
                판매가 <h2 style="display: inline-block;" class="font-weight-semi-bold mb-4" id="product-price">[25000]</h2>원
            </div>
            <p class="mb-4">Volup erat ipsum diam elitr rebum et dolor. Est nonumy elitr erat diam stet sit clita ea.
                Sanc invidunt ipsum et, labore clita lorem magna lorem ut. Erat lorem duo dolor no sea nonumy. Accus
                labore stet, est lorem sit diam sea et justo, amet at lorem et eirmod ipsum diam et rebum kasd
                rebum.</p>
            <div>
                구매가 <h4 style="display: inline-block;"><span id="product-total-price">25000</span></h4>원
            </div>
            <div class="d-flex align-items-center mb-4 pt-2">
                <div class="input-group quantity mr-3" style="width: 150px;">
                    <div class="input-group-btn" style="margin-right: 10px;">
                        <button class="btn btn-primary btn-minus" id="btn-minus">
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
            </div>
        </div>
    </div>
    <div style="display: flex; justify-content: center;">
        <img src="${pageContext.request.contextPath}/resources/common/cart/product_detail_img_sample.jpg"
             style="max-width: 100%;">
    </div>
</div>

<script>
    const productIdx = 1;
    const productPrice = '25000';

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
        formTag.action = '${pageContext.request.contextPath}' + '/customer/add-cart';

        const productIdxTagForm = document.createElement('input');
        productIdxTagForm.setAttribute('name', 'productIdx');
        productIdxTagForm.setAttribute('value', productIdx);

        const buyCountTagForm = document.createElement('input');
        buyCountTagForm.setAttribute('name', 'buyCount');
        buyCountTagForm.setAttribute('value', buyCountTag.val());

        document.body.appendChild(formTag).submit();
    });
</script>
</body>
</html>
