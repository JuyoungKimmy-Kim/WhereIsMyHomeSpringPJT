import {listCount, detail, insert, update, deletePost} from '@/common/board.js';


const boardStore = {
    namespaced: true,
    state:{
        boardResult:{
            status: "",
            message: "",
        },
        post:{},
        totalListItemCount: 0,
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
            state.post = payload.post;

            if(payload.fileList != null){
                state.post.fileList = payload.fileList;
            }
        },
        SET_TOTAL_LIST_ITEM_COUNT(state, payload){
            state.totalListItemCount = payload;
        },
        SET_SAME_USER(state, payload){
            state.sameUser = payload.sameUser;
        }
    },
    actions:{
        async getTotalCount({commit}, boardClsf){
            await listCount(boardClsf,
                ({data})=>{
                    if(data.result == "SUCCESS"){
                        console.log(data);
                        commit("SET_TOTAL_LIST_ITEM_COUNT", data.listCount);
                    }
                },
                (error)=>{
                    console.log(error);
                })
        },
        async postDetail({commit}, payload){
            await detail(payload.boardId, payload.userEmail,
                ({data})=>{
                    console.log(data);
                    if(data.result == "SUCCESS"){
                        console.log(data);
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
                        commit("SET_POST", data.post);
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