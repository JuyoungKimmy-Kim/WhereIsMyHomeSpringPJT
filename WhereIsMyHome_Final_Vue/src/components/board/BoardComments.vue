<template>
  <div class="col-md-11 ms-4 me-auto pt-5">
    <div>
      <div class="d-flex" v-for="(comment, index) in list" :key="index">
        <div>
          <a href="javascript:;">
            <div class="position-relative">
              <div class="blur-shadow-avatar rounded-circle">
                <img class="avatar" :src="require(`@/assets/img/profile${comment.profileImageUrl}`)" alt="...">
              </div>
            </div>
          </a>
        </div>
        <div class="container">
          <div class="row justify-content-start pt-0">
            <h6 class="px-0">{{comment.name}} <span class="text-muted text-xs">· {{timeForToday(comment.regDt)}}</span></h6>
            {{comment.content}}
          </div>
          <div class="text-end justify-content-end">
            <a v-show="userInfo.email == comment.email" @click="rmvComment(comment.no)" class="btn text-dark px-2 btn-link">
              <i class="material-icons">delete</i>삭제
            </a>
            <a @click="plusHeart(comment)" class="btn text-danger px-2 btn-link">
              <i class="fas fa-heart"></i> {{comment.heart}}
            </a>
          </div>
        </div>

      </div>
    </div>
    <div class="d-flex" v-if="isLogin">
      <div>
        <a class="author" href="javascript:;">
          <div class="position-relative">
            <div class="blur-shadow-avatar rounded-circle">
              <img class="avatar" alt="64x64" :src="require(`@/assets/img/profile${userInfo.profileImageUrl}`)">
            </div>
          </div>
        </a>
      </div>
      <div class="ms-3 w-100">
        <div class="input-group input-group-static">
          <textarea class="form-control" placeholder="메시지를 입력하세요..." rows="4" v-model="content"></textarea>
        </div>
        <div>
          <a href="javascript:;" class="btn bg-gradient-success pull-end mt-2" @click="submit">
            <i class="fa fa-send me-2"></i> 작성
          </a>
        </div>
      </div>
    </div> <!-- end media-post -->
  </div>
</template>

<script>
import {list, submitComment, removeComment, increaseHeart} from '@/common/comments';

import {mapState, mapActions} from 'vuex';

const boardStore = "boardStore";
const userStore = "userStore";

export default {
  data() {
    return {
      list: [],
      content: "",
    }
  },
  computed:{
    ...mapState(boardStore, ["post"]),
    ...mapState(userStore, ["userInfo", "isLogin"]),
  },
  methods: {
    ...mapActions(userStore, ["getUserInfo"]),
    async getList(){
      this.list = [];
      await list(this.post.boardId,
        ({data})=>{
          let tmpList = data;
          tmpList.forEach(el => {
            if(el.name == this.post.writer){
              el.name = "작성자";
            }
            this.list.push(el);
          });
        },
        (error)=>{
          console.log(error);
        }
      )
    },
    async submit(){
      let token = sessionStorage.getItem("access-token");
      await this.getUserInfo(token);

      const comment = {
        email: this.userInfo.email,
        boardId: this.post.boardId,
        content: this.content,
      }

      await submitComment(comment,
        ({data})=>{
          if(data.result == "SUCCESS"){
            this.content = "";
            this.getList();
          }
        },
        (error)=>{
            console.log(error);
        }
      )
    },
    rmvComment(no){
      this.$alertify.confirm("삭제하시겠습니까?", async ()=>{
        await removeComment(no
        , ()=>{
          this.getList();
        }
        ,(error)=>{
            console.log(error);
          }
        )
      })
    },
    async plusHeart(comment){
      if(!this.isLogin) return;
    
      const params = {
        email: this.userInfo.email,
        boardId: this.post.boardId,
        no: comment.no,
      }
      await increaseHeart(params,
        ()=> {
          this.getList();
        },
        (error)=>{
          console.log(error);
        }
      )
    },
    timeForToday(dateTime) {
      const start = new Date(dateTime.date.year, dateTime.date.month - 1, dateTime.date.day, dateTime.time.hour, dateTime.time.minute, dateTime.time.second);
      const end = new Date(); // 현재 날짜
      
      const diff = (end - start); // 경과 시간
    
      const times = [
        {time: "분", milliSeconds: 1000 * 60},
        {time: "시간", milliSeconds: 1000 * 60 * 60},
        {time: "일", milliSeconds: 1000 * 60 * 60 * 24},
        {time: "개월", milliSeconds: 1000 * 60 * 60 * 24 * 30},
        {time: "년", milliSeconds: 1000 * 60 * 60 * 24 * 365},
      ].reverse();
      
      // 년 단위부터 알맞는 단위 찾기
      for (const value of times) {
        const betweenTime = Math.floor(diff / value.milliSeconds);
        
        // 큰 단위는 0보다 작은 소수 단위 나옴
        if (betweenTime > 0) {
          return `${betweenTime}${value.time} 전`;
        }
      }
      
      // 모든 단위가 맞지 않을 시
      return "방금 전";
    }
  }
}
</script>

<style>
.row {
    padding-top: 5%;
}


</style>