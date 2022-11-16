<template>
<div class="card card-body sticky blur shadow-blur mx-3 mx-md-12 mt-n6">
  <div class="container">
    <div class="row border-radius-md pb-4 p-3 mx-sm-0 mx-1 position-relative">
      <div class="col-lg-2 mt-lg-n2 mt-2">
        <label class="ms-0">시/도</label>
        <multiselect v-model="sidoValue" :options="sido" @select="clearGugunDong" :searchable="true" :close-on-select="true" :show-labels="false" placeholder="">
          <span slot="noResult">Oops! No elements found. Consider changing the search query.</span>
        </multiselect>
      </div>
      <div class="col-lg-2 mt-lg-n2 mt-2">
        <label class="ms-0">구/군</label>
        <multiselect v-model="gugunValue" :options="gugun" @select="clearDong" :disabled="sidoValue.length == 0" :searchable="true" :close-on-select="true" :show-labels="false" placeholder="">
          <span slot="noResult">Oops! No elements found. Consider changing the search query.</span>
        </multiselect>
      </div>
      <div class="col-lg-2 mt-lg-n2 mt-2">
        <label class="ms-0">동</label>
        <multiselect v-model="dongValue" :options="dong" :disabled="gugunValue.length == 0"  :searchable="true" :close-on-select="true" :show-labels="false" placeholder="">
          <span slot="noResult">Oops! No elements found. Consider changing the search query.</span>
        </multiselect>
      </div>
      <div class="col-lg-3 mt-lg-n2 mt-2">
        <label class="ms-0">검색</label>
        <div class="input-group input-group-dynamic ">
          <span class="input-group-text"><i class="fas fa-search" aria-hidden="true"></i></span>
          <input class="form-control" placeholder="Search" type="text" >
        </div>
      </div>

      <div class="col-lg-3 mt-lg-n2 mt-2">
        <label>&nbsp;</label>
        <button type="button" class="btn bg-gradient-info w-100 mb-0">찾기</button>
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
      sido: ["sample1", "sample2", "sample3"],
      sidoValue: [],
      
      gugun: ["sample1", "sample2", "sample3"],
      gugunValue: [],
      
      dong: ["sample1", "sample2", "sample3"],
      dongValue: [],

    }
  },
  methods:{
    clearGugunDong(){
      this.gugunValue = [];
      this.clearDong();
    },
    clearDong(){
      this.dongValue = [];
    }
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>
.card-body{
  z-index: 1;
}

.input-group{
  width: auto;
  height: auto;
}
</style>
