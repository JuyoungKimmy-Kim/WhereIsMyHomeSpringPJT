import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

import userStore from "@/store/modules/userStore.js";

export default new Vuex.Store({
    modules:{
        userStore
    },
    state:{
        isMapView: false,
    },
    mutations:{
        IS_MAP_VIEW(state, status){
            state.isMapView = status;
        }
    },
    actions:{

    },
})