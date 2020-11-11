<template>
  <div class="search">
    <!-- <Navbar /> -->

    <div
      class="Navbar"
      style="padding-top: 0px; top: 0; left: 0; z-index: 40; width: 100%; position: fixed;"
    >
      <div class="navigation">
        <div style="padding: 0">
          <v-card-title style="padding: 10px 30px 0px;">
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
                append-icon="mdi-magnify"
                @click:append="videoSearch(keyword)"
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
                  style="margin-bottom: 0 !important"
                >
                  <v-icon>mdi-google-translate</v-icon>
                </v-btn>
              </div>
            </div>
            <router-link to="/mypage">
              <p class="navBtn my-auto mr-3" style="font-size: 13px !important">
                마이페이지
              </p>
              <!-- <v-avatar class="mr-3" color="indigo" size="38">
              <v-icon dark>
                mdi-account-circle
              </v-icon>
            </v-avatar> -->
            </router-link>
            <p class="navBtn mr-2 my-auto" style="font-size: 13px !important">
              로그아웃
            </p>
            <!-- <v-icon size="25" class="mr-3">mdi-logout-variant</v-icon> -->
          </v-card-title>
        </div>

        <!-- 검색어 없이 번역버튼 눌렀을 경우 스낵바 -->
        <v-container
          fluid
          class="text-center"
          style="height: 1px; padding-bottom: 0px"
        >
          <!-- <v-row
        class="flex"
        justify="space-between"
      > -->
          <v-col cols="12" class="mt-2" style="height: 0px!important;">
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
    <div style="display: flex; justify-content: center; margin: 3% 0;">
      <!-- <h4 style="font-size: 18px;">
        Search results for " {{ search_word }} " ...
      </h4> -->
    </div>

    <!-- 비디오 나오는 부분 -->
    <div
      style="padding: 0 180px; display: flex; flex-flow: row wrap; justify-content: space-between;"
    >
      <div v-for="(video, i) in videolst" :key="i" style="margin-bottom: 10px;">
        <div
          class="col"
          style="width: clac(33.33% - 60px); margin: auto; display: inline-block;"
        >
          <v-hover v-slot:default="{ hover }" close-delay="200">
            <!-- <router-link :to="{ name: 'Video', params: { vid: video.id } }"> -->
            <div
              style="margin-bottom: 0px;"
              @click="$router.push({ name: 'Video', params: { vid: video.id } });"
            >
              <v-img
                :elevation="hover ? 16 : 2"
                :class="{ 'on-hover': hover }"
                :src="video.imgUrl"
                alt=""
                width="330"
              />
            </div>
            <!-- </router-link> -->
          </v-hover>
          <!-- <h3 v-if="video.title.length > 20">
            {{ video.title.substring(0, 20) }} ...
          </h3> -->
          <h4 class="searchTitle mt-4">
            {{ video.title }}
          </h4>
          <div>
            <v-btn
              v-for="(lang, j) in video.captions.slice(0, 4)"
              :key="j"
              rounded
              color="primary"
              dark
              x-small
              style="margin: 0 1px;"
            >
              <div v-if="lang.lang_translated.indexOf('(') > -1">
                {{
                  lang.lang_translated.substring(
                    0,
                    lang.lang_translated.indexOf("(")
                  )
                }}
              </div>
              <div v-else-if="lang.lang_translated.length > 10">
                {{ lang.lang_translated.substring(0, 10) }}
              </div>
              <div v-else>
                {{ lang.lang_translated }}
              </div>
              <!-- <div v-if="j > 5 && j==video.captions.length - 1">
                + {{ video.captions.length - 5 }}
              </div> -->
            </v-btn>
            <div
              v-if="video.captions.length > 4"
              @mouseover="hover = true"
              @mouseleave="hover = false"
            >
              + {{ video.captions.length - 4 }}
            </div>
            <!-- <span v-if="hover"> 
            <div v-for="(lang, j) in video.captions"
              :key="j">
              {{ lang.lang_translated }}

            </div> -->
            <!-- </span> -->
          </div>
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
import axios from "@/plugins/axios";
import InfiniteLoading from "vue-infinite-loading";
import store from "../store/index.js";
import Swal from "sweetalert2";
// import Navbar from "@/components/Navbar";

// import store from "@/../src/store/index.js";
// function getInputValue() {
//   var searchValue = $('#search_word').val();
// }

// 상단 네브바 고정
// var nav = document.getElementsByClassName("navigation");
// window.onscroll = function sticky() {
//   if (window.pageYOffset > nav[0].offsetTop) {
//     nav[0].classList.add("nav");
//   } else {
//     nav[0].classList.remove("nav");
//   }
// };
export default {
  name: "Search",
  components: {
    InfiniteLoading
    // Navbar
  },
  data() {
    return {
      hover: false,
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
      this.keyword = this.search_word;
      this.videoSearch(this.search_word);
    } else if (store.state.searchWord != null) {
      this.keyword = store.state.searchWord;
      this.videoSearch(store.state.searchWord);
    }
  },
  methods: {
    selectVideo(vid, vimg) {
      Swal.fire(
        {
          title: "영상을 구매하시겠습니까?",
          text: "10 Point 결제 부탁드립니다.",
          imageUrl: vimg,
          imageWidth: 400,
          imageHeight: 200,
          imageAlt: "Video image",

          showCancelButton: true,

          // closeOnConfirm: false,
          // showLoaderOnConfirm: true
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Buy it!"
        }).then((result) => {
          if (result.isConfirmed) {
            Swal.fire({
              title: "결제 성공!",
              text: "해당 영상 학습 페이지로 이동합니다.",
              icon: "success",
              confirmButtonColor: "#3085d6",
              confirmButtonText: "OK",
              
              // "success"
            }) .then((result) => {
              if (result.isConfirmed) {
                this.$router.push({ name: "Video", params: { vid: vid } });
              }
            })
          }
        })
        // function() {
        //   setTimeout(function() {
        //     Swal("결제가 완료되었습니다!");
        //   }, 2000);
        // }
    
      // setTimeout(function() {}, 500);
    },
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
         `/newuser/translate?query=${this.keyword}&src_lang=kr&target_lang=${lang}`
        )
        .then(res => {
          this.keyword = res.data;
        });
    },
    videoSearch(search) {
      this.search_word = search;
      console.log("이것은 keyword");
      console.log(this.keyword);
      this.next = 10;
      this.click = true;
      this.tmp = search;
      axios
        .get(`/newuser/search?q=${search}&start=0`)
        .then(res => {
          // console.log("asdf")
          this.videolst = res.data;
          console.log(res);
          // this.start = this.start + 10
        });

      store.state.searchWord = search;
    },
    infiniteHandler($state) {
      setTimeout(() => {
        // const temp = [];
        axios
          .get(`/newuser/search?q=${this.tmp}&start=${this.next}`)
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

<style scoped>
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
/* .nav {
  position: fixed;
  background: beige;
  padding: 0;
} */

.navigation {
  width: 100%;
  top: 0;
}

.searchTitle {
  display: inline-block;
  width: 330px;
  /* white-space: nowrap; */
  overflow: hidden;
  /* text-overflow: ellipsis; */

  white-space: normal;
  line-height: 1.2;
  height: 2.4em;
  text-align: left;
  word-wrap: break-word;
  /* display: -webkit-box; */
  /* -webkit-line-clamp: 2; */
  -webkit-box-orient: vertical;
}
.Navbar{
  position: fixed;
  top: 0;
  background: black;
}
</style>