<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="banSellerModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalToggleLabel">판매자 정지 사유</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <select name="banReason" id="banReason" onchange="changeFn()" class="form-select" aria-label="Default select example">
                    <option name="banReason" value="" selected>==============정지 사유 선택==============</option>
                    <option name="banReason" value="신고수 누적">1. 신고수 누적</option>
                    <option name="banReason" value="불법 물품 판매">2. 불법 물품 판매</option>
                    <option name="banReason" value="천재 쇼핑몰과 어울리지 않는 물품 판매">3. 천재 쇼핑몰과 어울리지 않는 물품 판매</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-danger" onclick="banSellerFn()">확인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>
