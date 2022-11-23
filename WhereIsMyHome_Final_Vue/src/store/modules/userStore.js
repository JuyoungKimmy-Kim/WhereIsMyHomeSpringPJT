import jwtDecode from "jwt-decode";
import { login, signUp, modifyInfo, validateToken, logout, createAccessToken } from "@/common/user";

import util from '@/common/utils';

const userStore = {
    namespaced: true,
    state:{
        // alertify를 위함
        userResult:{
            status: "",
            message: "",
        },
        isLogin: false,
        userInfo: {},
        validToken: null,
    },
    mutations:{
        SET_IS_LOGIN(state, payload) {
            state.isLogin = payload;
        },
        CLEAR_RESULT_MESSAGE(state){
            state.userResult = {}
        },
        SET_RESULT_MESSAGE_SUCCESS(state, payload){
            state.userResult = {
                status: "SUCCESS",
                message: payload
            }
        },
        SET_RESULT_MESSAGE_FAIL(state, payload){
            state.userResult = {
                status: "FAIL",
                message: payload
            }
        },
        SET_VALID_TOKEN(state, payload){
            state.validToken = payload;
        },
        CLEAR_USER_INFO(state){
            state.userInfo = {};
        },
        SET_USER_INFO(state, payload){
            state.userInfo = {
                seq:payload.seq,
                name:payload.name,
                email:payload.email,
                profileImageUrl:payload.profileImageUrl,
                gugunCode: payload.gugunCode
            };   
            
            if(payload.profileImageUrl == "/no_img.png"){
                state.userInfo.profileImageUrl = require("@/assets/img/profile/no_img.png");
            }

            if(payload.regDt != null){
                state.userInfo.regDate = util.makeDateStr(payload.regDt.date.year, payload.regDt.date.month, payload.regDt.date.day, '/');
            }

            if(payload.role != null){
                state.userInfo.role = payload.role;
            }
            
        }
    },
    actions:{
        async signUpUser({commit}, user){
            await signUp(user,
                ({data})=>{ // SUCCESS
                    if(data.result == "SUCCESS"){
                        commit("SET_RESULT_MESSAGE_SUCCESS", "회원가입에 성공하셨습니다!!");
                    }
                },
                (error)=>{ // FAIL
                    console.log(error);
                });
        },
        async loginConfirm({commit}, user){
            await login(user, ({data}) => {
                    console.log("loginConfirm >>");
                    console.dir(data);
                    if(data.result == "SUCCESS"){
                        commit("SET_IS_LOGIN", true);
                        commit("SET_VALID_TOKEN", true);
                        commit("SET_USER_INFO", data);
                        commit("SET_RESULT_MESSAGE_SUCCESS", "로그인에 성공하였습니다!!");
                        sessionStorage.setItem("access-token", data.accessToken);
                        sessionStorage.setItem("refresh-token", data.refreshToken);
                    }else{
                        commit("SET_IS_LOGIN", false);
                        commit("SET_VALID_TOKEN", false);
                        commit("SET_RESULT_MESSAGE_FAIL", "");
                    }
                }),(error) => {
                    console.log(error);
                }
        },
        async logoutConfirm({commit, state}){
            const params = {
                email: state.userInfo.email
            }

            await logout(params,
                    ({data})=>{
                        if(data.result == "SUCCESS"){
                            commit("SET_IS_LOGIN", false);
                            commit("SET_VALID_TOKEN", false);
                            commit("CLEAR_USER_INFO");
                            commit("SET_RESULT_MESSAGE_SUCCESS", "로그아웃이 완료되었습니다.");
                        }else{
                            console.log("유저 정보 없음!!!");
                        }
                    },
                    (error)=>{
                        console.log(error);
                    }
                )
        },
        async getUserInfo({commit, dispatch}, token){
            if(token == null){
                console.log("아직 로그인하지 않음");
                return;
            }

            const decodedToken = jwtDecode(token);
            const params = {
                email: decodedToken.email
            };
            
            await validateToken(params, 
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        commit("SET_USER_INFO", data);
                        commit("SET_RESULT_MESSAGE_SUCCESS","");
                    }else{
                        commit("CLEAR_USER_INFO");
                        commit("SET_IS_LOGIN", false);
                        commit("SET_VALID_TOKEN", false);
                        commit("SET_RESULT_MESSAGE_FAIL", "토큰이 만료되었습니다.");
                    }
                },
                async (error)=>{
                    console.log("AccessToken을 찾을 수 없습니다. >> " + error);
                    commit("SET_VALID_TOKEN", false);
                    await dispatch("generateAccessToken");
                });
        },
        async generateAccessToken({commit, state}){
            console.log("= 토큰 재발급 = ");
            await createAccessToken(
                state.userInfo,
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        sessionStorage.setItem("access-token", data.accessToken);
                        commit("SET_VALID_TOKEN", true);
                        this.getUserInfo(data.accessToken);
                    }
                },
                async (error)=>{
                    if(error.response.status == 401){
                        console.log("AccessToken 발급 실패");

                        
                        const params = {
                            email: state.userInfo.email
                        };

                        await logout(params,
                            ({data})=>{
                                if(data.result == "SUCCESS") console.log("리프레시 토큰 제거 성공");

                                commit("SET_RESULT_MESSAGE_FAIL", "토큰이 만료되었습니다! 다시 로그인해 주세요.");
                                commit("SET_IS_LOGIN", false);
                                commit("SET_VALID_TOKEN", false);
                            },
                            (error)=>{
                                console.log(error);
                                commit("SET_IS_LOGIN", false);
                            }
                        )
                        commit("CLEAR_USER_INFO");
                    }
                }
            )
        },
        async updateInfo({commit}, user){
            await modifyInfo(user, 
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        commit("SET_RESULT_MESSAGE_SUCCESS", "회원정보 수정이 완료되었습니다.");
                        commit("SET_USER_INFO", data.user);
                    }else{
                        commit("SET_RESULT_MESSAGE_FAIL", "회원정보 수정에 실패하였습니다.");
                    }
                },
                (error)=>{
                    console.log(error);
                })
        }
    },
}

export default userStore;