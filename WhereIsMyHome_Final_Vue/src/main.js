import Vue from 'vue'
import VueAlertify from "vue-alertify";

import App from './App.vue'
import router from '@/routers'
import store from '@/store'

Vue.use(VueAlertify);

//css
require("@/assets/css/material-kit.css?v=3.0.4")

// bootstrap
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'


Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router, store
}).$mount('#app')

window.Kakao.init("2a500c1bec06563369f0e683db14d573");
