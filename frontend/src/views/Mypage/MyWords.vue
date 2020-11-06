<template>
  <div class="ml-2">
    <!-- <h3 class="title ml-3 px-5">스크랩한 단어</h3> -->
    <!-- 언어 필터링 -->
    <v-container style="text-align:center;">
      <v-row no-gutters>
        <!-- 정렬 맞추기 위해 왼쪽 빈칸 사용 -->
        <!-- <v-col cols="12" sm="1"></v-col> -->
        <!-- @click="onLanguage(category.name, category.key)" -->
        <!-- <v-col v-for="(lang, i) in langLst" :key="i" cols="12" sm="1">
            <v-card
              class="pa-2 categoryCard"
              tile
            >
              <p class="mb-0 categoryTag">{{lang}}</p>
            </v-card>
          </v-col> -->
        <v-col v-for="(lang, i) in langLst" :key="i" sm="1">
          <v-chip color="green" text-color="white" @click="wordList(lang)">
            {{ language[lang] }}
          </v-chip>
        </v-col>
      </v-row>
    </v-container>

    <v-row class="px-5 mt-5 mx-3" justify="center">
      <v-container>
        <!-- 언어별 단어장 -->
        <!-- expander 사용예정 / expand => 뜻/예문 보이기 -->
        <!-- <h2 class="mb-3">영어</h2>
        <v-row no-gutters style="text-align:center; width:70%;">
          <v-col v-for="i in 6" :key="i" cols="12" sm="6">
            <v-card class="pa-2 selectLang" outlined tile>
              <p class="my-auto">{{ i }}</p>
            </v-card>
          </v-col>
        </v-row> -->
        <div v-if="wordlang">
          <h2 class="mt-5 mb-3">{{ language[selectlang] }}</h2>
          <h4>학습중</h4>

          <v-row no-gutters style="text-align:center; width:70%;">
            <v-col v-for="(word, i) in wordlist" :key="i" cols="12" sm="6">
              <v-card
                v-if="!word.isLearn"
                class="pa-2 selectLang"
                outlined
                tile
                style="position:relative"
              >
                <!-- append-icon="mdi-lead-pencil" label="복습" -->
                <v-checkbox
                  style="width: 27%;
                        position: absolute;
                        bottom: 7px;
                left: 5px;"
                  color="success"
                  value="success"
                  hide-details
                ></v-checkbox>

                <!-- <v-icon small left>mdi-lead-pencil</v-icon>
              복습 -->

                <p class="my-auto">{{ word.eachVoca }}</p>
              </v-card>
            </v-col>
          </v-row>

          <div style="height: 100px;"></div>

          <h4>학습완료</h4>
          <v-row no-gutters style="text-align:center; width:70%;">
            <v-col v-for="(word, i) in wordlist" :key="i" cols="12" sm="6">
              <v-card
                v-if="!word.isLearn"
                class="pa-2 selectLang"
                outlined
                tile
                style="position:relative"
              >
                <!-- append-icon="mdi-lead-pencil" label="복습" -->
                <v-checkbox
                  style="width: 27%;
                position: absolute;
                bottom: 7px;
                left: 5px;"
                  color="success"
                  value="success"
                  hide-details
                ></v-checkbox>

                <!-- <v-icon small left>mdi-lead-pencil</v-icon>
              복습 -->

                <p class="my-auto">{{ word.eachVoca }}</p>
              </v-card>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = "https://morelang.gq/api";

export default {
  mounted() {
    axios.get(`${SERVER_URL}/user/myvoca-country`).then(res => {
      console.log("단어장에 저장된 국가들");
      console.log(res);
      this.langLst = res.data;
    });
    // axios
    //   .get(`${SERVER_URL}/myvoca?country=en&direction=ASC&page=0&size=8`)
    //   .then(res => {
    //     console.log(res);
    //   });
  },
  data() {
    return {
      wordlist: [],
      selectlang: "",
      wordlang: false,
      langLst: [],
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
      }
    };
  },
  // mounted() {
  //   axios.get(`${SERVER_URL}/myvoca-country`)
  //   .then((res) => {
  //     console.log("단어장에 저장된 국가들")
  //     console.log(res)

  //   })
  // },
  methods: {
    // axios.get()
    wordList(lang) {
      if (lang == this.selectlang) {
        console.log("여기");
        this.wordlang = !this.wordlang;
      } else {
        this.wordlang = true;
      }
      this.selectlang = lang;
      axios
        .get(
          `${SERVER_URL}/user/myvoca?country=${lang}&direction=ASC&page=0&size=10`
        )
        .then(res => {
          console.log(res);
          this.wordlist = res.data.content;
          console.log("언어별 단어장");
        });
    }
  }
};
</script>

<style scoped>
.title {
  color: black;
}
</style>