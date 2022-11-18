<template>
  <div>
    <nav-bar @show-login="showLogin" @show-info="showInfo"></nav-bar>
    <router-view></router-view>
    <router-view name="Footer"></router-view>

    
    <login-modal @show-signUp="showSignUp" @close-this-modal="closeLogin"></login-modal>
    <signup-modal @close-this-modal="closeSignUp"></signup-modal>
    <update-modal></update-modal>


  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

import NavBar from "@/components/NavBar.vue"

import { Modal } from "bootstrap";
import LoginModal from "@/components/modal/LoginModal.vue"
import SignupModal from "@/components/modal/SignupModal.vue"
import UpdateModal from "@/components/modal/user/UpdateUserInfo.vue"


export default {
  data() {
    return {
      loginModal: null,
      signUpModal: null,
      updateModal: null,
    }
  },
  components: { LoginModal, SignupModal, NavBar, UpdateModal },
  methods: {
    showLogin(){
      this.loginModal.show();
    },
    closeLogin(){
      this.loginModal.hide();
    },
    showSignUp(){
      this.loginModal.hide();
      this.signUpModal.show();
    },
    closeSignUp(payload){
      if("SUCCESS" == payload.result){
        this.$alertify.success(payload.message);
        this.signUpModal.hide();
      }else{
        this.$alertify.error(payload.message);
      }
    },
    showInfo() {
      this.updateModal.show();
    }
  },
  mounted() {
      this.loginModal = new Modal(document.getElementById("loginModal"));
      this.signUpModal = new Modal(document.getElementById("signUpModal"));
      this.updateModal=new Modal(document.getElementById("updateModal"));
  },
}
</script>

<style>
</style>
