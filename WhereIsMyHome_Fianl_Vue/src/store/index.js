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

        map: {
            sidoList : [],
            gugunList : [],
            dongList : [],
            sidoCode:'',
            gugunCode: '',
            dongCode:'',
            sidoName: '',
            gugunName: '',
            dongName:'',
            property: [],
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
        },
        SET_SIDO_LIST(state, list) {
            state.map.sidoList=list;
        },
        SET_SIDO_CODE(state, payload) {
            state.map.sidoCode=payload.code;
            state.map.sidoName=payload.name;
        },
        SET_GUGUN_LIST(state, list) {
            state.map.gugunList=list;
        },
        SET_GUGUN_CODE(state, payload) {
            state.map.gugunCode=payload.code;
            state.map.gugunName=payload.name;
        },
        SET_DONG_LIST(state, list) {
            state.map.dongList=list;
        },
        SET_DONG_CODE(state, payload) {
            state.map.dongCode=payload.code;
            state.map.dongName=payload.name;
        },
        SET_PROPERTY (state, payload) {
            state.map.property=payload;
        }
        
    },
    actions:{

    },
    getters:{
        isLogin(state){
            return state.login.isLogin;
        },
        getSidoList : function (state) {
            return state.map.sidoList;
        },
        getSidoCode : function (state) {
            return state.map.sidoCode;
        },
        getSidoName : function (state) {
            return state.map.sidoName;
        },
        getGugunList : function (state) {
            return state.map.gugunList;
        },
        getGugunCode : function (state) {
            return state.map.gugunCode;
        },
        getGugunName : function (state) {
            return state.map.gugunName;
        },
        getDongList : function (state) {
            return state.map.dongList;
        },
        getDongCode : function (state) {
            return state.map.dongCode;
        },
        getDongName : function (state) {
            return state.map.dongName;
        },
        getProperty : function (state) {
            return state.map.property;
        }
    }
})