import http from "./axios.js";

async function list(boardId, success, fail){
    await http.get(`/comments/${boardId}`).then(success).catch(fail);
}

async function submitComment(comment, success, fail){
    await http.post("/comments", comment).then(success).catch(fail);
}

async function removeComment(no, success, fail){
    await http.delete(`/comments/${no}`).then(success).catch(fail);
}

async function increaseHeart(params, success, fail){
    console.log(params)
    await http.put(`/comments`, params).then(success).catch(fail);
}

export {list, submitComment, removeComment, increaseHeart};