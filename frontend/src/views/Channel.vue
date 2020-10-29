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
        <h2 class="intro">{{ channelInfo.title }}</h2>
        <div class="img-cover"></div>
      </div>
      <div class="channelInfoBox">
        <div class="channelInfo">
          <div style="width: 50%">
            <v-avatar
              style="width: 150px; height: 150px; display: inline-block;"
            >
              <img :src="channelInfo.imgUrl" alt="" />
            </v-avatar>
            <h3
              class="mx-6"
              style="display: inline-block; font-size: 30px; font-family: 'SilkSerif';"
            >
              {{ channelInfo.title }}
            </h3>
            <!-- <h3 class="mt-2">대표영상 What's In My Bag?</h3> -->

            <!-- <div style="margin-bottom: 4%">
              <strong>
                <p class="listTitle" style="margin-bottom: 1%">영상 카테고리</p>
              </strong>
            </div> -->
          </div>
          <div
            style="width: 100%; padding: 20px 0; display: flex; flex-flow: row wrap; justify-content: space-between;"
          >
            <div v-for="(video, i) in videolst" :key="i">
              <div
                class="col"
                style="width: clac(25% - 60px); margin: auto; display: inline-block;"
              >
              
                <div style="margin-bottom: 15px;">
                  <img
                    :src="video.imgUrl"
                    @error="$event.target.src = video.imgUrl.substring(0, 35) + 'mqdefault.jpg'"
                    alt=""
                    width="290"
                  />
                </div>
                    <!-- id="video_img" -->
                <h3 v-if="video.title.length > 22">
                  {{ video.title.substring(0, 22) }} ...
                </h3>
              </div>
            </div>
          </div>

          <!-- 무한스크롤 -->
          <infinite-loading @infinite="infiniteHandler" spinner="bubble">
            <div
              slot="no-more"
              style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
            >
              No More
            </div>
            <div
              slot="no-results"
              style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
            >
              No Results
            </div>
          </infinite-loading>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Navbar from "@/components/Navbar";
import InfiniteLoading from "vue-infinite-loading";

const SERVER_URL = "https://morelang.gq/api";

// video.imgUrl

// function altImg(imgerror) {
//   console.log("이미지에러다!")
// }
export default {
  data() {
    return {
      channelInfo: [],
      videolst: [],
      token: "",
      end: false,
      newimg: "",
      img: "",
    };
  },
  components: {
    Navbar,
    InfiniteLoading
  },
  mounted() {
    // console.log("adf");
    axios.get(`${SERVER_URL}/channel?id=UCSGC87iX0QhnIfUOI_B_Rdg`).then(res => {
      console.log(res);
      this.channelInfo = res.data;
      axios
        .get(`${SERVER_URL}/playlist?id=${this.channelInfo.uploads}`)
        .then(res => {
          console.log(res);
          this.videolst = res.data.items;
          this.token = res.data.token;
          // console.log("videolst" + this.videolst)
        });
    });
  },
  methods: {
    // imgUrlAlt(event){
    //   console.log("이미지에러")
    //   // console.log(img)
    //   var video_img = document.getElementById('video_img')
    //   event.target.src = video_img.replace("maxres", "mq")
    //   // this.newimg = img.replace("maxres", "mq")
    //   // event.target.src = "this.newimg"
    // },
    infiniteHandler($state) {
      setTimeout(() => {
        axios
          .get(
            `${SERVER_URL}/playlist?id=${this.channelInfo.uploads}&token=${this.token}`
          )
          .then(res => {
            // console.log("infinitehandler");
            // console.log(res);
            this.videolst = this.videolst.concat(res.data.items);
            this.token = res.data.token;
            if (res.data.items.length != 50) {
              this.end = true;
            }
            // console.log("videolst" + this.videolst)
          });
        $state.loaded();
        if (this.end) {
          $state.complete();
        }
      }, 1000);
    }
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
  font-size: 50px;
  font-family: "Calibre", sans-serif;
  position: absolute;
  top: 45%;
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
