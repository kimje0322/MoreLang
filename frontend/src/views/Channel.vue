<template>
  <div class="channelDetail">
    <Navbar />
    <div>
      <!-- 'url(' + picture + ')' -->
      <div
        class="backImg"
        v-bind:style="{
          'background-image':
            'url(https://boxmaster.co.kr/upload/magazine/5c99e01e4c37e.jpg)'
        }"
        style="
        height: 300px;
        display: flex;
        justify-content: center;
        align-items: center;
      "
      >
        <h2 class="intro">채널 이름</h2>
        <div class="img-cover"></div>
      </div>
      <div class="channelInfoBox">
        <div class="channelInfo">
          <div style="float: left; width: 50%">
            <h3 class="my-2">채널이름 Dear. Jerry</h3>
            <h3 class="mt-2">대표영상 What's In My Bag?</h3>

            <div style="margin-bottom: 4%">
              <strong>
                <p class="listTitle" style="margin-bottom: 1%">영상 카테고리</p>
              </strong>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from "@/components/Navbar";
export default {
  components: {
    Navbar,
  }
};

</script>

<style scoped>
.backImg {
  position: relative;
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat;
  height: 100vh;
  /* opacity: 0.3; */
}
.img-cover {
  position: absolute;
  height: 100%;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.6);
  z-index: 1;
}
.intro {
  position: absolute;
  top: 35%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  z-index: 2;
  text-align: center;
}

.channelInfoBox {
  /* margin: 0 150px; */
  z-index: 3;
  position: absolute;
  width: 100%;
  top: 250px;
  padding: 0 17%;
  border-radius: 4px;
}
.channelInfo {
  overflow: hidden;
  width: 100%;
  background: white;
   /* white; */
  padding: 2%;
}
</style>
