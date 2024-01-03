<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>

    <link href="${pageContext.request.contextPath}/resources/common/styles.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<section class="vh-100" style="background-color: #fff;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-xl-10">
                <div class="card" style="border-radius: 1rem;">
                    <div class="row g-0">
                        <div class="col-md-6 col-lg-5 d-none d-md-block" style="position: relative">
                            <img src="${pageContext.request.contextPath}/resources/common/images/chunjae_character.jpg"
                                 alt="login form" class="img-fluid"
                                 style="border-radius: 1rem 0 0 1rem; position: absolute; top: 160px;"/>
                        </div>
                        <div class="col-md-6 col-lg-7 d-flex align-items-center">
                            <div class="card-body p-4 p-lg-5 text-black">
                                <form>
                                    <div class="d-flex align-items-center mb-3 pb-1">
                                        <span class="h1 fw-bold mb-0">천재샵 로그인</span>
                                    </div>
                                    <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">로그인 후 더 많은 혜택을
                                        누리세요!</h5>
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="username">아이디</label>
                                        <input type="text" id="username" class="form-control form-control-lg"/>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="password">비밀번호</label>
                                        <input type="password" id="password" class="form-control form-control-lg"/>
                                    </div>
                                    <div class="pt-1 mb-4">
                                        <button class="btn btn-primary btn-block" type="button" onclick="loginBtn()">
                                            로그인
                                        </button>
                                    </div>
                                    <%--<a class="small text-muted" href="#!">비밀번호를 잊으셨나요?</a>--%>
                                    <p class="mb-5 pb-lg-2" style="color: #393f81;">
                                        아직 계정이 없나요?
                                        <a href="${pageContext.request.contextPath}/register" style="color: #393f81;">
                                            여기에서 가입하세요!
                                        </a>
                                    </p>
                                    <a href="#!" class="small text-muted">천재교육</a>
                                    <a href="#!" class="small text-muted">천재샵</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    const loginFailMsg = '<c:out value="${loginFailMsg}" />'
    $(document).ready(function () {
        if (loginFailMsg) {
            alert(loginFailMsg);
        }
    });
    let loginBtn = function () {
        const loginFormTag = document.createElement('form');
        loginFormTag.method = 'post';
        loginFormTag.action = '${pageContext.request.contextPath}' + '/login';
        const usernameTag = document.createElement('input');
        const passwordTag = document.createElement('input');
        usernameTag.setAttribute('name', 'username');
        usernameTag.setAttribute('value', $('#username').val());
        passwordTag.setAttribute('name', 'password');
        passwordTag.setAttribute('value', $('#password').val());
        loginFormTag.appendChild(usernameTag);
        loginFormTag.appendChild(passwordTag);
        document.body.appendChild(loginFormTag);
        loginFormTag.submit();
    }

</script>
</body>
</html>
