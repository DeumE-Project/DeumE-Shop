<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="${pageContext.request.contextPath}/resources/common/styles.css" rel="stylesheet">
    <!-- jQuery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul></ul>
            <ul></ul>
            <ul></ul>
            <ul></ul>
            <ul></ul>
            <ul></ul>
            <ul></ul>
            <ul></ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=1">필기구</a>
                </li>
            </ul>

            <ul></ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=2">노트/메모</a>
                </li>
            </ul>

            <ul></ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=3">사무용품</a>
                </li>
            </ul>

            <ul></ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=4">완구</a>
                </li>
            </ul>

            <ul></ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=5">교보재</a>
                </li>
            </ul>

            <ul></ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=6">PC용품</a>
                </li>
            </ul>



    </div>
</nav>

