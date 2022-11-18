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
