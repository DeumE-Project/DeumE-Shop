<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니 목록</title>

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

    <style>
        .outer-cart-div {
            margin-bottom: 100px;
        }

        @media screen and (max-width: 1199px) {
            .seller-name {
                padding-left: 0px;
            }
        }
        @media screen and (min-width: 1200px) {
            .seller-name {
                padding-left: 3rem;
            }
        }
    </style>
</head>
<body>
<!-- Page Header Start -->
<div class="container-fluid">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">장바구니 목록</h1>
        <div class="d-inline-flex">
            <p class="m-0">장바구니 개수: <c:out value="${cartList.size()}"/>개</p>
        </div>
    </div>
</div>

<!-- Cart Start -->
<div class="container-fluid"> <!--가장 바깥쪽 div 시작-->

    <c:choose>
        <c:when test="${empty cartList}">
            <h3>장바구니에 담긴 상품이 없습니다</h3>
        </c:when>
        <c:otherwise>
            <c:forEach items="${cartList}" var="cart" varStatus="outerIndex"><!--장바구니 전체 outer for-->
                <c:set var="oneCartTotal" value="0"/>
                <!-- 여기서부터 장바구니 한 개 시작 -->
                <div class="outer-cart-div">
                    <h5 class="seller-name">
                        [<c:out value="${outerIndex.index + 1}"/>]&nbsp;&nbsp;
                        판매자 이름: <c:out value="${cart.sellerName}"/>
                    </h5>
                    <div class="row px-xl-5">
                        <div class="col-lg-8 table-responsive mb-5">
                            <table class="table table-bordered text-center mb-0">
                                <thead class="text-dark" style="background-color: #EDF1FF">
                                <tr>
                                    <th>번호</th>
                                    <th>상품</th>
                                    <th>가격(&#8361;)</th>
                                    <th>수량</th>
                                    <th>합계(&#8361;)</th>
                                    <th>삭제</th>
                                </tr>
                                </thead>
                                <tbody class="align-middle ancestor-tbody">
                                <c:forEach items="${cart.cartDetailDTOList}" var="cartDetail" varStatus="innerIndex">
                                    <tr>
                                        <td class="align-middle"><c:out value="${innerIndex.index + 1}"/></td>
                                        <td class="align-middle"><img src="img/product-1.jpg" alt=""
                                                                      style="width: 100px; height: 100px;">
                                            <c:out value="${cartDetail.productName}"/>
                                        </td>
                                        <td class="align-middle product-price" id="product-price-${cartDetail.cartDetailIdx}"><c:out value="${cartDetail.productPrice}"/></td>
                                        <td class="align-middle">
                                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                                <div class="input-group-btn" style="margin-right: 5px;">
                                                    <button class="btn btn-sm btn-primary btn-minus" id="minus-button"
                                                            data-buttontype="minus"
                                                            data-cartdetailidx='<c:out value="${cartDetail.cartDetailIdx}"/>'
                                                            onclick="minusBtnClick(event)">
                                                        <i class="fa fa-minus"
                                                           data-buttontype="minus"
                                                           data-cartdetailidx='<c:out value="${cartDetail.cartDetailIdx}"/>'
                                                        ></i>
                                                    </button>
                                                </div>
                                                <input id='buy-count-<c:out value="${cartDetail.cartDetailIdx}"/>'
                                                       type="text"
                                                       class="form-control form-control-sm bg-light text-center buy-count"
                                                       value='<c:out value="${cartDetail.buyCount}"/>'
                                                       style="margin-right: 5px;" readonly>
                                                <div class="input-group-btn">
                                                    <button class="btn btn-sm btn-primary btn-plus"
                                                            data-buttontype="plus"
                                                            data-cartdetailidx='<c:out value="${cartDetail.cartDetailIdx}"/>'
                                                            onclick="plusBtnClick(event)">
                                                        <i class="fa fa-plus"
                                                           data-buttontype="plus"
                                                           data-cartdetailidx='<c:out value="${cartDetail.cartDetailIdx}"/>'
                                                        ></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="align-middle" id="sub-product-total-${cartDetail.cartDetailIdx}"><c:out
                                                value="${cartDetail.buyCount * cartDetail.productPrice}"/></td>
                                        <td class="align-middle">
                                            <button class="btn btn-sm btn-warning"
                                                    data-buttontype="delete"
                                                    data-cartdetailidx='<c:out value="${cartDetail.cartDetailIdx}"/>'
                                                    onclick="deleteBtnClick(event)">
                                                <i class="fa fa-times"
                                                   data-buttontype="delete"
                                                   data-cartdetailidx='<c:out value="${cartDetail.cartDetailIdx}"/>'
                                                ></i></button>
                                        </td>
                                    </tr>
                                    <c:set var="oneCartTotal"
                                           value="${oneCartTotal + (cartDetail.buyCount * cartDetail.productPrice)}"/>
                                </c:forEach><!--한 개의 장바구니에 담겨있는 각 상품의 detail-->
                                </tbody>
                            </table>
                        </div>
                        <div class="col-lg-4">
                            <div class="card border-secondary mb-5">
                                <div class="card-header border-0" style="background-color: #EDF1FF">
                                    <h4 class="font-weight-semi-bold m-0">주문 요약</h4>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex justify-content-between mb-3 pt-1">
                                        <h6 class="font-weight-medium">물품</h6>
                                        <h6 class="font-weight-medium"><c:out value="${oneCartTotal}"/></h6>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <h6 class="font-weight-medium">배송</h6>
                                        <h6 class="font-weight-medium">2,500</h6>
                                    </div>
                                </div>
                                <div class="card-footer border-secondary bg-transparent">
                                    <div class="d-flex justify-content-between mt-2">
                                        <h5 class="font-weight-bold">합계(&#8361;)</h5>
                                        <h5 class="font-weight-bold"><c:out value="${oneCartTotal + 2500}"/></h5>
                                    </div>
                                    <button class="btn btn-block btn-primary my-3 py-3" style="width: 100%;">주문하기
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--장바구니 한 개 끝-->
            </c:forEach><!--장바구니 전체 outer for 끝-->
        </c:otherwise>
    </c:choose>
</div> <!--가장 바깥쪽 div 끝-->

<script>
    const decreaseBuyCount = function (cartDetailIdx, event) {
        let buyCount = $('#buy-count-' + cartDetailIdx).val();
        console.log('checkBuyCount - buyCount = ' + buyCount);
        if (buyCount <= 1) {
            alert('최소 수량은 한 개 입니다');
        } else {
            $('#buy-count-' + cartDetailIdx).attr('value', buyCount - 1);
            sendAjaxRequestUpdateCartDetail(cartDetailIdx);
            recalculateSubTotalPrice(cartDetailIdx);
            recalculateTotalProductPriceWithoutDeliveryFee(event);
        }
    }

    const extractCartDetailIdx = function (event) {
        const clickedTag = $(event.target);
        const cartDetailIdxStr = clickedTag.data('cartdetailidx');
        const cartDetailIdx = parseInt(cartDetailIdxStr);
        console.log("cartDetailIdx = " + cartDetailIdx);
        return cartDetailIdx;
    }

    const increaseBuyCount = function (cartDetailIdx, event) {
        let buyCount = $('#buy-count-' + cartDetailIdx).val();
        $('#buy-count-' + cartDetailIdx).attr('value', parseInt(buyCount) + 1);

        sendAjaxRequestUpdateCartDetail(cartDetailIdx);
        recalculateSubTotalPrice(cartDetailIdx);
        recalculateTotalProductPriceWithoutDeliveryFee(event);
    }

    const sendAjaxRequestUpdateCartDetail = function (cartDetailIdx) {
        $.ajax({
            url: '/cart/change-cart-detail-buy-count',
            method: 'post',
            contentType: 'application/x-www-form-urlencoded',
            //dataType: 'text',
            data: {
                cartDetailIdx: cartDetailIdx,
                buyCount: $('#buy-count-' + cartDetailIdx).val()
            },
            async: false,
            success: function (result) {
                console.log('success');
                console.log(result);
            },
            error: function (errorMsg) {
                console.log('error');
                console.log(errorMsg);
            }
        });
    }

    const minusBtnClick = function (event) {
        event.stopPropagation();
        console.log("minusBtn");

        const cartDetailIdx = extractCartDetailIdx(event);

        decreaseBuyCount(cartDetailIdx, event);
    }

    const plusBtnClick = function (event) {
        event.stopPropagation();
        console.log("plusBtn");

        const cartDetailIdx = extractCartDetailIdx(event);

        increaseBuyCount(cartDetailIdx, event);
    }

    const deleteBtnClick = function (event) {
        event.stopPropagation();
        console.log("deleteBtn");
    }

    const recalculateSubTotalPrice = function (cartDetailIdx) {
        // const testPrice = parseInt('15,000,000'.replace(/,/g, ''), 10);
        const productPrice = parseInt($('#product-price-' + cartDetailIdx).text().replace(/,/g, ''), 10);
        const buyCount = parseInt($('#buy-count-' + cartDetailIdx).val());
        console.log('productPrice = ' + productPrice);
        console.log('buyCount = ' + buyCount);
        $('#sub-product-total-' + cartDetailIdx).html(productPrice * buyCount);
    }

    const recalculateTotalProductPriceWithoutDeliveryFee = function (event) {
        let total = 0;
        const eventTarget = $(event.target);
        console.log(eventTarget);
        let ancestorTbodyTag = eventTarget.closest('.ancestor-tbody');
        let allTrTags = ancestorTbodyTag.children('tr');
        console.log(allTrTags);
        $.each(allTrTags, function () {
            const price = commaStrToInt($(this).find('.product-price').html());
            const buyCount = commaStrToInt($(this).find('.buy-count').val());
            total += (price * buyCount);
        });
        console.log('calculatedPrice = ', total);
    }

    const commaStrToInt = function (str) {
        return parseInt(str.replace(/,/g, ''), 10);
    }
</script>
</body>
</html>
