<template>
<div id="topThreeDiv" class="container position-sticky z-index-sticky top-0">
  <div id="topThreeDiv" class="row">
    <div id="topThreeDiv" class="col-12">
      <nav class="navbar navbar-expand-lg  blur border-radius-xl top-0 z-index-fixed shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
        <div class="container-fluid px-0">
          <router-link to="/" class="nav-link ps-2 d-flex cursor-pointer align-items-center" rel="tooltip" data-placement="bottom">
            <i class="material-icons me-2">home</i>
            JYMS
          </router-link>
          <button
            class="navbar-toggler shadow-none ms-2"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navigation"
            aria-controls="navigation"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon mt-2">
              <span class="navbar-toggler-bar bar1"></span>
              <span class="navbar-toggler-bar bar2"></span>
              <span class="navbar-toggler-bar bar3"></span>
            </span>
          </button>
          <div
            class="collapse navbar-collapse w-100 pt-3 pb-2 py-lg-0 ms-lg-12 ps-lg-5"
            id="navigation"
          >
            <ul class="navbar-nav navbar-nav-hover ms-auto">
              <li class="nav-item">
                <router-link
                  to="/map"
                  class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                >
                  <i class="material-icons opacity-6 me-2 text-md">map</i>
                  Map
                </router-link>
              </li>
              <li class="nav-item">
                <router-link
                  to="/notice"
                  class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                >
                  <i class="material-icons opacity-6 me-2 text-md">library_books</i>
                  Notice
                </router-link>
              </li>
              <li class="nav-item">
                <a class="nav-link ps-2 d-flex cursor-pointer align-items-center" v-show="$store.state.login.isLogin"  @click="showLogin">
                  <i class="material-icons opacity-6 me-2 text-md">people</i>
                  Login
                </a>
              </li>
              <li class="nav-item">
                <router-link
                  to="/contact"
                  class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                >
                  <i class="material-icons opacity-6 me-2 text-md">contact_mail</i>
                  Contact
                </router-link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  </div>
</div>
</template>

<script>
export default{
  methods: {
    showLogin(){
      this.$emit("show-login");
    }
  },
}
</script>

<style scoped>
#topThreeDiv {
  height:0px;
}
</style>
