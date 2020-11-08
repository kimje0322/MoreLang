import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueYoutube from "vue-youtube";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import VueNumericInput from "vue-numeric-input";
import VueRecord from "@codekraft-studio/vue-record";

Vue.config.productionTip = false;

Vue.use(VueYoutube);
Vue.use(VueNumericInput);
Vue.use(VueRecord);
Vue.prototype.$http = axios;

new Vue({
    router,
    store,
    vuetify,
    render: (h) => h(App),
}).$mount("#app");

axios.interceptors.response.use(
    function(response) {
        return response;
    },
    async function(error) {
        console.log(error);
        if (error.response.state === 401 && store.state.refreshToken) {
            const refreshToken = store.state.refreshToken;
            const res = await axios.post(
                `${store.state.ServerURL}/guest/refresh`,
                null, {
                    headers: {
                        refreshToken: `Bearer ${refreshToken}`,
                    },
                }
            );
            if (res.data.success === true) {
                store.commit("setTOKEN", {
                    accessToken: res.data.accessToken,
                    refreshToken,
                });
                axios.defaults.headers.common.accessToken = res.data.accessToken;
                error.config.headers[
                    "Authorization"
                ] = `Bearer ${res.data.accessToken}`;
                return axios(error.config);
            } else {
                store.commit("Logout");
                store.commit("ShowAlert", {
                    flag: true,
                    msg: "리프레시 토큰이 만료되었습니다. 다시 로그인하세요",
                    color: "error",
                });
                setTimeout(() => {
                    store.commit("ShowAlert", {
                        flag: false,
                        msg: "",
                    });
                    router.push("/");
                }, 1000);
            }
        }
    }
);