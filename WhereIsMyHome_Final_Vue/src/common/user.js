import http from "./axios.js"

async function totalList(success, fail){
    await http.get("/users").then(success).catch(fail);
}

async function userList(limit, offset, success, fail){
    await http.get(`/users/${limit}/${offset}`).then(success).catch(fail);
}

// 회원가입
async function signUp(user, success, fail){
    await http.post("/signup", user).then(success).catch(fail);
}

async function login(user, success, fail){
    await http.post("/login", user).then(success).catch(fail);
}

async function findByEmail(email, success, fail){
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await http.get(`/users/${email}`).then(success).catch(fail);
}

async function logout(email, success, fail){
    await http.get(`/logout/${email}`).then(success).catch(fail);
}

// access-token이 만료되면 refresh-token으로 재생성
async function tokenRegeneration(user, success, fail) {
    http.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
    await http.post("/regen", user).then(success).catch(fail);
}

async function update(user, success, fail){
    await http.put("/users", user).then(success).catch(fail);
}

export { totalList, userList, signUp, login, findByEmail, logout, tokenRegeneration, update }