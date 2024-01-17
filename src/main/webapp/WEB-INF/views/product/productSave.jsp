<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

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
<jsp:include page="/WEB-INF/views/common/register.jsp"/>
<body>
<div class="container mt-5">
    <h1>상품 등록</h1>
    <form:form modelAttribute="productSaveDTO" action="/product/productSave" method="post" enctype="multipart/form-data">

        <div class="mb-3">
            <label for="productName" class="form-label">상품명</label>
            <form:input type="text" class="form-control" id="productName" path="productName"
                        placeholder="상품명을 입력하세요 (ex. 3색 볼펜 / 한국어, 영어, 숫자만 20글자 이하로 입력 가능합니다.)" autofocus="autofocus"
                        oninput="checkProductNameLength()"/>
            <form:errors path="productName" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <label for="productExplain" class="form-label">상품 간단 설명</label>
            <form:input class="form-control" path="productExplain" rows="3"
                        placeholder="상품 설명을 입력하세요(ex. 부드러운 3색 볼펜입니다./ 한국어, 영어, 숫자만 50글자 이하만 입력 가능합니다.)" oninput="checkProductExplain()"/>
            <form:errors path="productExplain" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <label for="productPrice" class="form-label">가격</label>
            <form:input type="number" class="form-control" path="productPrice"
                        placeholder="상품 가격을 입력하세요(ex. 10000 / 숫자만 입력해주세요/ 10만원 이하만 입력가능합니다.)" onkeypress="return isNumberKey(event)" oninput="checkProductPrice()"/>
            <form:errors path="productPrice" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <label for="categoryIdx" class="form-label">카테고리</label>
            <form:select class="form-select" path="categoryIdx" require="true">
                <form:option value="">카테고리 선택</form:option>
                <form:option value="1">필기구(볼펜 등)</form:option>
                <form:option value="2">노트/메모(공책 등)</form:option>
                <form:option value="3">사무용품(파일,테이프 등)</form:option>
                <form:option value="4">완구(체스,윷놀이 등)</form:option>
                <form:option value="5">교보재(찰흙,소고 등)</form:option>
                <form:option value="6">PC용품(wd-40 등)</form:option>
                <form:errors path="categoryIdx" cssClass="text-danger"/>
                <!-- 다른 카테고리 옵션 추가 -->
            </form:select>
        </div>
        <div class="mb-3">
            <label for="productStock" class="form-label">재고 수량</label>
            <form:input type="number" class="form-control" path="productStock"
                        placeholder="재고 수량을 입력하세요(숫자만 입력해주세요/1000개 이하만 입력 가능합니다.)" onkeypress="return isNumberKey(event)" onchange="checkStockLimit()"/>
            <form:errors path="productStock" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <label for="productImg" class="form-label">제품 사진</label>
            <form:input type="file" class="form-control" path="productImg" id="productImgInput" accept="image/*"
                        onchange="validateImageType('productImgInput', 'productImgWarning')" />
            <form:errors path="productImg" cssClass="text-danger"/>
            <div id="productImgWarning" class="text-danger"></div>
        </div>
        <div class="mb-3">
            <label for="productImg" class="form-label"></label>
            <img id="productImgPreview" class="thumbnail" />
        </div>
        <div class="mb-3">
            <label for="productDetailImg" class="form-label">제품 설명 사진</label>
            <form:input type="file" class="form-control" path="productDetailImg" id="productDetailImgInput" accept="image/*"
                        onchange="validateImageType('productDetailImgInput', 'productDetailImgWarning')" />
            <form:errors path="productDetailImg" cssClass="text-danger"/>
            <div id="productDetailImgWarning" class="text-danger"></div>
        </div>
        <div class="mb-3">
            <label for="productDetailImg" class="form-label"></label>
            <img id="productDetailImgPreview" class="thumbnail" />
        </div>
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="reset" class="btn btn-primary">초기화</button>
        <button type="button" class="btn btn-secondary" onclick="goBack()">뒤로 가기</button>

    </form:form>

    <script>
        function previewImage(input, targetId) {
            let reader = new FileReader();
            reader.onload = function (e) {
                let targetElement = document.getElementById(targetId);
                targetElement.src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        }

        document.getElementById('productImgInput').onchange = function () {
            validateAndPreviewImage(this, 'productImgPreview', 'productImgWarning');
        };

        document.getElementById('productDetailImgInput').onchange = function () {
            validateAndPreviewImage(this, 'productDetailImgPreview', 'productDetailImgWarning');
        };

        function validateAndPreviewImage(input, targetId, warningId) {
            let allowedTypes = ['image/jpeg', 'image/png', 'image/jpg'];
            let fileType = input.files[0].type;
            let fileSize = input.files[0].size; // 추가: 파일 크기 확인
            let warning = document.getElementById(warningId);
            let targetElement = document.getElementById(targetId);

            if (fileSize > 10485760) {
                warning.innerHTML = "파일 크기가 10MB를 초과합니다.";
                input.value = ''; // Clear the input
                targetElement.style.display = 'none'; // Hide the preview
                return;
            }
            if (allowedTypes.indexOf(fileType) === -1) {
                warning.innerHTML = "이미지 파일(jpg, jpeg, png)만 허용됩니다.";
                input.value = ''; // Clear the input
                targetElement.style.display = 'none'; // Hide the preview
            } else {
                warning.innerHTML = '';
                previewImage(input, targetId);
                targetElement.style.display = 'block'; // Show the preview
            }
        }
    </script>


    <script>
        function isNumberKey(evt) {
            let charCode = (evt.which) ? evt.which : event.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                return false;
            }
            return true;
        }
        function checkStockLimit() {
            let productStockInput = document.getElementById('productStock');
            let stockValue = parseInt(productStockInput.value);

            if (isNaN(stockValue) || stockValue < 0) {
                alert('숫자만 입력해주세요.');
                productStockInput.value = '';
            } else if (stockValue > 1000) {
                alert('재고 수량은 1000개 이하만 입력 가능합니다.');
                productStockInput.value = '1000';
            }
        }
        function checkProductPrice() {
            let productPriceInput = document.getElementById('productPrice');
            let priceValue = parseInt(productPriceInput.value);

            if (isNaN(priceValue) || priceValue < 0) {
                alert('숫자만 입력해주세요.');
                productPriceInput.value = '';
            } else if (priceValue > 100000) {
                alert('상품 가격은 10만원 이하만 입력 가능합니다.');
                productPriceInput.value = '100000';
            }
        }
    </script>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>

    <script>
        let reloading = false;

        window.addEventListener('pageshow', function(event) {

            if (reloading) {
                return;
            }

            if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
                reloading = true;
                location.reload(true);
            }
        });
    </script>

    <script>
        function checkProductNameLength() {
            let productNameInput = document.getElementById('productName');
            let productName = productNameInput.value;

            // 정규표현식을 사용하여 유효성 검사
            let regex = /^[a-zA-Z0-9가-힣]{1,20}$/;

            if (!regex.test(productName)) {
                if (!productNameInput.hasAttribute('readonly')) {
                    alert('한국어, 영어, 숫자만 20글자 이하로 입력해주세요.');
                    productNameInput.value = ''; // 입력값 초기화
                }
            }
        }
    </script>
    <script>
        function checkProductExplain() {
            let productExplainInput = document.getElementById('productExplain');
            let productExplain = productExplainInput.value;

            // 정규표현식을 사용하여 유효성 검사
            let regex = /^[a-zA-Z0-9가-힣\s]{1,50}$/;

            if (!regex.test(productExplain)) {
                alert('한국어, 영어, 숫자만 50글자 이하로 입력해주세요.');
                productExplainInput.value = ''; // 입력값 초기화
            }
        }
    </script>


</div>
