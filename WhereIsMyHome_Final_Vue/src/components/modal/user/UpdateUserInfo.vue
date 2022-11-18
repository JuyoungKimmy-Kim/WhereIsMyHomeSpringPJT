<template>
<div class="modal fade" id="updateModal" tabindex="-1"  aria-hidden="true">
<div class="modal-dialog modal-danger modal-dialog-centered modal-" role="document">
    <div class="modal-content">
    <div class="modal-body p-0">
        <div class="card bg-gray-200 shadow border-0 mb-0">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-info shadow-info border-radius-lg py-3 pe-1 text-center py-4">
                <h4 class="font-weight-bolder text-white mt-1 mb-0">회원 정보 수정</h4>
                <p class="mb-1 text-sm text-white">환영합니다.</p> 
                </div>
            </div>

            <div class="card-body">
                <form role="form text-start">
                <div class="input-group input-group-static mb-4">
                    <label>이름</label>
                    <input type="text" class="form-control in" v-model="userInfo.userName" disabled/>
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>이메일</label>
                    <input type="email" class="form-control" v-model="userInfo.userEmail" disabled/>
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>비밀번호</label>
                    <input type="password" v-model="userPassword" class="form-control" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>비밀번호 확인</label>
                    <input type="password" v-model="userPassword2" class="form-control" placeholder="•••••••••••••">
                </div>
                <div class="text-center">
                    <button type="button"  class="btn bg-gradient-info mt-4 mb-0">수정완료</button>
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
import { mapState, mapActions } from 'vuex';

const userStore = "userStore";

export default {
  data() {
    return {
      userPassword:"",
      userPassword2:"",
    }
  },
  methods:{
    ...mapActions(userStore, ["getUserInfo"]),
  },
  computed:{
    ...mapState(userStore, ["userInfo","result"]),
  },
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>

</style>