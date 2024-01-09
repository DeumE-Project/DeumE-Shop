<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>product Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 추가적인 스타일링이 필요한 경우 여기에 작성하세요. */
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-lg-6">
            <!-- 메인 이미지 -->
            <img src="상품_메인_이미지_주소" alt="상품 이미지" class="img-fluid">
        </div>
        <div class="col-lg-6">
            <!-- 상품 정보 -->
            <h2> 상품 상세보기</h2>
            <p>상품명 : ${product.productName}</p>
            <p>카테고리: ${product.categoryIdx}</p>
            <p>상품 설명: ${product.productExplain}</p>
            <p>가격: ${product.productPrice}</p>
            <p>재고: ${product.productStock}</p>
        </div>
    </div>

    <!-- 상품 설명 사진 -->
    <div class="row mt-4">
        <div class="col-lg-12">
            <h3>상품 설명 사진</h3>
            <img src="상품_설명_이미지_주소" alt="상품 설명 사진" class="img-fluid">
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
