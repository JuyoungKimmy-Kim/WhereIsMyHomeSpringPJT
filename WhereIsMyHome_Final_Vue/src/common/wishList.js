import http from "@/common/axios"

async function getWishList(userSeq, success, fail){
    await http.get(`/wish/${userSeq}`).then(success).catch(fail);
}

async function insertFavorite(params, success, fail){
    await http.post("/wish", params).then(success).catch(fail);
}

async function deleteFavorite(params, success, fail){
    await http.delete("/wish", {data:params}).then(success).catch(fail);
}

export { getWishList, insertFavorite, deleteFavorite }