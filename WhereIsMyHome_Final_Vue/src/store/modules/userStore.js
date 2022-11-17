// import router from "@/routers";
// import {signUp} from "@/common/user";


const userStore = {
    namespaced: true,
    state:{
        isLogin: false,
        userInfo: null,
    },
    mutations:{
        SET_LOGIN(state, payload) {
            state.isLogin = payload.isLogin;
        },
    },
    actions:{

    }
}

export default userStore;