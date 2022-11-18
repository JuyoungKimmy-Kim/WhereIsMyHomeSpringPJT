// import router from "@/routers";
import {login} from "@/common/user";


const userStore = {
    namespaced: true,
    state:{
        reusltMessage: "",
        isLogin: false,
        userInfo: null,
        validToken: null,
    },
    mutations:{
        SET_IS_LOGIN(state, payload) {
            state.isLogin = payload;
        },
        SET_RESULT_MESSAGE(state, payload){
            state.reusltMessage = payload;
        },
        SET_VALID_TOKEN(state, payload){
            state.validToken = payload;
        }
    },
    actions:{
        async loginConfirm({commit}, user){
            await login(user, ({data}) => {
                    console.log("loginConfirm >>");
                    console.dir(data);
                    if(data.result == "SUCCESS"){
                        commit("SET_IS_LOGIN", true);
                        commit("SET_RESULT_MESSAGE", data.message);
                        commit("SET_VALID_TOKEN", true);
                        sessionStorage.setItem("access-token", data.accessToken);
                        sessionStorage.setItem("refresh-token", data.refreshToken);
                    }else{
                        commit("SET_IS_LOGIN", false);
                        commit("SET_RESULT_MESSAGE", data.message);
                        commit("SET_VALID_TOKEN", false);
                    }
                }),(error) => {
                    console.log(error);
                }
        }
    },
}

export default userStore;