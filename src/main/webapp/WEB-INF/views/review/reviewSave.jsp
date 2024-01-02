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
    <jsp:include page="#"/>
</head>

<body class="bg-light">
<div class="container mt-5">
    <form action="/product/review/save" method="post" onsubmit="return validate();">
        <div class="form-group">
            <input type="text" name="customerIdx" class="form-control" placeholder="작성자" id="customerIdx" autofocus  onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <input type="text" name="reviewStar" class="form-control" placeholder="별점" id="reviewStar" autofocus  onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <textarea name="reviewContent" class="form-control" rows="5" placeholder="내용을 입력하세요" id="reviewContent"  onkeyup="characterCheck(this)" onkeydown="characterCheck(this)"></textarea>
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