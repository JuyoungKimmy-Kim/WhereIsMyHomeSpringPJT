import {detail, insert, update, deletePost} from '@/common/board.js';
import util from '@/common/utils.js';


const boardStore = {
    namespaced: true,
    state:{
        boardResult:{
            status: "",
            message: "",
        },
        post:{},
        sameUser: false,
    },
    mutations:{
        SET_RESULT_MESSAGE(state, payload){
            if(payload == null){
                state.boardResult = {};
            }else{
                state.boardResult = {
                    status: payload.result,
                    message: payload.message
                };
            }
        },
        SET_POST(state, payload){
            state.post = payload.board;
            
            state.post.regDate = util.makeDateStr(state.post.regDt.date.year, state.post.regDt.date.month, state.post.regDt.date.day, "/");
            state.post.regTime = util.makeTimeStr(state.post.regDt.time.hour, state.post.regDt.time.minute,state.post.regDt.time.second, ":");
            
            if(payload.fileList != null){
                state.post.fileList = payload.fileList;
            }
        },
        SET_SAME_USER(state, payload){
            state.sameUser = payload.sameUser;
        }
    },
    actions:{
        async postDetail({commit}, payload){
            await detail(payload.boardId, payload.userEmail,
                ({data})=>{
                    console.log(data);
                    if(data.result == "SUCCESS"){
                        commit("SET_POST", data);
                    }
                    commit("SET_RESULT_MESSAGE", data);
                },
                (error)=>{
                    console.log(error);
                });
        },
        async insertPost({commit}, post){
            console.log("post >> ");
            console.dir(post);
            await insert(post,
                ({data})=>{
                    commit("SET_POST", data);
                    commit("SET_RESULT_MESSAGE", data);
                },
                (error)=>{
                    console.log(error);
                })
        },
        async updatePost({commit}, post){
            await update(post,
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        commit("SET_POST", data);
                    }
                    
                    commit("SET_RESULT_MESSAGE", data);              
                },
                (error=>{
                    console.log(error);
                }));
        },
        async deletePost({commit}, boardId){
            await deletePost(boardId,
                ({data})=>{
                    commit("SET_RESULT_MESSAGE", data);
                },
                (error)=>{
                    console.log(error);
                })
        }
    },

}

export default boardStore;