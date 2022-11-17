import Vue from 'vue'
import App from './App.vue'
import router from './routers/router.js'


//css
require("@/assets/css/material-kit.css?v=3.0.4");

// bootstrap
import bootstrap from 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

// popper
import Popper from 'vue-popperjs';
import 'vue-popperjs/dist/vue-popper.css';

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router, bootstrap, Popper
}).$mount('#app')
