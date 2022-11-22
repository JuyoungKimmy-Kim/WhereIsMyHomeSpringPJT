import {sidoList, gugunList, dongList} from '@/common/map.js';

const mapStore = {
    namespaced: true,
    state:{
        map: {
            sidoList : [],
            gugunList : [],
            dongList : [],
            
            sido:{},
            gugun:{},
            dong:{},
            
            property: [],
        },
    },
    mutations:{
        SET_SIDO_LIST(state, list) {
            state.map.sidoList=list;
        },
        SET_SIDO(state, payload) {
            state.map.sido = payload;
        },
        SET_GUGUN_LIST(state, list) {
            state.map.gugunList=list;
        },
        SET_GUGUN(state, payload) {
            state.map.gugun = payload;
        },
        SET_DONG_LIST(state, list) {
            state.map.dongList=list;
        },
        SET_DONG(state, payload) {
            state.map.dong = payload;
        },
        SET_PROPERTY (state, payload) {
            state.map.property=payload;
        }
    },
    actions:{
        async getSidoList({commit}){
            await sidoList(({data})=>{
                commit("SET_SIDO_LIST", data);
            },
            (error)=>{
                console.log(error);
            })
        },
        async getGugunList({commit}, sidoValue){
            await gugunList(sidoValue.code, ({data})=>{
                commit("SET_GUGUN_LIST", data);
                commit("SET_SIDO", sidoValue);
            },
            (error)=>{
                console.log(error);
            }
            )
        },
        async getDongList({commit, state}, gugunValue){
            await dongList(state.map.sido.code, gugunValue.code,
                ({data})=>{
                    commit("SET_DONG_LIST", data);
                    commit("SET_GUGUN", gugunValue);
                },
                (error)=>{
                    console.log(error)
                })
        },
    }
}


export default mapStore;