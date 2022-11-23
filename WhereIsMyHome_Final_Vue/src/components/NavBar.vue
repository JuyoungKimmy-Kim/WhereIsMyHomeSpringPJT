<template>
<div id="topThreeDiv" class="container position-sticky z-index-sticky top-0">
  <div id="topThreeDiv" class="row">
    <div id="topThreeDiv" class="col-12">
      <nav class="navbar navbar-expand-lg  blur border-radius-xl top-0 z-index-fixed shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
        <div class="container-fluid px-0">
          <router-link to="/" class="nav-link ps-2 d-flex cursor-pointer align-items-center" rel="tooltip" data-placement="bottom">
            <!--<i class="material-icons me-2">home</i>-->
            <img id="logoImg" src="@/assets/img/logo.png" width="100px" height="50px"/>
          </router-link>
          <button class="navbar-toggler shadow-none ms-2" type="button" data-bs-toggle="collapse" data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon mt-2">
              <span class="navbar-toggler-bar bar1"></span>
              <span class="navbar-toggler-bar bar2"></span>
              <span class="navbar-toggler-bar bar3"></span>
            </span>
          </button>

          <!-- map이면 표시 -->
          <div id="multiSelect" class="w-100" v-if="$store.state.isMapView">
              <multiselect 
                v-model="value" 
                :maxHeight="300" 
                :limit="4" 
                :options="filteredOptions" 
                :multiple="true" 
                group-values="libs"
                group-label="language" 
                placeholder="검색어를 입력하세요."
                track-by="category" 
                label="name">
                  <span slot="noResult">검색하신 내용이 존재하지 않습니다.</span>
              </multiselect>
          </div>
          
          <div
            class="collapse navbar-collapse w-100 pt-3 pb-2 py-lg-0 ps-lg-5"
            id="navigation"
          >
            <ul class="navbar-nav navbar-nav-hover ms-auto">
              <li class="nav-item">
                <router-link
                  to="/map"
                  class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                >
                  <i class="material-icons opacity-6 me-2 text-md">map</i>
                  지도
                </router-link>
              </li>
              <li class="nav-item">
                <router-link
                  to="/boards"
                  class="nav-link ps-2 d-flex cursor-pointer align-items-center"
                >
                  <i class="material-icons opacity-6 me-2 text-md">library_books</i>
                  공지사항
                </router-link>
              </li>
              <li class="nav-item">
                <a class="nav-link ps-2 d-flex cursor-pointer align-items-center" v-if="!isLogin"  @click="showLogin">
                  <i class="material-icons opacity-6 me-2 text-md">people</i>
                  로그인
                </a>
                
              </li>
              <li class="nav-item dropdown">
                  <a class="nav-link ps-2 d-flex cursor-pointer align-items-center dropdown-toggle" id="dropdownUserMenu" data-bs-toggle="dropdown" aria-expanded="false" 
                    v-if="isLogin" >
                    <a class="avatar avatar-xs rounded-circle opacity-6 me-2 text-md">
                      <img alt="Image placeholder" :src=userInfo.profileImageUrl>
                    </a>
                    {{userInfo.name}}
                  </a>
                  <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownUserMenu">
                    <li><a class="dropdown-item border-radius-md" @click="doLogout">로그아웃</a></li>
                    <li><a class="dropdown-item border-radius-md" @click="tokenCheck('myinfo')">내 정보</a></li>
                    <li v-if="this.userInfo.role == '관리자'">
                      <a class="dropdown-item border-radius-md" @click="tokenCheck('manage')">사용자 관리</a></li>
                    <li><a class="dropdown-item border-radius-md" @click="tokenCheck('wishlist')">찜 목록</a></li>
                  </ul>
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
import Multiselect from 'vue-multiselect'
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default{
  components:{Multiselect},
  data() {
    return {
      options: [
          {
            language: '시도',
            libs: [
                { name: 'Vue.js', category: 'sido' },
                { name: 'Adonis', category: 'sido' }
            ]
          },
          {
            language: '구군',
            libs: [
                { name: 'Rails', category: 'gugun1' },
                { name: 'Rails', category: 'gugun2' },
                { name: 'Rails', category: 'gugun3' },
                { name: 'Rails', category: 'gugun4' },
                { name: 'Rails', category: 'gugun5' },
            ]
          },
      ],
      value: [],
    }
  },
  methods: {
    ...mapActions(userStore, ["logoutConfirm", "getUserInfo"]),
    showLogin(){
      this.$emit("show-login");
    },
    showInfo() {
      this.$emit("show-info");
    },
    doLogout(){
      this.$alertify.confirm("로그아웃 하시겠습니까?", async ()=>{
        await this.logoutConfirm();
        if(this.userResult.status == "SUCCESS"){
          sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
          sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
          this.$alertify.success(this.userResult.message);
        }else{
          this.$alertify.error(this.userResult.message);
        }
        this.$store.commit("userStore/CLEAR_RESULT_MESSAGE");
      });
    },
    async tokenCheck(message){
      let token = sessionStorage.getItem("access-token");
      await this.getUserInfo(token);

      if(this.userResult.status == "SUCCESS"){
        if(message == "myinfo") this.showInfo();
        else if(message == "manage" && this.$route.path != "/manage") this.$router.push("/manage");
        else if(message == "wishlist" && this.$route.path != "/wishlist") this.$router.push("/wishlist");
      }else{
        this.$alertify.error(this.userResult.message);
        if(this.$route.path != "/")
          this.$router.push("/");
      }
      this.$store.commit("userStore/CLEAR_RESULT_MESSAGE");
    }
  },
  computed:{
    ...mapState(userStore, ["isLogin", "userInfo", "userResult"]),
    filteredOptions(){
      return this.options.filter(el=>{
        let sidoCount = this.value.filter(el => el.category == "sido").length;
        if(sidoCount == 0){
          return el.language == "시도";
        }
        let gugunCount = this.value.filter(el => el.category.indexOf("gugun") != -1).length;
        if(gugunCount < 2){
          return el.language == "구군";
        }
      });
    },
  },
}
</script>

<style scoped src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>
#topThreeDiv {height:0px;}
.navbar .container-fluid{min-height: 44.39px;}
</style>