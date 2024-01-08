<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니 목록</title>

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/common/styles.css" rel="stylesheet">
    <!-- jQuery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <style>
        .outer-cart-div {
            margin-bottom: 100px;
        }
    </style>
</head>
<body>
<!-- Page Header Start -->
<div class="container-fluid">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">장바구니 목록</h1>
        <div class="d-inline-flex">
            <p class="m-0">장바구니 개수: [2]개</p>
        </div>
    </div>
</div>

<!-- Cart Start -->
<div class="container-fluid"> <!--가장 바깥쪽 div 시작-->
    
    <c:choose>
        <c:when test="${not empty cart}"></c:when>
    </c:choose>

    <!-- 여기서부터 장바구니 한 개 시작 -->
    <div class="outer-cart-div">
        <h5 class="seller-name" style="padding-left: 3rem;">판매자 이름: []</h5>
        <div class="row px-xl-5">
            <div class="col-lg-8 table-responsive mb-5">
                <table class="table table-bordered text-center mb-0">
                    <thead class="text-dark" style="background-color: #EDF1FF">
                    <tr>
                        <th>번호</th>
                        <th>상품</th>
                        <th>가격(&#8361;)</th>
                        <th>수량</th>
                        <th>합계(&#8361;)</th>
                        <th>삭제</th>
                    </tr>
                    </thead>
                    <tbody class="align-middle">
                    <tr>
                        <td class="align-middle">1</td>
                        <td class="align-middle"><img src="img/product-1.jpg" alt=""
                                                      style="width: 100px; height: 100px;"> Colorful Stylish Shirt
                        </td>
                        <td class="align-middle">10,000</td>
                        <td class="align-middle">
                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                <div class="input-group-btn" style="margin-right: 5px;">
                                    <button class="btn btn-sm btn-primary btn-minus">
                                        <i class="fa fa-minus"></i>
                                    </button>
                                </div>
                                <input type="text" class="form-control form-control-sm bg-light text-center"
                                       value="1" style="margin-right: 5px;">
                                <div class="input-group-btn">
                                    <button class="btn btn-sm btn-primary btn-plus">
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle">25,000</td>
                        <td class="align-middle">
                            <button class="btn btn-sm btn-warning"><i
                                    class="fa fa-times"></i></button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-4">
                <div class="card border-secondary mb-5">
                    <div class="card-header border-0" style="background-color: #EDF1FF">
                        <h4 class="font-weight-semi-bold m-0">주문 요약</h4>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-3 pt-1">
                            <h6 class="font-weight-medium">물품합계(&#8361;)</h6>
                            <h6 class="font-weight-medium">100,000</h6>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h6 class="font-weight-medium">배송비(&#8361;)</h6>
                            <h6 class="font-weight-medium">2,500</h6>
                        </div>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">총비용(&#8361;)</h5>
                            <h5 class="font-weight-bold">102,500</h5>
                        </div>
                        <button class="btn btn-block btn-primary my-3 py-3" style="width: 100%;">주문하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div> <!--장바구니 한 개 끝-->
</div> <!--가장 바깥쪽 div 끝-->
</body>
</html>
