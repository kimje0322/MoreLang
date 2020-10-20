<template>

  <div class="mypage" style="display:flex; padding: 5% 8%">
    <!-- 유저 정보 -->
    <div style="margin-right: 25px; width: 27%; text-align: center">
      <v-icon style="margin-top: 100px; margin-bottom: 17px; font-size: 80px;">mdi-account-circle</v-icon>
      <h4>당근이좋아 님</h4>
      <p style="margin-top:10px">500 point</p>
      <div>
        <h4 style="margin-top: 50px">포인트 랭킹</h4>
        <div style="align-items: center">
        <div style="margin: 15px 0px 0px 57px; width: 65%; border-radius: 12px; padding: 30px 0px; background-color: #FCE4EC">
          <p><strong>1위</strong> 기린이그린그림</p>
          <p><strong>2위</strong> 기린이그린그림</p>
          <p><strong>3위</strong> 기린이그린그림</p>
          <v-icon>mdi-dots-horizontal</v-icon>
          <p><strong>27위</strong> 기린이그린그림</p>
        </div>
        </div>
      </div>
    </div>

    <div >
    <!-- 탭 -->
    <!-- <v-card> -->
    <v-toolbar
      flat
      style="height:70px"
    >
      <template v-slot:extension>
        <v-tabs
          v-model="tab"
          align-with-title
        >
          <v-tab
            class="mypageTabs"
            v-for="item in items"
            :key="item"
          >
            {{ item }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>
    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
        <v-card flat>
          <div v-if="item=='영상학습'">
            <VideoLearning />
          </div>
          <div v-else-if="item=='스크랩'">
            <Scrap />
          </div>
          <div v-else>
           <Liked />
          </div>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  <!-- </v-card> -->
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
        items: [
          '영상학습', '스크랩', '좋아요'
        ],
      }
    },
  }
</script>

<style>
  .v-toolbar__content {
    height: 0px !important;
  }
  .v-tab--active {
    text-decoration:none !important;
    color: black !important;
    background-color:#FCE4EC;
  /* rgba(173, 220, 254, 0.4); */
  }
  .mypageTabs {
    /* padding: 0px 15px; */
    border-top-right-radius: 5px;
    border-top-left-radius: 5px;
  }
  .v-tabs-slider-wrapper {
    color: palevioletred;
  }
  .v-item-group{
    color: black;
    /* text-decoration:none !important; */
  }
</style>