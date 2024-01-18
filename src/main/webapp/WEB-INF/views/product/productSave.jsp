<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<!DOCTYPE html>
<html>
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
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>



</head>
<jsp:include page="/WEB-INF/views/common/register.jsp"/>
<body>
<div class="contentset">
<div class="container mt-5">
    <h1>상품 등록</h1>
    <form:form modelAttribute="productSaveDTO" action="/product/productSave" method="post" enctype="multipart/form-data">

        <div class="mb-3">
            <label for="productName" class="form-label">상품명</label>
            <form:input type="text" class="form-control" id="productName" path="productName"
                        placeholder="상품명을 입력하세요 (ex. 3색 볼펜 / 한국어, 영어, 숫자만 50글자 이하로 입력 가능합니다.)" autofocus="autofocus"
                        oninput="checkProductNameLength()"/>
            <form:errors path="productName" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <label for="productExplain" class="form-label">상품 간단 설명</label>
            <form:input class="form-control" path="productExplain" rows="3"
                        placeholder="상품 설명을 입력하세요(ex. 부드러운 3색 볼펜입니다./ 한국어, 영어, 숫자만 100글자 이하만 입력 가능합니다.)" oninput="checkProductExplain()"/>
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
            <label for="productDetailImg" class="form-label">제품 상세 사진</label>
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
    <br><br><br>
</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<%--
이벤트핸들링 : 웹 페이지에서 사용자 상호작용과 관련된 동적인 기능을 구현하기 위해 사용되는 기술
이벤트 : 마우스 클릭, 키보드 입력,스크롤 등과 같은 동작
--%>
    <script>
        // 이미지 미리보기
        function previewImage(input, targetId) {
            // 객체 생성
            let reader = new FileReader();
            // 파일 읽기가 완료되면 실행되는 이벤트 핸들러
            reader.onload = function (e) {
                //element의 id를 사용하여 해당 element를 가져옴
                // targetID 매개변수를 통해 전달된 id 사용
                let targetElement = document.getElementById(targetId);
                // 읽어들인 파일의 결과를 이미지 소스로 설정하여 미리보기 생성
                targetElement.src = e.target.result;
            };
            // input으로 전달된 파일의 내용을 Data URL로 읽어들임
            reader.readAsDataURL(input.files[0]);
        }
        // productImg에 대한 이벤트 핸들러
        document.getElementById('productImgInput').onchange = function () {
            // validateAndPreviewImage 함수 호출
            // this는 현재 변경된 input 엘리먼트를 나타냄
            // 'productImgPreview': 미리보기 이미지를 표시할 대상 element의 id
            // 'productImgWarning': 유효성 검사에 실패했을 때 경고를 표시할 element의 id
            validateAndPreviewImage(this, 'productImgPreview', 'productImgWarning');
        };
        // productDetailImg에 대한 이벤트 핸들러
        document.getElementById('productDetailImgInput').onchange = function () {
            // validateAndPreviewImage 함수 호출
            // this는 현재 변경된 input 엘리먼트를 나타냄
            // 'productDetailImgPreview': 미리보기 이미지를 표시할 대상 element의 id
            // 'productDetailImgWarning': 유효성 검사에 실패했을 때 경고를 표시할 element의 id
            validateAndPreviewImage(this, 'productDetailImgPreview', 'productDetailImgWarning');
        };
        // 이미지 유효성 검사 및 미리보기
        function validateAndPreviewImage(input, targetId, warningId) {
            // 허용된 이미지 파일 형식
            let allowedTypes = ['image/jpeg', 'image/png', 'image/jpg'];
            // 업로드된 이미지의 형식 확인
            let fileType = input.files[0].type;
            // 업로드된 이미지의 크기 확인
            let fileSize = input.files[0].size;
            // 경고 및 미리보기 대상 element 가져오기
            let warning = document.getElementById(warningId);
            let targetElement = document.getElementById(targetId);
            // 이미지 크기가 10MB를 초과하는지 확인
            if (fileSize > 10485760) {
                warning.innerHTML = "이미지파일 크기가 10MB를 초과합니다.";
                input.value = ''; //입력 필드 비우기
                targetElement.style.display = 'none'; // 미리보기 숨기기
                return;
            }
            // 허용된 이미지 형식 확인
            if (allowedTypes.indexOf(fileType) === -1) {
                // 허용되는 이미지 형식을 알려줌
                warning.innerHTML = "이미지 파일(jpg, jpeg, png)만 허용됩니다.";
                input.value = ''; // 입력 필드 비우기
                targetElement.style.display = 'none'; // 미리보기 숨기기
            } else {
                // 유효성 검사 통과 -> 경고 메시지 초기화 및 미리보기
                warning.innerHTML = '';
                previewImage(input, targetId);
                targetElement.style.display = 'block'; // 미리보기 표시
            }
        }
    </script>


    <script>
        // 숫자 입력만 허용
        function isNumberKey(evt) {
            // 키 코드 얻기
            let charCode = (evt.which) ? evt.which : event.keyCode;
            // 입력된 키가 숫자가 아니면(ASCII 코드에서 0부터 0까지의 범위가 아닌 경우)
            // ASCII에서 '0'에서 '9'까지의 숫자 문자는 각각 48에서 57까지의 코드를 가진다
            // 31보다 크면서 동시에 키 코드가 48보다 작거나 57보다 크면, if 문 내부의 코드 블록을 실행
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                // 입력 거무
                return false;
            }
            // 입력 허용
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
                // 경고메시지 출력
                alert('숫자만 입력해주세요.');
                // 필드 비우기
                productStockInput.value = '';
                // 1000을 초과한 경우
            } else if (stockValue > 1000) {
                // 경고메시지 출력
                alert('재고 수량은 1000개 이하만 입력 가능합니다.');
                // 입력가능한 값을 1000설정
                productStockInput.value = '1000';
            }
        }
        // 가격 제한
        function checkProductPrice() {
            // productPrice id를 가진 element를 가져옴
            let productPriceInput = document.getElementById('productPrice');
            // 입력 값 가져오기 및 정수 변환
            let priceValue = parseInt(productPriceInput.value);
            // 숫자가 아니거나 음수인 경우
            if (isNaN(priceValue) || priceValue < 0) {
                // 경고메시지 출력
                alert('숫자만 입력해주세요.');
                productPriceInput.value = ''; // 필드 비우기
            } else if (priceValue > 100000) { // 100000을 초과한 경우
                // 경고메시지 출력
                alert('상품 가격은 10만원 이하만 입력 가능합니다.');
                // 입력 값을 100000으로 설정
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
        // 페이지가 리로딩중인지 여부를 확인하는 변수 선언
        let reloading = false;
        // pageshow 이벤트에 대한 리스너 등록
        window.addEventListener('pageshow', function(event) {
            // 리로딩중이면 종료
            if (reloading) {
                return;
            }
            // event.persisted : 페이지가 세션 히스토리에서 가져와졌는지 여부 확인
            // indow.performance && window.performance.navigation.type === 2 : 세션 히스토리에서 가져온지 더 확인
            // 2인 경우 헤션 히스토리에서 가져온 경우임
            // 페이지가 뒤 또는 앞으로 이동하거나 세션 히스토리에서 가져온 것인지 확인
            if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
                // 리로딩을 true로 설정
                reloading = true;
                // 페이지를 강제 리로딩
                location.reload(true);
            }
        });
    </script>

    <script>
        // 제품명 길이 제한
        function checkProductNameLength() {
            // productName id를 가진 element를 가져옴
            let productNameInput = document.getElementById('productName');
            // 제풍명 입력값을 가져옴
            let productName = productNameInput.value;

            // 정규표현식을 사용하여 유효성 검사
            let regex = /^.{1,50}$/;
            // 정규 표현식 검사가 실패하면
            if (!regex.test(productName)) {
                // readonly 속성을 가지고 있지 않다면
                if (!productNameInput.hasAttribute('readonly')) {
                    // 경고메시지 출력
                    alert('50글자 이하로 입력해주세요.');
                    productNameInput.value = ''; // 입력값 초기화
                }
            }
        }
    </script>
    <script>
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
</html>

