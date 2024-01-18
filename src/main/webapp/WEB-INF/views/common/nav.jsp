<style>
    .navbar.sticky {
        position: fixed;
        top: 0;
        width: 100%;
        z-index: 1000;
    }
</style>
<script>
    window.onscroll = function() {
        const nav = document.querySelector('.navbar');
        if (window.pageYOffset > 50) {
            nav.classList.add('sticky');
        } else {
            nav.classList.remove('sticky');
        }
    };
    function scrollToSection(id) {
        const section = document.getElementById(id);
        section.scrollIntoView({ behavior: 'smooth' });
    }
    function throttle(callback, delay) {
        let previousCall = new Date().getTime();
        return function () {
            const time = new Date().getTime();

            if ((time - previousCall) >= delay) {
                previousCall = time;
                callback.apply(null, arguments);
            }
        };
    }
    window.onscroll = throttle(function() {
        const nav = document.querySelector('.navbar');
        if (window.pageYOffset > 50) {
            nav.classList.add('sticky');
        } else {
            nav.classList.remove('sticky');
        }
    }, 50);  // 200ms 간격으로 호출
</script>