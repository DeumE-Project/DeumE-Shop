<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="./topNavigation.jsp" %>
<jsp:include page="nav.jsp"/>
<head>
    <title>로그인</title>
    <style>
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
</head>

<body>
<div class="contentset">
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
                                        <span class="h1 fw-bold mb-0">듬이Shop 로그인</span>
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
                                    <div>
                                        <span>회원구분</span>&nbsp;&nbsp;
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type"
                                                   id="inlineRadio1" value="customer">
                                            <label class="form-check-label" for="inlineRadio1">고객</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="type"
                                                   id="inlineRadio2" value="seller">
                                            <label class="form-check-label" for="inlineRadio2">판매자</label>
                                        </div>
                                    </div>
                                    <br/>
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
                                    <a href="#!" class="small text-muted">듬이Shop</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</div>
<script>
    const loginFailMsg = '<c:out value="${loginFailMsg}" />'
    const sellerLoginFailMsg = '<c:out value="${sellerLoginFailMsg}"/>';

    $(document).ready(function () {
        if (loginFailMsg) {
            alert(loginFailMsg);
        }
        if (sellerLoginFailMsg) {
            alert(sellerLoginFailMsg);
        }
    });

    let loginBtn = function () {
        if ($('input[name="type"]:checked').length < 1) {
            alert("회원구분을 선택해주세요");
            return;
        }

        const loginFormTag = document.createElement('form');
        loginFormTag.method = 'post';
        loginFormTag.action = '${pageContext.request.contextPath}' + '/login';
        const usernameTag = document.createElement('input');
        const passwordTag = document.createElement('input');
        usernameTag.setAttribute('name', 'username');
        usernameTag.setAttribute('value', $('#username').val() + "=" + $('input[name="type"]:checked')[0].value);
        passwordTag.setAttribute('name', 'password');
        passwordTag.setAttribute('value', $('#password').val());
        loginFormTag.appendChild(usernameTag);
        loginFormTag.appendChild(passwordTag);
        document.body.appendChild(loginFormTag);
        loginFormTag.submit();
    }
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
