<template>
  <!-- 상단 -->
  <div
    class="Navbar"
    style="padding-top: 5px; top: 0; left: 0; z-index: 40; width: 100%; position: fixed;"
  >
    <div class="navigation">
      <div style="padding: 0">
        <v-card-title style="padding: 0px 30px 0px;">
          <router-link to="/">
            <h2 class="logo px-5 py-1" style="display:inline-block">
              Morelang
            </h2>
          </router-link>
          <div class="mx-auto mt-1">
            <v-text-field
              style="display: inline-block;"
              autocomplete="off"
              v-model="keyword"
              @keyup.enter="onSearch(keyword)"
              height="37"
              hide-details=""
              placeholder="키워드 검색"
              outlined
              clearable
              append-icon="mdi-magnify"
              @click:append="onSearch(keyword)"
            />
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
          <!-- <router-link to="/mypage"> -->
            <!-- @click="gotoMypage" -->
            <!-- <router-link :to="{ name: 'Mypage', params: { userid: userid } }">
              <p class="navBtn my-auto mr-3" style="font-size: 13px !important">
                마이페이지
              </p>
            </router-link> -->
            <!-- <v-avatar class="mr-3" color="indigo" size="38">
              <v-icon dark>
                mdi-account-circle
              </v-icon>
            </v-avatar> -->
          <!-- </router-link> -->
          <v-col cols="8" sm="3" lg="2" class="text-center py-0">
            <v-row no-gutters v-if="!$store.state.nickname">
              <v-col>
                <v-btn style="float: right" text @click="changeRoute('Login')" class="">로그인</v-btn>
              </v-col>
              <!-- <v-col cols="1" class=""><v-divider vertical></v-divider> </v-col> -->
              <!-- <v-col cols="6">
                <v-btn text @click="changeRoute('SignUp')" class="">회원가입</v-btn>
              </v-col> -->
            </v-row>
            <v-menu open-on-hover offset-y v-if="$store.state.nickname" no-gutters>
              <template v-slot:activator="{ on, attrs }">
                <v-card color="transparent" v-bind="attrs" v-on="on" flat>
                  <v-row no-gutters>
                    <v-col>
                      <v-avatar style="float: right;">
                        <v-img max-height="100%" :src="userThumbnail" alt="유저썸네일"></v-img>
                      </v-avatar>
                    </v-col>
                    <!-- <v-col cols="8">
                      <div class="text-left subtitle">{{ nickname }} 님</div>
                    </v-col> -->
                  </v-row>
                </v-card>
              </template>
              <v-list >
                <v-list-item-group color="primary">
                  <v-list-item v-for="(item, i) in items" :key="i" @click="userMenu(i)">
                    <v-list-item-title v-text="item"></v-list-item-title>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-menu>
          </v-col>
          <!-- <p class="navBtn mr-2 my-auto">로그아웃</p> -->
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
            <v-icon color="white" class="mr-1">mdi-alert-circle-outline</v-icon>
            <span>검색어를 입력해주세요</span>
          </v-tooltip>
        </v-col>
        <!-- </v-row> -->
      </v-container>

      <!-- 번역 언어 모달 -->
      <v-dialog
        class="dialog"
        v-model="transDialog"
        scrollable
        max-width="300px"
      >
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
          <v-card-actions class="mx-auto">
            <v-btn color="success" @click="onSearch(keyword)">검색</v-btn>
            <v-btn color="black" text @click="transDialog = false">닫기</v-btn>
            <!-- <v-btn
              color="black"
              text
              @click="transDialog = false"
              >닫기</v-btn
            > -->
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script scoped>
import "@/../public/css/Navbar.scss";
import axios from "axios";
import { mapState } from "vuex";
const SERVER_URL = "https://morelang.gq/api";
// import store from "@/../src/store/index.js";

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
      userid: null,
      keyword: "",
      transDialog: false,
      errSnackbar: false,
      timeout: 1500,
      errtext: "검색어를 입력해주세요",
      language: {
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
      items: ["Mypage", "Logout"]
    };
  },
  computed: mapState(["userThumbnail", "nickname", "accessToken"]),
  methods: {
    onSearch(word) {
      // store.state.target = word
      // console.log("target")
      // console.log(store.state.target)
      // if (window.location.href.indexOf("search") > -1) {
      //   console.log("현재페이지")
      // }
      // else {
      this.$router.push({ name: "Search", params: { target: word } });
      // }
      this.keyword = word;
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
          `${SERVER_URL}/newuser/translate?query=${this.keyword}&src_lang=kr&target_lang=${lang}`
        )
        .then(res => {
          this.keyword = res.data;
        });
    },
    // gotoMypage() {
    //   // path: 'mypage/${this.$store.state.member.userid}`'
    //   console.log("userid")
    //   console.log(this.userid)
    //   this.$router.push({ name: 'Mypage', params: { userid: this.userid}})
    // },
    userMenu(idx) {
      if (idx === 0) {
        // console.log("userid = " + this.$store.state.userid)
        this.$router.push({
          name: "Mypage",
          params: { userid:this.$store.state.userid }
        });
      // } else if (idx === 1) {
      //   this.$router.push({name: "Home"});
      // } 
      }else {
        this.$store.dispatch("LOGOUT"); 
      }
    },
    changeRoute(name) {
      // console.log(this.$route.name === name);
      if (this.$route.name === name) {
        this.$router.go({ name });
      } else {
        this.$router.push({ name });
      }
    },
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
