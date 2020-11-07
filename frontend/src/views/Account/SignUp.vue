<template>
  <div>
    <Navbar />
    <v-container style="margin-top: 8%;">
      <v-row no-gutters style="justify-content: center;">
        <!-- color="transparent" -->
        <v-col cols="12" md="6">
          <!-- <v-card tile :dark="isDark" flat class="mx-auto" max-width="50vw"> -->
          <v-card tile class="pa-9" height="600px" align="center">
            <v-card-title
              class="py-2 align-baseline"
              style="justify-content: center; margin-bottom: 2%;"
            >
              <h1 class="mb-0" style="font-family: 'Do Hyeon', sans-serif;">
                Sign Up
              </h1>
              <!-- <v-btn class="ml-auto" rounded outlined @click="goBack()"
            >뒤로가기</v-btn
          > -->
            </v-card-title>
            <!-- <div v-if="step === 'main'">
              <v-card-text>
                <v-btn tile block color="info" @click="step = 'e'"
                  >이메일로 회원가입</v-btn
                >
              </v-card-text>
            </div> -->
            <ByEmail />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import ByEmail from "@/components/SignUp/ByEmail.vue";
import { mapState } from "vuex";
import Navbar from "@/components/Navbar";

export default {
  name: "SignUp",
  components: {
    ByEmail,
    Navbar
  },
  data() {
    return {
      step: "main"
    };
  },
  computed: mapState(["isDark"]),
  methods: {
    goBack() {
      if (this.step === "main") {
        this.$router.go(-1);
      } else {
        this.step = "main";
      }
    }
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
</style>
