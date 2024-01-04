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
    <jsp:include page="nav.jsp"/>
</head>
<body class="bg-light">
<div class="container mt-5">
    <form action="/board/update" method="post" name="updateForm" onsubmit="return validate();">
        <div class="form-group">
            <input type="hidden" name="id" value="${board.id}" class="form-control" readonly onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="boardWriter">작성자</label>
            <input type="text" name="boardWriter" value="${board.boardWriter}" class="form-control" readonly onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="boardPass">비밀번호</label>
            <input type="password" name="boardPass" id="boardPass" class="form-control" placeholder="비밀번호" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="boardTitle">제목</label>
            <input type="text" name="boardTitle" value="${board.boardTitle}" class="form-control" id="boardTitle" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        </div>
        <div class="form-group">
            <label for="boardContents">내용</label>
            <textarea name="boardContents" class="form-control" rows="5" id="boardContents" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">${board.boardContents}</textarea>
        </div>
        <button type="button" class="btn btn-primary" onclick="updateReqFn()">수정</button>
    </form>
</div>
</body>
<script>
    const updateReqFn = () => {
        if (validate()) {
            const passInput = document.getElementById("boardPass").value;
            const passDB = '${board.boardPass}';
            if (passInput == passDB) {
                alert("수정이 완료되었습니다.")
                document.updateForm.submit();
            } else {
                alert("비밀번호가 일치하지 않습니다!!");
            }
        }
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
