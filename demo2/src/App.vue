<template>
  <div>
    <nav-bar @show-login="showLogin"></nav-bar>
    <router-view></router-view>
    <router-view name="Footer"></router-view>

    
    <login-modal @show-join="showJoinModal"></login-modal>
    <join-modal></join-modal>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue"

import { Modal } from "bootstrap";
import LoginModal from "@/components/modal/LoginModal.vue"
import JoinModal from "@/components/modal/JoinModal.vue"

export default {
  data() {
    return {
      loginModal: null,
      joinModal: null,
    }
  },
  components: { LoginModal, JoinModal, NavBar },
  methods: {
    showLogin(){
      this.loginModal.show();
    },
    showJoinModal(){
      this.loginModal.hide();
      this.joinModal.show();
    }
  },
  mounted() {
      this.loginModal = new Modal(document.getElementById("loginModal"));
      this.joinModal = new Modal(document.getElementById("joinModal"));
  },
}
</script>

<style>
</style>
