<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Product Detail</title>
    <!-- 부트스트랩 5 CSS 링크 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 나눔고딕 폰트 링크 추가 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-nanum@1.0.16/dist/nanum.min.css">
    <style>

        body {
            background-color: #f8f9fa;
            font-family: 'Nanum Gothic', sans-serif; /* 나눔고딕 폰트 적용 */
        }

        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            /*box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);*/
            margin-top: 20px;
        }

        h2 {
            color: #007bff;
            font-family: 'Noto Sans KR', sans-serif; /* 원하는 폰트로 변경 */
        }

        h3 {
            color: #343a40;
            font-family: 'Noto Sans KR', sans-serif; /* 원하는 폰트로 변경 */
        }

        p.product-name {
            font-size: 1.5em; /* 상품명 폰트 크기 조절 */
            font-family: 'Noto Sans KR', sans-serif; /* 원하는 폰트로 변경 */
        }

        p.product-category, p.product-description {
            font-size: 1.2em; /* 카테고리와 상품설명 폰트 크기 조절 */
            font-family: 'Noto Sans KR', sans-serif; /* 원하는 폰트로 변경 */
        }

        .price {
            font-weight: bold;
            font-size: 1.2em;
            color: #28a745;
            font-family: 'Noto Sans KR', sans-serif; /* 원하는 폰트로 변경 */
        }

        .stock {
            font-weight: bold;
            color: #dc3545;
            font-family: 'Noto Sans KR', sans-serif; /* 원하는 폰트로 변경 */
        }
        .img-fluid{

            height: auto;
            width: 80%;
        }

         #scrollTopBtn {
             display: none;
             position: fixed;
             bottom: 20px;
             right: 20px;
         }


        @media (min-width: 768px) {
            /* 화면 폭이 768px 이상일 때 적용되는 스타일 */
            .product-image, .product-description {
                order: 2;

            }
            .product-image {
                margin: 0 auto; /* 가운데 정렬 */
            }

            .description-image {
                order: 2; /* 오른쪽에 배치 */
                text-align: center; /* 가운데 정렬 */
            }
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-6 product-image">
            <!-- 메인 이미지 -->
            <%--/uploadPath/${productImgSaved}--%>
            <img src="/product/${product.productImgSaved}" alt="상품 이미지" class="img-fluid">
        </div>
        <div class="col-lg-6 product-description ">
            <!-- 상품 정보 -->
            <h2>상품 정보 상세보기</h2>     <br>
            <input type="hidden" value="${product.sellerIdx}">
            <input type="hidden" value="${product.productIdx}">
            <p class="product-name">상품명: <c:out value="${product.productName}"/></p>     <br>
            <p class="product-category">카테고리: <c:out value="${product.categoryName}"/></p>     <br>
            <p class="product-description">상품 간단 설명: <c:out value="${product.productExplain}"/></p>     <br>
            <p class="price">가격: <fmt:formatNumber value="${product.productPrice}"/>원</p>     <br>
            <p class="stock">재고: <fmt:formatNumber value="${product.productStock}"/>개</p>     <br>
            <div class="mb-3">
                <button class="btn btn-primary" onclick="location.href='/product/productInfoUpdate?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}'">상세정보수정</button>
                <button class="btn btn-secondary" onclick="location.href='/product/productImgUpdate?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}'">상품사진수정</button>
                <button class="btn btn-secondary" onclick="location.href='/product/productDetailImgUpdate?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}'">상품설명사진수정</button>
                <br/><button class="btn btn-secondary" type="button" onclick="loadReviewList(1)">사진리뷰보기</button>
                <button class="btn btn-secondary" type="button" onclick="loadCommentList(1)">한줄리뷰보기</button>
                <button class="btn btn-info" onclick="location.href='/seller/myProduct?sellerIdx=${product.sellerIdx}'">목록보기</button>
            </div>
        </div>
    </div>

    <!-- 상품 설명 사진 -->
    <div class="row mt-4">
        <div class="col-lg-12 description-image">

            <img src="/product/${product.productDetailSaved}" alt="상품 설명 사진" class="img-fluid">
        </div>
        <button id="scrollTopBtn" class="btn btn-primary" onclick="scrollToTop()">맨 위로 가기</button>
    </div>
    <div id="reviewListContainer"></div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script>
    function loadReviewList(page) {

        $.ajax({
            url: '/product/review/paging',
            type: 'GET',
            data: {
                productIdx: '<c:out value="${product.productIdx}"/>',
                page: page
            },
            success: function (data) {

                $('#reviewListContainer').html(data);
            },
            error: function () {

                console.error('Failed to fetch review list.');
            }
        });
    }
</script>
<script>
    function loadCommentList(page) {

        $.ajax({
            url: '/product/comment/paging',
            type: 'GET',
            data: {
                productIdx: '<c:out value="${product.productIdx}"/>',
                page: page
            },
            success: function (data) {

                $('#reviewListContainer').html(data);
            },
            error: function () {

                console.error('Failed to fetch review list.');
            }
        });
    }
</script>
<script>

    window.onscroll = function () {
        scrollFunction();
    };

    function scrollFunction() {
        var btn = document.getElementById("scrollTopBtn");
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            btn.style.display = "block";
        } else {
            btn.style.display = "none";
        }
    }


    function scrollToTop() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }
</script>
</html>
