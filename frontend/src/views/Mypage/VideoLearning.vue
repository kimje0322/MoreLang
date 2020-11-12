<template>
  <v-container>
    <h2>My video</h2>
  <!-- <div class="title" style="color: white;"><b class="outline"><span>My video</span></b></div> -->
    <div v-for="(video, i) in videoLst" :key="i" style="display: inline-block;">
      <div
          class="col"
          style=" margin: auto; display: inline-block;"
        >
          <div style="margin-bottom: 10px;">
            <img
              style="width:220px"
              :src="video.imgUrl"
              alt=""
            />
          </div>
          <h4 v-if="video.title.length > 34">{{ video.title.substring(0, 32) }} ...</h4>
          <h4 v-else>{{video.title}}</h4>
      </div>
    </div>
  </v-container>
</template>

<script>
import axios from "@/plugins/axios";

export default {
  data() {
    return {
      videoLst: []
    }
  },
  mounted() {
    axios
      .get("/user/myvideo?direction=DESC&page=0&size=30")
      .then((res) => {
        console.log(res)
        this.videoLst = res.data;
      })
  },
}
</script>

<style scoped>
@import url(//fonts.googleapis.com/css?family=Vibur);
.title b{
  font: 300 4vh "KyoboHand";
  color: #fdd;
  text-shadow: 0 -40px 100px, 0 0 2px, 0 0 1em #ff0000, 0 0 0.5em #ff0000, 0 0 0.1em #ff0000, 0 10px 3px #000;
}
.title b span{
  animation: blink linear infinite 2s;
}
.title b span:nth-of-type(2){
  animation: blink linear infinite 3s;
}
/* .outline {
  border: solid white 1px;
  padding: 3px 8px;
  border-radius: px;
} */
</style>