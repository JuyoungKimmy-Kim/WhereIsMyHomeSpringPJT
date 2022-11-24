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
          <div id="multiSelect" class="w-100" v-if="isMapView">
              <multiselect 
                v-model="inputValue" 
                :maxHeight="300" 
                :limit="4" 
                :options="options" 
                :multiple="true"  
                placeholder="검색어를 입력하세요."
                label="name"
                track-by="code"
                @input="setSidoGugunDong"
                @remove="removeSidoGugunDong"
                @open="listMapping"
                @close="isOpen = false"
                >
              <span slot="noOptions">검색하신 내용 혹은 목록이 존재하지 않습니다.</span>
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
import { propertyListByRegionCode, propertyListByDongName} from '@/common/map.js'

const userStore = "userStore";
const mapStore = "mapStore";

export default{
  components:{Multiselect},
  data() {
    return {
      isOpen: false,
      removed: false,
      options: [],
      sidoOptions: [],
      gugunOptions: [],
      dongOptions: [],
      propertyOptions: [],

      inputValue:[],
    }
  },
  methods: {
    ...mapActions(userStore, ["logoutConfirm", "getUserInfo"]),
    ...mapActions(mapStore, ["getGugunList", "getDongList"]),
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
    },
    async listMapping(){
      this.isOpen = true;
      this.inputValue = [];

      console.log(this.map);
      let sido = this.map.sido;
      let gugun = this.map.gugun;
      let dong = this.map.dong;

      if(Object.keys(sido).length == 0){
        if(this.sidoOptions.length == 0){
          this.map.sidoList.forEach(element => {
            this.sidoOptions.push(
                { name: element.name, code: element.code, category: "sido" },
            )
          });
        }
        
        this.gugunOptions = [];
        this.options = [...this.sidoOptions];
      }else if(Object.keys(gugun).length == 0){
        if(this.gugunOptions.length == 0){
          await this.getGugunList(this.map.sido);

          this.map.gugunList.forEach(element => {
            this.gugunOptions.push(
                { name: element.name, code: element.code, category: "gugun"},
            )
          });
        }
        
        this.dongOptions = [];
        this.options = [...this.gugunOptions];
      }else if(Object.keys(dong).length == 0){
        if(this.dongOptions.length == 0){
          await this.getDongList({
            sido: this.map.sido, 
            gugun: this.map.gugun
          });

          this.map.dongList.forEach(element => {
            this.dongOptions.push(
                { name: element.name, code: element.code, category: "dong"},
            )
          });
        }

        this.propertyOptions = [];
        this.options = [...this.dongOptions];
      }
      
      
      if(Object.keys(this.map.sido).length != 0)this.inputValue.push(this.map.sido);
      if(Object.keys(this.map.gugun).length != 0)this.inputValue.push(this.map.gugun);
      if(Object.keys(this.map.dong).length != 0)this.inputValue.push(this.map.dong);
      
    },
    async setSidoGugunDong(){
      if(this.removed){
        this.removed = false;
        return;
      }
      
      let item = this.inputValue.at(-1);
      if(item.code.length == 2){
        this.$store.commit("mapStore/SET_SIDO", item);
        this.getListBySidoCode(); 
      }else if(item.code.length == 5){
        this.$store.commit("mapStore/SET_GUGUN", item);
        this.getListByGugunCode();
      }else if(item.code.length == 10){
        this.$store.commit("mapStore/SET_DONG", item);
        this.getListByDongName();
      }
      
    },
    removeSidoGugunDong({code}){
      this.removed = true;
      console.log("remove");

      if(code.length == 2){
        this.$store.commit("mapStore/SET_SIDO", {});
        this.$store.commit("mapStore/SET_GUGUN", {});
        this.$store.commit("mapStore/SET_DONG", {});
        this.getListBySidoCode(); 

        this.gugunOptions = [];
        this.options = [...this.sidoOptions];
        for(let i = 0; i < this.inputValue.length; i++){
          this.inputValue.pop();
        }
      }else if(code.length == 5){
        this.$store.commit("mapStore/SET_GUGUN", {});
        this.$store.commit("mapStore/SET_DONG", {});
        this.getListBySidoCode();


        this.dongOptions = [];
        this.options = [...this.gugunOptions];

        for(let i = 0; i < this.inputValue.length - 1; i++){
          this.inputValue.pop();
        }
      }else if(code.length == 10){
        this.$store.commit("mapStore/SET_DONG", {});
        this.$store.commit("mapStore/SET_PROPERTY", {});
        this.getListByGugunCode();

        this.options = [...this.dongOptions];

        for(let i = 0; i < this.inputValue.length - 2; i++){
          this.inputValue.pop();
        }
      }
    },
    async getListBySidoCode(){
      await propertyListByRegionCode(this.map.sido.code,
        ({data})=>{
          this.$store.commit("mapStore/SET_PROPERTY_LIST", data);
        },
        (error)=>{
          console.log(error);
        }
      );
    },
    async getListByGugunCode(){
      await propertyListByRegionCode(this.map.gugun.code,
        ({data})=>{
          this.$store.commit("mapStore/SET_PROPERTY_LIST", data);
        },
        (error)=>{
          console.log(error);
        }
      );
    },
    async getListByDongName(){
      await propertyListByDongName(this.map.dong.name, this.map.gugun.code,
        ({data})=>{
          this.$store.commit("mapStore/SET_PROPERTY_LIST", data);
        },
        (error)=>{
          console.log(error);
        }
      );
    }
  },
  computed:{
    ...mapState(userStore, ["isLogin", "userInfo", "userResult"]),
    ...mapState(mapStore, ["map"]),
    isMapView(){
      return this.$store.state.isMapView;
    }
  },
  watch:{
    isMapView(value, oldValue){
      if(value){
        this.listMapping();
      }
    }
  },

}
</script>


<style scoped src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>
#topThreeDiv {height:0px;}
.navbar .container-fluid{min-height: 44.39px;}
</style>