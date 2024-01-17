<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="${pageContext.request.contextPath}/resources/common/styles.css" rel="stylesheet">
    <!-- jQuery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=1">필기구</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=2">노트/메모</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=3">사무용품</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=4">완구</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=5">교보재</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/product/productList?categoryIdx=6">PC용품</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Bootstrap JS -->


</body>
</html>
