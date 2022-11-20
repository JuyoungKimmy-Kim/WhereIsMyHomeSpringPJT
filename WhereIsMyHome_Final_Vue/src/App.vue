<template>
  <div>
    <nav-bar @show-login="showLogin" @show-info="showInfo"></nav-bar>
    <router-view></router-view>
    <router-view name="Footer"></router-view>

    
    <login-modal @show-signUp="showSignUp" @close-this-modal="closeLogin" ref="login_modal"></login-modal>
    <signup-modal @close-this-modal="closeSignUp" ref="signup_modal"></signup-modal>
    <update-modal @close-this-modal="closeInfo" ref="update_modal"></update-modal>


  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue"

import { Modal } from "bootstrap";
import LoginModal from "@/components/modal/LoginModal.vue"
import SignupModal from "@/components/modal/SignupModal.vue"
import UpdateModal from "@/components/modal/user/UpdateUserInfo.vue"

import { mapActions } from "vuex";

const userStore = "userStore";

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
    ...mapActions(userStore, ["getUserInfo"]),
    showLogin(){
      if(this.loginModal == null){
        this.loginModal = new Modal(document.getElementById("loginModal"));
      }

      this.$refs.login_modal.user.userEmail = "";
      this.$refs.login_modal.user.userPassword = "";

      this.loginModal.show();
    },
    showSignUp(){
      this.loginModal.hide();

      if(this.signUpModal == null){
        this.signUpModal = new Modal(document.getElementById("signUpModal"));
      }

      this.$refs.signup_modal.user = {
        userEmail: "",
        userName: "",
        userPassword: "",
        userPassword2: "",
      };

      this.$refs.signup_modal.valid = {
        email: "",
        name: "",
        password: "",
        password2: "",
      };


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

      this.$refs.update_modal.userPassword = "";
      this.$refs.update_modal.userPassword2 = "";
      this.$refs.update_modal.passwordColor = "";
      this.$refs.update_modal.passwordColor2 = "";

      this.updateModal.show();
    }
  },
  async mounted() {
    let token = sessionStorage.getItem("access-token");
    await this.getUserInfo(token);
  },
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
