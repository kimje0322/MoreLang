<template>
  <div>
    <h3 class="title">닉네임님의 영상</h3>
    <div v-for="(video, i) in videoLst" :key="i" style="display: inline-block;">
      <div
          class="col"
          style=" margin: auto; display: inline-block;"
        >
          <div style="margin-bottom: 10px;">
            <img
              style="width:300px"
              :src="video.imgUrl"
              alt=""
            />
          </div>
          <h3 v-if="video.title.length > 38">{{ video.title.substring(0, 36) }} ...</h3>
          <h3 v-else>{{video.title}}</h3>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = "https://morelang.gq/api"

export default {
  data() {
    return {
      videoLst: []
    }
  },
  mounted() {
    axios
      .get(`${SERVER_URL}/newuser/search?q=glee&start=0`)
      .then((res) => {
        // console.log(res)
        this.videoLst = res.data;
      })
  },
}
</script>

<style scoped>
  .title {
    color: black;
  }
</style>