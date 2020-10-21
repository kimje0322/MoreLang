<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <div style="width: 100%;">
      <!-- 상단 로고 & 메뉴 -->
      <header
        style="padding-top: 30px; top: 0; left: 0; z-index: 40; width: 100%; position: fixed;"
      >
        <div
          style="margin: 0 100px; display: flex; align-items: center; justify-content: space-between;"
        >
          <div
            style="top: 10px; font-size: 18px; pointer-event: none; font-family: SilkSerif; color: lightgray;"
          >
            #morelang
          </div>

          <!-- 구글 로그인 -->
          <div></div>

          <!-- 메뉴바 -->
          <!-- <div style="width: 50px; ">
            <v-icon>hamburger-menu</v-icon>
          </div> -->
        </div>
      </header>
      <!-- 소개 영상 -->
      <div style="width: 100%; height: 100vh;">
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
        <div style="margin: 0 100px;">
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
            <div>
              <v-icon large>mdi-chevron-left</v-icon>
            </div>
            <div>
              <v-icon large>mdi-chevron-right</v-icon>
            </div>
          </div>
          <!-- 채널 동그라미 -->
          <div
            data-scroll
            data-scroll-offset="100"
            data-scroll-speed="2"
            data-scroll-direction="horizontal"
            style="transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 18.15, 0, 0, 1);"
          >
            <div style="margin: 0 auto; transform: translate3d(-2160px, 0px, 0px); transition: all 0ms ease 0s;">
              <a href="#" style="width: 240px;">
                <div style="animation: float 2s ease-in-out infinite; ">
                  <img src="../assets/img/channel0.jpg" alt="">
                  dfad
                </div>
                adfasdf
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- 인기 영상 -->
      <div></div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Home"
};
</script>
