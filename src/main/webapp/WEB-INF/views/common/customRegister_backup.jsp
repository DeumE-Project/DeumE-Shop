<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>

    <!-- Bootstrap css -->
    <link href="${pageContext.request.contextPath}/resources/common/styles.css?ver=1" rel="stylesheet">
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <!-- jQuery cdn-->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
            crossorigin="anonymous"></script>
</head>
<body>
    <h1>회원가입</h1>
    <form:form modelAttribute="registerFormDTO" method="post" action="${pageContext.request.contextPath}/customRegister">
        아이디: <form:input path="id" /><form:errors path="id" /><br />
        이름: <form:input path="name" /><form:errors path="name" /><br />
        이메일: <form:input path="email" /><form:errors path="email" /><br />
        비밀번호: <form:input path="password" /><form:errors path="password" /><br />
        전화번호: <form:input path="phone" /><form:errors path="phone" /><br />
        우편번호: <form:input path="zipcode" /><form:errors path="zipcode" /><br />
        주소: <form:input path="address1" /><form:errors path="address1" /><br />
        상세주소: <form:input path="address2" /><form:errors path="address2" /><br />
        <input type="submit" value="회원가입" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form:form>
</body>
</html>
