import jwtDecode from "jwt-decode";
import { signup, login, findByEmail, logout, tokenRegeneration, update } from "@/common/user";


const userStore = {
    namespaced: true,
    state:{
        userResult:{
            status: "",
            message: "",
        },
        isLogin: false,
        userInfo: {
            userSeq:"",
            userName:"",
            userEmail:"",
            userProfileImgUrl:"",
            regDt:"",
            userClsf:"",
        },
        validToken: null,
    },
    mutations:{
        SET_IS_LOGIN(state, payload) {
            state.isLogin = payload;
        },
        SET_RESULT_MESSAGE(state, payload){
            if(payload == null){
                state.userResult = {};
            }else{
                state.userResult = {
                    status: payload.status,
                    message: payload.message
                }
            }
        },
        SET_VALID_TOKEN(state, payload){
            state.validToken = payload;
        },
        SET_USER_INFO(state, payload){
            if(payload == null){
                state.userInfo = {
                    userName:"",
                    userEmail:"",
                    userProfileImgUrl:"",
                    regDt:"",
                    userClsf:"",
                };   
            }else{
                state.userInfo = payload;
            }
        }
    },
    actions:{
        async signUp({commit}, user){
            await signup(user,
                ({data})=>{ // SUCCESS
                    commit("SET_RESULT_MESSAGE", {status: data.result, message: data.message});
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
                        commit("SET_RESULT_MESSAGE", {status: data.result, message: data.message});
                        sessionStorage.setItem("access-token", data.accessToken);
                        sessionStorage.setItem("refresh-token", data.refreshToken);
                    }else{
                        commit("SET_IS_LOGIN", false);
                        commit("SET_VALID_TOKEN", false);
                        commit("SET_RESULT_MESSAGE",  {status: data.result, message: data.message});
                    }
                }),(error) => {
                    console.log(error);
                }
        },
        async logoutConfirm({commit, state}){
            await logout(state.userInfo.userEmail,
                    ({data})=>{
                        if(data.result == "SUCCESS"){
                            commit("SET_IS_LOGIN", false);
                            commit("SET_VALID_TOKEN", false);
                            commit("SET_RESULT_MESSAGE", {status: data.result, message: data.message});
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
            const decodedToken = jwtDecode(token);
            await findByEmail(decodedToken.userEmail, 
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        commit("SET_RESULT_MESSAGE", {status: "SUCCESS", message: "토큰 검증 완료"});
                        commit("SET_USER_INFO", data.userInfo);
                    }else{
                        commit("SET_USER_INFO", null);
                        commit("SET_IS_LOGIN", false);
                        commit("SET_VALID_TOKEN", false);
                        
                        console.log("유저 정보 없음!!!!");
                    }
                },
                async (error)=>{
                    console.log("토큰만료 >> " + error);
                    commit("SET_VALID_TOKEN", false);
                    await dispatch("accessTokenRegeneration");
                });
        },
        async accessTokenRegeneration({commit, state}){
            console.log("= 토큰 재발급 = ");
            await tokenRegeneration(
                state.userInfo,
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        sessionStorage.setItem("access-token", data.accessToken);
                        commit("SET_RESULT_MESSAGE", {status: "Fail", message: "다시 시도 해주세요."});
                        commit("SET_VALID_TOKEN", true);
                    }
                },
                async (error)=>{
                    if(error.response.status == 401){
                        console.log("AccessToken 갱신 실패");
                        await logout(state.userInfo.userEmail,
                            ({data})=>{
                                if(data.result == "SUCCESS") console.log("리프레시 토큰 제거 성공");

                                commit("SET_RESULT_MESSAGE", {status: "Fail", message: "RefreshToken 기간 만료!!! 다시 로그인해 주세요."});
                                commit("SET_IS_LOGIN", false);
                                commit("SET_VALID_TOKEN", false);
                                commit("SET_USER_INFO", null);
                            },
                            (error)=>{
                                console.log(error);
                                commit("SET_IS_LOGIN", false);
                                commit("SET_USER_INFO", null);
                            }
                        )
                    }
                }
            )
        },
        async passwordUpdate({commit}, user){
            await update(user, 
                ({data})=>{
                    commit("SET_USER_INFO", data.userInfo);
                    commit("SET_RESULT_MESSAGE", {status: data.result, message: data.message});
                },
                (error)=>{
                    console.log(error);
                })
        }
    },
}

export default userStore;