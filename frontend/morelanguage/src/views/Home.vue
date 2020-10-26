<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <div style="width: 100%;">
      <!-- 상단 로고 & 메뉴 -->
      <header
        style="padding-top: 30px; top: 0; left: 0; z-index: 40; width: 100%; position: fixed;"
      >
        <div class="navigation">
          <div
            style="margin: 0 100px; display: flex; align-items: center; justify-content: space-between;"
          >
            <div
              style="top: 10px; font-weight: 600; font-size: 18px; pointer-event: none; font-family: 'Calibre',sans-serif; color: darkgray;"
            >
              #MORELANG
            </div>
            <!-- 구글 로그인 -->
            <div></div>

            <!-- 검색 -->
            <div>
              <!-- <router-link to="/search" style="text-decoration: none;"> -->
              <v-icon large @click="search = true">mdi-magnify</v-icon>
              <!-- </router-link> -->
            </div>
            <div v-if="search" scroll="no">
              <div style="visibility: visible; opacity: 1; transition-delay: 0s; width: 100%; height: 100vh; position: fixed; top: 0; left: 0; background-color: rgba(231,229,221,.95); transition: visibility .3s ease-in-out,opacity .3s ease-in-out; z-index: 15;">
                <!-- <div style="justify-content: center; align-items: center;"> -->
                  <div style="margin: 10% 20%;">
                  <!-- <form action="/search" method="get"></form> -->
                  <input type="search" placeholder="SEARCH" autofocus style="border-bottom: 1px solid #737475; font-size: 30px; letter-spacing: 0.075em; display: inline-block;" />
                  <span style="display: inline-block" @click="search = false">X</span>
                </div>
              </div>
            </div>
          </div>
          <!-- 메뉴바 -->
          <!-- <div style="width: 50px; ">
            <v-icon>hamburger-menu</v-icon>
          </div> -->
        </div>
      </header>
      <!-- 소개 영상 -->
      <div style="width: 100%; height: 100vh; display: block;">
        <video
          style="width: 100%;"
          muted
          autoplay
          loop
          playsinline
          src="../assets/video/투잡.mp4"
        ></video>
      </div>

      <!-- 추천 채널 -->
      <div style="padding: 50px 0; display: block;">
        <div
          style="margin: 0 100px;"
          data-aos="slide-right"
          aos-offset="300"
          data-aos-duration="1500"
        >
          <h3>
            <span
              style="display: block; font-size: 30px; font-weight: 300; font-family: 'SilkSerif';"
              >MORE</span
            >
            <strong
              style="font-size: 40px; font-weight: 900; font-family:  'Calibre',sans-serif; line-height: 0.5;"
              >CHANNEL</strong
            >
          </h3>
        </div>

        <!-- 채널 목록 -->
        <div
          data-scroll
          data-scroll-offset="100"
          data-scroll-speed="2"
          data-scroll-direction="horizontal"
          style="position: relative; height: 240px;"
        >
          <!-- 채널 좌우바 -->
          <div
            style="transform: translateY(250%); position: absolute; padding: 0 50px; z-index: 5; justify-content: space-between; align-items: center; display: flex; width: 100%;"
          >
            <div
              style="margin: 100px; color: darkgray;"
              class="swiper-button-prev"
              slot="button-prev"
            ></div>
            <div
              style="margin: 100px; color: darkgray; "
              class="swiper-button-next"
              slot="button-next"
            ></div>
          </div>
          <!-- 채널 동그라미 -->
          <!-- style="transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 18.15, 0, 0, 1);" -->
          <!-- data-scroll
            data-scroll-offset="100"
            data-scroll-speed="2"
            data-scroll-direction="horizontal" -->
          <!-- <div class="swiper-container"> -->
          <!-- <div style="margin: 0 auto; transform: translate3d(-2160px, 0px, 0px); transition: all 0ms ease 0s;"> -->
          <!-- style="margin: 20px 150px;" -->
          <swiper
            style="width: 80%; margin-top: 40px;"
            class="swiper"
            :options="swiperOption"
            data-aos="slide-left"
            aos-offset="500"
            data-aos-duration="2500"
          >
            <!-- <a href="/" style="width: 240px;"> -->
            <!-- style="animation: float 2s ease-in-out infinite; " -->
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>
            <swiper-slide>
              <img src="../assets/img/channel0.jpg" alt="" />
            </swiper-slide>

            <!-- <div class="swiper-button-prev" slot="button-prev"></div>
            <div class="swiper-button-next" slot="button-next"></div> -->

            <!-- </a> -->
            <div class="swiper-pagination" slot="pagination"></div>
          </swiper>
          <!-- </div> -->
        </div>
      </div>

      <!-- <div style="margin: 100px;">
        <swiper class="swiper" :options="swiperOption">
          <swiper-slide>Slide 1</swiper-slide>
          <swiper-slide>Slide 2</swiper-slide>
          <swiper-slide>Slide 3</swiper-slide>
          <swiper-slide>Slide 4</swiper-slide>
          <swiper-slide>Slide 5</swiper-slide>
          <swiper-slide>Slide 6</swiper-slide>
          <swiper-slide>Slide 7</swiper-slide>
          <swiper-slide>Slide 8</swiper-slide>
          <swiper-slide>Slide 9</swiper-slide>
          <swiper-slide>Slide 10</swiper-slide>
          <div class="swiper-button-prev" slot="button-prev"></div>
          <div class="swiper-button-next" slot="button-next"></div>
        </swiper>
      </div> -->
      <!-- 인기 영상 -->
      <div style="padding: 50px 0; display: block;">
        <div
          style="margin: 0 100px;"
          data-aos="slide-right"
          aos-offset="300"
          data-aos-duration="1000"
        >
          <h3>
            <span
              style="display: block; font-size: 30px; font-weight: 300; font-family: 'SilkSerif';"
              >Hot</span
            >
            <strong
              style="font-size: 40px; font-weight: 900; font-family:  'Calibre',sans-serif; line-height: 0.5;"
              >VIDEO</strong
            >
          </h3>
        </div>
        <div class="example-3d">
          <swiper class="swiper" :options="swiperOption">
            <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
            <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
            <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
            <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
            <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
            <div class="swiper-pagination" slot="pagination"></div>
          </swiper>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

// import { Swiper, Navigation, Pagination, Scrollbar } from "swiper";

// import 'swiper/swiper-bundle.css'

// Swiper.use([Navigation, Pagination, Scrollbar]);

import { Swiper, SwiperSlide } from "vue-awesome-swiper";
// import "swiper/css/swiper.css";
import "swiper/swiper-bundle.css";

// 상단 네브바 고정
var nav = document.getElementsByClassName("navigation");
window.onscroll = function sticky() {
  if (window.pageYOffset > nav[0].offsetTop) {
    nav[0].classList.add("nav");
  } else {
    nav[0].classList.remove("nav");
  }
};

export default {
  name: "Home",
  components: {
    Swiper,
    SwiperSlide
  },
  data() {
    return {
      search: false,
      swiperVideo: {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true
        },
        pagination: {
          el: ".swiper-pagination-video"
        }
      },
      swiperOption: {
        // navigation: {
        //   nextEl: ".swiper-button-next",
        //   prevEl: ".swiper-button-prev"
        // }
        slidesPerView: 4,
        spaceBetween: 10,
        // centeredSlides: true,
        pagination: {
          el: ".swiper-pagination",
          clickable: true
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        }
      }
    };
  }
};
</script>

<style lang="scss" scoped>
.swiper-container {
  width: 100%;
  height: 100%;
}
.swiper-slide {
  width: 80%;
  text-align: center;
  align-items: center;
  justify-content: left;
  /* display: flex; */
}
/* .swiper-slide {
  width: 60%;
}
.swiper-slide:nth-child(2n) {
  width: 40%;
}
.swiper-slide:nth-child(3n) {
  width: 20%;
} */

.example-3d {
  width: 100%;
  height: 400px;
  padding-top: 50px;
  padding-bottom: 50px;
}

.swiper-video {
  height: 100%;
  width: 100%;
}

.swiper-slide-video {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 300px;
  height: 300px;
  text-align: center;
  font-weight: bold;
}

.nav {
  position: fixed;
  background: beige;
  padding: 30px 0;
}

.navigation {
  width: 100%;
  top: 0;
}
</style>