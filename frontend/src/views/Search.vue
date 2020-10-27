<template>
  <div class="search">
    <!-- 상단 로고 & 메뉴 -->
    <header
      style="padding-top: 30px; top: 0; left: 0; width: 100%; display: block;"
    >
      <div class="navigation">
        <div
          style="margin: 0 100px; display: flex; align-items: center; justify-content: space-between;"
        >
          <router-link to="/" style="text-decoration: none;">
            <div
              style="top: 10px; font-weight: 600; font-size: 18px; pointer-event: none; font-family: 'Calibre', sans-serif; color: darkgray;"
            >
              #MORELANG
            </div>
          </router-link>
          <!-- 구글 로그인 -->
          <div></div>

          <!-- 검색 -->
          <div>
            <!-- <router-link to="/search" style="text-decoration: none;"> -->
            <form action="/search" method="get">
              <input
                v-model="search_word"
                type="search"
                placeholder="SEARCH"
                autofocus
                style="border-bottom: 1px solid #737475; font-size: 30px; letter-spacing: 0.075em; display: inline-block;"
              />
              <v-icon
                large
                style="display: inline-block"
                @click="videoSearch(search_word)"
                >mdi-magnify</v-icon
              >
            </form>
            <!-- </router-link> -->
          </div>
        </div>
      </div>
    </header>

    <!-- 검색어 -->
    <div style="display: flex; justify-content: center; margin: 50px 0;">
      <h4 style="font-size: 18px;">Search results for " {{ search_word }} "...</h4>
    </div>

    <!-- 비디오 나오는 부분 -->
    <div
      style="padding: 0 90px; display: flex; flex-flow: row wrap; justify-content: space-between;"
    >
      <div v-for="(video, i) in videolst" :key="i">
        <div
          class="col"
          style="width: clac(33.33% - 60px); margin: auto; display: inline-block;"
        >
          <div style="margin-bottom: 15px;">
            <img
              :src="video.imgUrl"
              alt=""
              width="330"
            />
          </div>
          <h3 v-if="video.title.length > 20">{{ video.title.substring(0, 20) }} ...</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "https://morelang.gq/api";
// import axios from "axios";

// function getInputValue() {
//   var searchValue = $('#search_word').val();
// }

export default {
  name: "Search",
  components: {},
  data() {
    return {
      search_word: "",
      videolst: []
    };
  },
  methods: {
    videoSearch(search) {
      axios.get(`${SERVER_URL}/search?q=${search}&start=10`).then(res => {
        console.log("asdf")
        this.videolst = res.data;
        console.log(res);
      });
    }
  }
};
</script>

<style>
</style>