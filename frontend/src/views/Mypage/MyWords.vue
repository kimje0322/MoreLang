<template>
  <div class="ml-2">
    <h3 class="title ml-3 px-5">스크랩한 단어</h3>
    <v-row class="px-5 mt-5 mx-3" justify="center">
      <v-container >
      <!-- 언어별 단어장 -->
      <!-- expander 사용예정 / expand => 뜻/예문 보이기 -->
        <h2 class="mb-3">영어</h2>
        <v-row no-gutters style="text-align:center; width:70%;">
          <v-col v-for="i in 6" :key="i" cols="12" sm="6">
            <v-card
              class="pa-2 selectLang"
              outlined
              tile
            >
              <p class="my-auto">{{i}}</p>
            </v-card>
          </v-col>
        </v-row>
        <h2 class="mt-5 mb-3">일본어</h2>
        <v-row no-gutters style="text-align:center; width:70%;">
          <v-col v-for="i in 6" :key="i" cols="12" sm="6">
            <v-card
              class="pa-2 selectLang"
              outlined
              tile
            >
              <p class="my-auto">{{i}}</p>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-row>
  </div>
</template>

<script>
// import axios from "axios";
// const SERVER_URL = "https://morelang.gq/api"

export default {
  mounted() {
    // axios
    //   .get(`${SERVER_URL}/translate?query=""`)
  },
  data() {
    return {

    }
  },
}
</script>

<style>
  .title {
    color: black;
  }
</style>