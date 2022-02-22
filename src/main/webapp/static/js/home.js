window.onload = function () {
    //button scroll to top
    var btnScrollToTop = document.getElementById("btn-scroll");
    window.onscroll = function() {
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        btnScrollToTop.style.display = "block";
      } else {
        btnScrollToTop.style.display = "none";
      }
    };

    btnScrollToTop.addEventListener('click', () => {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    });

    //reviews slider
    var swiper = new Swiper(".review-slider", {
        loop:true,
        spaceBetween: 20,
        autoplay: {
            delay: 6000,
            disableOnInteraction: false,
        },
        centeredSlides: true,
        breakpoints: {
          0: {
            slidesPerView: 1,
          },
          768: {
            slidesPerView: 2,
          },
          1020: {
            slidesPerView: 3,
          },
        },
    });
}