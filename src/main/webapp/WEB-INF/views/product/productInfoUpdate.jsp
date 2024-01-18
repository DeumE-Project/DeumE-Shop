<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <title>product Info Update</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>제품 상세 정보 수정</h1>

    <form:form modelAttribute="productDTO" action="/product/productInfoUpdate?sellerIdx=${productDTO.sellerIdx}&productIdx=${productDTO.productIdx}" method="post">
        <input type="hidden" name="sellerIdx" value="${productDTO.sellerIdx}">
        <input type="hidden" name="productIdx" value="${productDTO.productIdx}">
        <div class="mb-3">
            <label for="productName" class="form-label"> 상품명  </label>
            <input type="text" id="productName" name="productName" class="form-control" value="<c:out value='${productDTO.productName}'/>" readonly>
        </div>

        <div class="mb-3">
            <label for="productPrice" class="form-label"> 상품 가격  </label>
            <form:input type="number" path="productPrice" class="form-control" onkeypress="return isNumberKey(event)" oninput="checkProductPrice()"/>
            <form:errors path="productPrice" cssClass="text-danger"/>
        </div>

        <div class="mb-3">
            <label for="productStock" class="form-label"> 상품 재고  </label>
            <form:input type="number" path="productStock" class="form-control" onkeypress="return isNumberKey(event)" onchange="checkStockLimit()"/>
            <form:errors path="productStock" cssClass="text-danger"/>
        </div>

        <div class="mb-3">
            <label for="productExplain" class="form-label"> 상품 간단 설명  </label>
            <form:input path="productExplain" class="form-control" oninput="checkProductExplain()" />
            <form:errors path="productExplain" cssClass="text-danger"/>
        </div>


        <button type="submit" class="btn btn-primary">수정</button>
    </form:form>

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
        function checkProductExplain() {
            let productExplainInput = document.getElementById('productExplain');
            let productExplain = productExplainInput.value;

            // 정규표현식을 사용하여 유효성 검사
            let regex = /^.{1,50}$/;

            if (!regex.test(productExplain)) {
                alert('입력은 50글자 이하로 제한됩니다.');
                productExplainInput.value = ''; // 입력값 초기화
            }
        }
    </script>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
