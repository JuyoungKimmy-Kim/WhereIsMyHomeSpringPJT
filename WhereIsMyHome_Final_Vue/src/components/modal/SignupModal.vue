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
                    <label :style="{color:valide.name}">이름</label>
                    <input type="text" class="form-control in" v-model="user.userName" @blur="validateUserName" placeholder="이름을 입력하세요.">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label :style="{color:valide.email}">이메일</label>
                    <input type="email" class="form-control" v-model="user.userEmail" @blur="validateUserEmail" placeholder="john@email.com">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label :style="{color:valide.password}">비밀번호</label>
                    <input type="password" class="form-control" v-model="user.userPassword" @blur="validatePassword" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label :style="{color:valide.password2}">비밀번호 확인</label>
                    <input type="password" class="form-control" @blur="validatePassword2" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label class="typo__label">관심지역</label>
                    <multiselect v-model="value" :options="options" :multiple="true" group-values="libs" group-label="language" :group-select="true" placeholder="Type to search" track-by="name" label="name">
                        <span slot="noResult">목록이 존재하지 않습니다.</span>
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
import {mapActions} from "vuex";
import {signUp} from "@/common/user";

const userStore = "userStore";


export default {
  components: {
    Multiselect,
  },
  data () {
    return {
      user:{
        userName:'',
        userEmail:'',
        userPassword:'',
      },
      valide:{
        name: "",
        email: "",
        password: "",
        password2: "",
      },
      options: [
        {
          language: '시도',
          libs: [
            { name: 'Vue.js', category: 'Front-end' },
            { name: 'Adonis', category: 'Backend' }
          ]
        },
      ],
      value: []
    }
  },
  methods : {
    ...mapActions(userStore, ["signUp"]),
    async callSignUp(){
      await signUp(this.user,
        ({data})=>{ // SUCCESS
          this.$emit("close-this-modal", data);
        },
        (error)=>{ // FAIL
          console.log(error);
        });
    },
    activateButton(){
      return this.valide.name == "green" && this.valide.email == "green" && this.valide.password == "green" && this.valide.password2 == "green";
    },
    // 길이가 3 이상이면 이름 valid
    validateUserName() {
        if (this.user.userName.length >= 3) {
          this.valide.name = "green";
        }else{
          this.valide.name = "red";
        }
    },
    // 하나 이상의 숫자, 알파벳, 특수문자 포함
    validatePassword() {
        var patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
        var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
        var patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

        if (
          patternEngAtListOne.test(this.user.userPassword) &&
          patternSpeAtListOne.test(this.user.userPassword) &&
          patternNumAtListOne.test(this.user.userPassword) &&
          this.user.userPassword.length >= 8
        ) {
          this.valide.password = "green";
        } else this.valide.password = "red";
    },
    validatePassword2(e) {
        this.valide.password2 = 
          e.target.value != "" && e.target.value == this.user.userPassword 
          ? "green" 
          : "red";
    },
    validateUserEmail() {
        // ^ 시작일치, $ 끝 일치
        // {2, 3} 2개 ~ 3개
        // * 0회 이상, + 1회 이상
        // [-_.] - 또는 _ 또는 .
        // ? 없거나 1회
        let regexp =
          /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if (regexp.test(this.user.userEmail)) this.valide.email = "green";
        else this.valide.email = "red";
      },
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>

</style>