<template>
<div>
    <div class="progress">
        <div
            class="progress-bar bg-success"
            role="progressbar"
            :aria-valuenow="progress"
            aria-valuemin="0"
            aria-valuemax="100"
            :style="{ width: progress + '%' }"
        >
        </div>
    </div>

    <label class="btn btn-default">
        <input type="file" ref="file" @change="selectFile"/>
    </label>
    <div class="alert alert-info text-white font-weight-bold" role="alert">
        {{ message }}
    </div>

    <div>
        <div class="px-3 py-1" v-for="(file, index) in fileInfos" :key="index">
            {{file.name}}
            <i class="material-icons" @click="deleteFile(file.name)">clear</i>
        </div>
    </div>
</div>
</template>

<script>
import http from "@/common/axios.js";
import {mapState, mapActions} from "vuex";

const userStore = "userStore";
const boardStore = "boardStore";

export default {
    data() {
        return {
            selectedFiles: undefined,
            progress: 0,
            message: "",

            fileInfos: []
        };
    },
    methods: {
        ...mapActions(userStore, ["getUserInfo"]),
        async tokenCheck(){
            let token = sessionStorage.getItem("access-token");
            await this.getUserInfo(token);
            if(this.userResult.status == "SUCCESS"){
                this.upload();
            }else{
                this.$alertify.error(this.userResult.message);
            }
        },
        selectFile(){
            this.selectedFiles = this.$refs.file.files;

            let target = this.fileInfos.findIndex(el => el.name == this.selectedFiles.item(0).name);
            console.log(target);
            if(target >= 0){
                this.message = "중복된 파일을 업로드할 수 없습니다."
            }else{
                this.message = "파일을 추가하였습니다."
                this.fileInfos.push(this.selectedFiles.item(0));
            }
        },
        deleteFile(fileName){
            for (let i = 0; i < this.fileInfos.length; i++) {
                if(this.fileInfos[i].name == fileName){
                    this.fileInfos.splice(i, 1);
                    this.message = "파일을 제거하였습니다."
                    break;
                } 
            }
        },
        upload() {
            this.progress = 0;
            let formData = new FormData();

            this.fileInfos.forEach(file=>{
                formData.append("file", file);
            })
            
            http.post(`/upload/${this.post.boardId}`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                },
                onUploadProgress: (event) => {
                    this.progress = Math.round((100 * event.loaded) / event.total);
                }
            })
            .then(({data}) => {
                this.message = data.message;
            })
            .catch(() => {
                this.progress = 0;
                this.message = "파일 업로드에 실패하였습니다!";
            });

            this.selectedFiles = undefined;
        }
    },
    computed:{
        ...mapState(userStore, ["userResult"]),
        ...mapState(boardStore, ["post"]),
    },
};
</script>

<style>

</style>