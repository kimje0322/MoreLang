import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);
const getDefaultState = () => {
    return {
//       ServerURL: "https://morelang.gq/api",
        ServerURL: "http://localhost:8080",
        nickname: null,
        username: null,
        alertColor: null,
        AlertMessage: "",
        showAlert: false,
        searchWord: null,
        payReady: null,
        formerLink: null,
        oauthToken: null
    };
};
export default new Vuex.Store({
    state: getDefaultState(),
    plugins: [createPersistedState()],
    mutations: {
        LOGIN(state, payload) {
            state.nickname = payload.nickname;
            state.username = payload.username;
        },
        LOGOUT(state) {
            Object.assign(state, getDefaultState());
        },
        TOKEN(state, payload) {
            const { accessToken, refreshToken } = payload;
            state.accessToken = accessToken;
            state.refreshToken = refreshToken;
            // 앞으로의 모든 HTTP 요청 헤더에 Auth 추가
            axios.defaults.headers.common.Authorization = `Bearer ${state.accessToken}`;
        },
        Thumbnail(state, payload) {
            state.userThumbnail = `${state.ImgURL}${payload}`;
        },
        FormerLink(state, payload) {
            state.formerLink = payload;
        },
        setRefreshToken(state, refreshToken) {
            console.log(refreshToken);
            state.refreshToken = refreshToken;
        },
        setOauthToken(state, oauthToken) {
            state.token = oauthToken;
        },
        ShowAlert(state, payload) {
            state.AlertMessage = payload.msg;
            state.alertColor = payload.color;
            state.showAlert = payload.flag;
        },
        setPayReady(state, payReady) {
          state.payReady = payReady;
        },
    },
    actions: {
        async LOGIN({ commit, dispatch }, loginData) {
          axios.defaults.headers.common.Authorization = ``;
          const res = await axios.post(
            `${this.state.ServerURL}/newuser/login`,
            null,
            {
              params: {
                ...loginData
              }
            }
          );
          if (res.data === "") {
            await Promise.reject("비밀번호가 다릅니다");
          } else {
            commit("LOGIN", {
              nickname: res.data.nickname,
              username: loginData.username
            });
            commit("TOKEN", {
              accessToken: res.data.accessToken,
              refreshToken: res.data.refreshToken
            });
            dispatch("Thumbnail", loginData.username);
          }
        },
        async LOGOUT({ commit }) {
          await axios.post(`${this.state.ServerURL}/user/out`);
          commit("LOGOUT");
          commit("ShowAlert", {
            flag: true,
            msg: "로그아웃 되었습니다.",
            color: "info"
          });
          setTimeout(() => {
            commit("ShowAlert", {
              flag: false,
              msg: ""
            });
            window.location.reload();
          }, 1000);
        },
        async SIGNUP({ dispatch }, signupData) {
          var memberData = {
            userid : signupData.username,
            password : signupData.password,
            name : signupData.nickname
          }
          console.log(memberData);
          const { data } = await axios.post(
            `${this.state.ServerURL}/newuser/add`,
            memberData
          );
          if (data.success === true) {
            const loginData = {
              username: signupData.username,
              password: signupData.password
            };
            dispatch("LOGIN", loginData);
          } else {
            console.log("자동로그인중 오류 발생");
          }
        },
        async SocialLogin({ commit, dispatch }, data){
            commit("LOGIN", {
                nickname: data.nickname,
                username: data.userid
            });
            commit("TOKEN", {
                accessToken: data.accessToken,
                refreshToken: data.refreshToken
            });
            dispatch("Thumbnail", data.userid);
        },
        async Thumbnail({ commit }, payload) {
          const res = await axios.get(
            `${this.state.ServerURL}/newuser/get_profile_img`,
            {
              params: {
                username: payload
              }
            }
          );
          commit("Thumbnail", res.data);
          window.location.reload();
        }
      },
    modules:{}
});