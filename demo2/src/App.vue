<template>
  <div>
    <nav-bar></nav-bar>
    <router-view></router-view>
    <router-view name="Footer"></router-view>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
export default {
  components : {
    NavBar,
  }
}
</script>

<style>
@import "@/assets/css/material-kit.css";
</style>
