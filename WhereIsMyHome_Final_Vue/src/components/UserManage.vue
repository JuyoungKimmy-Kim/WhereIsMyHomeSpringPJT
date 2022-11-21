<template>
<div>
    <header-page></header-page>

    <div class="card card-body sticky blur shadow-blur mx-3 mx-md-6 mt-n6">
        <section>
        <div class="container">
            <h4 class="row justify-content-center">사용자 관리</h4>
            <div class="row justify-content-center pb-5">
            <div class="col-lg-11">
                <div class="card">
                <div class="table-responsive" style="overflow-x: hidden; overflow-y: hidden">
                    <table class="table align-items-center mb-0">
                    <thead>
                        <tr>
                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 col-6">유저</th>
                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2 col-1">관리</th>
                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 col-1">가입일자</th>
                        <th class="text-secondary text-xxs opacity-7 col-1"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(user, index) in userlist" :key="index">
                            <td>
                                <div class="d-flex px-2 py-1">
                                <div>
                                    <img src="@/assets/img/team-2.jpg" class="avatar avatar-sm me-3">
                                </div>
                                <div class="d-flex flex-column justify-content-center">
                                    <h6 class="mb-0 text-xs">{{user.name}}</h6>
                                    <p class="text-xs text-secondary mb-0">{{user.email}}</p>
                                </div>
                                </div>
                            </td>
                            <td>
                                <p class="text-xs font-weight-bold mb-0">{{user.role}}</p>
                                <p class="text-xs text-secondary mb-0">Organization</p>
                            </td>
                            <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">{{user.regDate}}</span>
                            </td>
                            <td class="align-middle">
                                <div class="btn-group dropdown">
                                    <a class="text-secondary font-weight-bold text-xs dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Edit</a>
                                    <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton">
                                        <li><a class="dropdown-item border-radius-md" href="javascript:;">옵션1</a></li>
                                        <li><a class="dropdown-item border-radius-md" href="javascript:;">옵션2</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                    </table>
                </div>
                </div>
            </div>
            
            <pagination-ui @call-move-page ="movePage" :listRowCount="listRowCount" :pageLinkCount="pageLinkCount" :currentPageIndex="currentPageIndex" :totalListItemCount="totalListItemCount"></pagination-ui>

            </div>
        </div>
    </section>
    </div>
</div>
</template>

<script>
import HeaderPage from '@/components/HeaderPage.vue'
import PaginationUi from '@/components/PaginationUI.vue'

import {mapState, mapActions} from 'vuex'
import {userList, totalCount} from '@/common/user.js';
import util from '@/common/utils.js';

const userStore = "userStore";

export default {
    components:{
        HeaderPage,
        PaginationUi
    },
    data() {
        return {
            limit: 7,
            offset: 0,
            listRowCount: 7,
            pageLinkCount: 5,
            currentPageIndex: 1,
            totalListItemCount: 0,
            
            
            userlist:[],
        }
    }, 
    computed:{
        ...mapState(userStore, ["userResult"]),
    },
    methods: {
        ...mapActions(userStore, ["getUserInfo"]),
        async getUserListCount(){
            await totalCount(
                ({data})=>{
                    this.totalListItemCount = data.count;
                },
                (error)=>{
                    console.log(error);
                }
            )

        },
        async callUserList(){
            await userList(this.limit, this.offset,
                ({data})=>{
                    this.userlist = data;   
                    this.userlist.forEach(el=>{
                        el.regDate = util.makeDateStr(el.regDt.date.year, el.regDt.date.month, el.regDt.date.day, "/");
                        el.regTime = util.makeTimeStr(el.regDt.time.hour, el.regDt.time.minute, el.regDt.time.second, ":");
                    })
                }, 
                (error)=>{
                    console.log("유저목록 호출 에러 => " + error);
                })
        },
        movePage(pageIndex){
            this.offset = (pageIndex - 1) * this.listRowCount;
            this.currentPageIndex = pageIndex;
            this.callUserList();
        },
    },
    async mounted(){
        
        let token = sessionStorage.getItem("access-token");
        await this.getUserInfo(token);

        if(this.userResult.status != "SUCCESS"){
            this.$alertify.error(this.userResult.message);
            this.$store.commit("userStore/CLEAR_RESULT_MESSAGE");
            this.$router.push("/");
        }
        
        this.getUserListCount();
        this.callUserList();
    }
}
</script>

<style>
</style>