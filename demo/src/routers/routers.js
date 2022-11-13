import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

import MainPage from "@/components/MainPage.vue";
import Footer from "@/components/FooterPage.vue";
import Map from "@/components/MapPage.vue";
import MyPage from "@/components/MyPage.vue";
import RegistProperty from "@/components/RegistPropertyPage.vue";
import LeftSideBar from "@/components/LeftSideBar.vue";
import HomeContent from "@/components/HomeContent.vue";
import UserProfile from "@/components/UserProfile.vue";

export default new VueRouter({
    routes : [
        {
            path:'/',
            components : {
                default : MainPage,
                Footer 
            },
        },
        {
            path : '/map',
            components : {
                default : Map
            }
        },
        {
            path : '/mypage',
            components : {
                default : MyPage,
                Footer
            }
        },
        {
            path : '/registproperty',
            components :{
                default : RegistProperty,
                Footer
            }
        },
        {
            path : '/leftsidebar',
            components : {
                default : LeftSideBar,
                Footer
            }
        },
        {
            path : '/homecontent',
            components : {
                default : HomeContent,
                Footer
            }
        },
        {
            path : '/userprofile',
            components : {
                default : UserProfile,
                Footer
            }
        },
    ]

});

