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
    <link rel="icon" type="image/x-icon" href="<%= request.getContextPath() %>/resources/img/logo2.png" /><br/>
    <link href="${pageContext.request.contextPath}/resources/common/styles.css" rel="stylesheet">
    <!-- jQuery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>

    <style>
        .main-header {
            background: url('${pageContext.request.contextPath}/resources/common/images/mainpage_image.jpg');
            background-repeat: no-repeat;
            max-height: 27rem !important;
            text-align: center;
            background-size: cover; /* 사진이 창 크기에 맞게 늘어나도록 설정 */
        }

        .character {
            max-width: 100%;
        }
        header{
            width: 100%;
            height: 30px;
            position: fixed;
            top: 0;
            z-index: 9;
        }
    </style>
    <title>DeumE-Shop</title>
</head>
<body>
<!-- back키 입력방지-->
<jsp:include page="/WEB-INF/views/common/register.jsp" />
<!-- Navigation-->
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="navbar container px-4 px-lg-5">
            <a class="navbar-brand" href="/main">
            <img src="<%= request.getContextPath() %>/resources/img/logo2.png" width="80" height="80">
            듬이Shop</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <%--    <form class="d-flex">
                    <button type="button" class="btn btn-outline-dark" onclick="location.href='/product/productList?categoryIdx=1'">
                        상품보기
                    </button>
                </form>--%>

                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=1">상품보기</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/notice/">공지사항</a>
                    </li>
                </ul>

                <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown1" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">고객 마이페이지</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/cart/list">
                                        <i class="bi-cart-fill me-1"></i>
                                        장바구니
                                    </a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider"/>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/order/product/list">
                                    주문히스토리
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_SELLER')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown2" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">판매자 마이페이지</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                                <li><a class="dropdown-item" href="/product/productSave">상품 등록</a></li>
                                <li>
                                    <hr class="dropdown-divider"/>
                                </li>
                                <li><a class="dropdown-item" href="/seller/mySellerPage">상품 관리</a></li>
                            </ul>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown3" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">관리자 마이페이지</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown3">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/recognize">판매자 관리</a></li>
                                <li>
                                    <hr class="dropdown-divider"/>
                                </li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/notice/">공지사항 관리</a></li>
                            </ul>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="isAnonymous()">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown5" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">로그인/회원가입</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown5">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">로그인</a></li>
                                <li>
                                    <hr class="dropdown-divider"/>
                                </li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/register">회원가입</a></li>
                            </ul>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown6" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">로그아웃</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown6">
                                <li onclick="logoutFn()">로그아웃</li>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        <script>
            function logoutFn() {
                const logoutForm = document.createElement('form');
                logoutForm.method = 'post';
                logoutForm.action = '/customLogout';
                document.body.appendChild(logoutForm);
                logoutForm.submit();
            }
        </script>
    </nav>
</header>
