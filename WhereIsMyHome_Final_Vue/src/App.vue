<template>
  <div>
    <nav-bar @show-login="showLogin" @show-info="showInfo"></nav-bar>
    <router-view></router-view>
    <router-view name="Footer"></router-view>

    
    <login-modal @show-signUp="showSignUp" @close-this-modal="closeLogin" ref="login_modal"></login-modal>
    <signup-modal @close-this-modal="closeSignUp" ref="signup_modal"></signup-modal>
    <update-modal @close-this-modal="closeInfo" ref="update_modal"></update-modal>

    <spinner-ui v-if="isLoading"></spinner-ui>

  </div>
</template>

<script>
import http from "@/common/axios";

import NavBar from "@/components/NavBar.vue"
import SpinnerUi from '@/components/SpinnerUI.vue'


import { Modal } from "bootstrap";
import LoginModal from "@/components/modal/LoginModal.vue"
import SignupModal from "@/components/modal/SignupModal.vue"
import UpdateModal from "@/components/modal/user/UpdateUserInfo.vue"

import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  data() {
    return {
      loginModal: null,
      signUpModal: null,
      updateModal: null,
    }
  },
  components: { LoginModal, SignupModal, NavBar, UpdateModal, SpinnerUi },
  computed:{
    ...mapState(userStore, ["userInfo"]),
    ...mapState(["isLoading"]),
  },
  methods: {
    ...mapActions(userStore, ["getUserInfo"]),
    showLogin(){
      if(this.loginModal == null){
        this.loginModal = new Modal(document.getElementById("loginModal"));
      }

      this.$refs.login_modal.user.email = "";
      this.$refs.login_modal.user.password = "";

      this.loginModal.show();
    },
    showSignUp(){
      this.loginModal.hide();

      if(this.signUpModal == null){
        this.signUpModal = new Modal(document.getElementById("signUpModal"));
      }

      this.$refs.signup_modal.user = {
        email: "",
        name: "",
        password: "",
        password2: "",
        profileImageUrl: "",
        gugunCode: null,
      };

      this.$refs.signup_modal.sido = null;
      this.$refs.signup_modal.gugun = null;
      this.$refs.signup_modal.dong = null;
      this.$refs.signup_modal.valid = {
        name: "",
        email: "",
        password: "",
        password2: "",
      }

      if(this.userInfo != {}){
        this.$refs.signup_modal.user.email = this.userInfo.email;
        this.$refs.signup_modal.user.name = this.userInfo.name;
        this.$refs.signup_modal.user.profileImageUrl = this.userInfo.profileImageUrl;
      }

      this.signUpModal.show();
    },
    closeLogin(){
      this.loginModal.hide();
    },
    closeSignUp(){
      this.signUpModal.hide();
    },
    closeInfo(){
      this.updateModal.hide();
    },
    async showInfo() {
      if(this.updateModal == null){
        this.updateModal= new Modal(document.getElementById("updateModal"));
      }

      this.$refs.update_modal.password = "";
      this.$refs.update_modal.password2 = "";
      this.$refs.update_modal.passwordColor = "";
      this.$refs.update_modal.passwordColor2 = "";

      this.updateModal.show();
    }
  },
  async mounted() {
    let token = sessionStorage.getItem("access-token");
    await this.getUserInfo(token);
  },
  created(){
      http.interceptors.request.use(
          (config) => {
              this.$store.commit("SET_IS_LOADING", true);
              return config;
          },
          (error) => {
              this.$store.commit("SET_IS_LOADING", false);
              return Promise.reject(error);
          }
      );

      http.interceptors.response.use(
          (response) => {
            this.$store.commit("SET_IS_LOADING", false);
            return response;
          },
          (error) => {
            this.$store.commit("SET_IS_LOADING", false);
            return Promise.reject(error);
          }
      );
  }
}
</script>

<style>
html{
     /* 드래그 방지 */
    -ms-user-select: none;
    -moz-user-select: -moz-none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    user-select: none;
}
</style>
