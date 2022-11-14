<template>
  <div id="wrapper">
    <nav-bar></nav-bar>
    
    <div class="clearfix"></div>

    <router-view></router-view>
    <router-view name="Footer"></router-view>
    
    <a data-scroll href="#wrapper" class="go-up"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>

    <div id="preloader">
        <div id="status">
            <div class="status-mes"></div>
        </div>
    </div>
    
    <div id="scriptForm"></div>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';

export default {
  components: {
    NavBar, 
  }
}
</script>

<style>
</style>
