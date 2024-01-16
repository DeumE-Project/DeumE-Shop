<script>
    window.onpageshow = function(event) {
//back 이벤트 일 경우 (글작성 후 back키 입력시 데이터가 남아있던 오류 수정)
        if (event.persisted) {
            location.reload(true);
        }
    }
</script>