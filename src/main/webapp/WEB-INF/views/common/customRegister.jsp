<%@ page contentType="text/html;charset=UTF-8" language="java"
         trimDirectiveWhitespaces="true" %>

<%@ include file="./topNavigation.jsp" %>
<jsp:include page="nav.jsp"/>
<head>
    <title>회원가입</title>
    <style>
        .contentset{
            min-height: calc(100vh - 30px);
            padding-top: 100px;
        }
    </style>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>

    <style>
        body {
            min-height: 100vh;
        }

        .input-form {
            max-width: 680px;
            margin-top: 80px;
            padding: 32px;
            background: #fff;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            border-radius: 10px;
            -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
        }

        #id-check-span {
            font-weight: bolder;
        }
    </style>
</head>

<div class="container contentset">
    <div class="input-form-backgroud row">
        <div class="input-form col-md-12 mx-auto">
            <h2 class="mb-3">듬이Shop 회원가입</h2>
            <hr class="mb-4">
            <form:form modelAttribute="registerFormDTO" action="${pageContext.request.contextPath}/register"
                       class="validation-form" novalidate="true" onsubmit="return submitCheckFunc()">
                <h4 style="margin-bottom: 20px"><form:errors path="globalError" cssClass="text-danger"/></h4>
                <div class="mb-3">
                    <label for="id">아이디 (영문 소문자 및 숫자: 5 ~ 15자)<br/>중복검사는 자동으로 돼요</label>
                    <form:input path="id" type="text" class="form-control" placeholder="아이디를 입력해주세요" required="true"/>
                    <div class="invalid-feedback">
                        아이디를 입력해주세요 (영문 소문자 및 숫자를 사용해서 최소 5자 최대 15자까지 가능합니다)
                    </div>
                    <div>
                        <span id="id-check-span"></span>
                    </div>
                    <div>
                        <form:errors path="id" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email">이름</label>
                    <form:input path="name" type="text" class="form-control" placeholder="이름을 입력해주세요" required="true"/>
                    <div class="invalid-feedback">
                        이름을 입력해주세요
                    </div>
                    <div>
                        <form:errors path="name" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email">이메일</label>
                    <form:input path="email" type="email" class="form-control" placeholder="example@example.com"
                                required="true"/>
                    <div class="invalid-feedback">
                        이메일을 입력해주세요
                    </div>
                    <div>
                        <form:errors path="email" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="password1">
                        비밀번호<br/>
<%--                        5자리 이상 16자리 이하, 영어 대소문자 각각 한 개 이상, 숫자 한 개 이상, 특수기호 !, @, #, $ 중 한 개 이상--%>
                    </label>
                    <form:input path="password1" type="password" class="form-control" placeholder="비밀번호를 입력해주세요"
                                required="true"/>
                    <div class="invalid-feedback">
                        비밀번호를 입력해주세요
                    </div>
                    <div>
                        <form:errors path="password1" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="password2">비밀번호 확인</label><br/>
                    <form:input path="password2" type="password" class="form-control" placeholder="비밀번호를 재입력해주세요"
                                required="true"/>
                    <div class="invalid-feedback">
                        비밀번호를 다시 한 번 입력해주세요
                    </div>
                    <div>
                        <form:errors path="passwordErrorMsg" cssClass="text-danger"/><br/>
                        <form:errors path="password2" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="phone">전화번호</label>
                    <form:input path="phone" type="text" class="form-control" placeholder="'-' 기호 없이 숫자만 입력해주세요"
                                required="true"/>
                    <div class="invalid-feedback">
                        전화번호를 입력해주세요 (- 기호를 제외한 숫자만 입력해주세요)
                    </div>
                    <div>
                        <form:errors path="phone" cssClass="text-danger"/>
                    </div>
                </div>
                <div>
                    <div class="mb-3">
                        <button type="button" class="btn btn-link"
                                onclick="sample6_execDaumPostcode()">주소검색
                        </button>
                    </div>
                    <div class="mb-3">
                        <label for="zipcode">우편번호</label>
                        <form:input path="zipcode" type="text" class="form-control" placeholder="주소 검색을 해주세요"
                                    readonly="true"/>
                        <div class="invalid-feedback">
                            주소 검색을 통해 우편번호가 자동 입력되도록 해주세요
                        </div>
                        <div>
                            <form:errors path="zipcode" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="address1">주소</label>
                        <form:input path="address1" type="text" class="form-control" placeholder="주소 검색을 해주세요"
                                    readonly="true"/>
                        <div class="invalid-feedback">
                            주소 검색을 통해 주소가 자동 입력되도록 해주세요
                        </div>
                        <div>
                            <form:errors path="address1" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="address2">상세주소</label>
                        <form:input path="address2" type="text" class="form-control" placeholder="상세 주소를 입력해주세요 (동/호수)"
                                    required="true"/>
                        <div class="invalid-feedback">
                            상세 주소를 입력해주세요
                        </div>
                        <div>
                            <form:errors path="address2" cssClass="text-danger"/>
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="type">회원구분</label>
                    <form:select path="type" class="form-select" required="true">
                        <form:option value="">회원구분 선택</form:option>
                        <form:option value="customer">고객</form:option>
                        <form:option value="seller">판매자</form:option>
                    </form:select>
                    <div class="invalid-feedback">
                        회원구분 중 하나를 선택해주세요
                    </div>
                    <div>
                        <form:errors path="type" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="mb-3" id="div-seller-tax-id">
                    <label for="sellerTaxId">사업자등록번호 <br/>(xxx-xx-xxxxx 형식으로 입력해주세요)</label>
                    <form:input path="sellerTaxId" type="text" class="form-control"
                                placeholder="사업자등록번호를 입력해주세요"/>
                    <form:errors path="sellerTaxId" cssClass="text-danger"/>
                </div>
                <hr class="mb-4">
                <div>
                    <input class="btn btn-primary" type="submit" value="회원가입하기"/>
                </div>
            </form:form>
        </div>
    </div>
    <footer class="my-3 text-center text-small">
        <p class="mb-1">&copy; 2023 Chunjaeshop, all rights reserved</p>
    </footer>
</div>

<script>
    const userType = '<c:out value="${userType}"/>';
    const divSellerTaxIdTag = document.getElementById("div-seller-tax-id");
    if (userType != 'seller') {
        divSellerTaxIdTag.style.display = 'none';
    }

    $('#type').on('change', function () {
        let selectedType = $('#type option:selected').val();
        if (selectedType == 'seller') {
            divSellerTaxIdTag.style.display = 'block';
        } else {
            divSellerTaxIdTag.style.display = 'none';
        }
    })
</script>

<script>
    let idCheck = false;
    $(document).ready(function () {
        console.log('ready');
        $('#id').on('input', function (event) {
            let userInputId = event.target.value;
            console.log(userInputId.length);
            if (/^[a-z0-9]{5,15}$/.test(userInputId) === true) {
                console.log('id 중복 검사...');
                idDuplicateCheckAjax();
                console.log('idCheck = ' + idCheck);
                if (idCheck) {
                    $('#id-check-span').html('사용할 수 있는 아이디입니다');
                    $('#id-check-span').removeClass('text-warning');
                    $('#id-check-span').addClass('text-success');
                } else {
                    console.log('이미 사용 중인 아이디입니다');
                    $('#id-check-span').html('이미 사용 중인 아이디입니다');
                    $('#id-check-span').removeClass('text-success');
                    $('#id-check-span').addClass('text-warning');
                }
            } else {
                idCheck = false;
                $('#id-check-span').html('아이디는 5자부터 15자까지 영어 소문자 및 숫자만 사용할 수 있습니다');
                $('#id-check-span').removeClass('text-success');
                $('#id-check-span').addClass('text-warning');
            }
        });
    });
    let idDuplicateCheckAjax = function () {
        $.ajax({
            url: '${pageContext.request.contextPath}' + '/register/id-duplicate-check',
            method: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'text',
            data: {
                id: $('#id').val(),
            },
            async: false,
            success: function (result) {
                console.log('success!');
                console.log('result = ' + result);
                if (result == 'canuse') {
                    idCheck = true;
                } else {
                    idCheck = false;
                }
            },
            error: function (error) {
                console.log('error!');
                console.log(error);
                idCheck = false;
            }
        });
    }

    const submitCheckFunc = function () {
        if (idCheck === true) {
            return true;
        } else {
            alert('아이디 검사에 실패해서 회원가입할 수 없습니다\n아이디 중복검사를 해주세요')
            return false;
        }
    }
</script>

<script>
    /*window.addEventListener("load", () => {
        const form = document.getElementById("registerFormDTO");
        form.addEventListener("submit", function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add("was-validated");
        }, false);
    }, false);*/
</script>

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
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById("address1").value = addr;
                document.getElementById("address2").focus();
            }
        }).open();
    }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
