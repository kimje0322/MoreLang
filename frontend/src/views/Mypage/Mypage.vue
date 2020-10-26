<template>
  <div class="mypage" style="padding: 5% 8%">
    <!-- 유저 정보 -->
    <div style="margin-right: 25px; width: 27%; text-align: center">
      <!-- 탭 -->
      <v-tabs vertical style="margin: 10% 15%">
        <div style="color:black; margin: 15px 0px">
        <h3>닉네임</h3>
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
          스크랩
        </v-tab>
        <v-tab>
          <v-icon left>
            mdi-book-play-outline
          </v-icon>
          좋아요
        </v-tab>

        <v-tab-item>
          <v-card flat>
            <v-card-text>
               <VideoLearning />
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <Scrap />
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <Liked />
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
  </div>
  </div>
</template>

<script>
import VideoLearning from "@/views/Mypage/VideoLearning";
import Scrap from "@/views/Mypage/Scrap";
import Liked from "@/views/Mypage/Liked";
import "@/../public/css/Mypage.scss"

export default {
  components: {
    VideoLearning,
    Scrap,
    Liked,
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
      console.log('충전하기')
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