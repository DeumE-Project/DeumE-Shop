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
        }

        .navbar-nav {
            display: flex;
            justify-content: center; /* 가운데 정렬 추가 */
            align-items: center; /* 세로 가운데 정렬 추가 */
        }

        .navbar-nav .nav-item {
            margin-right: 10px;
            text-align: center; /* 텍스트 중앙 정렬 추가 */
        }

        .navbar-nav .nav-item a {
            display: inline-block;
            white-space: nowrap;
            padding-right: 1.5rem; /* 카테고리 이름을 더 오른쪽으로 이동시키는 부분 */
        }

        /* 한글 폰트 추가 */
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
        body, .navbar-nav .nav-item a {
            font-family: 'Nanum Gothic', sans-serif;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">로고</a>
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
                <a class="nav-link" href="#">사무용품</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">완구</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">교보재</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">pc용품</a>
            </li>
        </ul>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
