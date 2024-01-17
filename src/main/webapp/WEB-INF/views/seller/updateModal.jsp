<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="updateModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalToggleLabel">배송 상태 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <select name="updateStatus" id="updateStatus" onchange="changeFn()" class="form-select" aria-label="Default select example">
                    <option name="updateStatus" selected>==============배송 상태 선택==============</option>
                    <option name="updateStatus" value="배송중">배송중</option>
                    <option name="updateStatus" value="배송완료">배송완료</option>
                  </select>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-danger" onclick="updateFn()">확인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>
