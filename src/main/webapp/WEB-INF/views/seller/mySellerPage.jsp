<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/common/topNavigation.jsp" %>

<head>
    <title>판매자 마이 페이지</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- Favicon-->
<%--    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />--%>
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

            <div class="row gx-5">
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">등록 된 상품</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${myCount} 개</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-success shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                        누적 매출</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${myRev} 원</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-info shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">이달 매출
                                    </div>
                                    <div class="row no-gutters align-items-center">
                                        <div class="col-auto">
                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${dateRev} 원</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">월 평균 매출</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${avgRev} 원</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


                <div class="row gx-5">
                    <div class="col-md-6">
                        <!-- 바 차트를 그리기 위한 canvas 요소 -->
                        <canvas id="bar-chart" width="300" height="200"></canvas>
                    </div>
                    <div class="col-md-6">
                        <!-- 도넛 차트를 그리기 위한 canvas 요소 -->
                        <canvas id="donutChart" style="max-width: 100%; height: auto;"></canvas>
                    </div>
                </div>

                <script language="JavaScript">
                    window.onload = function () {
                        // 라벨과 데이터 배열 초기화
                        let labelsBarChart = [];
                        let dataBarChart = [];

                        // 월간 판매 리스트에 대한 반복
                        <c:forEach items="${monthlySalesList}" var="monthly" varStatus="loop">
                        // 라벨을 라벨 배열에 추가
                        labelsBarChart.push("${monthly.salesDate}");
                        // 데이터를 데이터 배열에 추가 (따옴표로 감싸기)
                        dataBarChart.push(parseInt("${monthly.sales}"));
                        </c:forEach>

                        labelsBarChart.reverse();
                        dataBarChart.reverse();

                        // 만약 데이터가 6개를 초과하면, 6개까지만 자르기
                        if (dataBarChart.length > 6) {
                            labelsBarChart = labelsBarChart.slice(0, 6);
                            dataBarChart = dataBarChart.slice(0, 6);
                        }

                        // 생성된 라벨과 데이터를 사용하여 바 차트 생성
                        let barChartElement = document.getElementById("bar-chart");

                        if (dataBarChart.every(value => value === 0)) {
                            // 만약 데이터가 모두 0이면, 판매된 수익이 없다는 메시지 표시
                            let noRevenueMessage = document.createElement('p');
                            noRevenueMessage.className = 'text-center lead fw-bold';
                            noRevenueMessage.textContent = ' ';
                            barChartElement.insertAdjacentElement('afterend', noRevenueMessage);
                        } else {
                            new Chart(barChartElement, {
                                type: 'bar',
                                data: {
                                    labels: labelsBarChart,
                                    datasets: [
                                        {
                                            label: "월간 판매량",
                                            backgroundColor: "#3e95cd",
                                            data: dataBarChart
                                        }
                                    ]
                                },
                                options: {
                                    legend: {display: false},
                                    title: {
                                        display: true,
                                        text: '월간 판매량 차트'
                                    }
                                }
                            });

                    // 라벨과 데이터 배열 초기화
                    let labelsDonutChart = [];
                    let dataDonutChart = [];

                    <c:forEach items="${categorySales}" var="catSales" varStatus="loop">
                    labelsDonutChart.push("${catSales.category}");
                    dataDonutChart.push(parseInt("${catSales.categorySales}"));
                    </c:forEach>
                            console.log(labelsDonutChart)

                    // 데이터의 총합 계산
                    let totalDonutChart = dataDonutChart.reduce((acc, value) => acc + value, 0);

                    // 데이터 배열의 각 값을 비율로 변환
                    let ratioDataDonutChart = dataDonutChart.map(value => (value / totalDonutChart) * 100);

                    // 동적으로 색상 생성
                    let dynamicColorsDonutChart = [];
                    for (let i = 0; i < labelsDonutChart.length; i++) {
                    let color = getRandomColor();
                    dynamicColorsDonutChart.push(color);
                }

                    // 도넛 차트 데이터
                    var donutData = {
                    labels: labelsDonutChart,
                    datasets: [{
                    data: ratioDataDonutChart,
                    backgroundColor: dynamicColorsDonutChart, // 동적으로 생성한 색상 배열 적용
                }]
                };

                    // 도넛 차트 옵션
                    var donutOptions = {
                    cutout: '70%', // 중앙의 원형 구멍 크기 설정 (도넛 차트의 경우)
                };

                    // 도넛 차트 생성
                        var donutChart = new Chart(document.getElementById("donutChart"), {
                            type: 'doughnut',
                            data: donutData,
                            options: {
                                cutout: '70%',
                                responsive: true,  // 반응형 크기 조절 활성화
                                maintainAspectRatio: false  // false로 설정하여 canvas의 크기를 자유롭게 조절
                            }
                        });

                    // 랜덤 색상 생성 함수
                    function getRandomColor() {
                    var letters = '0123456789ABCDEF';
                    var color = '#';
                    for (var i = 0; i < 6; i++) {
                    color += letters[Math.floor(Math.random() * 16)];
                }
                    return color;
                }
                        };
                    }
            </script>

            <br>

            <c:if test="${empty bestSellCountList}">
                <div class="text-center my-5">
                    <h3 class="fw-bolder">판매된 상품이 없습니다. 리스트를 표시할 수 없습니다.</h3>
                </div>
            </c:if>
            <c:if test="${not empty bestSellCountList}">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <table class="table table-bordered">
                                <thead class="table-dark">
                                <tr>
                                    <th style="width: 10%;">번호</th>
                                    <th style="width: 20%;">상품명</th>
                                    <th style="width: 20%;">판매량</th>
                                </tr>
                                </thead>
                                <c:forEach items="${bestSellCountList}" var="sellProduct" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${sellProduct.productName}</td>
                                        <td>${sellProduct.sellCount}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>

                        <div class="col">
                            <table class="table table-bordered">
                                <thead class="table-dark">
                                <tr>
                                    <th style="width: 10%;">번호</th>
                                    <th style="width: 20%;">상품명</th>
                                    <th style="width: 20%;">판매수익</th>
                                </tr>
                                </thead>
                                <c:forEach items="${bestSellRevList}" var="revProduct" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${revProduct.productName}</td>
                                        <td>${revProduct.sellRev}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>

            <br>

            <!-- 작업 버튼 -->
            <div class="row gx-5 justify-content-end">
                <div class="col-lg-6">

                    <button class="btn btn-primary" onclick="location.href='/product/productSave'">상품 등록하기</button>
                    <button class="btn btn-secondary mx-2"
                            onclick="location.href='/seller/myProduct'">판매 상품 관리
                    </button>
                    <button class="btn btn-outline-secondary" onclick="location.href='#'">뒤로 가기</button>
                </div>
            </div>
        </div>
    </section>
</main>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>--%>
</body>
</html>