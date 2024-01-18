<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
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
    </style>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>--%>
</head>
<body>
<div class="contentset">
<div class="container mt-5">
    <h1 class="mb-4">상품 사진 수정</h1>
    <form:form modelAttribute="productMainImgUpdateDTO"
               action="/product/productImgUpdate?sellerIdx=${productMainImgUpdateDTO.sellerIdx}
               &productIdx=${productMainImgUpdateDTO.productIdx}" method="post"  enctype="multipart/form-data">
        <input type="hidden" name="sellerIdx" value="${productMainImgUpdateDTO.sellerIdx}" />
        <input type="hidden" name="productIdx" value="${productMainImgUpdateDTO.productIdx}" />
        <input type="hidden" name="productImgOriginal" value="${productMainImgUpdateDTO.productImgOriginal}" />
        <input type="hidden" name="productImgSaved" value="${productMainImgUpdateDTO.productImgSaved}" />
        <input type="hidden" name="productThumbSaved" value="${productMainImgUpdateDTO.productThumbSaved}" />

        <div class="row mb-3">
            <div class="col-md-6">
                <label for="productImgSaved" class="form-label"></label>
                <img src="/product/${productMainImgUpdateDTO.productImgSaved}" class="img-fluid">
            </div>
            <div class="col-md-6">
                <label for="mainImg" class="form-label">새로운 상품 사진</label>
                <form:input type="file" class="form-control" path="mainImg" id="mainImgInput" accept="image/*"
                            onchange="validateAndPreviewImage('mainImgInput', 'mainImgWarning', 'mainImgPreview')" />
                <form:errors path="mainImg" cssClass="text-danger"/>
                <div id="mainImgWarning" class="text-danger"></div>
                <div class="btn-container">
                    <button type="submit" class="btn btn-primary">등록</button>
                    <a class="btn btn-secondary"
                       href="/product/productDetail?sellerIdx=${productMainImgUpdateDTO.sellerIdx}
               &productIdx=${productMainImgUpdateDTO.productIdx}">취소</a>
                </div>
                <img id="mainImgPreview" class="img-fluid" style="display:none;" />
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


        document.getElementById('mainImgInput').onchange = function () {
            validateAndPreviewImage(this, 'mainImgPreview', 'mainImgWarning');
        };

        function validateAndPreviewImage(input, targetId, warningId) {
            let allowedTypes = ['image/jpeg', 'image/png', 'image/jpg'];
            let fileType = input.files[0].type;
            let fileSize = input.files[0].size; // 파일 크기 확인
            let warning = document.getElementById(warningId);
            let targetElement = document.getElementById(targetId);

            if (fileSize > 10485760) {
                warning.innerHTML = "이미지 파일 크기가 10MB를 초과합니다.";
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

</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
