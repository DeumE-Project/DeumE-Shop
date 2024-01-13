<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<head>
    <title>상품 사진 수정</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Noto Sans KR', sans-serif;
        }

        h1 {
            color: #007bff;
        }

        .container {
            margin-top: 50px;
        }

        .mb-3 {
            margin-bottom: 1.5rem;
        }

        .form-label {
            font-weight: bold;
        }

        .img-container {
            display: flex;
            justify-content: space-between;
        }

        .img-container img {
            max-width: 48%;
            margin-bottom: 10px;
            border-radius: 8px;
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

<div class="container">
    <h1 class="mb-4">상품 사진 수정</h1>
    <form:form modelAttribute="productDetailImgUpdateDTO" action="/product/productDetailImgUpdate" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sellerIdx" value="${product.sellerIdx}" />
        <input type="hidden" name="productIdx" value="${product.productIdx}" />

        <div class="img-container">
            <div class="mb-3">
                <label for="productDetailSaved" class="form-label">이전 상품 사진</label>
                <img src="/product/${product.productDetailSaved}" alt="이전 상품 사진" style="max-width: 100%; max-height: 300px;">
            </div>

            <div class="mb-3">
                <label for="detailImg" class="form-label">새로운 상품 사진</label>
                <input type="file" class="form-control" name="detailImg" />
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
        $("input[name='detailImg']").change(function() {
            readURL(this, "newImagePreview");
        });

        // 초기에 이전 이미지 미리보기
        readURL("input[name='productDetailSaved']", "newImagePreview");
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
