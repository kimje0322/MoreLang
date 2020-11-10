<template>
  <v-container>
  <h3 class="title" style="color: white">닉네임님의 영상</h3>
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
      .get("/newuser/search?q=glee&start=0")
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