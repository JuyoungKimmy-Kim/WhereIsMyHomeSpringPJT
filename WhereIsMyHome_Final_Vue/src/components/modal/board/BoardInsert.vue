<template>
    <div class="modal fade modal-xl" id="boardInsertModal" tabindex="-1" aria-labelledby="insertModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="insertModalLabel">새 글</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            

            <div class="modal-body">
              <div class="input-group input-group-static mb-4">
                <label>제목</label>
                <input v-model="title" type="text" class="form-control" placeholder="제목을 입력하세요." aria-label="제목">
              </div>
              <div>
                <label>내용</label>
                <div id=InsertEditor></div>
              </div>
            </div>
            
            <upload-files ref="upload"></upload-files>

            <div class="modal-footer justify-content-between">
              <button type="button" class="btn bg-gradient-dark mb-0" data-bs-dismiss="modal">닫기</button>
              <button type="button" class="btn bg-gradient-primary mb-0" @click="tokenCheck">작성</button>
            </div>

          </div>
        </div>
    </div>
</template>

<script>
import Vue from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
Vue.use(CKEditor)

import UploadFiles from "@/components/board/UploadFiles.vue";

import {mapState, mapActions} from "vuex";

const userStore = "userStore";
const boardStore = "boardStore";

export default {
  components:{
    UploadFiles
  },
  data() {
    return {
      title:"",
      CKEditor: null,
    }
  },
  methods: {
    ...mapActions(boardStore, ["insertPost"]),
    ...mapActions(userStore, ["getUserInfo"]),
    async tokenCheck(){
      let token = sessionStorage.getItem("access-token");
      await this.getUserInfo(token);
      if(this.userResult.status == "SUCCESS"){
        this.submitPost();
      }else{
        this.$alertify.error(this.userResult.message);
        this.$store.commt("boardStore/SET_RESULT_MESSAGE", null); 
      }
    },
    async submitPost(){
      const post = {
        userSeq: this.userInfo.userSeq,
        title: this.title,
        content: this.CKEditor.getData(),
        userEmail: this.userInfo.userEmail
      };
      
      await this.insertPost(post);
      this.$refs.upload.upload();

      
      await setTimeout(() => {
          if(this.boardResult.status == "SUCCESS"){
            this.$alertify.success(this.boardResult.message);
            this.$emit("close-this-modal");
          }else{
            this.$alertify.error(this.boardResult.message);
          }

        this.$store.commt("boardStore/SET_RESULT_MESSAGE", null);
      }, 1000);
    },
    clearUpload(){
        this.$refs.upload.selectedFiles = undefined;
        this.$refs.upload.uploadProcess = false;
        this.$refs.upload.progress = 0;
        this.$refs.upload.message = "";
        this.$refs.upload.fileInfos = [];
    }
  },
  computed:{
    ...mapState(userStore, ["userInfo", "userResult"]),
    ...mapState(boardStore, ["boardResult", "post"]),
  },
  mounted() {
    ClassicEditor.create(document.getElementById("InsertEditor"))
      .then(res => {
        this.CKEditor = res;
        this.title = "";
        this.content = "";
        this.CKEditor.setData("");
    });
  },
}
</script>

<style scoped>
.modal-body >>> .ck-editor__editable{height: 400px}
</style>