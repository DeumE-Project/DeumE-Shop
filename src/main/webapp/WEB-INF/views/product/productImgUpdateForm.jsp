<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <title>상품 사진 수정</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Noto Sans KR', sans-serif;
            color: #495057;
        }

        h1 {
            color: #007bff;
        }

        .form-label {
            font-weight: bold;
        }

        .img-fluid {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            margin-top: 10px;
        }

        .btn-container {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }

        .btn-container button {
            margin-left: 10px;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">상품 사진 수정</h1>
    <form:form modelAttribute="productMainImgUpdateDTO" action="/product/productImgUpdate" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sellerIdx" value="${product.sellerIdx}" />
        <input type="hidden" name="productIdx" value="${product.productIdx}" />
        <input type="hidden" name="productImgOriginal" value="${product.productImgOriginal}" />
        <input type="hidden" name="productImgSaved" value="${product.productImgSaved}" />
        <input type="hidden" name="productThumbSaved" value="${product.productThumbSaved}" />

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="productImgSaved" class="form-label">이전 상품 사진</label>
                <img src="/product/${product.productImgSaved}" alt="이전 상품 사진" class="img-fluid">
            </div>
            <div class="col-md-6">
                <label for="mainImg" class="form-label">새로운 상품 사진</label>
                <input type="file" class="form-control" name="mainImg" />
                <img id="newImagePreview" class="img-fluid" style="display: none;">
            </div>
        </div>

        <div class="btn-container">
            <button type="submit" class="btn btn-primary">등록</button>
            <a class="btn btn-secondary" href="/product/productDetail?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}">취소</a>
        </div>
    </form:form>
</div>

<!-- 파일 선택 시 새로운 이미지 미리보기 기능 -->
<script>
    $(document).ready(function() {
        $("input[name='mainImg']").change(function() {
            readURL(this, "newImagePreview");
        });

        // 초기에 이전 이미지 미리보기
        readURL("input[name='productImgSaved']", "newImagePreview");
    });

    function readURL(input, previewId) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                var allowedFormats = ["image/png", "image/jpeg", "image/jpg"];
                // 파일이 허용된 형식 중 하나인지 확인
                if (allowedFormats.includes(input.files[0].type)) {
                    $("#" + previewId).attr("src", e.target.result);
                    $("#" + previewId).show();
                } else {
                    // 파일이 허용된 형식이 아닌 경우 처리
                    alert("PNG, JPG, 또는 JPEG 이미지를 선택해주세요.");
                    // 선택한 파일을 초기화하는 등의 추가 처리를 선택적으로 수행할 수 있습니다.
                    // input.value = "";
                }
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
