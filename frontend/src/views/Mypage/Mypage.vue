<template>
<div>
  <Navbar />
  <div class="mypage" style="padding: 0% 5%">
    <!-- 유저 정보 -->
    <div style="margin-right: 25px; ">
      <v-tabs vertical style="margin: 3% 0%">
        <div style="color:black; margin: 15px 0px">
        <h3 style="text-align: center">닉네임</h3>
        <p style="margin-top:10px">500 point  
        <v-chip
        small
        class="ma-2"
        color="green"
        label
        text-color="white"
        @click="onCharge"
        >
        충전
        </v-chip>
      </p> 
      </div>
      <!-- 탭 -->
        <v-tab>
          <v-icon left>
            mdi-youtube
          </v-icon>
          영상학습
        </v-tab>
        <v-tab>
          <v-icon left>
            mdi-alphabetical-variant
          </v-icon>
          단어모음
        </v-tab>
        <v-tab>
          <v-icon left>
            mdi-book-open
          </v-icon>
          문장모음
        </v-tab>

        <v-tab-item>
          <v-card flat>
            <v-card-text style="margin-left:35px">
               <VideoLearning />
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text style="margin-left:35px">
              <MyWords />
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text style="margin-left:35px">
              <MySentences />
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
  </div>
  </div>
  </div>
</template>

<script>
import VideoLearning from "@/views/Mypage/VideoLearning";
import MyWords from "@/views/Mypage/MyWords";
import MySentences from "@/views/Mypage/MySentences";
import Navbar from "@/components/Navbar";
import "@/../public/css/Mypage.scss"

export default {
  components: {
    Navbar,
    VideoLearning,
    MyWords,
    MySentences,
  },
  data () {
      return {
        tab: null,
        items: {
          '영상학습':'mdi-youtube' , '스크랩':'mdi-alphabetical-variant', '좋아요':'mdi-book-play-outline'
      },
        icons: ['mdi-youtube', 'mdi-alphabetical-variant', 'mdi-book-play-outline']
      }
    },
  methods: {
    onCharge() {
      alert('충전하시겠습니까?')
      },
    }
  }
</script>

<style scoped>
  .v-toolbar__content {
    height: 0px !important;
  }
  .v-tab--active {
    text-decoration:none !important;
    color: black !important;
    background-color:#def5df;
  }
  .mypageTabs {
    border-top-right-radius: 5px;
    border-top-left-radius: 5px;
  }
  .v-tabs-slider-wrapper {
    display:none !important;
  }

  .v-item-group{
    color: black;
    /* text-decoration:none !important; */
  }
</style>