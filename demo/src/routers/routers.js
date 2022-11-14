import Vue from 'vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);

import MainPage from "@/components/MainPage.vue";
import Footer from "@/components/FooterPage.vue";
import Map from "@/components/MapPage.vue";
import MyPage from "@/components/MyPage.vue";
import RegistProperty from "@/components/RegistPropertyPage.vue";

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
            },
            children:[
                {
                    path : 'registproperty',
                    components: {
                        default : RegistProperty
                    }
                }
            ]
        },
    ]

});

