<template>
<div>
    <div v-if="currentFile" class="progress">
        <div
            class="progress-bar progress-bar-info progress-bar"
            role="progressbar"
            :aria-valuenow="progress"
            aria-valuemin="0"
            aria-valuemax="100"
            :style="{ width: progress + '%' }"
        >
            {{ progress }}%
        </div>
    </div>

    <label class="btn btn-default">
        <input type="file" ref="file" @change="selectFile" />
    </label>
    <button class="btn bg-gradient-secondary  btn-sm" v-show="selectedFiles" @click="upload">
        업로드
    </button>

    <div class="alert alert-light alert-dismissible fade show" role="alert">
        {{ message }}
    </div>
</div>
</template>

<script>
import UploadService from "@/common/UploadFilesService";

export default {
    data() {
        return {
            selectedFiles: undefined,
            currentFile: undefined,
            progress: 0,
            message: "",

            fileInfos: []
        };
    },
    methods: {
        selectFile(){
            this.selectedFiles = this.$refs.file.files;
        },
        upload() {
        this.progress = 0;

        this.currentFile = this.selectedFiles.item(0);
        UploadService.upload(this.currentFile, event => {
                this.progress = Math.round((100 * event.loaded) / event.total);
            })
            .then(response => {
                this.message = response.data.message;
                return UploadService.getFiles();
            })
            .then(files => {
                this.fileInfos = files.data;
            })
            .catch(() => {
                this.progress = 0;
                this.message = "파일 업로드에 실패하였습니다!";
                this.currentFile = undefined;
            });

        this.selectedFiles = undefined;
        }
    },
};
</script>

<style>

</style>