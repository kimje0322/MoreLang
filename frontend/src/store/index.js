import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    member: {},
    user: {},
    refreshToken: null,
    ServerURL: "https://morelang.gq/api",
    LocalURL: "http://localhost:8080",
    alertColor: null,
    AlertMessage: "",
    showAlert: false,
  },
  plugins: [createPersistedState()],
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setMember(state, member) {
      console.log(member);
      state.member = member;
    },
    setRefreshToken(state, refreshToken) {
      console.log(refreshToken);
      state.refreshToken = refreshToken;
    },
    setToken(state, Token) {
      state.member.accessToken = Token.accessToken;
      state.refreshToken = Token.refreshToken;
    },
    ShowAlert(state, payload) {
      state.AlertMessage = payload.msg;
      state.alertColor = payload.color;
      state.showAlert = payload.flag;
    },
  },
  actions: {
    Logout({ state, commit }) {
      state.user.disconnect().then(() => {
      });
      commit("setUser", null);
      commit("setMember", null);
      commit("setRefreshToken", null);
      commit("ShowAlert", {
        flag: true,
        msg: "로그아웃 되었습니다.",
        color: "info",
      });
      setTimeout(() => {
        commit("ShowAlert", {
          flag: false,
          msg: "",
        });
        window.location.reload();
      }, 1000);
    },
  },
});
