<template>
<div>
  <header-page></header-page>
  <search-box></search-box>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6">
    <section class="pt-8 pb-4" id="count-stats">
      <div class="container">
        <div class="row py-0">
          <div class="col-lg-9 mx-auto py-3">
            <div class="row container-fluid px-0">
              <div class="col-md-6 position-relative" style="display: flex; flex-direction: column;">
                <h4 class="row justify-content-center" style="flex:1">오늘의 뉴스</h4>
                <news-section></news-section>
                <hr class="vertical dark">
              </div>
              <div class="col-md-6 position-relative" style="display: flex; flex-direction: column;">
                <h4 class="row justify-content-center" style="flex:1">공지사항</h4>
                <notice-fragment @show-this-notice="showDetail"></notice-fragment>
              </div>
            </div>
          </div>
        </div>

        <bottom-card></bottom-card>
      </div>
    </section>
  </div>
  <board-detail-modal ref="detail_modal"></board-detail-modal>
</div>

</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue"
import SearchBox from "@/components/SearchBox.vue"
import NoticeFragment from "@/components/NoticeFragment.vue"
import NewsSection from "@/components/NewsSection.vue"
import BottomCard from "@/components/BottomCard.vue"

import {mapState, mapActions} from "vuex";

const boardStore = "boardStore";
const userStore = "userStore";


import { Modal } from "bootstrap";
import BoardDetailModal from '@/components/board/modal/BoardDetail.vue'

export default {
  components : {
    HeaderPage,SearchBox, NoticeFragment, NewsSection, BottomCard, BoardDetailModal
  },
  methods: {
    ...mapActions(boardStore, ["postDetail"]),
    async showDetail(boardId){
      await this.postDetail({boardId: boardId, userEmail:this.userInfo.userEmail});
      if(this.boardResult.status == "SUCCESS"){
        await this.$router.push("/boards");
      }else{
        this.$alertify.error(this.boardResult.message);
      }
      this.$store.commit("boardStore/SET_RESULT_MESSAGE", null);
    },
  },
  computed:{
    ...mapState(boardStore, ["boardResult"]),
    ...mapState(userStore, ["userInfo"])
  },
  mounted() {
    this.BoardDetailModal = new Modal(document.getElementById("boardDetailModal"));
  },
}
</script>

<style>

</style>