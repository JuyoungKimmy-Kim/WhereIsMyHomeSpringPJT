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
  components: { LoginModal, SignupModal, NavBar, UpdateModal },
  methods: {
    ...mapActions(userStore, ["getUserInfo"]),
    showLogin(){

      this.$refs.login_modal.user.userEmail = "";
      this.$refs.login_modal.user.userPassword = "";

      this.loginModal.show();
    },
    showSignUp(){
      this.loginModal.hide();

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
      console.log("showInfo");
      this.$refs.update_modal.userPassword = "";
      this.$refs.update_modal.userPassword2 = "";

      this.updateModal.show();
    }
  },
  computed:{
    ...mapState(userStore, ["result"]),
  },
  mounted() {
      this.loginModal = new Modal(document.getElementById("loginModal"));
      this.signUpModal = new Modal(document.getElementById("signUpModal"));
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
