<template>
<div class="modal fade" id="joinModal" tabindex="-1"  aria-hidden="true">
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
                    <label>이름</label>
                    <input type="text" class="form-control in" v-model="userName" placeholder="홍길동">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>이메일</label>
                    <input type="email" class="form-control" v-model="userEmail" placeholder="john@email.com">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>비밀번호</label>
                    <input type="password" class="form-control" v-model="userPassword" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>비밀번호 확인</label>
                    <input type="password" class="form-control" v-model="userPassword2" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label class="typo__label">관심지역</label>
                    <multiselect v-model="value" :options="options" :multiple="true" group-values="libs" group-label="language" :group-select="true" placeholder="Type to search" track-by="name" label="name">
                        <span slot="noResult">Oops! No elements found. Consider changing the search query.</span>
                    </multiselect>
                </div>
                <div class="text-center">
                    <button type="button" class="btn bg-gradient-info mt-4 mb-0">회원가입</button>
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
import Multiselect from 'vue-multiselect'

export default {
  components: {
    Multiselect,
  },
  data () {
    return {
      // userId='',
      // userEmail='',
      // userPassword='',
      // userPassword2='',
      valid: false,

      options: [
        {
          language: '시도',
          libs: [
            { name: 'Vue.js', category: 'Front-end' },
            { name: 'Adonis', category: 'Backend' }
          ]
        },
        {
          language: '구군',
          libs: [
            { name: 'Rails', category: 'Backend' },
            { name: 'Sinatra', category: 'Backend' }
          ]
        },
        {
          language: '동',
          libs: [
            { name: 'Laravel', category: 'Backend' },
            { name: 'Phoenix', category: 'Backend' }
          ]
        }
      ],
      value: []
    }
  },
  methods : {
    // 길이가 3 이상이면 이름 valid
    validateUserName(userName) {
        if (userName.length >= 3) return true;
        else return false;
    },
    // 하나 이상의 숫자, 알파벳, 특수문자 포함
    validatePassword(userPassword) {
        var patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
        var patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
        var patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

        if (
          patternEngAtListOne.test(userPassword) &&
          patternSpeAtListOne.test(userPassword) &&
          patternNumAtListOne.test(userPassword) &&
          userPassword.length >= 8
        ) {
          return true;
        } else return false;
    },
    validatePassword2(userPassword2) {
        if (userPassword2 == document.querySelector("#userPassword").value)
          return true;
        else return false;
    },
    validateUserEmail(userEmail) {
        // ^ 시작일치, $ 끝 일치
        // {2, 3} 2개 ~ 3개
        // * 0회 이상, + 1회 이상
        // [-_.] - 또는 _ 또는 .
        // ? 없거나 1회
        let regexp =
          /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if (regexp.test(userEmail)) return true;
        else return false;
      },
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>

</style>