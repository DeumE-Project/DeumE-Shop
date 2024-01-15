<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Product Detail</title>
    <!-- 부트스트랩 5 CSS 링크 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 추가적인 스타일링이 필요한 경우 여기에 작성하세요. */
        body {
            background-color: #f8f9fa;
        }

        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        h2 {
            color: #007bff;
        }

        h3 {
            color: #343a40;
        }

        img.img-fluid {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .product-description p {
            margin-bottom: 10px;
        }

        .price {
            font-weight: bold;
            font-size: 1.2em;
            color: #28a745;
        }

        .stock {
            font-weight: bold;
            color: #dc3545;
        }

        @media (min-width: 768px) {
            /* 화면 폭이 768px 이상일 때 적용되는 스타일 */
            .product-image {
                order: 1; /* 왼쪽에 배치 */
            }

            .product-description {
                order: 2; /* 오른쪽에 배치 */
            }

            .description-image {
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
        <div class="col-lg-6 product-description">
            <!-- 상품 정보 -->
            <h2>상품 정보 상세보기</h2>
            <input type="hidden" value="${product.sellerIdx}">
            <input type="hidden" value="${product.productIdx}">
            <p>상품명: <c:out value="${product.productName}"/></p>
            <p>카테고리: <c:out value="${product.categoryName}"/></p>
            <p>상품 설명: <c:out value="${product.productExplain}"/></p>
            <p class="price">가격: <fmt:formatNumber value="${product.productPrice}"/>원</p>
            <p class="stock">재고: <fmt:formatNumber value="${product.productStock}"/>개</p>
            <div class="mb-3">
                <button class="btn btn-primary" onclick="location.href='/product/productInfoUpdate?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}'">상세정보수정</button>
                <button class="btn btn-secondary" onclick="location.href='/product/productImgUpdate?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}'">상품사진수정</button>
                <button class="btn btn-info" onclick="location.href='/product/productDetailImgUpdate?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}'">상품설명사진수정</button>
            </div>
        </div>
    </div>

    <!-- 상품 설명 사진 -->
    <div class="row mt-4">
        <div class="col-lg-12 description-image">
            <h3>상품 상세 설명</h3>
            <img src="/product/${product.productDetailSaved}" alt="상품 설명 사진" class="img-fluid">
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
