<template>
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalForm" aria-hidden="true">
<div class="modal-dialog modal-danger modal-dialog-centered modal-" role="document">
    <div class="modal-content">
    <div class="modal-body p-0">
        <div class="card bg-gray-200 shadow border-0 mb-0">
        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-info shadow-info border-radius-lg py-3 pe-1 text-center py-4">
                <h4 class="font-weight-bolder text-white mt-1 mb-0">로그인</h4>
                <p class="mb-1 text-sm text-white">다시 돌아온걸 환영합니다.</p>
                <a @click="kakaoLogin()" id="cusom-login-btn" class="cursor-pointer">
                    <img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="222">
                </a>  
            </div>
        </div>
        <div class="card-body">
            <form role="form text-start">
            <div class="input-group input-group-static mb-4">
                <label>이메일</label>
                <input type="email" class="form-control" v-model="user.email" @keyup.enter="confirm" placeholder="john@email.com">
            </div>
            <div class="input-group input-group-static mb-4">
                <label>비밀번호</label>
                <input type="password" class="form-control" v-model="user.password" @keyup.enter="confirm" placeholder="•••••••••••••">
            </div>

            <div class="form-check form-switch d-flex align-items-center">
                <input class="form-check-input" type="checkbox" id="rememberMe" checked="">
                <label class="form-check-label mb-0 ms-3" for="rememberMe">내 정보 기억하기</label>
            </div>

            <div class="text-center">
                <button type="button" class="btn bg-gradient-info mt-4 mb-0" @click="confirm">로그인</button>
            </div>
            </form>
        </div>
        <div class="card-footer text-center pt-0">
            <p class="mb-4 text-sm mx-auto">
            계정이 없으신가요?
            <a href="javascript:;" class="text-info text-gradient font-weight-bold" @click="showRegister">회원가입</a>
            </p>
        </div>
        </div>
    </div>
    </div>
</div>
</div>
</template>

<script>
import http from '@/common/axios'
import { mapState, mapActions } from "vuex";
const userStore = "userStore";

export default {
    data() {
        return {
            user:{
                email: "",
                password: ""
            }
        }
    },
    computed:{
        ...mapState(userStore, ["isLogin", "userResult"]),
    },
    methods: {
        ...mapActions(userStore, ["loginConfirm"]),
        showRegister(){
            this.$emit("show-signUp");
        },
        async confirm(){
            await this.loginConfirm(this.user);
            
            if(this.isLogin){
                this.$alertify.success(this.userResult.message);
                this.$emit("close-this-modal");
            }else{
                this.$alertify.error(this.userResult.message);
            }
        },
        // kakaoLogin(){
        // },
        kakaoLogin(){
            window.Kakao.API.request({
                url: '/v2/user/me',
            })
            .then((response)=>{
                let userEmail = response.kakao_account.email;
                let userName = response.properties.nickname;
                let userProfileimgUrl = response.properties.thumbnail_image;

                let kakaoInfo = {
                    email: userEmail,
                    name: userName,
                    profileImageUrl: userProfileimgUrl
                }
                this.validateServer(kakaoInfo);
            })
            .catch((error)=>{
                console.log(error);
            })
        },
        async validateServer(kakaoInfo){
            await http.post("/login/kakao", kakaoInfo)
                .then(({data})=>{
                    if(data.result == "SUCCESS"){
                        
                        this.$store.commit("userStore/SET_IS_LOGIN", true);
                        this.$store.commit("userStore/SET_VALID_TOKEN", true);
                        this.$store.commit("userStore/SET_USER_INFO", data);

                        this.$alertify.success("로그인에 성공하였습니다!!");
                        sessionStorage.setItem("access-token", data.accessToken);
                        sessionStorage.setItem("refresh-token", data.refreshToken);

                        this.$emit("close-this-modal");
                    }else{
                        this.$store.commit("userStore/SET_USER_INFO", kakaoInfo);
                        this.$emit("show-signUp");
                    }
                })
                .catch(error=> console.log(error))
        }
    }
}
</script>

<style>

</style>