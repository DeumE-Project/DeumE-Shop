<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<head>
    <title>상품목록</title>
    <style>

        #noProductMessage {
            text-align: center;
        }
    </style>
</head>

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
    <jsp:include page="productCategoryList.jsp"></jsp:include>
</header>
<!-- Section-->
<section class="py-5">
    <form class="row gx-2 justify-content-center mb-4" method="get" action="/product/productList">
        <input type="hidden" name="categoryIdx" value="${categoryIdx}"/>
        <input type="hidden" name="productIdx" value="${productIdx}"/>
        <div class="col-lg-2">
            <select class="form-select" name="searchField" id="select-option">
                <option value="title">상품명</option>
                <%--<option value="category">카테고리</option>--%>
            </select>
        </div>
        <div class="col-lg-4">
            <div class="input-group">
                <input type="text" id="searchWord" name="searchWord" class="form-control" placeholder="검색어(상품명)를 입력하세요"/>
                <button class="btn btn-primary" type="submit" <c:if test="${empty productListPaging}"></c:if>>검색</button>
            </div>
        </div>
    </form>
    <div class="container px-4 px-lg-5 mt-5">
        <c:if test="${empty productListPaging}">
            <div id="noProductMessage">
                <h1>등록된 상품이 없습니다</h1>
            </div>
        </c:if>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach items="${productListPaging}" var="product">
                <%--<c:choose>
                    <c:when test="${searchWord eq '' || searchWord eq null}">
                        <td>${(productPageDTO.page - 1) * productPageDTO.pageLimit + loop.index +1}</td>
                    </c:when>
                    <c:otherwise>
                        <td>${(productListSearchPageDTO.page - 1) * productListSearchPageDTO.pageLimit + loop.index +1}</td>
                    </c:otherwise>
                </c:choose>--%>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="/product/${product.productThumbSaved}" alt="..."/>
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
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="/product/detail?productIdx=${product.productIdx}">상품보러가기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="d-flex justify-content-center mt-4">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${searchWord eq null}">
                        <c:choose>
                            <c:when test="${productPageDTO.page > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="/product/productList?page=${productPageDTO.page - 1}&categoryIdx=${productListPaging[0].categoryIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Previous">
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

                        <!-- 페이지 번호 -->
                        <c:forEach begin="${productPageDTO.startPage}" end="${productPageDTO.endPage}" var="i" step="1">
                            <li class="page-item">
                                <c:choose>
                                    <c:when test="${i eq productPageDTO.page}">
                                        <span class="page-link current">${i}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="page-link" href="/product/productList?page=${i}&categoryIdx=${productListPaging[0].categoryIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>

                        <!-- 다음 페이지 -->
                        <c:choose>
                            <c:when test="${productPageDTO.page < productPageDTO.maxPage}">
                                <li class="page-item">
                                    <a class="page-link" href="/product/productList?page=${productPageDTO.page + 1}&categoryIdx=${productListPaging[0].categoryIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Next">
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
                    </c:if>

                    <c:if test="${searchWord ne null}">
                        <c:choose>
                            <c:when test="${productListSearchPageDTO.page > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="/product/productList?page=${productPageDTO.page - 1}&categoryIdx=${productListPaging[0].categoryIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Previous">
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

                        <!-- 페이지 번호 -->
                        <c:forEach begin="${productListSearchPageDTO.startPage}" end="${productListSearchPageDTO.endPage}" var="i" step="1">
                            <li class="page-item">
                                <c:choose>
                                    <c:when test="${i eq productListSearchPageDTO.page}">
                                        <span class="page-link current">${i}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="page-link" href="/product/productList?page=${i}&categoryIdx=${productListPaging[0].categoryIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>

                        <!-- 다음 페이지 -->
                        <c:choose>
                            <c:when test="${productListSearchPageDTO.page < productListSearchPageDTO.maxPage}">
                                <li class="page-item">
                                    <a class="page-link" href="/product/productList?page=${productListSearchPageDTO.page + 1}&categoryIdx=${productListPaging[0].categoryIdx}&searchField=${param.searchField}&searchWord=${param.searchWord}" aria-label="Next">
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
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</section>
<script>
    const productNotExistErrorMsg = '<c:out value="${productNotExistErrorMsg}"/>';
    if (productNotExistErrorMsg) {
        alert(productNotExistErrorMsg);
    }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>