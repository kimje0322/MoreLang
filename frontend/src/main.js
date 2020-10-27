import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueYoutube from "vue-youtube";
import vuetify from "./plugins/vuetify";
import axios from "axios";

Vue.config.productionTip = false;

Vue.use(VueYoutube);
Vue.prototype.$http = axios;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
