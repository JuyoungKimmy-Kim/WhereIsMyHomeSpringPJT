<template>
  <div
    class="modal fade"
    id="boardDetailModal"
    tabindex="-1"
    aria-hidden="true"
  >
    <div
      class="modal-dialog modal-xl modal-danger modal-dialog-centered modal-"
      role="document"
    >
      <div class="modal-content">
        <div class="modal-body p-0">
          <div class="modal-content">
            <div class="modal-body">
                <section class="pt-3 pt-md-5 pb-md-5 mt-8 mt-md-7 mt-lg-0">
                <div class="container">
                    <div class="col-12 mx-auto pb-5">
                    <div class="card shadow-lg">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                        <div class=" bg-gradient-dark shadow-dark border-radius-lg p-4">
                            <h3 class="text-white mb-0">{{post.title}}</h3>
                            <p class="text-white opacity-8 mb-0">조회 수: {{post.readCount}}</p>
                            <p class="text-white opacity-8 mb-0">작성 일자: {{post.regDt}}</p>
                        </div>
                        </div>
                        <div class="card-body p-5">
                          <div v-html="post.content"></div>
                        </div>
                        <div class="card-footer py-3 align-items-center row">
                          <div class="col-sm-3">
                            <small class="text-uppercase font-weight-bold">첨부파일</small>
                          </div>
                          <div class="col-sm-9">
                            <h6 class="mb-0" v-for="(file, index) in post.fileList" :key="index">{{file.fileName}}</h6>
                          </div>
                        </div>
                    </div>
                    </div>
                </div>
                </section>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn bg-gradient-secondary" data-bs-dismiss="modal" @click="closeModal">닫기</button>
                <button type="button" class="btn bg-gradient-warning" v-if="userInfo != null && userInfo.userSeq == post.writer" @click="showUpdate(post.boardId)">수정</button>
                <button type="button" class="btn bg-gradient-danger" v-if="userInfo != null && userInfo.userSeq == post.writer" @click="deleteThisPost(post.boardId)">삭제</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState, mapActions} from 'vuex';

const userStore = "userStore";
const boardStore = "boardStore";

export default {
  methods: {
    ...mapActions(boardStore, ["deletePost"]),
    ...mapActions(userStore, ["getUserInfo"]),
    showUpdate(boardId){
      this.$emit("show-this-update", boardId);
    },
    closeModal(){
      this.$emit("close-this-modal");
    },
    async deleteThisPost(boardId){
      this.$alertify.confirm("정말 삭제하시겠습니까?", async ()=>{
          let token = sessionStorage.getItem("access-token");
          await this.getUserInfo(token);

          if(this.userResult.status == "SUCCESS"){
            await this.deletePost(boardId);
            if(this.boardResult.status == "SUCCESS"){
              this.$alertify.success(this.boardResult.message);
              this.$emit("close-this-modal");
            }else{
              this.$alertify.error(this.boardResult.message);
            }
          }else{
              this.$alertify.error(this.userResult.message);
          }
          
          this.$store.commit("boardStore/SET_RESULT_MESSAGE", null);
        },
      );
      
    }
  },
  computed:{
    ...mapState(boardStore, ["post", "boardResult"]),
    ...mapState(userStore, ["userInfo", "userResult"]),
  }
};
</script>

<style>
</style>