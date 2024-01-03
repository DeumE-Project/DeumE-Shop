<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <title>save</title>
    <script>
        function validate() {
            var re = /^[1-5]$/; // 작성자가 적합한지 검사할 정규식

            var reviewStar = document.getElementById("reviewStar");
            var reviewContent = document.getElementById("reviewContent");

            if(!check(re, reviewStar, "1점에서 5점만 입력가능")) {
                return false;
            }

            if(reviewStar.value.trim() === "") {
                alert("별점을 입력해 주세요");
                reviewStar.focus();
                return false;
            }

            if(reviewContent.value.trim() === "") {
                alert("내용을 입력해 주세요");
                reviewContent.focus();
                return false;
            }
            alert("작성 완료")
            return true;
        }

        function check(re, what, message) {
            if(re.test(what.value)) {
                return true;
            }
            alert(message);
            what.value = "";
            what.focus();
            return false;
        }
    </script>
    <script>
        // 특수문자 입력 방지
        function characterCheck(obj){
            var regExp = /[ \{\}\[\]\/|\)`^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
            if( regExp.test(obj.value) ){
                alert("특수문자는 입력하실수 없습니다.");
                obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 한자리 지움
            }
        }
    </script>

    <script>
        function showImage(fileCallPath) {
            $(".bigPictureWrapper").css("display", "flex").show();
            $(".bigPicture")
                .html("<img src='/product/review/display?fileName=" + encodeURI(fileCallPath) + "'>")
                .animate({width: '100%', height: '100%'}, 1000);
        }

        $(".bigPictureWrapper").on("click", function (e) {
            $(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
            setTimeout(function () {
                $('.bigPictureWrapper').hide();
            }, 1000);
        });
        $(".uploadResult").on("click","span",function (e){
            var targetFile = $(this).data("type");
            console.log(targetFile);
            $.ajax({
                url: '/product/review/deleteFile',
                data: {fileName: targetFile, type: type},
                dataType: 'text',
                type: 'POST',
                 success: function (result){
                    alert(result);
                 }
            });
        });
        $(document).ready(function (){
            var regex = new RegExp("(.*?)\.(exe|sh|zip|alz|txt)$");
            var maxSize = 5242880;

            function checkExtension(fileName, fileSize) {
                if (fileSize >= maxSize) {
                    alert("파일 사이즈 초과");
                    return false;
                }

                if (regex.test(fileName)) {
                    alert("해당 종류의 파일은 업로드할 수 없습니다.");
                    return false;
                }
                return true;
            }

            var cloneObj  = $(".uploadDiv").clone();

            $("#uploadBtn").on("click", function (e){
                let formDate = new FormData();

                let inputFile = $("input[name='uploadFile']");

                let files = inputFile[0].files;

                console.log(files);

                for(let i = 0; i< files.length; i++){
                    if(!checkExtension(files[i].name, files[i].size)){
                        return false;
                    }
                    formDate.append("uploadFile", files[i]);
                }

                $.ajax({
                    url: '/product/review/uploadAjaxAction',
                    processData: false,
                    contentType: false,
                    data: formDate,
                    type: 'POST',
                    dataType: 'json',
                    success: function (result){
                        alert("Uploaded");
                        console.log(result);
                        showUploadedFile(result);
                        $(".uploadDiv").html(cloneObj.html());

                        $("#reviewThumbSaved").val(result[0].uploadPath + "/" + "s_" + result[0].uuid + "_" + result[0].fileName);
                        $("#reviewImgOriginal").val(result[0].fileName);
                        $("#reviewImgSaved").val(result[0].uuid + "_" + result[0].fileName);
                    }
                }); //$.ajax
            });


            let uploadResult = $(".uploadResult ul");

            function showUploadedFile(uploadResultArr){
                let str = "";
                $(uploadResultArr).each(function(i, obj){
                    if(!obj.image){
                        let fileCallPath  = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
                        // str += "<li><img src='/resources/img/9.png'>"+obj.fileName+"</li>";
                        str += "<li><div><a href='/product/review/download?fileName="+fileCallPath+"'>"+"<img src='/resources/img/9.png'>"+obj.fileName+"</a>"+"<span data-file=\'"+fileCallPath+"\' data-type='file'>XX</span>"+"<div></li>"
                    }else{

                        let fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid+"_"+obj.fileName);
                        let originPath = obj.uploadPath+ "\\"+obj.uuid+"_"+obj.fileName;
                        originPath = originPath.replace(new RegExp(/\\/g),"/");
                        str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\"><img src='/product/review/display?fileName="+fileCallPath+"'></a>"+"<span data-file=\'"+fileCallPath+"\' data-type='image'>XX</span>"+"</li>";
                    }
                });
                uploadResult.append(str);
            }
        });

    </script>
    <jsp:include page="#"/>
</head>

<body class="bg-light">
<div class="container mt-5">
    <div class="uploadDiv">
        <label for="uploadFile">사진등록</label>
        <input type="file" name="uploadFile" class="uploadFile" id="uploadFile" multiple>
    </div>

    <div class="uploadResult">
        <ul>
        </ul>
    </div>
    <div class="bigPictureWrapper">
        <div class="bigPicture">
        </div>
    </div>
    <button id="uploadBtn">Upload</button>

    <form action="/product/review/save" method="post" onsubmit="return validate();" enctype="multipart/form-data">

        <div class="form-group">
            <input type="hidden" id="productIdx" name="productIdx" value="1">
            <input type="hidden" id="reviewThumbSaved" name="reviewThumbSaved"  value="">
            <input type="hidden" id="reviewImgOriginal" name="reviewImgOriginal" value="">
            <input type="hidden" id="reviewImgSaved" name="reviewImgSaved" value="">

            <label for="customerIdx">${customerIdx}</label><input type="text" name="customerIdx" class="form-control" placeholder="작성자" id="customerIdx" autofocus onkeyup="characterCheck(this)" onkeydown="characterCheck(this)" readonly>
        </div>
        <div class="form-group">
            <label for="reviewStar">별점</label><input type="text" name="reviewStar" class="form-control" placeholder="별점" id="reviewStar" autofocus onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="reviewContent">상품평내용</label><textarea name="reviewContent" class="form-control" rows="5" placeholder="내용을 입력하세요" id="reviewContent" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)"></textarea>
        </div>

        <c:if test="${errors.errorCount > 0}">
            <div class="alert alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors.allErrors}">
                        <li>${error.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <button type="submit" class="btn btn-primary">작성</button>
    </form>
</div>
</body>
</html>