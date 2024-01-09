<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>판매자 마이 페이지</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column">
<main class="flex-shrink-0">
    <!-- 판매자 정보 -->
    <section class="bg-light py-5">
        <div class="container px-5 my-5">
            <div class="text-center mb-5">
                <h1 class="fw-bolder">${mySeller.sellerName}</h1>
                <p class="lead fw-normal text-muted mb-0">${mySeller.sellerPhone} | 가입일: ${mySeller.sellerJoined}</p>
            </div>
            <!-- 판매자 통계 -->
            <div class="row gx-5 justify-content-center mb-4">
                <div class="col-lg-6">
                    <div class="row mb-3">
                        <div class="col-12">
                            <strong>등록 상품 개수: </strong><span id="registeredProducts">${myCount}</span>
                        </div>
                        <div class="col-12">
                            <strong>누적 매출: </strong><span id="totalSales">${myRev}</span>
                        </div>
                        <div class="col-12">
                            <strong>이달 매출: </strong><span id="thisMonthSales">${dateRev}</span>
                        </div>
                        <div class="col-12">
                            <strong>월 평균 매출: </strong><span id="avgRev">${avgRev}</span>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 작업 버튼 -->
            <div class="row gx-5 justify-content-end">
                <div class="col-lg-6">
                    <button class="btn btn-primary" onclick="location.href='/product/productSave'">상품 등록하기</button>
                    <button class="btn btn-secondary mx-2" onclick="location.href='/seller/myProduct?sellerIdx=1'">판매 상품 관리</button>
                    <button class="btn btn-outline-secondary" onclick="location.href='#'">뒤로 가기</button>
                </div>
            </div>
        </div>
    </section>
</main>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>