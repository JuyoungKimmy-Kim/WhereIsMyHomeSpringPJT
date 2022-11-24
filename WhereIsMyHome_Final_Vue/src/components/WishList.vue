<template>
<div>
    <header-page></header-page>

    <div class="card card-body sticky blur shadow-blur mx-3 mx-md-6 mt-n6">
        <section>
        <div class="container">
            <h4 class="row justify-content-center">찜목록 관리</h4>
            <div class="row justify-content-center pb-5">
            <div class="col-lg-11">
                <div class="card">
                <div class="table-responsive" style="overflow-x: hidden">
                    <table class="table align-items-center mb-0">
                        <thead>
                            <tr bgcolor="#2F4858">
                            <th class="text-white text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 col-8">매물</th>
                            <th class="text-white text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2 col-3">가격</th>
                            <th class="text-white text-secondary text-xxs opacity-7 text-center col-1">목록에서 삭제</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(property, index) in list" :key="index">
                                <td>
                                    <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="@/assets/img/apt/송정삼정그린코아더시티.jpg" class="avatar avatar-sm me-3">
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <a class="text-sm font-weight-bold mb-0 cursor-pointer">{{property.aptname}}</a>
                                        <p class="text-xs text-secondary mb-0">{{property.buildyear}}</p>
                                    </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">단위 (만원)</p>
                                    <p class="text-xs text-secondary mb-0">{{property.dealAmount}}</p>
                                </td>
                                <td class="align-middle text-center">
                                    <button type="button" class="btn btn-outline-danger btn-sm me-2" @click="deleteThis(index, property.code)">삭제</button>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                </div>
            </div>
            </div>
        </div>
    </section>
    </div>
</div>
</template>

<script>
import HeaderPage from '@/components/HeaderPage.vue'
import util from '@/common/utils'

import {deleteFavorite, getWishList} from '@/common/wishList'
import {mapState} from 'vuex'

export default {
    components:{
        HeaderPage
    },
    data() {
        return {
            list: [],
        }
    },
    async mounted(){

        this.list = [];

        const seq = this.userInfo.seq;
        await getWishList(seq,
            ({data})=>{
                data.forEach(element => {
                    if(element.dealAmount > 0){
                        element.dealAmount = util.numberToKorean(element.dealAmount);
                    }
                    this.list.push(element);
                });
            },(error)=>{
                console.log(error);
                this.$router.push("/");
            }
        );
    },
    computed:{
        ...mapState("userStore", ["userInfo"])
    },
    methods: {
        async deleteThis(index, no){
            await deleteFavorite({
                    houseNo: no,
                    userSeq: this.userInfo.seq,
                },
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        this.$store.commit("userStore/REMOVE_WISH_LIST", no);
                        this.list.splice(index, 1);
                    }
                },(error)=>console.log(error));
        }
    },
}
</script>

<style>
</style>