<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<html>
<head>
    <title>update.jsp</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Optional custom styles */
        body {
            padding-top: 20px;
        }
        .table-bordered {
            border: 1px solid #dee2e6;
        }
        .center-buttons {
            text-align: center;
        }
        th{
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
    <br>
    <h1>공지사항 수정하기</h1><br>
    <form:form modelAttribute="noticeDTO" action="/notice/update" method="post" name="updateForm">
        <form:input path="noticeIdx" type="hidden" name="idx" value="${notice.noticeIdx}" readonly="true"/>
        <table class="table table-bordered">
            <tr>
                <th class="table-dark">제목</th>
                <td>
                    <form:input path="noticeTitle" type="text" class="form-control"/>
                </td>
            </tr>
            <tr>
                <th class="table-secondary">내용</th>
                <td>
                    <form:textarea path="noticeContent" name="noticeContent" cols="30" rows="10" class="form-control"/>
                </td>
            </tr>
        </table>
        <div class="center-buttons">
            <button type="submit" class="btn btn-primary">수정</button>
            <button type="button" onclick="cancelFn()" class="btn btn-secondary">뒤로가기</button>
        </div>
    </form:form>
</div>
<!-- Bootstrap 5 JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
    const cancelFn = () => {
        const idx = '${noticeDTO.noticeIdx}';
        location.href = "/notice?idx=" + idx;
    }

    var error='${error}';

    if (error){
        // alert(error);
        // Swal.fire(error);
        Swal.fire({
            title: error,
            icon: 'warning',
            className: 'swal-wide',
            confirmButtonColor: '#3085d6',
            confirmButtonText: '확인',
        });
    }
</script>
</body>
</html>