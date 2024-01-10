<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <title>update</title>
    <script>
        function validate() {
            var title = document.getElementById("boardTitle");
            var content = document.getElementById("boardContents");

            if (title.value.trim() === "") {
                alert("제목을 입력해 주세요");
                title.focus();
                return false;
            }

            if (content.value.trim() === "") {
                alert("내용을 입력해 주세요");
                content.focus();
                return false;
            }

            return true;
        }
    </script>
    <jsp:include page="#"/>
</head>
<body class="bg-light">
<div class="container mt-5">
    <form action="/product/review/update" method="post" name="updateForm" onsubmit="return validate();">
        <div class="form-group">
            <input type="hidden" name="id" value="${productReview.reviewIdx}" class="form-control" readonly onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="customerIdx">작성자</label>
            <input type="text" name="customerIdx" value="${productReview.customerIdx}" class="form-control" readonly onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="reviewStar">별점</label>
            <input type="text" name="reviewStar" value="${productReview.reviewStar}" class="form-control" id="reviewStar" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="reviewContent">내용</label>
            <textarea name="reviewContent" class="form-control" rows="5" id="reviewContent" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">${productReview.reviewContent}</textarea>
        </div>
        <button type="button" class="btn btn-primary" onclick="updateReqFn()">수정</button>
    </form>
</div>
</body>
<script>
    const updateReqFn = () => {
        document.updateForm.submit();
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
</html>
