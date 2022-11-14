import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

import Footer from "@/components/FooterPage.vue";
import MainPage from "@/components/MainPage.vue";
import Register from "@/components/RegisterForm.vue";
import Login from "@/components/LoginForm.vue";
import Map from "@/components/MapPage.vue";
import Contact from "@/components/ContactUs.vue"
import Search from "@/components/SearchForm.vue"

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
            path:'/register',
            components : {
                default : Register,
                Footer 
            }
        },
        {
            path:'/login',
            components : {
                default : Login,
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
            path:'/contact',
            components : {
                default : Contact,
                Footer 
            }
        },
        {
            path:'/search',
            components : {
                default : Search,
                Footer 
            }
        },
    ]

})