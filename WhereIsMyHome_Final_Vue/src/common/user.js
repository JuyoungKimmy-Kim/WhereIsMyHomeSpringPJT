import http from "./axios.js"

async function totalCount(success, fail){
    await http.get("/users/count").then(success).catch(fail);
}

async function userList(limit, offset, success, fail){
    await http.get(`/users/${limit}/${offset}`).then(success).catch(fail);
}

// 로그인
async function login(user, success, fail){
    await http.post("/login", user).then(success).catch(fail);
}

// 회원가입
async function signUp(user, success, fail){
    await http.post("/signup", user).then(success).catch(fail);
}

// 회원정보 수정
async function modifyInfo(user, success, fail){
    await http.put("/users", user).then(success).catch(fail);
}

async function validateToken(params, success, fail){
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await http.get(`/users/token`, {params}).then(success).catch(fail);
}

async function logout(params, success, fail){
    await http.get(`/logout`, {params}).then(success).catch(fail);
}

// access-token이 만료되면 refresh-token으로 재생성
async function createAccessToken(user, success, fail) {
    http.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
    await http.post("/users/token", user).then(success).catch(fail);
}


export { totalCount, userList, login, signUp, modifyInfo, validateToken, logout, createAccessToken }