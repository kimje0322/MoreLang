import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Video from "../views/Video.vue";
import Channel from "../views/Channel";
import Mypage from "../views/Mypage/Mypage";

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
        path: "/mypage",
        name: "Mypage",
        component: Mypage
    },
    {
        path: "/video",
        name: "Video",
        component: Video
    }
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;