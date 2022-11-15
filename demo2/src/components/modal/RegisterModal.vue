<template>
<div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="exampleModalForm" aria-hidden="true">
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
                    <input type="text" class="form-control" placeholder="홍길동">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>이메일</label>
                    <input type="email" class="form-control" placeholder="john@email.com">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>비밀번호</label>
                    <input type="password" class="form-control" placeholder="•••••••••••••">
                </div>
                <div class="input-group input-group-static mb-4">
                    <label>비밀번호 확인</label>
                    <input type="password" class="form-control" placeholder="•••••••••••••">
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
    Multiselect
  },
  data () {
    return {
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
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>

</style>