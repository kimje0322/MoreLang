<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png" /> -->
    <div style="width: 100%; height: 800px;">
      <!-- 상단 로고 & 메뉴 -->
      <header
        style="padding-top: 30px; top: 0; left: 0; z-index: 40; width: 100%; position: fixed;"
      >
        <div
          style="margin: 0 100px; display: flex; align-items: center; justify-content: space-between;"
        >
          <div
            style="top: 10px; font-size: 18px; pointer-event: none; font-family: SilkSerif; color: lightgray;"
          >
            #morelang
          </div>

          <!-- 구글 로그인 -->
          <div>
            <v-btn text @click="login" class="" v-if="!member.name">Login</v-btn>
            <v-menu open-on-hover offset-y v-else-if="member.name" no-gutters>
              <template v-slot:activator="{ on, attrs }">
                <v-card color="transparent" v-bind="attrs" v-on="on" flat>
                  <v-row no-gutters>
                    <v-col cols="4" class="d-nome d-md-flex">
                      <v-avatar>
                        <v-img max-height="100%" :src="member.profileImg" alt="유저썸네일"></v-img>
                      </v-avatar>
                    </v-col>
                    <v-col cols="8">
                      <div class="text-left subtitle">{{ member.name }}</div>
                    </v-col>
                  </v-row>
                </v-card>
                </template>
            </v-menu>
          </div>

          <!-- 메뉴바 -->
          <!-- <div style="width: 50px; ">
            <v-icon>hamburger-menu</v-icon>
          </div> -->
        </div>
        <div>
          <button @click="logout">logout</button>
        </div>
      </header>
      <!-- 소개 영상 -->
      <div style="width: 100%; height: 100vh;">
        <video
          muted
          autoplay
          loop
          playsinline
          src="../assets/video/투잡.mp4"
        ></video>
      </div>


      <!-- 추천 영상 -->
      <div style="padding: 100px 0; display: block;">
        <div style="margin: 0 100px;">
          <h3>
            <strong style="font-size: 40px; font-weight: 900; font-family: SilkSerif;">추천</strong>
          </h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script src="https://apis.google.com/js/platform.js"></script>
<script>
import axios from "axios"
export default {
  name: "Home",
  data(){
    return {
      gauth: {},
      member:{},
      refreshToken:"",
      user:{}
    }
  },
  mounted(){
    gapi.load('auth2', ()=> { 
        this.gauth = gapi.auth2.init({
          client_id: '258439612277-a2k3f6ro1jvdkbois85pt4cngrs6hctk.apps.googleusercontent.com'
        });      
        this.gauth.then(function(){
            console.log('init success');
        }, function(){
            console.error('init fail');
        })
    });
  },
  methods : {
    login() {
    this.gauth.grantOfflineAccess()
    .then((data)=>{
      console.log(data.code);
      const fd = new FormData();
      fd.append("code", data.code);
      fd.append("redirect", window.location.href)
      axios.post('https://morelang.gq/api/login',fd)
      .then((response)=>{
        console.log("성공!")
        this.member = response.data.member
        console.log(this.member)
        this.refreshToken = response.data.refreshToken;
      })
    });
    },
    logout(){
      this.user = this.gauth.currentUser.get()
      this.user.disconnect()
      .then(()=>{
        this.member ={}
        this.refreshToken = ""
        this.user={}
      });
    },
  }
};
</script>
