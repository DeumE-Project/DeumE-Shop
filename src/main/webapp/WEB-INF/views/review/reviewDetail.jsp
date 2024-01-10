<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <title>detail</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <jsp:include page="#"/>

</head>
<body class="bg-light">
<div class="container mt-4">
    <table class="table">
        <tr>
            <th>번호</th>
            <td>${productReview.reviewIdx}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${productReview.customerIdx}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td>${productReview.reviewContent}</td>
        </tr>
        <tr>
            <th>별점</th>
            <td>${productReview.reviewStar}</td>
        </tr>
        <tr>
            <th>파일이름</th>
            <td>${productReview.reviewImgSaved}</td>
        </tr>
        <tr>
            <th>작성시간</th>
            <td>${productReview.reviewDate}</td>
        </tr>
    </table>

    <div class="mb-3">
        <button class="btn btn-secondary" onclick="listFn()">목록</button>
        <button class="btn btn-warning" onclick="updateFn()">수정</button>
        <button class="btn btn-danger" onclick="deleteFn()">삭제</button>
    </div>

    <div class="mb-3">
        <input type="text" id="commentWriter" class="form-control" placeholder="작성자"   onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        <input type="text" id="commentContents" class="form-control" placeholder="내용"   onkeyup="characterCheck(this)" onkeydown="characterCheck(this)">
        <button class="btn btn-success" id="comment-write-btn" onclick="commentWrite()"  onkeyup="characterCheck(this)" onkeydown="characterCheck(this)" >댓글작성</button>
    </div>

   <%-- <div id="comment-list">
        <table class="table table-striped">
            <tr>
                <th>댓글번호</th>
                <th>작성자</th>
                <th>내용</th>
                <th>작성시간</th>
            </tr>
            <c:forEach items="${commentList}" var="comment">
                <tr>
                    <td>
                        <a href="/comment?id=${comment.id}">${comment.id}</a>
                    </td>
                    <td>${comment.commentWriter}</td>
                    <td>${comment.commentContents}</td>
                    <td>${comment.commentCreatedTime}</td>
                </tr>
            </c:forEach>
        </table>
    </div>--%>
</div>

</body>
<script>
    const listFn = () => {
        const page = '${page}';
        location.href = "/product/review/paging?page=" + page;
    }
    const updateFn = () => {
        const reviewIdx = '${productReview.reviewIdx}';
        location.href = "/product/review/update?reviewIdx=" + reviewIdx;
    }
    const deleteFn = () => {
        const reviewIdx = `${productReview.reviewIdx}`;

        const isConfirmed = window.confirm("정말 삭제하시겠습니까?");

        if (isConfirmed) {
            alert("삭제 되었습니다.");
            location.href = "/product/review/delete?reviewIdx=" + reviewIdx;
        } else {
            alert("삭제가 취소되었습니다.");
        }
    };
    const commentWrite = () => {
        const writer = document.getElementById("commentWriter").value;
        const contents = document.getElementById("commentContents").value;
        const board = '${board.id}';
        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                commentWriter: writer,
                commentContents: contents,
                boardId: board
            },
            dataType: "json",
            success: function(commentList) {
                console.log("작성성공");
                console.log(commentList);
                let output = "<table class='table table-striped'>";
                output += "<tr><th>댓글번호</th>";
                output += "<th>작성자</th>";
                output += "<th>내용</th>";
                output += "<th>작성시간</th></tr>";

                for (let i in commentList) {
                    output += "<tr>";
                    output += "<td><a href='/comment?id=" + commentList[i].id + "'>" + commentList[i].id + "</a></td>";
                    output += "<td>" + commentList[i].commentWriter + "</td>";
                    output += "<td>" + commentList[i].commentContents + "</td>";
                    output += "<td>" + commentList[i].commentCreatedTime + "</td>";
                    output += "</tr>";
                }

                output += "</table>";
                document.getElementById('comment-list').innerHTML = output;
                document.getElementById('commentWriter').value = '';
                document.getElementById('commentContents').value = '';
            },
            error: function() {
                console.log("실패");
            }
        });
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