<template>
  <div>
    <nav-bar @show-login="showLogin" @show-info="showInfo"></nav-bar>
    <router-view></router-view>
    <router-view name="Footer"></router-view>

    
    <login-modal @show-join="showJoinModal"></login-modal>
    <join-modal></join-modal>
    <update-modal></update-modal>


  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue"

import { Modal } from "bootstrap";
import LoginModal from "@/components/modal/LoginModal.vue"
import JoinModal from "@/components/modal/JoinModal.vue"
import UpdateModal from "@/components/modal/user/UpdateUserInfo.vue"

export default {
  data() {
    return {
      loginModal: null,
      joinModal: null,
      updateModal: null,
    }
  },
  components: { LoginModal, JoinModal, NavBar, UpdateModal },
  methods: {
    showLogin(){
      this.loginModal.show();
    },
    showJoinModal(){
      this.loginModal.hide();
      this.joinModal.show();
    },
    showInfo() {
      this.updateModal.show();
    }
  },
  mounted() {
      this.loginModal = new Modal(document.getElementById("loginModal"));
      this.joinModal = new Modal(document.getElementById("joinModal"));
      this.updateModal=new Modal(document.getElementById("updateModal"));
  },
}
</script>

<style>
</style>
