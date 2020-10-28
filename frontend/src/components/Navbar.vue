<template>
  <!-- 상단 -->
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
            <form
              style="display:inline-block; width:385px;"
              action="/search"
              method="get"
            >
              <v-text-field
                v-model="keyword"
                @keyup.enter="onSearch"
                height="37"
                hide-details=""
                placeholder="키워드 검색"
                outlined
                clearable
                prepend-inner-icon="mdi-magnify"
              />
            </form>
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
            <v-icon color="white" class="mr-1">mdi-alert-circle-outline</v-icon>
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
</template>

<script scoped>
import "@/../public/css/Navbar.scss";
import axios from "axios";
const SERVER_URL = "https://morelang.gq/api";

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
  updated() {
    if (this.errSnackbar && this.keyword) {
      this.errSnackbar = false;
    }
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
      }
    };
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
