<template>
  <v-app>
    <v-snackbar :color="alertColor" :value="showAlert" centered multi-line>
        {{ AlertMessage }}
      </v-snackbar>
    <router-view/>
      
    <!-- <Home /> -->
  </v-app>
</template>

<script>
// import HelloWorld from "./components/HelloWorld";
// import Home from "@/views/Home"
import axios from "axios";
import { mapState } from "vuex";
export default {
  name: "App",

  components: {
    // HelloWorld
    // Home
  },
  computed: mapState(["showAlert","AlertMessage", "alertColor"]),
  data: () => ({
    //
  }),
  created() {
    if (this.$store.state.accessToken) {
      if (this.$store.state.accessToken) {
        // 갱신 후에도 토큰이 유효하다면
        axios.defaults.headers.common.Authorization = `Bearer ${this.$store.state.accessToken}`;
      }
    }
  },

};
</script>
