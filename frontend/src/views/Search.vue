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

    <!-- 무한스크롤 -->
    <infinite-loading v-if="click" @infinite="infiniteHandler" spinner="bubble">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
      >
        No More
      </div>
      <div
        slot="no-results"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px">
        No Results
      </div>
    </infinite-loading>

  </div>
</template>

<script>
import axios from "axios";
import InfiniteLoading from 'vue-infinite-loading';

const SERVER_URL = "https://morelang.gq/api";
// import axios from "axios";

// function getInputValue() {
//   var searchValue = $('#search_word').val();
// }

export default {
  name: "Search",
  components: {
    InfiniteLoading,
  },
  data() {
    return {
      search_word: "",
      videolst: [],
      start : 0,
      tmp: "",
      next: 10,
      click: false,
    };
  },
  methods: {
    videoSearch(search) {
      this.click = true;
      this.tmp = search;
      axios.get(`${SERVER_URL}/search?q=${search}&start=0`).then(res => {
        // console.log("asdf")
        this.videolst = res.data
        console.log(res);
        // this.start = this.start + 10
      });
    },
    infiniteHandler($state) {
      setTimeout(() => {
        // const temp = [];
        axios.get(`${SERVER_URL}/search?q=${this.tmp}&start=${this.next}`).then((res) => {
          this.videolst = this.videolst.concat(res.data)
        })
        // console.log("이거는 this.search_word" + this.search_word)
        this.next = this.next + 10
        $state.loaded();
        if(this.next > 30) {
          $state.complete()
        }
      }, 1000);
    }
  }
};
</script>

<style>
</style>