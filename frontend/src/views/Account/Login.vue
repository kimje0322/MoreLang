<template>
   <v-container>
    <v-row no-gutters class="mt-10">
      <v-col cols="12" sm="5">
        <v-card
          tile
          class="pa-9"
          height="650px"
          align="center"
        >
          <v-card-title class="pa-0 mb-7 justify-space-between">
            <h1 class="ma-0" style="font-family: 'Do Hyeon', sans-serif;">
              로그인
            </h1>
            <v-btn fab dark @click="$router.push({ name: 'Home' })">
              <v-icon>mdi-home-outline</v-icon>
            </v-btn>
          </v-card-title>
          <v-form ref="form">
            <v-text-field
              class="my-3"
              v-model="loginData.username"
              placeholder="hello@naver.com"
              label="이메일"
              required
              @keypress.enter="onSubmit()"
            ></v-text-field>
            <v-text-field
              type="password"
              v-model="loginData.password"
              label="비밀번호"
              required
              @keypress.enter="onSubmit()"
            ></v-text-field>
            <v-card-text style="height: 16px;">
              <small class="red--text">{{ errorMsg }}</small>
              <v-progress-circular indeterminate v-if="loading"></v-progress-circular>
            </v-card-text>
            <v-card-actions class="px-0 mt-4">
              <v-btn
                block
                :color="'success'"
                tile
                @click="onSubmit()"
                >로그인</v-btn
              >
            </v-card-actions>

            <v-card-actions class="px-0 my-3">
              <v-btn
                block :color="'primary'" tile @click="toRegister()"
                >회원 가입</v-btn
              >
            </v-card-actions>
            <small
              style="cursor:pointer" @click="$router.push({ name: 'FindPass' })"
              >비밀번호찾기</small
            >
            <div>
              <hr style="width:41%; display: inline-block;" class="mb-1 mr-3">
              <p style="display: inline-block;">OR</p>
              <hr style="width:41%; display: inline-block;" class="mb-1 ml-3">
              </div>
            <!-- 소셜 로그인 -->
              <v-row justify="center">
                <v-container style="text-align:center;">
                  <v-row no-gutters>
                    <v-col
                      class="mb-3"
                      cols="12"
                      sm="12"
                    >
                      <v-card
                        class="pa-2"
                        outlined
                        tile
                        @click="GoogleLogin()"
                      >
                        <img style="width:26px; float:left" src="@/assets/img/google.png" alt="">
                        <p class="my-auto" style="display:inline-block; ">Sign up with Google</p>
                      </v-card>
                    </v-col>
                    <v-col
                      class="mb-3"
                      cols="12"
                      sm="12"
                    >
                      <a :href=naverUrl>
                      <v-card
                        class="pa-2"
                        outlined
                        tile
                        @click="NaverLogin()"
                      >
                        <img style="width:26px; float:left" src="@/assets/img/naver.png" alt="">
                        <p class="my-auto">Sign up with naver</p>
                      </v-card>
                      </a>
                    </v-col>
                    <v-col
                      class="mb-3"
                      cols="12"
                      sm="12"
                    >
                      <v-card
                        class="pa-2"
                        outlined
                        tile
                        @click="KakaoLogin()"
                      >
                        <img style="width:26px; float:left" src="@/assets/img/kakao.png" alt="">
                        <p class="my-auto">Sign up with kakao</p>
                      </v-card>
                    </v-col>
                  </v-row>
                </v-container>
              </v-row>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script src="https://apis.google.com/js/platform.js"></script>
<script>
import Navbar from "@/components/Navbar";
import { mapState } from "vuex";
import axios from "axios";
export default {
  name: "Login",
  data() {
    return {
      loginData: {
        username: this.$route.query.username,
        password: null
      },
      errorMsg: null,
      showMenu: false,
      loading: false,
      gauth: {},
      naverUrl: '',
    };
  },
  methods: {
    async onSubmit() {
      console.log("click")
      if (!this.loginData.username || !this.loginData.password) {
        this.errorMsg = "이메일 또는 비밀번호를 입력해주세요";
      } else {
        this.errorMsg = null;
        this.loading = true;
        try {
          await this.$store.dispatch("LOGIN", this.loginData);
          if (this.$store.state.formerLink) {
            this.$router.push(this.$store.state.formerLink);
          } else {
            this.$router.push({ name: "Home" });
          }
        } catch (e) {
          this.errorMsg = "이메일 또는 비밀번호를 확인해주세요";
          console.error(e);
        }
        this.loading = false;
      }
    },
    toRegister() {
      this.$router.push({ name: "SignUp" });
    },
    toFindId() {
      this.$router.push({ name: "FindId" });
    },
    toFindPw() {
      this.$router.push({ name: "FindPass" });
    },
    async GoogleLogin() {
      await gapi.load("auth2", () => {
      this.gauth = gapi.auth2.init({
        client_id:
          "258439612277-a2k3f6ro1jvdkbois85pt4cngrs6hctk.apps.googleusercontent.com"
      });
      this.gauth.then(
        function() {
          console.log("init success");
        },
        function() {
          console.error("init fail");
        }
      );
      this.Glogin();
    });
    },
    async Glogin() {
      await this.gauth.grantOfflineAccess().then(data => {
        console.log(data.code);
        const fd = new FormData();
        axios.defaults.headers.common.Authorization = ``;
        fd.append("code", data.code);
        fd.append("redirect", window.location.href);
        axios
          .post(`${this.$store.state.ServerURL}/newuser/googlelogin`, fd)
          .then(response => {
            console.log("성공!");
            console.log(response);
            this.$store.dispatch("SocialLogin", response.data);
            if (this.$store.state.formerLink) {
              this.$router.push(this.$store.state.formerLink);
            } else {
              this.$router.push({ name: "Home" });
            }
          });
      });
    },
    KakaoLogin() {
      window.Kakao.Auth.loginForm({
        success: this.GetMe,
      });
    },
    GetMe(authObj) {
      console.log(authObj);
      const fd = new FormData();
      fd.append("accessToken", authObj.access_token);
      axios.post(`${this.$store.state.ServerURL}/newuser/kakaologin`, fd)
        .then((response) => {
          console.log("성공!")
          console.log(response)
          this.$store.dispatch("SocialLogin", response.data);
            if (this.$store.state.formerLink) {
              this.$router.push(this.$store.state.formerLink);
            } else {
              this.$router.push({ name: "Home" });
            }
      });
    },
    NaverLogin(){
      var clientId = "YOwQRknl_3ldIN3E5UQN";//애플리케이션 클라이언트 아이디값";
      var redirectURI = "http://localhost:8081/"
      var apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
      apiURL += "&client_id=" + clientId;
      apiURL += "&redirect_uri=" + redirectURI+"/oauth2/redirect";
      apiURL += "&state=" + 234235;
      this.naverUrl = apiURL;
    },
  }
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.p_page {
  margin-top: "200vh";
}
.loginPage {
    text-align: center!important;
  }
</style>