<template>
  <div class="card card-body sticky blur shadow-blur mx-3 mx-md-12 mt-n6">
    <div class="container">
      <div class="row border-radius-md pb-4 p-3 mx-sm-0 mx-1 position-relative">
        <div class="col-lg-2 mt-lg-n2 mt-2">
          <label class="ms-0">시/도</label>
          <multiselect label="name" 
            v-model="sidoValue"
            :options="getSidoList"
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
            :options="getGugunList"
            :searchable="true"
            :close-on-select="true"
            :show-labels="false"
            placeholder="구/군 선택"
          >
          </multiselect>
        </div>
        <div class="col-lg-2 mt-lg-n2 mt-2">
          <label class="ms-0">동</label>
          <multiselect label="name" 
            v-model="dongValue"
            :options="getDongList"
            :searchable="true"
            :close-on-select="true"
            :show-labels="false"
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
import http from "@/common/axios.js";

export default {
  components: {
    Multiselect,
  },
  data() {
    return {
      sido: [],
      sidoValue: '',

      gugun: [],
      gugunValue: '',

      dong: [],
      dongValue: '',
    };
  },
  created: function () {
    this.sidoList();
  },
  computed: {
    getSidoList() {
      return this.$store.getters.getSidoList;
    },
    getGugunList() {
      return this.$store.getters.getGugunList;
    },
    getDongList() {
      return this.$store.getters.getDongList;
    },

  },
  watch : {
    sidoValue() {
      console.log ('sido value 변경 -->  gugun List 출력')
      this.gugun="",
      this.gugunValue="",
      this.dong="",
      this.dongValue="",
      this.gugunList(this.sidoValue.code);
    },
    gugunValue() {
      console.log ('gugun value 변경 -->  dong List 출력')
      this.dongList(this.gugunValue.code)
    },
    dongValue() {
      console.log ('dong value 변경 --> store 저장')
      this.setStore(this.dongValue.code);
    }
  },
  methods: {
    setSidoInfo() {
      this.gugunValue = [];
      this.dongValue = [];

    },
    setGugunInfo() {
      this.dongValue = [];
    },
    setDongInfo() {
 
    },
    setStore () {
      this.$store.commit("SET_SIDO_CODE", this.sidoValue);
      this.$store.commit("SET_GUGUN_CODE", this.gugunValue);
      this.$store.commit ("SET_DONG_CODE", this.dongValue);
    },
    gotoMap() {
      this.$router.push("/map");
    },

    async sidoList() {
      try {
        let { data } = await http.get("/address");
        this.$store.commit("SET_SIDO_LIST", data);
      } catch (error) {
        console.error(error);
      }
    },
    async gugunList() {
      try {
        let { data } = await http.get("/address/" + this.sidoValue.code);
        this.$store.commit("SET_GUGUN_LIST", data);
      } catch (error) {
        console.error(error);
      }
    },
    async dongList() {
      try {
        let { data } = await http.get(
          "/address/" +this.sidoValue.code+ "/" + this.gugunValue.code
        );
        this.$store.commit("SET_DONG_LIST", data);
      } catch (error) {
        console.error(error);
      }
    },
  },
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
