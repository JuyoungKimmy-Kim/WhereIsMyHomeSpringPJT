<template>
  <div>
    <nav-bar @show-login="showLogin"></nav-bar>
    <router-view></router-view>
    <router-view name="Footer"></router-view>

    
    <login-modal @show-register="showRegister"></login-modal>
    <register-modal></register-modal>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue"

import { Modal } from "bootstrap";
import LoginModal from "@/components/modal/LoginModal.vue"
import RegisterModal from "@/components/modal/RegisterModal.vue"

export default {
  data() {
    return {
      loginModal: null,
      registerModal: null,
    }
  },
  components: { LoginModal, RegisterModal, NavBar },
  methods: {
    showLogin(){
      this.loginModal.show();
    },
    showRegister(){
      this.loginModal.hide();
      this.registerModal.show();
    }
  },
  mounted() {
      this.loginModal = new Modal(document.getElementById("loginModal"));
      this.registerModal = new Modal(document.getElementById("registerModal"));
  },
}
</script>

<style>
</style>
