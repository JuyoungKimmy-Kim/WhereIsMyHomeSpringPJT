import http from "./axios.js"

async function sidoList(success, fail){
    await http.get("/address").then(success).catch(fail);
}

async function gugunList(sidoCode, success, fail){
    await http.get(`/address/${sidoCode}`).then(success).catch(fail);
}

async function dongList(sidoCode, gugunCode, success, fail){
    await http.get(`/address/${sidoCode}/${gugunCode}`).then(success).catch(fail);
}

async function propertyListByRegionCode(regionCode, success, fail){
    await http.get(`/property/${regionCode}`).then(success).catch(fail);
}

async function propertyListByDongName(dongName, gugunName, success, fail){
    await http.get(`/property/${dongName}/${gugunName}`).then(success).catch(fail);
}

async function propertyDetail(houseNo, success, fail) {
    await http.get(`/propertyDetail/${houseNo}`).then(success).catch(fail);
}

async function stationList(success, fail) {
    await http.get("/stations").then(success).catch(fail);
}

export {sidoList, gugunList, dongList, propertyListByRegionCode, propertyListByDongName, stationList, propertyDetail}