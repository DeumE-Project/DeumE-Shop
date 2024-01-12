<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>상품 정보 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>상품 정보 수정</h2>

    <form id="updateForm" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sellerIdx" value="${product.sellerIdx}">
        <input type="hidden" name="sellerIdx" value="${product.productIdx}">
        <input type="hidden" name="existingProductImg" value="${product.productImgSaved}">
        <input type="hidden" name="existingDetailImg" value="${product.productDetailSaved}">

        <div class="mb-3">
            <label for="productName" class="form-label">상품 이름</label>
            <input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" readonly>
        </div>

        <div class="mb-3">
            <label for="productPrice" class="form-label">상품 가격</label>
            <input type="text" class="form-control" id="productPrice" name="productPrice" value="${product.productPrice}" >
        </div>

        <div class="mb-3">
            <label for="productStock" class="form-label">상품 재고량</label>
            <input type="number" class="form-control" id="productStock" name="productStock" value="${product.productStock}">
        </div>

        <div class="mb-3">
            <label for="productImg" class="form-label">메인 이미지</label>
            <input type="file" class="form-control" id="productImg" name="productImg">
            <img src="/product/${product.productImgSaved}" class="img-thumbnail mt-2" alt="메인 이미지">
        </div>

        <div class="mb-3">
            <label for="productDetailImg" class="form-label">상세 이미지</label>
            <input type="file" class="form-control" id="productDetailImg" name="productDetailImg">
            <img src="/product/${product.productDetailSaved}" class="img-thumbnail mt-2" alt="상세 이미지">
        </div>

        <button type="submit" class="btn btn-primary">상품 수정</button>
       <%-- <button type="button" class="btn btn-primary" onclick="updateProduct()">상품 수정</button>
        <button type="button" class="btn btn-secondary" onclick="cancelUpdate()">취소</button>--%>
    </form>
</div>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function updateProduct() {
        // Ajax로 상품 정보 수정 처리
        var formData = new FormData(document.getElementById('updateForm'));

        $.ajax({
            type: 'POST',
            url: '/product/update',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                alert(response);
                // 성공 시 필요한 처리 수행
            },
            error: function (error) {
                alert(error.responseText);
                // 실패 시 필요한 처리 수행
            }
        });
    }
    function cancelUpdate() {
        $.ajax({
            url: "/product/cancelUpdate",
            type: "GET",
            success: function(data) {
                // 페이지 갱신
                $("body").html(data);
            },
            error: function() {
                alert("취소 중 오류가 발생했습니다.");
            }
        });
    }
</script>--%>

</body>
</html>
