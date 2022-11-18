import http from "./axios.js"

// 회원가입
async function signUp(user, success, fail){
    await http.post("/signup", user).then(success).catch(fail);
}

async function login(user, success, fail){
    await http.post("/login", user).then(success).catch(fail);
}

async function findByEmail(email, success, fail){
    http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await http.get(`/info/${email}`).then(success).catch(fail);
}

async function logout(email, success, fail){
    await http.get(`/logout/${email}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
    http.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
    await http.post("/regen", user).then(success).catch(fail);
}

export { signUp, login, findByEmail, logout, tokenRegeneration }