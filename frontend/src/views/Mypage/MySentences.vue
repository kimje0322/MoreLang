<template>
  <v-container>
    <h3 class="title" style="color: white">스크랩한 문장</h3>
    <v-row no-gutters style="text-align:center; width:70%;">
      <v-col v-for="(sentence, i) in setences" :key="i">
        <v-card class="pa-2 selectLang" outlined tile style="position:relative">
          <!-- append-icon="mdi-lead-pencil" label="복습" -->
          <!-- <v-checkbox
            style="width: 27%;
                        position: absolute;
                        bottom: 7px;
                left: 5px;"
            color="success"
            value="success"
            hide-details
          ></v-checkbox> -->

          <!-- <v-icon small left>mdi-lead-pencil</v-icon>
              복습 -->

          <p class="my-auto">{{ sentence.sentence }}</p>
          <p class="my-auto">{{ sentence.memo }}</p>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "@/plugins/axios";

export default {
  data() {
    return {
      setences: []
    };
  },
  mounted() {
    axios
      .get("/user/myscrap?direction=ASC&page=0&size=10")
      .then(res => {
        console.log("여기요!");
        console.log(res.data.content);
        this.setences = res.data.content;
      });
  }
};
</script>

<style scoped>
.title {
  color: black;
}
</style>