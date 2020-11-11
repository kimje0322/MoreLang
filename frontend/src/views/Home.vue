<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <div style="width: 100%;">
      <Navbar />

      <!-- 메뉴바 -->
      <!-- <div style="width: 50px; ">
            <v-icon>hamburger-menu</v-icon>
          </div> -->
    </div>

    <!-- 소개 영상 -->
    <div style="width: 100%; height: 48%; display: block; margin-top: 50px;">
      <video
        style="width: 100%;"
        muted
        autoplay
        loop
        playsinline
        src="../assets/video/투잡.mp4"
      ></video>
    </div>

    <!-- 언어 필터링 -->
    <div>
      <div style="width: 30%; position: absolute; right: 20px;">
        <v-select
          :items="language"
          label="language"
          v-model="selectlang"
        ></v-select>
      </div>
    </div>
    <!-- 추천 채널 -->
    <div style="margin-top: 80px; padding: 0; display: block;">
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
            style="font-size: 40px; font-weight: 900; font-family: 'Calibre', sans-serif; line-height: 0.5;"
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
        <!-- <div
            style="transform: translateY(250%); position: absolute; padding: 0 50px; z-index: 5; justify-content: space-between; align-items: center; display: flex; width: 100%;"
          > -->
        <!-- @click="prev()" -->
        <!-- <div
              style="margin: 100px; color: darkgray;"
              class="swiper-button-prev"
              slot="button-prev"
            ></div> -->
        <!-- @click="next()" -->
        <!-- <div
              style="margin: 100px; color: darkgray; "
              class="swiper-button-next"
              slot="button-next"
            ></div> -->
        <!-- </div> -->
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
          style="width: 90%; margin-top: 40px;"
          class="swiper"
          :options="swiperOption"
          data-aos="slide-left"
          aos-offset="500"
          data-aos-duration="2500"
          ref="mySwiperRef"
        >
          <!-- <a href="/" style="width: 240px;"> -->
          <!-- style="animation: float 2s ease-in-out infinite; " -->
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->
            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJw5uA4EO-kzzPoI2xiGReKnp2PsDuWAg4SX8_Iv=s176-c-k-c0x00ffffff-no-rj"
                alt=""
              />
            </v-avatar>
          </swiper-slide>
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->

            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJySqp_uHFpOtyFEfp2OH-hELnCOhIYgUlPRp8GK=s176-c-k-c0x00ffffff-no-rj-mo"
                alt=""
              />
            </v-avatar>
          </swiper-slide>
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->
            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJw5uA4EO-kzzPoI2xiGReKnp2PsDuWAg4SX8_Iv=s176-c-k-c0x00ffffff-no-rj"
                alt=""
              />
            </v-avatar>
          </swiper-slide>
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->

            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJySqp_uHFpOtyFEfp2OH-hELnCOhIYgUlPRp8GK=s176-c-k-c0x00ffffff-no-rj-mo"
                alt=""
              />
            </v-avatar>
          </swiper-slide>
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->
            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJw5uA4EO-kzzPoI2xiGReKnp2PsDuWAg4SX8_Iv=s176-c-k-c0x00ffffff-no-rj"
                alt=""
              />
            </v-avatar>
          </swiper-slide>
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->

            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJySqp_uHFpOtyFEfp2OH-hELnCOhIYgUlPRp8GK=s176-c-k-c0x00ffffff-no-rj-mo"
                alt=""
              />
            </v-avatar>
          </swiper-slide>
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->
            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJw5uA4EO-kzzPoI2xiGReKnp2PsDuWAg4SX8_Iv=s176-c-k-c0x00ffffff-no-rj"
                alt=""
              />
            </v-avatar>
          </swiper-slide>
          <swiper-slide>
            <!-- <img src="../assets/img/channel0.jpg" alt="" /> -->

            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img
                src="https://yt3.ggpht.com/a/AATXAJySqp_uHFpOtyFEfp2OH-hELnCOhIYgUlPRp8GK=s176-c-k-c0x00ffffff-no-rj-mo"
                alt=""
              />
            </v-avatar>
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
    <div style="padding: 50px 0; display: block; background: black;">
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
        <swiper
          style="width: 90%;"
          class="swiper"
          :options="swiperOption"
          data-aos="slide-left"
          aos-offset="500"
          data-aos-duration="2500"
        >
          <swiper-slide class="swiper-slide">
            <!-- Slide 1 -->
            <v-img
              :elevation="hover ? 16 : 2"
              :class="{ 'on-hover': hover }"
              src="https://i.ytimg.com/vi/HPQ5mqovXHo/mqdefault.jpg"
              alt=""
              width="300"
            />
          </swiper-slide>
          <swiper-slide class="swiper-slide">
            <!-- Slide 1 -->
            <v-img
              :elevation="hover ? 16 : 2"
              :class="{ 'on-hover': hover }"
              src="https://i.ytimg.com/vi/HPQ5mqovXHo/mqdefault.jpg"
              alt=""
              width="300"
            />
          </swiper-slide>
          <swiper-slide class="swiper-slide">
            <!-- Slide 1 -->
            <v-img
              :elevation="hover ? 16 : 2"
              :class="{ 'on-hover': hover }"
              src="https://i.ytimg.com/vi/HPQ5mqovXHo/mqdefault.jpg"
              alt=""
              width="300"
            />
          </swiper-slide>
          <swiper-slide class="swiper-slide">
            <!-- Slide 1 -->
            <v-img
              :elevation="hover ? 16 : 2"
              :class="{ 'on-hover': hover }"
              src="https://i.ytimg.com/vi/HPQ5mqovXHo/mqdefault.jpg"
              alt=""
              width="300"
            />
          </swiper-slide>
          <swiper-slide class="swiper-slide">
            <!-- Slide 1 -->
            <v-img
              :elevation="hover ? 16 : 2"
              :class="{ 'on-hover': hover }"
              src="https://i.ytimg.com/vi/HPQ5mqovXHo/mqdefault.jpg"
              alt=""
              width="300"
            />
          </swiper-slide>
          <!-- <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
          <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
          <swiper-slide class="swiper-slide">Slide 1</swiper-slide>
          <swiper-slide class="swiper-slide">Slide 1</swiper-slide> -->
          <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
      </div>
    </div>
  </div>
</template>
<script>
// @ is 0an alias to /src

// import { Swiper, Navigation, Pagination, Scrollbar } from "swiper";

// import 'swiper/swiper-bundle.css'

// Swiper.use([Navigation, Pagination, Scrollbar]);

import Navbar from "@/components/Navbar";
import axios from "axios";
import { Swiper, SwiperSlide, directive } from "vue-awesome-swiper";
// import "swiper/css/swiper.css";
import "swiper/swiper-bundle.css";
// import { directive } from 'vue/types/umd';

// 상단 네브바 고정
// var nav = document.getElementsByClassName("navigation");
// window.onscroll = function sticky() {
//   if (window.pageYOffset > nav[0].offsetTop) {
//     nav[0].classList.add("nav");
//   } else {
//     nav[0].classList.remove("nav");
//   }
// };

export default {
  name: "Home",
  components: {
    Swiper,
    SwiperSlide,
    Navbar
  },
  directives: {
    mySwiperRef: directive
  },
  data() {
    return {
      userid: "",
      selectlang: "all",
      search_word: "",
      recommend_channel:{},
      // logout: false,
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
      },
      language: [
        "영어",
        "일본어",
        "중국어",
        "프랑스어",
        "스페인어",
        "독일어",
        "이탈리아어",
        "포르투갈어",
        "러시아"
      ]
    };
  },
  mounted() {
    this.recommend();
  },
  computed:{
    changeLang: function() {
				return this.recommend(this.selectlang);
			}
  },
  methods: {
    prev() {
      this.$refs.mySwiperRef.$swiper.slidePrev();
    },
    next() {
      this.$refs.mySwiperRef.$swiper.slideNext();
    },
    recommend(val){
      var server_url = "https://morelang.gq/api"
      var request_url = `${server_url}/newuser/recommend-list?&direction=ASC&page=0&size=10`
      if(val != null){
        request_url = `${server_url}/newuser/recommend-list?country=${val}&direction=ASC&page=0&size=10`
      }
      axios
      .get(request_url)
      .then(res => {
        console.log("여기요!");
        console.log(res.data.content);
        this.recommend_channel = res.data.content;
      });
    }
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
  padding-top: 20px;
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

// .nav {
//   position: fixed;
//   background: beige;
//   padding: 30px 0;
// }

// .navigation {
//   width: 100%;
//   top: 0;
// }
</style>