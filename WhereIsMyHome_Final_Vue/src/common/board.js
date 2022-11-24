import http from "./axios.js";

async function list(limit, offset, success, fail){
    await http.get(`/boards/${limit}/${offset}`).then(success).catch(fail);
}

async function listCount(boardClsf, success, fail){
    await http.get(`/boards/list/${boardClsf}`).then(success).catch(fail);
}

async function detail(boardId, userEmail, success, fail){
    await http.get(`/boards/${boardId}?userEmail=${userEmail}`).then(success).catch(fail);
}

async function insert(post, success, fail){
    await http.post("/boards", post).then(success).catch(fail);
}

async function update(post, success, fail){
    await http.put("/boards", post).then(success).catch(fail);
}

async function deletePost(boardId, success, fail){
    await http.delete(`/boards/${boardId}`).then(success).catch(fail);
}

async function getWishList(userSeq, success, fail){
    await http.get(`/boards/wish/${userSeq}`).then(success).catch(fail);
}

export {list, listCount, detail, insert, update, deletePost, getWishList};