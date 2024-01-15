<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<head>
    <title>주문상세</title>
</head>

<!-- Page Header Start -->
<div class="container-fluid">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">주문 상세정보</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="#">천재교육</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">주문 상세정보</p>
        </div>
    </div>
</div>
<!-- Page Header End -->

<!-- Checkout Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <div class="col-lg-8">
            <div class="mb-4">
                <div class="card-header border-0" style="background-color: #EDF1FF; padding: 10px 0px; padding-left: 10px;
                    border-top-left-radius: 5px; border-top-right-radius: 5px;">
                    <h4 class="font-weight-semi-bold m-0">
                        배송지
                    </h4>
                </div>
                <div style="margin-top: 15px;">
                    <div class="form-group">
                        <label>수취인</label>
                        <input type="text" class="form-control" value="${order.orderName}" disabled/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label>이메일</label>
                        <input class="form-control" type="text" value="${order.orderEmail}" disabled/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label>전화번호</label>
                        <input class="form-control" type="text" value="${order.orderPhone}" disabled/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label>우편번호</label><br/>
                        <input class="form-control" type="text" value="${order.orderZipcode}" disabled/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label>주소</label>
                        <input class="form-control" type="text" value="${order.orderAddress1}" disabled/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label>상세주소</label>
                        <input class="form-control" type="text" value="${order.orderAddress2}" disabled/>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label>배송요청사항</label>
                        <select class="form-control" disabled>
                            <option ${order.orderRequest == "op-none" ? 'selected' : ''}>요청사항 없음</option>
                            <option ${order.orderRequest == "op-text" ? 'selected' : ''}>배송 전 문자 남겨주세요</option>
                            <option ${order.orderRequest == "op-call" ? 'selected' : ''}>배송 전 전화 해주세요</option>
                            <option ${order.orderRequest == "op-security" ? 'selected' : ''}>경비실에 맡겨주세요</option>
                        </select><br/>
                    </div>
                </div>
            </div>
            <div style="margin-top: 2.5rem;">
                <!-- 여기서부터 장바구니 한 개 시작 -->
                <div>
                    <h5 class="seller-name">판매자 이름: <c:out
                            value="${order.orderDetailList.get(0).productDTO.sellerName}"/></h5>
                    <div>
                        <div class="table-responsive mb-5">
                            <table class="table table-bordered text-center mb-0">
                                <thead class="text-dark" style="background-color: #EDF1FF">
                                <tr>
                                    <th>상품</th>
                                    <th>가격</th>
                                    <th>수량</th>
                                    <th>합계</th>
                                </tr>
                                </thead>
                                <tbody class="align-middle">
                                <c:forEach items="${order.orderDetailList}" var="orderDetailDTO">
                                    <tr>
                                        <td class="align-middle">
                                            <img src="" alt="" style="width: 100px; height: 100px;">
                                            <c:out value="${orderDetailDTO.productDTO.productName}"/>
                                        </td>
                                        <td class="align-middle">
                                            <fmt:formatNumber value="${orderDetailDTO.productPrice}" pattern="#,###"/>
                                        </td>
                                        <td class="align-middle">
                                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                                <input type="text"
                                                       class="form-control form-control-sm text-center"
                                                       value="${orderDetailDTO.productCount}" readonly>
                                            </div>
                                        </td>
                                        <td class="align-middle">
                                            <fmt:formatNumber
                                                    value="${orderDetailDTO.productTotalPrice}"
                                                    pattern="#,###"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4">
            <div class="card border-secondary mb-5">
                <div class="card-header border-0" style="background-color: #EDF1FF">
                    <h4 class="font-weight-semi-bold m-0">주문 요약</h4>
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-between mb-3 pt-1">
                        <h6 class="font-weight-medium">상품</h6>
                        <h6 class="font-weight-medium">
                            <fmt:formatNumber value="${order.orderTotalPrice}" pattern="#,###"/>
                        </h6>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">배송</h6>
                        <h6 class="font-weight-medium">
                            <fmt:formatNumber value="${2500}" pattern="#,###"/>
                        </h6>
                    </div>
                </div>
                <div class="card-footer border-secondary bg-transparent">
                    <div class="d-flex justify-content-between mt-2">
                        <h5 class="font-weight-bold">합계</h5>
                        <h5 class="font-weight-bold">
                            <fmt:formatNumber value="${order.orderTotalPrice + 2500}" pattern="#,###"/>
                        </h5>
                    </div>
                </div>
            </div>
            <div class="card border-secondary mb-5">
                <div class="card-header border-0" style="background-color: #EDF1FF">
                    <h4 class="font-weight-semi-bold m-0">결제수단</h4>
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="checkbox" class="custom-control-input" name="payment" id="point" checked
                                   disabled>
                            <label class="custom-control-label" for="point">포인트</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
