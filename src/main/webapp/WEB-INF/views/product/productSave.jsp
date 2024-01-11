<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <form:form modelAttribute="productSaveDTO" action="/product/productSave" method="post" enctype="multipart/form-data">

        <div class="mb-3">
            <label for="productName" class="form-label">상품 등록</label>
            <form:input type="text" class="form-control" path="productName" placeholder="상품명을 입력하세요" required="true" autofocus="autofocus"/>
        </div>
        <div class="mb-3">
            <label for="productExplain" class="form-label">상품 설명</label>
            <form:textarea class="form-control" path="productExplain" rows="3" placeholder="상품 설명을 입력하세요" required="true"/>
        </div>
        <div class="mb-3">
            <label for="productPrice" class="form-label">가격</label>
            <form:input type="number" class="form-control" path="productPrice" placeholder="상품 가격을 입력하세요" required="true"/>
        </div>
        <div class="mb-3">
            <label for="categoryIdx" class="form-label">카테고리</label>
            <form:select class="form-select" path="categoryIdx" require="true">
                <form:option value="">카테고리 선택</form:option>
                <form:option value="1">필기구</form:option>
                <form:option value="2">사무용품</form:option>
                <!-- 다른 카테고리 옵션 추가 -->
            </form:select>
        </div>
        <div class="mb-3">
            <label for="productStock" class="form-label">재고 수량</label>
            <form:input type="number" class="form-control" path="productStock" placeholder="재고 수량을 입력하세요" required="true"/>
        </div>
        <div class="mb-3">
            <label for="productImg" class="form-label">제품 사진</label>
            <form:input type="file" class="form-control" path="productImg" required="true"/>
        </div>
        <div class="mb-3">
            <label for="productDetailImg" class="form-label">제품 설명 사진</label>
            <form:input type="file" class="form-control" path="productDetailImg" required="true"/>
        </div>
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="reset" class="btn btn-primary">초기화</button>

    </form:form>
</div>
