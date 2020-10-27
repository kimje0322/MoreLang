import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Video from "../views/Video.vue";
import Channel from "../views/Channel.vue";
import Search from "../views/Search.vue"
import Mypage from "../views/Mypage/Mypage.vue"

// import Swiper from "../views/Swiper.vue";

Vue.use(VueRouter);

const routes = [{
        path: "/",
        name: "Home",
        component: Home
    },
    {
        path: "/channel",
        name: "Channel",
        component: Channel
    },
    {
        path: "/video",
        name: "Video",
        component: Video
    },
    {
        path: "/search",
        name: "Search",
        component: Search,
    },
    {

        path: "/mypage",
        name: "Mypage",
        component: Mypage
    },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;