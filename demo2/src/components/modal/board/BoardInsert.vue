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
                <input type="text" class="form-control" placeholder="제목을 입력하세요." aria-label="제목">
              </div>
              <div>
                <label>내용</label>
                <div id=InsertEditor></div>
              </div>
            </div>
            
            <upload-files></upload-files>

            <div class="modal-footer justify-content-between">
              <button type="button" class="btn bg-gradient-dark mb-0" data-bs-dismiss="modal">닫기</button>
              <button type="button" class="btn bg-gradient-primary mb-0">작성</button>
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

export default {
  components:{
    UploadFiles
  },
  data() {
    return {
      title:"",
      content:"",
      CKEditor: null,
    }
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