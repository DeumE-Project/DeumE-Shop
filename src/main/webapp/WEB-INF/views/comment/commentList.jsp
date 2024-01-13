<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <title>list</title>
    <jsp:include page="#"/>
</head>

<body class="bg-light">
<div class="container mt-3">
    <table class="table table-striped">
        <tr>
            <th>댓글번호</th>
            <th>내용</th>
            <th>작성시간</th>
        </tr>
        <c:forEach items="${commentList}" var="comment">
            <tr>
                <td>${comment.commentIdx}</td>
                <td>${comment.commentContents}</td>
                <td>${comment.commentCreatedTimeStr}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
<script>
      const commentWrite = () => {
        const contents = document.getElementById("commentContents").value;
        const productIdx = 2;
        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                commentContents: contents,
                productIdx: productIdx
            },
            dataType: "json",
            success: function(commentList) {
                console.log("작성성공");
                console.log(commentList);
                let output = "<table class='table table-striped mb3' >";
                output += "<tr><th>댓글번호</th>";
                output += "<th>내용</th>";
                output += "<th>작성시간</th></tr>";
                for(let i in commentList){
                    output += "<tr>";
                    output += "<td>"+commentList[i].commentIdx+"</td>";
                    output += "<td>"+commentList[i].commentContents+"</td>";
                    output += "<td>"+commentList[i].commentCreatedTimeStr+"</td>";
                    output += "</tr>";
                }
                output += "</table>";
                document.getElementById('comment-list').innerHTML = output;
                document.getElementById('commentWriter').value='';
                document.getElementById('commentContents').value='';
            },
            error: function() {
                console.log("실패");
            }
        });
    }

</script>
</html>
