<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>product Info Update</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>제품 상세 정보 수정</h1>

    <form:form modelAttribute="productDTO" action="/product/productInfoUpdate?sellerIdx=${productDTO.sellerIdx}&productIdx=${productDTO.productIdx}" method="post">
        <input type="hidden" name="sellerIdx" value="${productDTO.sellerIdx}">
        <input type="hidden" name="productIdx" value="${productDTO.productIdx}">
        <div class="mb-3">
            <label for="productName" class="form-label"> 상품명  </label>
            <input type="text" id="productName" name="productName" class="form-control" value="<c:out value='${productDTO.productName}'/>" readonly>
        </div>

        <div class="mb-3">
            <label for="productPrice" class="form-label"> 상품 가격  </label>
            <form:input type="number" path="productPrice" class="form-control" onkeypress="return isNumberKey(event)" />
            <form:errors path="productPrice" cssClass="text-danger"/>
        </div>

        <div class="mb-3">
            <label for="productStock" class="form-label"> 상품 재고  </label>
            <form:input type="number" path="productStock" class="form-control" onkeypress="return isNumberKey(event)" />
            <form:errors path="productStock" cssClass="text-danger"/>
        </div>

        <div class="mb-3">
            <label for="productExplain" class="form-label"> 상품 설명  </label>
            <%--<form:input id="productExplain" path="productExplain" class="form-control" value="<c:out value='${product.productExplain}'"/>--%>
            <form:input path="productExplain" class="form-control"/>
            <form:errors path="productExplain" cssClass="text-danger"/>
        </div>


        <button type="submit" class="btn btn-primary">수정</button>
    </form:form>

<script>
    function isNumberKey(evt) {
        let charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
</script>
</div>


<!-- 부트스트랩 5 JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
