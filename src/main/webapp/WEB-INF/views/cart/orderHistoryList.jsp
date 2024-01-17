<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<title>주문내역 리스트</title>
<style>
    td {
        vertical-align: middle;
    }

    .container-history {
        border-top: 1px solid black;
        padding: 0px;
    }

    .history-list {
        border-style: none!important;
    }
</style>


<div class="container" style="margin-top: 2rem;">
    <h4>주문 내역</h4>
</div>
<br/>

<c:choose>
    <c:when test="${empty orderProductList}">
        <h5>주문 내역이 없습니다</h5>
    </c:when>
    <c:otherwise>
        <c:forEach items="${orderProductList}" var="orderProduct">
            <div class="container container-history">
                <table class="table">
                    <thead class="table-light">
                    <tr>
                        <th colspan="3">
                            <c:out value="${orderProduct.formattedOrderDateWithoutTime}"/>
                        </th>
                        <th>
                            <a href="/order/product/detail?orderIdx=${orderProduct.orderIdx}">상세내역</a>
                        </th>
                    </tr>
                    </thead>
                    <tbody class="history-list">
                    <tr>
                        <td>
                            <c:forEach items="${orderProduct.orderDetailList}" var="temp">
                                <img width="100px" height="145px" src="/product/${temp.productDTO.productThumbSaved}"/>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${orderProduct.orderDetailList}" var="orderDetail">
                                <c:out value="${orderDetail.productDTO.productName}"/>
                            </c:forEach>
                            <br/>
                            <c:choose>
                                <c:when test="${orderProduct.orderDetailCount == 1}">
                                    <c:out value="${orderProduct.orderDetailCount}"/> 건
                                </c:when>
                                <c:otherwise>
                                    외 <c:out value="${orderProduct.orderDetailCount - 1}"/> 건
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <fmt:formatNumber value="${orderProduct.orderTotalPrice}" pattern="#,###"/> 원
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${orderProduct.orderStatus eq '1'}">
                                    <span style="color: red;">배송중</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="color: green;">배송완료</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <br/>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>

<script>
    const orderDetailErrorMsg = '<c:out value="${orderDetailErrorMsg}"/>';
    if (orderDetailErrorMsg) {
        alert(orderDetailErrorMsg);
    }
    const globalErrorMsg = '<c:out value="${globalErrorMsg}"/>';
    if (globalErrorMsg) {
        alert(globalErrorMsg);
    }
    const orderSuccessMsg = '<c:out value="${orderSuccessMsg}"/>';
    if (orderSuccessMsg) {
        alert(orderSuccessMsg);
    }
</script>
</html>
