<template>
<div>
    <header-page></header-page>

    <div class="card card-body sticky blur shadow-blur mx-3 mx-md-6 mt-n6">
        <section>
        <div class="container">
            <h4 class="row justify-content-center">공지사항</h4>

            <!-- 관리자에게만 보여야함 v-show or v-if -->
            <div class="row justify-content-md-end">
                <button type="button" class="btn bg-gradient-success w-auto btn-sm me-2"  v-if="userInfo.role == '관리자'" @click="showInsertModal">글 쓰기</button>
            </div>

            <div class="row justify-content-center pb-5">
                <div class="col-lg-11">
                    <div class="card">
                    <div class="table-responsive">
                        <table class="table align-items-center mb-0">
                            <thead>
                                <tr bgcolor="#2F4858">
                                    <th scope="col" class="text-white text-uppercase text-secondary text-xs font-weight-bolder opacity-7 col-2 px-3">#</th>
                                    <th scope="col" class="text-white text-uppercase text-secondary text-xs font-weight-bolder opacity-7 ps-2 col-5">제목</th>
                                    <th scope="col" class="text-white text-center text-uppercase text-secondary text-xs font-weight-bolder opacity-7 col-2">작성자</th>
                                    <th scope="col" class="text-white text-center text-uppercase text-secondary text-xs font-weight-bolder opacity-7 col-2">작성일자</th>   
                                    <th scope="col" class="text-white text-center text-uppercase text-secondary text-xs font-weight-bolder opacity-7 col-1">조회 수</th>  
                                    <th class="text-secondary text-xxs opacity-7"></th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr v-for="(post, index) in list" :key="index">
                                    <td>
                                        <div class="d-flex px-2 py-1">
                                        <div class="d-flex flex-column justify-content-center">
                                            <h6 class="mb-0 text-xs">{{post.boardId}}</h6>
                                        </div>
                                        </div>
                                    </td>
                                    <td>
                                        <a class="text-xs font-weight-bold mb-0 cursor-pointer" @click="showDetail(post.boardId)">{{post.title}}</a>
                                    </td>
                                    <td class="align-middle text-center text-sm">
                                        <p class="text-xs font-weight-bold mb-0">{{post.writer}}</p>
                                    </td>
                                    <td class="align-middle text-center">
                                        <span class="text-secondary text-xs font-weight-bold">{{post.regDate}}</span>
                                    </td>
                                    <td class="align-middle text-center">
                                        <span class="text-secondary text-xs font-weight-bold">{{post.readCount}}</span>
                                    </td>
                                </tr>
                                
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>
                            
                <!-- 페이지네이션 -->
                <pagination-ui @call-move-page ="movePage" :listRowCount="listRowCount" :pageLinkCount="pageLinkCount" :currentPageIndex="currentPageIndex" :totalListItemCount="totalListItemCount"></pagination-ui>
            </div>
        </div>
    </section>
    </div>

    <board-insert-modal @close-this-modal="closeInsertModal" ref="insert_modal"></board-insert-modal>
    <board-detail-modal @close-this-modal="closeDetailModal" @show-this-update="showUpdate" ref="detail_modal"></board-detail-modal>
    <board-update-modal @close-this-modal="closeUpdateModal" ref="update_modal"></board-update-modal>
</div>
</template>

<script>
import HeaderPage from '@/components/HeaderPage.vue'
import PaginationUi from '@/components/PaginationUI.vue'

import { Modal } from "bootstrap";
import BoardInsertModal from '@/components/board/modal/BoardInsert.vue'
import BoardDetailModal from '@/components/board/modal/BoardDetail.vue'
import BoardUpdateModal from '@/components/board/modal/BoardUpdate.vue'

import {list, listCount} from '@/common/board.js';
import util from '@/common/utils.js';
import {mapState, mapActions} from 'vuex';

const userStore = "userStore";
const boardStore = "boardStore";

export default {
    components:{
        HeaderPage,
        PaginationUi,

        BoardInsertModal,
        BoardDetailModal,
        BoardUpdateModal
    },
    data() {
        return {
            boardInsertModal: null,
            boardDetailModal: null,
            boardUpdateModal: null,
            

            list: [],

            limit: 10,
            offset: 0,
            listRowCount: 10,
            pageLinkCount: 10,
            currentPageIndex: 1,
            totalListItemCount: 0,
        }
    },
    methods:{
        ...mapActions(boardStore, ["postDetail", "getTotalCount"]),
        async callList(){
            await list(this.limit, this.offset, ({data})=>{
                if(data.result == "SUCCESS"){
                    data.list.forEach(el => {
                        el.regDate = util.makeDateStr(el.regDt.date.year, el.regDt.date.month, el.regDt.date.day, "/");
                        el.regTime = util.makeTimeStr(el.regDt.time.hour, el.regDt.time.minute, el.regDt.time.second, ":");
                    });
                    this.list = [...data.list];
                    console.log(this.list);
                }else{
                    this.list = [];
                    this.$alertify.error(data.message);
                }
            },
            (error)=>{
                console.log(error);
            })
        },
        async showInsertModal(){
            if(this.boardInsertModal == null){
                this.boardInsertModal = new Modal(document.getElementById("boardInsertModal"));
            }

            this.$refs.insert_modal.clearUpload();
            this.$refs.insert_modal.title = "";
            if(this.$refs.insert_modal.CKEditor != null){
                this.$refs.insert_modal.CKEditor.setData("");
            }
            this.boardInsertModal.show();
        },
        async showDetail(boardId){
            await this.postDetail({
                boardId: boardId, 
                userEmail: this.userInfo.email
            });

            if(this.boardResult.status == "SUCCESS"){
                if(this.boardDetailModal == null){
                    this.boardDetailModal = new Modal(document.getElementById("boardDetailModal"));
                }
                
                this.$refs.detail_modal.hideCollapse();
                this.boardDetailModal.show();
            }else{
                this.$alertify.error(this.boardResult.message);
            }
            this.$store.commit("boardStore/SET_RESULT_MESSAGE", null);
        },
        async showUpdate(boardId){
            await this.postDetail({boardId: boardId, userEmail:this.userInfo.email});
            if(this.boardResult.status == "SUCCESS"){
                if(this.update_modal == null){
                    this.boardUpdateModal = new Modal(document.getElementById("boardUpdateModal"));
                }

                this.$refs.update_modal.title = this.post.title;
                this.$refs.update_modal.content = this.post.content;
                this.$refs.update_modal.CKEditor.setData(this.post.content);
                
                this.boardUpdateModal.show();
                this.boardDetailModal.hide();
            }else{
                this.$alertify.error(this.boardResult.message);
            }
        },
        async getTotalCount(clsf){
            await listCount(clsf,
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        this.totalListItemCount = data.listCount;
                    }
                },
                (error)=>{
                    console.log(error);
                })
        },
        async closeInsertModal(){
            await this.callList();
            this.boardInsertModal.hide();
        },
        async closeDetailModal(){
            await this.callList();
            this.boardDetailModal.hide();
        },
        closeUpdateModal(){
            this.boardUpdateModal.hide();
            this.showDetail(this.post.boardId);
        },
        movePage(pageIndex){
            this.offset = (pageIndex - 1) * this.listRowCount;
            this.currentPageIndex = pageIndex;
            this.callList();
        },
    },
    computed:{
        ...mapState(userStore, ["userInfo"]),
        ...mapState(boardStore, ["boardList", "boardResult", "post"]),
    },
    async mounted() {
        await this.getTotalCount("001");
        await this.callList();

    },
}
</script>

<style>
</style>