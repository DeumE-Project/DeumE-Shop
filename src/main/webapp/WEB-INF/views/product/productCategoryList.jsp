<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
            background-color: #ffffff; /* 바탕을 흰색으로 변경 */
            color: #000000; /* 글씨를 검은색으로 변경 */
        }

        .navbar {
            background-color: #f8f9fa !important; /* 흰색보다 조금 어둡게 변경 */
            color: #000000 !important; /* 네비게이션 바 글씨를 검은색으로 변경 */
        }

        .navbar-toggler-icon {
            background-color: #000000; /* 토글 아이콘 색상을 검은색으로 변경 */
        }

        .navbar-nav .nav-link {
            color: #000000 !important; /* 네비게이션 바 링크 글씨를 검은색으로 변경 */
        }

        /* 네비게이션 바 토글 버튼 색상을 검은색으로 변경 */
        .navbar-toggler {
            border-color: #000000;
        }

        .navbar-nav {
            display: flex;
            justify-content: center;
            align-items: center;


        }

        .navbar-nav .nav-item {
            margin-right: 10px;
            text-align: center;

        }


        .navbar-nav .nav-item a {
            display: inline-block;
            white-space: nowrap;
            margin-right: 20px;

        }
        .navbar-nav .nav-item:first-child a {
            margin-left: 170px;

        }
        .navbar-nav .nav-link {
            font-weight: bold;
            font-size: 16px;
        }

        /* 한글 폰트 추가 */
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
        body, .navbar-nav .nav-item a {
            font-family: 'Nanum Gothic', sans-serif;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark" >
    <a class="navbar-brand" href="#"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/product/productList?categoryIdx=1">필기구</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/productList?categoryIdx=2">노트류</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/productList?categoryIdx=3">사무용품</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/productList?categoryIdx=4">완구</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/productList?categoryIdx=5">교보재</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/product/productList?categoryIdx=6">pc용품</a>
            </li>
        </ul>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
