<template>
  <div class="search">
    <!-- <Navbar /> -->

    <div
      class="Navbar"
      style="padding-top: 5px; top: 0; left: 0; z-index: 40; width: 100%; position: fixed;"
    >
      <div class="navigation">
        <div>
          <v-card-title>
            <router-link to="/">
              <h2 class="logo px-5 py-1" style="display:inline-block">
                Morelang
              </h2>
            </router-link>
            <div class="mx-auto mt-1">
              <!-- 검색창 -->
              <!-- <form
              style="display:inline-block; width:385px;"
              action="/search"
              method="get"
            > -->
              <v-text-field
                style="display: inline-block;"
                autocomplete="off"
                v-model="keyword"
                @keyup.enter="videoSearch(keyword)"
                height="37"
                hide-details=""
                placeholder="키워드 검색"
                outlined
                clearable
                prepend-inner-icon="mdi-magnify"
              />
              <!-- </form> -->
              <!-- 번역버튼 -->
              <div class="mx-1" style="display:inline-block">
                <v-btn
                  @click="beforeTrans"
                  class="py-4 mb-2"
                  x-small
                  color="#43A047"
                  dark
                >
                  <v-icon>mdi-google-translate</v-icon>
                </v-btn>
              </div>
            </div>
            <router-link to="/mypage">
              <p class="navBtn my-auto mr-3">마이페이지</p>
              <!-- <v-avatar class="mr-3" color="indigo" size="38">
              <v-icon dark>
                mdi-account-circle
              </v-icon>
            </v-avatar> -->
            </router-link>
            <p class="navBtn mr-2 my-auto">로그아웃</p>
            <!-- <v-icon size="25" class="mr-3">mdi-logout-variant</v-icon> -->
          </v-card-title>
        </div>

        <!-- 검색어 없이 번역버튼 눌렀을 경우 스낵바 -->
        <v-container
          fluid
          class="text-center"
          style="height:1px; padding-bottom:0px"
        >
          <!-- <v-row
        class="flex"
        justify="space-between"
      > -->
          <v-col cols="12" class="mt-2" style="height:0px!important;">
            <v-tooltip v-model="errSnackbar" top>
              <template v-slot:activator="{ on, attrs }">
                <v-btn icon v-bind="attrs" v-on="on"> </v-btn>
              </template>
              <v-icon color="white" class="mr-1"
                >mdi-alert-circle-outline</v-icon
              >
              <span>검색어를 입력해주세요</span>
            </v-tooltip>
          </v-col>
          <!-- </v-row> -->
        </v-container>

        <!-- 번역 언어 모달 -->
        <v-dialog v-model="transDialog" scrollable max-width="300px">
          <v-card>
            <v-toolbar color="#43A047" dark>
              <v-toolbar-title class="toolbarTitle">
                <p
                  class="my-auto"
                  style="margin-left: 85px; text-align:center!important"
                >
                  검색어 번역
                </p>
              </v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon> </v-btn>
            </v-toolbar>
            <v-card-text style="height: 300px;">
              <v-card class="mx-auto mt-5" max-width="500" flat>
                <v-row justify="center">
                  <v-container class="language" style="text-align:center;">
                    <v-row no-gutters>
                      <!-- 언어 선택 -->
                      <v-col
                        v-for="(kr, en) in language"
                        :key="en"
                        cols="12"
                        sm="12"
                      >
                        <v-card
                          @click="onTranslate(en)"
                          class="pa-2 selectLang"
                          outlined
                          tile
                        >
                          <p class="my-auto">{{ kr }}</p>
                        </v-card>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-row>
              </v-card>
            </v-card-text>
            <v-divider class="mb-0"></v-divider>
            <v-card-actions>
              <v-btn
                color="black"
                text
                block
                class="mx-auto"
                @click="transDialog = false"
                >닫기</v-btn
              >
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
    </div>

    <!-- 검색어 -->
    <div style="display: flex; justify-content: center; margin: 10% 0;">
      <h4 style="font-size: 18px;">
        Search results for " {{ search_word }} " ...
      </h4>
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
            <img :src="video.imgUrl" alt="" width="330" />
          </div>
          <h3 v-if="video.title.length > 20">
            {{ video.title.substring(0, 20) }} ...
          </h3>
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
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px"
      >
        No Results
      </div>
    </infinite-loading>
  </div>
</template>

<script>
import axios from "axios";
import InfiniteLoading from "vue-infinite-loading";

// import Navbar from "@/components/Navbar";

// import store from "@/../src/store/index.js";

const SERVER_URL = "https://morelang.gq/api";
// import axios from "axios";

// function getInputValue() {
//   var searchValue = $('#search_word').val();
// }


// 상단 네브바 고정
var nav = document.getElementsByClassName("navigation");
window.onscroll = function sticky() {
  if (window.pageYOffset > nav[0].offsetTop) {
    nav[0].classList.add("nav");
  } else {
    nav[0].classList.remove("nav");
  }
};
export default {
  name: "Search",
  components: {
    InfiniteLoading,
    // Navbar
  },
  data() {
    return {
     keyword: "",
      transDialog: false,
      errSnackbar: false,
      timeout: 1500,
      errtext: "검색어를 입력해주세요",
      language: {
        kr: "한국어",
        en: "영어",
        jp: "일본어",
        cn: "중국어",
        vi: "베트남어",
        id: "인도네시아어",
        ar: "아랍어",
        bn: "뱅갈어",
        de: "독일어",
        es: "스페인어",
        fr: "프랑스어",
        hi: "힌디어",
        it: "이탈리아어",
        ms: "말레이시아어",
        nl: "네덜란드어",
        pt: "포르투갈어",
        ru: "러시아",
        th: "태국어",
        tr: "터키어"
      },
      search_word: "",
      videolst: [],
      start: 0,
      tmp: "",
      next: 10,
      click: false,
      end: false
    };
  },
  mounted: function() {
    // if(store.state.target != null) {
    //   this.videoSearch(store.state.target)
    //   // store.state.target = null
    // }
    if (this.$route.params.target != null) {
      // console.log("parmas 받아옴")
      this.search_word = this.$route.params.target;
      this.keyword = this.search_word
      this.videoSearch(this.search_word);
      
    }
  },
  methods: {
     beforeTrans() {
      if (this.keyword) {
        this.transDialog = true;
      } else {
        this.errSnackbar = true;
      }
    },
    onTranslate(lang) {
      axios
        .get(
          `${SERVER_URL}/translate?query=${this.keyword}&src_lang=kr&target_lang=${lang}`
        )
        .then(res => {
          this.keyword = res.data;
        });
    },
    videoSearch(search) {
      this.search_word = search;
      console.log("이것은 keyword")
      console.log(this.keyword)
      this.next = 10;
      this.click = true;
      this.tmp = search;
      axios.get(`${SERVER_URL}/search?q=${search}&start=0`).then(res => {
        // console.log("asdf")
        this.videolst = res.data;
        console.log(res);
        // this.start = this.start + 10
      });
    },
    infiniteHandler($state) {
      setTimeout(() => {
        // const temp = [];
        axios
          .get(`${SERVER_URL}/search?q=${this.tmp}&start=${this.next}`)
          .then(res => {
            this.videolst = this.videolst.concat(res.data);
            if (res.data.length != 10) {
              this.end = true;
            }
          });
        // console.log("이거는 this.search_word" + this.search_word)
        this.next = this.next + 10;
        $state.loaded();
        if (this.end) {
          $state.complete();
        }
      }, 1000);
    }
  }
};
</script>

<style>
.logo {
  font-family: "Kaushan Script", cursive;
}
a {
  color: black !important;
  text-decoration: none !important;
}
.navBtn {
  font-family: "Nanum Gothic", sans-serif;
  /* font-family: 'Do Hyeon', sans-serif; */
  font-size: 17.5px;
  color: #616161;
}
.selectLang:hover {
  background-color: #def5df;
}
.language {
  font-family: "Nanum Gothic", sans-serif;
}
.v-snack__wrapper {
  min-width: none;
  min-height: none;
}
.container text-center container--fluid {
  min-height: none;
}
.nav {
  position: fixed;
  background: beige;
  padding: 0;
}

.navigation {
  width: 100%;
  top: 0;
}
</style>