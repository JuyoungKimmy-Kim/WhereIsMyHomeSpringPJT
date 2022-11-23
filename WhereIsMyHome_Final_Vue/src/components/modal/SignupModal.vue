<template>
<div class="modal fade" id="signUpModal" tabindex="-1"  aria-hidden="true">
<div class="modal-dialog modal-danger modal-dialog-centered modal-" role="document">
    <div class="modal-content">
    <div class="modal-body p-0">
        <div class="card bg-gray-200 shadow border-0 mb-0">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-info shadow-info border-radius-lg py-3 pe-1 text-center py-4">
                <h4 class="font-weight-bolder text-white mt-1 mb-0">회원가입</h4>
                <p class="mb-1 text-sm text-white">환영합니다.</p>
                </div>
            </div>
            <div class="card-body">
                <form role="form text-start">
                <div class="input-group input-group-static mb-4">
                    <label :style="{color:valid.name}">이름</label>
                    <input type="text" class="form-control in" v-model="user.name" placeholder="이름을 입력하세요.">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label :style="{color:valid.email}">이메일</label>
                    <input type="email" class="form-control" v-model="user.email" placeholder="john@email.com">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label :style="{color:valid.password}">비밀번호</label>
                    <input type="password" class="form-control" v-model="user.password" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label :style="{color:valid.password2}">비밀번호 확인</label>
                    <input type="password" class="form-control" v-model="user.password2" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label class="typo__label">관심지역</label>
                    <multiselect 
                      v-model="inputValue" 
                      :maxHeight="300" 
                      :limit="3" 
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
                <div class="text-center">
                    <button type="button" :class="[activateButton() ? 'btn bg-gradient-info mt-4 mb-0' : 'btn bg-gradient-info mt-4 mb-0 disabled']" @click="callSignUp">회원가입</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    </div>
</div>
</div>
</template>

<script>
import Multiselect from 'vue-multiselect';
import {mapState, mapActions} from "vuex";

const userStore = "userStore";
const mapStore = "mapStore";


export default {
  components: {
    Multiselect,
  },
  data () {
    return {
      user:{
        mame:'',
        email:'',
        password:'',
        password2:'',
        profileImageUrl:'',
        gugunCode: null,
      },
      valid:{
        name: "",
        email: "",
        password: "",
        password2: "",
      },
      options: [],
      sidoOptions: [],
      gugunOptions: [],
      inputValue: [],
      sido: null,
      gugun: null,
    }
  },
  computed:{
    ...mapState(userStore, ["userResult"]),
    ...mapState(mapStore, ["map"])
  },
  methods : {
    ...mapActions(userStore, ["signUpUser"]),
    ...mapActions(mapStore, ["getGugunList"]),
    async callSignUp(){
      await this.signUpUser(this.user);

      if(this.userResult.status == "SUCCESS"){
        this.$alertify.success(this.userResult.message);
        this.$emit("close-this-modal");
      }
      else{
        this.$alertify.error(this.userResult.message);
      }
      this.$store.commit("userStore/CLEAR_RESULT_MESSAGE");
    },
    activateButton(){
      return this.valid.name == "green" && this.valid.email == "green" && this.valid.password == "green" && this.valid.password2 == "green" && this.user.gugunCode != null;
    },
    // 길이가 3 이상이면 이름 valid
    validateName() {
        if (this.user.name != null && this.user.name.length >= 3) {
          this.valid.name = "green";
        }else{
          this.valid.name = "red";
        }
    },
    // 하나 이상의 숫자, 알파벳, 특수문자 포함
    validatePassword() {
        var patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
        var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
        var patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

        if (
          patternEngAtListOne.test(this.user.password) &&
          patternSpeAtListOne.test(this.user.password) &&
          patternNumAtListOne.test(this.user.password) &&
          this.user.password.length >= 8
        ) {
          this.valid.password = "green";
        } else this.valid.password = "red";
    },
    validatePassword2() {
        this.valid.password2 = this.user.password2 != "" && this.user.password == this.user.password2 
          ? "green" 
          : "red";
    },
    validateEmail() {
        // ^ 시작일치, $ 끝 일치
        // {2, 3} 2개 ~ 3개
        // * 0회 이상, + 1회 이상
        // [-_.] - 또는 _ 또는 .
        // ? 없거나 1회
        let regexp =
          /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if (regexp.test(this.user.email)) this.valid.email = "green";
        else this.valid.email = "red";
    },
    async listMapping(){
      this.options = [];
      if(this.sido == null){
        if(this.sidoOptions.length == 0){
          this.map.sidoList.forEach(element => {
            this.sidoOptions.push(
                { name: element.name, code: element.code, category: "sido" },
            )
          });
        }

        this.options = this.sidoOptions;
        this.gugunOptions = [];
      }

      if(this.sido != null && this.gugun == null ){
        if(this.gugunOptions.length == 0){
          await this.getGugunList(this.sido);

          this.map.gugunList.forEach(element => {
            this.gugunOptions.push(
                { name: element.name, code: element.code, category: "gugun"},
            )
          });
        }
        this.options = [...this.gugunOptions];
      }
    
    },
    async setSidoGugunDong(){
      await this.inputValue.forEach(item=>{
        if(item.code.length == 2){
          this.sido = item;
        }else if(item.code.length == 5){
          this.gugun = item;
          this.user.gugunCode = this.gugun.code;
        }
      })
    },
    removeSidoGugunDong({code}){
      if(code.length == 2){
        this.gugunOptions = [];
        this.options = [...this.sidoOptions];

        this.sido = null;
        this.gugun = null;
        this.dong = null;

        for(let i = 0; i < this.inputValue.length; i++){
          this.inputValue.pop();
        }
      }else if(code.length == 5){
        this.options = [...this.gugunOptions];

        this.gugun = null;
        this.dong = null;

        for(let i = 0; i < this.inputValue.length - 1; i++){
          this.inputValue.pop();
        }
      }
    }
  },
  watch:{
    'user.name'(){
      this.validateName();
    },
    'user.email'(){
      this.validateEmail();
    },
    'user.password'(){
      this.validatePassword();
    },
    'user.password2'(){
      this.validatePassword2();
    }
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>

</style>