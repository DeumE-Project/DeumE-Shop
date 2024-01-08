<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>product Save</title>
    <!-- 부트스트랩 CDN 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 썸네일 이미지 스타일 */
        .thumbnail {
            max-width: 200px;
            max-height: 200px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1>상품 등록</h1>
    <form action="/product/productSave" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="productName" class="form-label">상품 등록</label>
            <input type="text" class="form-control" id="productName" placeholder="상품명을 입력하세요" required autofocus>
        </div>
        <div class="mb-3">
            <label for="productDescription" class="form-label">상품 설명</label>
            <textarea class="form-control" id="productDescription" rows="3" placeholder="상품 설명을 입력하세요" required></textarea>
        </div>
        <div class="mb-3">
            <label for="productPrice" class="form-label">가격</label>
            <input type="number" class="form-control" id="productPrice" placeholder="상품 가격을 입력하세요" required>
        </div>
        <div class="mb-3">
            <label for="productCategory" class="form-label">카테고리</label>
            <select class="form-select" id="productCategory">
                <option selected>카테고리 선택</option>
                <option value="clothing">의류</option>
                <option value="electronics">전자제품</option>
                <!-- 다른 카테고리 옵션 추가 -->
            </select>
        </div>
        <div class="mb-3">
            <label for="productStock" class="form-label">재고 수량</label>
            <input type="number" class="form-control" id="productStock" placeholder="재고 수량을 입력하세요" required>
        </div>
        <button type="submit" class="btn btn-primary">등록</button>
    </form>
</div>
