import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state:{
        login:{
            // navbar
            isLogin: false,

            userName: "",
            userProfileImageUrl: "",
        },
        isMapView: false,
    },
    mutations:{
        SET_LOGIN(state, payload) {
            state.login.isLogin = payload.isLogin;
            state.login.userName = payload.userName;
            state.login.userProfileImageUrl = payload.userProfileImageUrl;
        },
        IS_MAP_VIEW(state, status){
            state.isMapView = status;
        }
    },
    actions:{

    },
    getters:{
        isLogin(state){
            return state.login.isLogin;
        },
    }
})