<template>
    <div class="modal fade" id="boardInsertModal" tabindex="-1" aria-labelledby="insertModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="insertModalLabel">TEST</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
            <div class="modal-body">
              <table class="table">
                <tr>
                  <td> 제목 </td>
                  <td><input type="text" class="form-control" v-model="title"></td>
                </tr>
                <tr>
                  <td> 내용 </td>
                  <td><textarea rows="10" class="form-control" v-model="contents"></textarea></td>
                </tr>
              </table>
            </div>
            
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn bg-gradient-dark mb-0" data-bs-dismiss="modal">닫기</button>
              <button type="button" class="btn bg-gradient-primary mb-0">작성</button>
            </div>

          </div>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
      title:"",
      contents:"",
    }
  },
}
</script>

<style>

</style>