<template>
  <div class="card card-body sticky blur shadow-blur mx-3 mx-md-12 mt-n6">
    <div class="container">
      <div class="row border-radius-md pb-4 p-3 mx-sm-0 mx-1 position-relative">
        <div class="col-lg-2 mt-lg-n2 mt-2">
          <label class="ms-0">시/도</label>
          <multiselect label="name" 
            v-model="sidoValue"
            :options="map.sidoList"
            :searchable="true"
            :close-on-select="true"
            :show-labels="false"
            placeholder="시/도 선택"
          >
          </multiselect>
        </div>
        <div class="col-lg-2 mt-lg-n2 mt-2">
          <label class="ms-0">구/군</label>
          <multiselect label="name" 
            v-model="gugunValue"
            :options="map.gugunList"
            :searchable="true"
            :close-on-select="true"
            :show-labels="false"
            :disabled="map.gugunList.length == 0"
            placeholder="구/군 선택"
          >
          </multiselect>
        </div>
        <div class="col-lg-2 mt-lg-n2 mt-2">
          <label class="ms-0">동</label>
          <multiselect label="name" 
            v-model="dongValue"
            :options="map.dongList"
            :searchable="true"
            :close-on-select="true"
            :show-labels="false"
            :disabled="map.dongList.length == 0"
            placeholder="동 선택"
          >
          </multiselect>
        </div>
        <div class="col-lg-3 mt-lg-n2 mt-2">
          <label class="ms-0">검색</label>
          <div class="input-group input-group-dynamic">
            <span class="input-group-text"><i class="fas fa-search" aria-hidden="true"></i></span>
            <input class="form-control" placeholder="Search" type="text" />
          </div>
        </div>

        <div class="col-lg-3 mt-lg-n2 mt-2">
          <label>&nbsp;</label>
          <button type="button" class="btn bg-gradient-info w-100 mb-0" @click="gotoMap">
            찾기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Multiselect from "vue-multiselect";

import {mapState, mapActions} from "vuex";

const mapStore = "mapStore";

export default {
  components: {
    Multiselect,
  },
  data() {
    return {
      sidoValue: '',
      gugunValue: '',
      dongValue: '',
    };
  },
  computed: {
    ...mapState(mapStore, ["map"]),
  },
  watch : {
    sidoValue() {
      if(this.sidoValue == null || this.sidoValue.code != this.map.value.sido.code){
        this.gugunValue = "";
        this.dongValue = "";
        this.$store.commit("mapStore/SET_GUGUN", {});
        this.$store.commit("mapStore/SET_DONG", {});
      }

      if(this.sidoValue != null){
        console.log ('sido value 변경 -->  gugun List 출력');
        this.$store.commit("mapStore/SET_SIDO", this.sidoValue);
        this.getGugunList(this.sidoValue);
      }
    },
    gugunValue() {
      if(this.gugunValue  == null || this.gugunValue.code != this.map.value.gugun.code){
        this.dongValue = "";
        this.$store.commit("mapStore/SET_DONG", {});
      }
      if(this.gugunValue != null) {
      console.log ('gugun value 변경 -->  dong List 출력');
        this.getDongList(this.gugunValue);
      }
    },
    dongValue() {
      if(this.dongValue != null){
        console.log ('dong value 변경 --> store 저장')
        this.$store.commit("mapStore/SET_DONG", this.dongValue);
      }
    }
  },
  methods: {
    ...mapActions(mapStore, ["getSidoList", "getGugunList", "getDongList"]),
    gotoMap() {
      this.$router.push("/map");
    },
  },
  async created(){
    await this.getSidoList();
  }
};
</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>
.card-body {
  z-index: 1;
}
.input-group {
  width: auto;
  height: auto;
}
</style>