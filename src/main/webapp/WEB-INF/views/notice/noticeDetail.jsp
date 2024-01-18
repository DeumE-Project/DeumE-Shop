<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<html>
<head>
    <title>detail.jsp</title>
    <style>
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 30px;
        }
        th {
            width: 120px;
            text-align: center;
            vertical-align: middle;
            font-size: x-large;
        }
        td{
            font-size: x-large;
        }
        .center-buttons {
            text-align: center;
        }
        .content-section {
            font-size: x-large;
            height: 300px;
            overflow: auto;
        }
    </style>
</head>
<body>

<div class="contentset">
    <br><br><br><br>
<div class="container">
    <br>
    <h1>공지사항 상세보기</h1><br>
    <table class="table table-bordered">
        <tr>
            <th class="table-dark">제목</th>
            <td><c:out value="${notice.noticeTitle}"/></td>
        </tr>
        <tr>
            <th class="table-dark">작성일</th>
            <td>${notice.noticeDateStr}</td>
        </tr>
        <tr>
            <th class="table-secondary">내용</th>
            <td colspan="3" class="content-section"><c:out value="${notice.noticeContent}"/></td>
        </tr>
    </table>
    <div class="center-buttons">
        <button onclick="listFn()" class="btn btn-primary">목록</button>
        <button onclick="updateFn()" class="btn btn-secondary">수정</button>
        <button onclick="deleteFn()" class="btn btn-danger">삭제</button>
    </div>
</div>
</div>
<script>
    const listFn = () => {
        const page = '${page}';
        location.href = "/notice/?page=" + page;
    }
    const updateFn = () => {
        const idx = '${notice.noticeIdx}';
        location.href = "/notice/update?idx=" + idx;
    }
    const deleteFn = () => {
        const idx = '${notice.noticeIdx}';
        location.href = "/notice/delete?idx=" + idx;
    }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>