<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<html lang="ko">
<head>
    <title>상품 설명 사진 수정</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {

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

        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
      
        #scrollTopBtn {
            display: none;
            position: fixed;
            bottom: 20px;
            right: 20px;
            width: 50px; /* 변경된 부분: 버튼 크기를 50px로 조정 */
            height: 50px; /* 변경된 부분: 버튼 크기를 50px로 조정 */
        }
    </style>

</head>
<body>
<div class="contentset">
<div class="container mt-5">
    <h1 class="mb-4">상품 상세 사진 수정</h1>
    <form:form modelAttribute="productDetailImgUpdateDTO"
               action="/product/productDetailImgUpdate?sellerIdx=${productDetailImgUpdateDTO.sellerIdx}
               &productIdx=${productDetailImgUpdateDTO.productIdx}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sellerIdx" value="${productDetailImgUpdateDTO.sellerIdx}" />
        <input type="hidden" name="productIdx" value="${productDetailImgUpdateDTO.productIdx}" />
        <input type="hidden" name="productDetailOriginal" value="${productDetailImgUpdateDTO.productDetailOriginal}" />
        <input type="hidden" name="productDetailSaved" value="${productDetailImgUpdateDTO.productDetailSaved}" />

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="productDetailSaved" class="form-label"></label>
                <img src="/product/${productDetailImgUpdateDTO.productDetailSaved}" class="img-fluid">
            </div>

            <div class="col-md-6">
                <label for="detailImg" class="form-label">새로운 상품 상세 사진</label>
                <form:input type="file" class="form-control" path="detailImg" id="detailImgInput" accept="image/*"
                            onchange="validateAndPreviewImage('detailImgInput', 'detailImgWarning','detailImgPreview')" />
                <form:errors path="detailImg" cssClass="text-danger"/>
                <div id="detailImgWarning" class="text-danger"></div>
                <div class="btn-container">
                    <button type="submit" class="btn btn-primary">등록</button>
                    <a class="btn btn-secondary" href="/product/productDetail?sellerIdx=${productDetailImgUpdateDTO.sellerIdx}
            &productIdx=${productDetailImgUpdateDTO.productIdx}">취소</a>
                </div>
                <img id="detailImgPreview" class="img-fluid" style="display:none;">
            </div>
            <div>
                <button type="button" id="scrollTopBtn" class="btn btn-primary" onclick="scrollToTop()"><i class="fas fa-arrow-up"></i></button>
            </div>
        </div>
    </form:form>

    <!-- 파일 선택 시 새로운 이미지 미리보기 기능 -->
    <script>
        function previewImage(input, targetId) {
            let reader = new FileReader();
            reader.onload = function (e) {
                let targetElement = document.getElementById(targetId);
                targetElement.src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        }


        document.getElementById('detailImgInput').onchange = function () {
            validateAndPreviewImage(this, 'detailImgPreview', 'detailImgWarning');
        };

        function validateAndPreviewImage(input, targetId, warningId) {
            let allowedTypes = ['image/jpeg', 'image/png', 'image/jpg'];
            let fileType = input.files[0].type;
            let fileSize = input.files[0].size;
            let warning = document.getElementById(warningId);
            let targetElement = document.getElementById(targetId);

            if (fileSize > 10485760) {
                warning.innerHTML = "이미지 파일 크기가 10MB를 초과합니다.";
                input.value = ''; /
                targetElement.style.display = 'none';
                return;
            }

            if (allowedTypes.indexOf(fileType) === -1) {
                warning.innerHTML = "이미지 파일(jpg, jpeg, png)만 허용됩니다.";
                input.value = ''; // Clear the input
                targetElement.style.display = 'none';
            } else {
                warning.innerHTML = '';
                previewImage(input, targetId);
                targetElement.style.display = 'block';
            }
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

        window.onscroll = function () {
            scrollFunction();
        };

        function scrollFunction() {
            let btn = document.getElementById("scrollTopBtn");
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                btn.style.display = "block";
            } else {
                btn.style.display = "none";
            }
        }


        function scrollToTop() {
            $('html, body').animate({scrollTop : 0},0);
        }
    </script>
</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
