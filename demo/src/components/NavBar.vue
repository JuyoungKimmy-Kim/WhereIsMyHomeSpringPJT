<template>
  <!-- START SECTION HEADINGS -->
  <!-- Header Container
        ================================================== -->
  <header id="header-container">
    <!-- Header -->
    <div id="header">
      <div class="container container-header">
        <!-- Left Side Content -->
        <div class="left-side">
          <!-- Logo -->
          <div id="logo">
            <router-link to ="/"><img src="images/logo-gray.svg" alt="" /></router-link>
          </div>
          <!-- Mobile Navigation -->
          <div class="mmenu-trigger">
            <button class="hamburger hamburger--collapse" type="button">
              <span class="hamburger-box">
                <span class="hamburger-inner"></span>
              </span>
            </button>
          </div>
          <!-- Main Navigation -->
          <nav id="navigation" class="style-1">
            <ul id="responsive">
              <li><router-link to="/map">지도</router-link></li>
              <li><router-link to="/mypage/registproperty">매물 등록</router-link></li>
              <li><router-link to = "/mypage">마이 페이지</router-link></li>
              <li><router-link to = "/leftsidebar">Side Bar</router-link></li>
              <li><router-link to = "/homecontent">Home Content</router-link></li>
              <li><router-link to = "/userprofile">User Profile</router-link></li>
              <li class="d-none d-xl-none d-block d-lg-block">
                <a href="login.html">Login</a>
              </li>
              <li class="d-none d-xl-none d-block d-lg-block">
                <a href="register.html">Register</a>
              </li>
              <li
                class="
                  d-none d-xl-none d-block d-lg-block
                  mt-5
                  pb-4
                  ml-5
                  border-bottom-0
                "
              >
                <a
                  href="add-property.html"
                  class="button border btn-lg btn-block text-center"
                  >Add Listing<i class="fas fa-laptop-house ml-2"></i
                ></a>
              </li>
            </ul>
          </nav>
          <!-- Main Navigation / End -->
        </div>
        <!-- Left Side Content / End -->

        <!-- Right Side Content / End -->
        <div class="right-side d-none d-none d-lg-none d-xl-flex">
          <!-- Header Widget -->
          <div class="header-widget">
            <router-link to="/mypage/registproperty" class="button border"
              >매물 등록<i class="fas fa-laptop-house ml-2"></i
            ></router-link>
          </div>
          <!-- Header Widget / End -->
        </div>
        <!-- Right Side Content / End -->

        <!-- Right Side Content / End -->
        <div class="header-user-menu user-menu add">
          <div class="header-user-name" @click="headerUserNameClicked">
            <span><img src="images/testimonials/ts-1.jpg" alt="" /></span>Hi,
            Mary!
          </div>
          <ul :class="headerUserMenuUl">
            <li><router-link to="/mypage"> 프로필 수정</router-link></li>
            <li><a href="add-property.html"> 매물 등록</a></li>
            <li><a href="change-password.html"> 비밀번호 변경</a></li>
            <li><a href="#">로그아웃</a></li>
          </ul>
        </div>
        <!-- Right Side Content / End -->

        <div class="right-side d-none d-none d-lg-none d-xl-flex sign ml-0">
          <!-- Header Widget -->
          <div class="header-widget sign-in">
            <div class="show-reg-form modal-open"><a href="#">로그인</a></div>
          </div>
          <!-- Header Widget / End -->
        </div>
        <!-- Right Side Content / End -->
      </div>
    </div>
    <!-- Header / End -->
    <register-form></register-form>
  </header>
</template>

<script>
import RegisterForm from "@/components/RegisterForm.vue";
export default {
  data() {
    return {
      headerUserMenuUl: "",
      headeruserNameDiv: "header-user-name",
    }
  },
  components: {
    RegisterForm,
  },
  methods:{
    headerUserNameClicked(){
      if(this.headerUserMenuUl == ""){
        this.headerUserMenuUl = "hu-menu-vis";
      }else{
        this.headerUserMenuUl = "";
      }

      if(this.headeruserNameDiv.indexOf("hu-menu-visdec") == -1){
        this.headeruserNameDiv = "header-user-name hu-menu-visdec";
      }else{
        this.headeruserNameDiv = "header-user-name";
      }


    }
  }
};
</script>

<style>
</style>