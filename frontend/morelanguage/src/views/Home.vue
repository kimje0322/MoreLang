<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <div style="width: 100%; height: 800px;">
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
          muted
          autoplay
          loop
          playsinline
          src="../assets/video/투잡.mp4"
        ></video>
      </div>


      <!-- 추천 영상 -->
      <div style="padding: 100px 0; display: block;">
        <div style="margin: 0 100px;">
          <h3>
            <strong style="font-size: 40px; font-weight: 900; font-family: SilkSerif;">추천</strong>
          </h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Home"
};
</script>
