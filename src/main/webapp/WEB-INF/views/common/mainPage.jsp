<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="./topNavigation.jsp" %>

<head>
    <title>메인페이지</title>

    <style>
        .navbar.sticky {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
        }
    </style>
</head>
<script>
    window.onscroll = function() {
        const nav = document.querySelector('.navbar');
        if (window.pageYOffset > 50) {
            nav.classList.add('sticky');
        } else {
            nav.classList.remove('sticky');
        }
    };
    function scrollToSection(id) {
        const section = document.getElementById(id);
        section.scrollIntoView({ behavior: 'smooth' });
    }
    function throttle(callback, delay) {
        let previousCall = new Date().getTime();
        return function () {
            const time = new Date().getTime();

            if ((time - previousCall) >= delay) {
                previousCall = time;
                callback.apply(null, arguments);
            }
        };
    }
    window.onscroll = throttle(function() {
        const nav = document.querySelector('.navbar');
        if (window.pageYOffset > 50) {
            nav.classList.add('sticky');
        } else {
            nav.classList.remove('sticky');
        }
    }, 50);  // 200ms 간격으로 호출
</script>
<!-- Header-->
<header class="main-header py-5">
    <img src="${pageContext.request.contextPath}/resources/common/images/mainpage_image_bgremoved.png"
         class="character"/>
    <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-white">
            <h1 class="display-4 fw-bolder"></h1>
            <p class="lead fw-normal text-white-50 mb-0"></p>
        </div>
    </div>
</header>
<!-- Section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <c:if test="${empty productList}">
            <h1>등록된 상품이 없습니다</h1>
        </c:if>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach items="${productList}" var="product">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Sale badge-->
                        <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">
                            신상품
                        </div>
                        <!-- Product image-->
                        <img class="card-img-top main-img" src="/product/${product.productThumbSaved}"
                             alt="..."/>
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <h5 class="fw-bolder">
                                    <c:out value="${product.productName}"/>
                                </h5>
                                <fmt:formatNumber value="${product.productPrice}" pattern="#,###"/> (원)
                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto"
                                                        href="/product/detail?productIdx=${product.productIdx}">상품보러가기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</section>
<!-- Footer-->
<footer class="py-5 bg-dark">
   <%-- <div class="container">
        <a class="m-0 text-center text-white">Copyright &copy; 천재교육</a>
    </div>--%>

    <div class="row align-items-center justify-content-between flex-column flex-sm-row">
        <div class="col-auto"><div class="small m-0 text-white">&nbsp;&nbsp;Copyright &copy; 천재교육 &middot; Team DeumE-Shop</div></div>


        <div class="col-auto">
            <a class="link-light small" href="https://github.com/DeumE-Shop/DeumE-Shop">Git Hub</a>
            <span class="text-white mx-1">&middot;</span>
            <a class="link-light small" href="#">Notion</a>&nbsp;&nbsp;
        </div>
    </div>

</footer>

<script>
    const productNotExistErrorMsg = '<c:out value="${productNotExistErrorMsg}"/>';
    if (productNotExistErrorMsg) {
        alert(productNotExistErrorMsg);
    }
</script>
</body>
</html>
