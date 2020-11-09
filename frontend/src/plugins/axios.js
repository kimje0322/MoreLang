import axios from 'axios'

const instance = axios.create({
    baseURL: 'https://morelang.gq/api',
    timeout: 1000
  });

/*
    1. 요청 인터셉터
    2개의 콜백 함수를 받습니다.
*/
instance.interceptors.request.use(
    function (config) {
        // 요청 성공 직전 호출됩니다.
        // axios 설정값을 넣습니다. (사용자 정의 설정도 추가 가능)
        return config;
    }, 
    function (error) {
        // 요청 에러 직전 호출됩니다.
        return Promise.reject(error);
    }
);

/*
    2. 응답 인터셉터
    2개의 콜백 함수를 받습니다.
*/
instance.interceptors.response.use(
    function (response) {
    /*
        http status가 200인 경우
        응답 성공 직전 호출됩니다. 
        .then() 으로 이어집니다.
    */
        return response;
    },

    function (error) {
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