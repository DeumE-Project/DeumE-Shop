<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>product review save</title>
    <!-- 부트스트랩 CDN 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>
</head>
<body>
<div class="contentset">
<div class="container mt-5">
    <h1>한줄 리뷰 등록</h1>

    <%-- 스프링 폼을 통해 유효성검사 실행--%>
    <%--@elvariable id="commentSaveDTO" type="kr.co.chunjaeshop.product_comment.dto.CommentSaveDTO"--%>
    <form:form modelAttribute="commentSaveDTO" action="/product/comment/save?orderDetailIdx=${commentSaveDTO.orderDetailIdx}"
               method="post" cssClass="mb-5" >

        <div class="mb-3">
            <label for="commentWriter" class="form-label">작성자</label>
            <form:input class="form-control" path="commentWriter" rows="3" placeholder="작성자를 입력하세요" />
        </div>
        <div>
        <%--바인딩 에러 출력하는곳 ( 에러가없을경우 표시되지않고 있을경우 span으로 출력)--%>
            <form:errors path="commentWriter" cssClass="text-danger"/>
        </div>
        <div class="mb-3">
            <label for="commentContents" class="form-label">리뷰 내용</label>
            <form:textarea class="form-control" path="commentContents" rows="3" placeholder="리뷰 내용을 입력하세요" />
        </div>
        <div>
        <form:errors path="commentContents" cssClass="text-danger"/>
        </div>

        <button type="submit" class="btn btn-primary">등록</button>
        <button type="reset" class="btn btn-primary">초기화</button>

    </form:form>

</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>