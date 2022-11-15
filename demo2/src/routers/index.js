import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

import Footer from "@/components/FooterPage.vue";
import MainPage from "@/components/MainPage.vue";
import Map from "@/components/MapPage.vue";
import Contact from "@/components/ContactUs.vue";
import Notice from "@/components/NoticePage.vue";

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
            path:'/notice',
            components: {
                default: Notice,
                Footer
            }
        },
        {
            path:'/contact',
            components : {
                default : Contact,
                Footer 
            }
        },
    ]

})