<template>
<div>
    <header-page></header-page>

    <div class="card card-body sticky blur shadow-blur mx-3 mx-md-6 mt-n6">
        <section>
        <div class="container">
            <h4 class="row justify-content-center">공지사항</h4>

            <!-- 관리자에게만 보여야함 v-show or v-if -->
            <div class="row justify-content-md-end">
                <button type="button" class="btn bg-gradient-success w-auto btn-sm me-2"  @click="showInsertModal">글 쓰기</button>
            </div>

            <div class="row justify-content-center pb-5">
                <div class="col-lg-11">
                    <div class="card">
                    <div class="table-responsive">
                        <table class="table align-items-center mb-0">
                        <thead>
                            <tr>
                                <th scope="col" class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 col-1">#</th>
                                <th scope="col" class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2 col-6">제목</th>
                                <th scope="col" class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 col-3">작성자</th>
                                <th scope="col" class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 col-2">작성일자</th>    
                            </tr>
                        </thead>
                        <tbody>
                            <!-- sample -->
                            <tr>
                                <td>
                                    <div class="d-flex px-2 py-1">
                                    <div class="d-flex flex-column justify-content-center">
                                        <h6 class="mb-0 text-xs">글 번호</h6>
                                    </div>
                                    </div>
                                </td>
                                <td>
                                    <a class="text-xs font-weight-bold mb-0 cursor-pointer" @click="showDetail">제목</a>
                                </td>
                                <td class="align-middle text-center text-sm">
                                    <p class="text-xs font-weight-bold mb-0">유저이름</p>
                                </td>
                                <td class="align-middle text-center">
                                    <span class="text-secondary text-xs font-weight-bold">23/04/18</span>
                                </td>
                            </tr>
                            <!-- sample -->
                        </tbody>
                        </table>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 페이지네이션 -->
    <pagination-ui></pagination-ui>
    </div>

    <board-insert-modal></board-insert-modal>
    <board-detail-modal></board-detail-modal>
</div>
</template>

<script>
import HeaderPage from '@/components/HeaderPage.vue'
import PaginationUi from '@/components/PaginationUI.vue'

import { Modal } from "bootstrap";
import BoardInsertModal from '@/components/modal/board/BoardInsert.vue'
import boardDetailModal from '@/components/modal/board/BoardDetail.vue'

export default {
    components:{
        HeaderPage,
        PaginationUi,

        BoardInsertModal,
        boardDetailModal,
    },
    data() {
        return {
            boardInsertModal: null,
            boardDetailModal: null,

        }
    },
    methods:{
        showInsertModal(){
            this.boardInsertModal.show();
        },
        showDetail(){
            this.boardDetailModal.show();
        }
    },
    mounted() {
        this.boardInsertModal = new Modal(document.getElementById("boardInsertModal"));
        this.boardDetailModal = new Modal(document.getElementById("boardDetailModal"));
    },
}
</script>

<style>
</style>