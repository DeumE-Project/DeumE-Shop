<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>판매 상품 관리</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" />
    <style>
        table {
            font-size: 14px; /* 테이블 전체의 글자 크기 조절 */
        }

        .table th,
        .table td {
            text-align: center; /* 테이블 셀의 텍스트 가로 정렬 */
            vertical-align: middle !important; /* 테이블 셀의 텍스트 세로 정렬 */
        }

        .table th {
            font-size: 16px; /* 테이블 헤더의 글자 크기 조절 */
        }

        .btn {
            padding: 4px 8px; /* 버튼의 패딩 조절 */
            font-size: 12px; /* 버튼 내의 텍스트 크기 조절 */
        }

        .btn-outline-secondary:hover,
        .btn-outline-primary:hover {
            color: #fff;
            background-color: #6c757d; /* btn-outline-secondary와 btn-outline-primary 버튼에 호버 시 배경색 조절 */
            border-color: #6c757d;
        }

        .btn-outline-primary {
            color: #007bff; /* btn-outline-primary 버튼의 텍스트 색상 조절 */
        }

        .btn-outline-primary:hover {
            background-color: #007bff; /* btn-outline-primary 버튼에 호버 시 배경색 조절 */
            border-color: #007bff;
        }

        .btn-group-vertical > .btn,
        .btn-group > .btn {
            text-align: center;


        }
    </style>
</head>
<body class="d-flex flex-column">
<main class="flex-shrink-0">
    <section class="bg-light py-5">
        <div class="container px-5 my-5">
            <div class="text-center mb-5">
                <h1 class="fw-bolder">판매 상품 관리</h1>
            </div>

            <!-- 검색 폼 -->
            <form class="row gx-2 justify-content-center mb-4">
                <div class="col-lg-2">
                    <select class="form-select" name="searchField" id="select-option">
                        <option value="title">상품명</option>
                        <option value="category">카테고리</option>
                    </select>
                </div>
                <div class="col-lg-4">
                    <div class="input-group">
                        <input type="text" id="searchWord"  name="searchWord" class="form-control" placeholder="검색어를 입력하세요"/>
                        <button class="btn btn-primary" type="submit">검색</button>
                    </div>
                </div>
            </form>

            <table class="table table-bordered">
                <thead class="table-dark">
                <tr>
                    <th style="width: 10%;">번호</th>
                    <th style="width: 20%;">상품명</th>
                    <th style="width: 20%;">카테고리</th>
                    <th style="width: 15%;">재고</th>
                    <th style="width: 15%;">상세 관리</th>
                    <th style="width: 20%;">판매 관리</th>
                </tr>
                </thead>
                <c:forEach items="${myProductList}" var="product" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${product.productName}</td>
                        <td>${product.categoryName}</td>
                        <td>${product.productStock}</td>
                        <td ><a href="#" class="btn btn-outline-secondary btn-sm ">상세 관리</a></td>
                        <td><a href="#" class="btn btn-outline-primary btn-sm">판매 관리</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>