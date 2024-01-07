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
            font-size: 14px;
        }

        .table th,
        .table td {
            text-align: center;
            vertical-align: middle !important;
        }

        .table th {
            font-size: 16px;
        }

        .btn {
            padding: 4px 8px;
            font-size: 12px;
        }

        .btn-outline-secondary:hover,
        .btn-outline-primary:hover {
            color: #fff;
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .btn-outline-primary {
            color: #007bff;
        }

        .btn-outline-primary:hover {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-group-vertical > .btn,
        .btn-group > .btn {
            text-align: center;
        }

        .pagination {
            margin-top: 20px;
        }

        .pagination .page-item.active .page-link {
            z-index: 3;
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
        }

        .pagination .page-link {
            color: #007bff;
            border: 1px solid #007bff;
        }

        .pagination .page-link:hover {
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
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
                        <input type="text" id="searchWord" name="searchWord" class="form-control" placeholder="검색어를 입력하세요"/>
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
                        <td>${(sellProductpaging.page - 1) * sellProductpaging.pageLimit + loop.index +1}</td>
                        <td>${product.productName}</td>
                        <td>${product.categoryName}</td>
                        <td>${product.productStock}</td>
                        <td><a href="#" class="btn btn-outline-secondary btn-sm">상세 관리</a></td>
                        <td><a href="#" class="btn btn-outline-primary btn-sm">판매 관리</a></td>
                    </tr>
                </c:forEach>
            </table>

            <!-- 페이지네이션 추가 -->
            <div class="d-flex justify-content-center mt-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:choose>
                        <c:when test="${sellProductpaging.page > 1}">
                        <li class="page-item">
                            <a class="page-link" href="/seller/myProduct?page=${sellProductpaging.page - 1}&sellerIdx=${myProductList[0].sellerIdx}" aria-label="Previous">
                                <span aria-hidden="true">&laquo; 이전</span>
                            </a>
                        </li>
                        </c:when>
                        <c:otherwise>
                        <li class="page-item disabled">
                            <span class="page-link" aria-hidden="true">&laquo; 이전</span>
                        </li>
                        </c:otherwise>
                        </c:choose>

                        <c:forEach begin="${sellProductpaging.startPage}" end="${sellProductpaging.endPage}" var="i" step="1">
                        <li class="page-item">
                            <c:choose>
                                <c:when test="${i eq sellProductpaging.page}">
                                    <span class="page-link current">${i}</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="page-link" href="/seller/myProduct?page=${i}&sellerIdx=${myProductList[0].sellerIdx}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                        </c:forEach>

                        <c:choose>
                        <c:when test="${sellProductpaging.page < sellProductpaging.maxPage}">
                        <li class="page-item">
                            <a class="page-link" href="/seller/myProduct?page=${sellProductpaging.page + 1}&sellerIdx=${myProductList[0].sellerIdx}" aria-label="Next">
                                <span aria-hidden="true">다음 &raquo;</span>
                            </a>
                        </li>
                        </c:when>
                        <c:otherwise>
                        <li class="page-item disabled">
                            <span class="page-link" aria-hidden="true">다음 &raquo;</span>
                        </li>
                        </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </div>
        </div>
    </section>
</main>
</body>
</html>