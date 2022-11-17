import http from "./axios.js"

// 회원가입
async function signUp(user, success, fail){
    await http.post("/signup", user).then(success).catch(fail);
}


export { signUp }