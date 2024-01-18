<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>
<jsp:include page="/WEB-INF/views/common/nav.jsp"/>
<head>
    <title>주문페이지</title>
    <style>
        .red {
            color: red;
        }
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>
</head>

<body>
<div class="contentset">
<!-- Page Header Start -->
<div class="container-fluid">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">주문/결제</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="#">천재교육</a></p>
            <p class="m-0 px-2">-</p>
            <p class="m-0">주문페이지</p>
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
                        배송지 입력
                    </h4>
                </div>

                <form:form modelAttribute="orderProductForm" method="post" action="/cart/order?cartIdx=${cartIdx}">
<%--                    <input type="hidden" name="cartIdx" value="${cartIdx}"/>--%>
                    <div style="margin-top: 15px;">
                        <div class="form-group">
                            <label>수취인</label>
                            <form:input path="orderName" class="form-control" type="text"
                                        placeholder="이름을 입력해주세요"/>
                            <form:errors path="orderName" cssClass="red"/>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label>이메일</label>
                            <form:input path="orderEmail" class="form-control" type="text"
                                        placeholder="이메일을 입력해주세요"/>
                            <form:errors path="orderEmail" cssClass="red"/>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label>전화번호</label>
                            <form:input path="orderPhone" class="form-control" type="text"
                                        placeholder="숫자만 입력해주세요"/>
                            <form:errors path="orderPhone" cssClass="red"/>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label>우편번호</label><br/>
                            <button type="button" class="btn btn-link"
                                    onclick="sample6_execDaumPostcode()">우편번호 검색
                            </button><br/>
                            <form:input path="orderZipcode" class="form-control" type="text"
                                        placeholder="우편번호 검색을 클릭해주세요" readonly="true"/>
                            <form:errors path="orderZipcode" cssClass="red"/>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label>주소</label>
                            <form:input path="orderAddress1" class="form-control" type="text"
                                        placeholder="우편번호 검색을 통해 입력되게 해주세요" readonly="true"/>
                            <form:errors path="orderAddress1" cssClass="red"/>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label>상세주소</label>
                            <form:input path="orderAddress2" class="form-control" type="text"
                                        placeholder="동/호수 등 상세주소를 입력해주세요"/>
                            <form:errors path="orderAddress2" cssClass="red"/>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label>배송요청사항</label>
                            <form:select path="orderRequest" class="form-control">
                                <option value="op-none">주문 요청사항 선택</option>
                                <option value="op-text">배송 전 문자 남겨주세요</option>
                                <option value="op-call">배송 전 전화 해주세요</option>
                                <option value="op-security">경비실에 맡겨주세요</option>
                            </form:select><br/>
                        </div>
                    </div>
                </form:form>
            </div>


            <div style="margin-top: 2.5rem;">
                <!-- 여기서부터 장바구니 한 개 시작 -->
                <div>
                    <h5 class="seller-name">판매자 이름: <c:out value="${cart.sellerName}"/></h5>
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
                                <c:set var="productTotalPriceWithoutDeliveryFee" value="0"/>
                                <c:forEach items="${cart.cartDetailDTOList}" var="cartDetailDTO">
                                    <tr>
                                        <td class="align-middle">
                                            <img src="/product/${cartDetailDTO.productThumbSaved}" alt="" style="width: 100px; height: 100px;">
                                            <c:out value="${cartDetailDTO.productName}"/>
                                        </td>
                                        <td class="align-middle">
                                            <fmt:formatNumber value="${cartDetailDTO.productPrice}" pattern="#,###"/>
                                        </td>
                                        <td class="align-middle">
                                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                                <input type="text"
                                                       class="form-control form-control-sm text-center"
                                                       value="${cartDetailDTO.buyCount}" readonly>
                                            </div>
                                        </td>
                                        <td class="align-middle">
                                            <fmt:formatNumber value="${cartDetailDTO.productPrice * cartDetailDTO.buyCount}" pattern="#,###"/>
                                        </td>
                                    </tr>
                                    <c:set var="productTotalPriceWithoutDeliveryFee"
                                           value="${productTotalPriceWithoutDeliveryFee + (cartDetailDTO.productPrice * cartDetailDTO.buyCount)}"/>
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
                            <fmt:formatNumber value="${productTotalPriceWithoutDeliveryFee}" pattern="#,###"/>
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
                            <fmt:formatNumber value="${productTotalPriceWithoutDeliveryFee + 2500}" pattern="#,###"/>
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
                <div class="card-footer border-secondary bg-transparent">
                    <button class="btn btn-primary font-weight-bold" style="width: 100%;" id="submit-btn">주문하기</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Checkout End -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                var addr = '';
                var extraAddr = '';
                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }
                if (data.userSelectedType === 'R') {
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    addr += extraAddr;

                }
                document.getElementById('orderZipcode').value = data.zonecode;
                document.getElementById("orderAddress1").value = addr;
                document.getElementById("orderAddress2").focus();
            }
        }).open();
    }
</script>

<script>
    $('#submit-btn').on('click', function () {
        $('#orderProductForm').submit();
    })
</script>
</html>
