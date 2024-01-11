<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>product Info Update</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>제품 상세 정보 수정</h1>

    <form action="/product/productInfoUpdate" method="post">
        <input type="hidden" name="sellerIdx" value="${product.sellerIdx}">
        <input type="hidden" name="productIdx" value="${product.productIdx}">
        <div class="mb-3">
            <label for="productName" class="form-label"> 상품명  </label>
            <input type="text" id="productName" name="productName" class="form-control" value="${product.productName}">
        </div>

        <div class="mb-3">
            <label for="productPrice" class="form-label"> 상품 가격  </label>
            <input type="text" id="productPrice" name="productPrice" class="form-control" value="${product.productPrice}">
        </div>
        <div class="mb-3">
            <label for="productStock" class="form-label"> 상품 재고  </label>
            <input type="text" id="productStock" name="productStock" class="form-control" value="${product.productStock}">
        </div>

        <div class="mb-3">
            <label for="categoryIdx" class="form-label"> 카테고리  </label>
            <select id="categoryIdx" name="categoryIdx" class="form-select" value="${product.categoryIdx}">
                <option value="1">카테고리 1</option>
                <option value="2">카테고리 2</option>
                <option value="3">카테고리 3</option>
                <!-- 추가적인 카테고리 옵션들을 필요에 따라 추가해주세요 -->
            </select>
        </div>

        <div class="mb-3">
            <label for="productExplain" class="form-label"> 상품 설명  </label>
            <input id="productExplain" name="productExplain" class="form-control" value="${product.productExplain}"> </input>
        </div>


        <button type="submit" class="btn btn-primary">수정</button>

</div>

<!-- 부트스트랩 5 JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
