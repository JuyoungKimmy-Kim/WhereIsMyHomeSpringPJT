import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

import Footer from "@/components/FooterPage.vue";
import MainPage from "@/components/MainPage.vue";
import Map from "@/components/map/MapPage.vue";
import Board from "@/components/board/BoardMain.vue";
import UserManage from "@/components/UserManage.vue";
import WishList from "@/components/WishList.vue";

export default new VueRouter({
    routes : [
        {
            path:'/',
            components : {
                default : MainPage,
                Footer 
            }
        },
        {
            path:'/map',
            components : {
                default : Map,
            }
        },
        {
            path:'/boards',
            components: {
                default: Board,
                Footer
            }
        },
        {
            path:'/manage',
            components: {
                default: UserManage,
                Footer
            }
        },
        {
            path:'/wishlist',
            components: {
                default: WishList,
                Footer
            }
        },
    ]

})