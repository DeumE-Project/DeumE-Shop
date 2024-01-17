<script>
    // back입력시 데이터 삭제
    let reloading = false;

    window.addEventListener('pageshow', function(event) {

        if (reloading) {
            return;
        }

        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            reloading = true;
            location.reload(true);
        }
    });
</script>