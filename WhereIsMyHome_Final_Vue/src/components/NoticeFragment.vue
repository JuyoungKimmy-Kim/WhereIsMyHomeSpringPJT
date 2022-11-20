<template>
    <table class="table align-items-center mb-0">
        <thead>
        <tr>
            <th scope="col" class="text-uppercase text-secondary text-s font-weight-bolder opacity-7 col-3 ps-3">#</th>
            <th scope="col" class="text-uppercase text-secondary text-s font-weight-bolder opacity-7 col-8 ps-2">제목</th>
            <th scope="col" class="text-uppercase text-secondary text-s font-weight-bolder opacity-7 col-1 ps-2">작성 날짜</th>
            <th scope="col" class="text-uppercase text-secondary text-s font-weight-bolder opacity-7 col-1 ps-2">조회 수</th>
        </tr>
        </thead>
        <tbody>
            <tr v-for="(post, index) in list" :key="index">
                <th scope="row">
                    <div class="d-flex px-2 py-1">
                    <div class="d-flex flex-column justify-content-center">
                        <h6 class="mb-0 text-s">{{post.boardId}}</h6>
                    </div>
                    </div>
                </th>
                <td>
                    <a class="text-s font-weight-bold mb-0 cursor-pointer" @click="gotoNotice(post.boardId)">{{post.title}}</a>
                </td>
                <td>
                    <span class="text-secondary text-s font-weight-bold">{{post.regDate}}</span>
                </td>
                <td>
                    <span class="text-secondary text-s font-weight-bold">{{post.readCount}}</span>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<script>

import {list} from '@/common/board.js';
import util from '@/common/utils.js';


export default {
    data() {
        return {
            limit: 5,
            offset: 0,
            list:[],
        }
    },
    methods:{
        async callList(){
            await list(this.limit, this.offset, ({data})=>{
                console.log(data);
                if(data.result == "SUCCESS"){
                    data.list.forEach(el => {
                        let date = new Date(el.regDt);
                        
                        el.regDate = util.makeDateStr(date.getFullYear(), date.getMonth() + 1, date.getDate(), "/");
                        el.regTime = util.makeTimeStr(date.getHours(), date.getMinutes(), date.getSeconds(), ":");
                    });
                    this.list = data.list;
                }else{
                    this.list = [];
                    this.$alertify.error(data.message);
                }
            },
            (error)=>{
                console.log(error);
            })
        },
        gotoNotice(boardId){
            this.$emit("show-this-notice", boardId);
        }
    },
    mounted(){
        this.callList();
    }
}
</script>

<style>

</style>