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
                            <p class="text-white opacity-8 mb-0">작성자 : {{post.writer}} </p>
                            <p class="text-white opacity-8 mb-0">조회 수: {{post.readCount}}</p>
                            <p class="text-white opacity-8 mb-0">작성 일자: {{post.regDate}}  {{post.regTime}}</p>
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
                            <h6 class="mb-0 cursor-pointer" v-for="(file, index) in post.fileList" :key="index" @click="downloadFile(file.fileId)">{{file.fileName}}</h6>
                          </div>
                        </div>
                    </div>
                    </div>
                  <div class="accordion-item mb-3" style="scroll-behavior: smooth">
                    <h5 class="accordion-header" id="headingOne">
                      <button class="accordion-button border-bottom font-weight-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        댓글
                        <i class="collapse-close fa fa-plus text-xs pt-1 position-absolute end-0"></i>
                        <i class="collapse-open fa fa-minus text-xs pt-1 position-absolute end-0"></i>
                      </button>
                    </h5>
                    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionRental">
                      <div class="accordion-body text-sm opacity-8">
                        <board-comments ref="board_comment"></board-comments>
                      </div>
                    </div>
                  </div>
                </div>
                </section>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn bg-gradient-secondary" data-bs-dismiss="modal" @click="closeModal">닫기</button>
                <button type="button" class="btn bg-gradient-warning" v-if="userInfo != null && userInfo.userSeq == post.userSeq" @click="showUpdate(post.boardId)">수정</button>
                <button type="button" class="btn bg-gradient-danger" v-if="userInfo != null && userInfo.userSeq == post.userSeq" @click="deleteThisPost(post.boardId)">삭제</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/common/axios.js';
import {mapState, mapActions} from 'vuex';
import BoardComments from '@/components/board/BoardComments.vue';

import {Collapse} from "bootstrap";

const userStore = "userStore";
const boardStore = "boardStore";


export default {
  components: { BoardComments },
  data() {
    return {
      collapse: null,
    }
  },
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
          this.$store.commit("userStore/SET_RESULT_MESSAGE", null);
        },
      );
      
    },
    async downloadFile(fileId){
      await http.get(`/download/${fileId}`)
      .then((response)=>{
        // 파일 이름 업기
        const name = response.headers["content-disposition"]
        .split("filename=")[1]
        .replace(/"/g, "");

        // 파일 경로
        const url = window.URL.createObjectURL(new Blob([response.data]));
        
        
        // a 태그 생성 후 다운로드 하고 삭제
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", name);
        link.style.cssText = "display:none";
        document.body.appendChild(link);
        link.click();
        link.remove();
      })
      .catch(error=>console.log(error));
    },
    hideCollapse(){
      this.collapse.hide();
    }
  },
  computed:{
    ...mapState(boardStore, ["post", "boardResult"]),
    ...mapState(userStore, ["userInfo", "userResult"]),
  },
  mounted(){
        let myCollapse = document.getElementById("collapseOne");
        this.collapse = new Collapse(myCollapse);
        myCollapse.addEventListener("show.bs.collapse", async ()=>{
          await this.$refs.board_comment.getList();
        })
  }
};
</script>

<style scoped>
.modal-body *{
     /* 드래그 방지 */
    -ms-user-select: text;
    -moz-user-select: text;
    -webkit-user-select: text;
    -khtml-user-select: text;
    user-select: text;
}
</style>