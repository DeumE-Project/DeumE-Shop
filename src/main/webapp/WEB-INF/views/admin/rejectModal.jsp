<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="rejectModal" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalToggleLabel">판매자 가입 거절 사유</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <select name="rejectReason" id="rejectReason" onchange="changeFn()" class="form-select" aria-label="Default select example">
                    <option name="rejectReason" value="" selected>==============거절 이유 선택==============</option>
                    <option name="rejectReason" value="통신판매업 미신고">1. 통신판매업 미신고</option>
                    <option name="rejectReason" value="대표자명 불일치">2. 대표자명 불일치</option>
                    <option name="rejectReason" value="사업장소재지 불일치">3. 사업장소재지 불일치</option>
                    <option name="rejectReason" value="'듬이샵'과 어울리지 않는 업종">4. '듬이샵'과 어울리지 않는 업종</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-danger" onclick="rejectFn()">확인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>