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
    <style>
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="contentset">
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
</div>
</div>
    <script>
        // 숫자 입력만 허용
        function isNumberKey(evt) {
            // 키 코드 얻기
            let charCode = (evt.which) ? evt.which : event.keyCode;
            // 입력된 키가 숫자가 아니면(ASCII 코드에서 0부터 0까지의 범위가 아닌 경우)
            // ASCII에서 '0'에서 '9'까지의 숫자 문자는 각각 48에서 57까지의 코드를 가진다
            // 31보다 크면서 동시에 키 코드가 48보다 작거나 57보다 크면, if 문 내부의 코드 블록을 실행
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                // 입력 거부
                return false;
            }
            //입력 허용
            return true;
        }
        // 재고 수량 입력 제한
        function checkStockLimit() {
            // productStock id를 가진 element를 가져옴
            let productStockInput = document.getElementById('productStock');
            // 재고 입력 값 가져오기 및 정수 변환
            let stockValue = parseInt(productStockInput.value);
            // 숫자가 아니거나 음수인 경우
            if (isNaN(stockValue) || stockValue < 0) {
                // 경고 메시지 출력
                alert('숫자만 입력해주세요.');
                productStockInput.value = ''; // 필드 비우기
                // 1000을 초과한 경우
            } else if (stockValue > 1000) {
                // 경고 메시지 출력
                alert('재고 수량은 1000개 이하만 입력 가능합니다.');
                // 입력 가능한 값 '1000' 설정
                productStockInput.value = '1000';
            }
        }
        // 상품 가격 제한
        function checkProductPrice() {
            // productPrice id를 가진 element를 가져옴
            let productPriceInput = document.getElementById('productPrice');
            // 입력 값 가져오기 및 정수 변환
            let priceValue = parseInt(productPriceInput.value);
            // 숫자가 아니거나 음수인 경우
            if (isNaN(priceValue) || priceValue < 0) {
                // 경고 메시지 출력
                alert('숫자만 입력해주세요.');
                productPriceInput.value = ''; // 필드 비우기
                // 100000을 초과한 경우
            } else if (priceValue > 100000) {
                // 경고 메시지 출력
                alert('상품 가격은 10만원 이하만 입력 가능합니다.');
                // 입력 값을 '100000'으로 설정
                productPriceInput.value = '100000';
            }
        }
        // 상품 간단 설명 길이 제한
        function checkProductExplain() {
            // productExplain id를 가진 element를 가져옴
            let productExplainInput = document.getElementById('productExplain');
            // 입력값을 가져옴
            let productExplain = productExplainInput.value;

            // 정규표현식을 사용하여 유효성 검사
            let regex = /^.{1,100}$/;
            // 유효성 검사에 실패하면
            if (!regex.test(productExplain)) {
                // 경고메시지 출력
                alert('입력은 100글자 이하로 제한됩니다.');
                productExplainInput.value = ''; // 입력값 초기화
            }
        }
    </script>
</div>

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>

</html>
