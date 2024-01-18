<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<html>
<head>
    <title>save</title>
    <style>
        .center-buttons {
            text-align: center;
        }
        th {
            text-align: center;
            vertical-align: middle;
            font-size: x-large;
            width: 10%;
        }
        td{
            font-size: x-large;
        }
    </style>
</head>
<body>
<div class="container">
    <br><br><br><br>
    <h1>공지사항 등록하기</h1><br>
    <form:form modelAttribute="noticeDTO" action="${pageContext.request.contextPath}/notice/save" method="post">
        <table class="table table-bordered">
            <tr>
                <th class="table-dark">제목</th>
                <td>
                    <form:input path="noticeTitle" type="text" class="form-control" placeholder="제목을 입력하세요"/>
                </td>
            </tr>
            <tr>
                <th class="table-secondary">내용</th>
                <td>
                    <form:textarea path="noticeContent" cols="30" rows="10" class="form-control" placeholder="내용을 입력하세요"></form:textarea>
                </td>
            </tr>
        </table>
        <div class="center-buttons">
            <button type="submit" class="btn btn-primary">등록</button>
            <button type="button" onclick="cancelFn()" class="btn btn-secondary">뒤로가기</button>
        </div>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    const cancelFn = () => {
        location.href = "/notice/";
    }
    var error='${error}';

    if (error){
        Swal.fire({
            title: error,
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
        });
    }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>