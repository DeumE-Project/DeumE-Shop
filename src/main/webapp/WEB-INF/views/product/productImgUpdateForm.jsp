<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 사진 수정</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <h1>상품 사진 수정</h1>
    <form:form modelAttribute="productMainImgUpdateDTO" action="/product/productImgUpdate" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sellerIdx" value="${product.sellerIdx}" />
        <input type="hidden" name="productIdx" value="${product.productIdx}" />
        <input type="hidden" name="productImgOriginal" value="${product.productImgOriginal}" />
        <input type="hidden" name="productImgSaved" value="${product.productImgSaved}" />
        <input type="hidden" name="productThumbSaved" value="${product.productThumbSaved}" />


        <div class="mb-3">
            <label for="productImgSaved" class="form-label">이전 상품 사진</label>
            <img src="/product/${product.productImgSaved}" alt="이전 상품 사진" style="max-width: 100%; max-height: 300px; margin-top: 10px;">
        </div>

        <div class="mb-3">
            <label for="mainImg" class="form-label">새로운 상품 사진</label>
            <input type="file" class="form-control" name="mainImg" />
            <img id="newImagePreview" style="max-width: 100%; max-height: 300px; margin-top: 10px; display: none;">
        </div>

        <button type="submit" class="btn btn-primary">등록</button>
        <a class="btn btn-primary" href="/product/productDetail?sellerIdx=${product.sellerIdx}&productIdx=${product.productIdx}">취소</a>
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
</body>
</html>
