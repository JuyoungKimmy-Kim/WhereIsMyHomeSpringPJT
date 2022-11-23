<template>
<section class="py-4">
    <div class="container">
        <div class="row justify-space-between py-2">
        <div class="col-lg-4 mx-auto">
            <ul class="pagination pagination-info m-4 justify-content-center">
                <li v-if="prev" class="page-item">
                    <a class="page-link" @click="paginationChanged(startPageIndex - 1)" aria-label="Previous">
                        <span aria-hidden="true">
                            <i class="material-icons" aria-hidden="true">chevron_left</i>
                        </span>
                    </a>
                </li>
                <li v-for="index in endPageIndex - startPageIndex + 1" :key="index" :class="[ isActive(index) ? 'page-item active' : 'page-item']">
                    <a class="page-link" @click="paginationChanged(startPageIndex - 1 + index)">
                        {{startPageIndex - 1 + index}}
                    </a>
                </li>
                <li v-if="next" class="page-item">
                    <a class="page-link" href="javascript:;" aria-label="Next">
                    <span aria-hidden="true"><i class="material-icons" aria-hidden="true">chevron_right</i></span>
                    </a>
                </li>
            </ul>
        </div>
        </div>
    </div>
</section>
</template>

<script>

const boardStore = "boardStore";

export default {
    props: [
        "listRowCount",
        "pageLinkCount",
        "currentPageIndex",
        "totalListItemCount"
    ],
    methods: {
        paginationChanged(pageIndex) {
            this.$emit("call-move-page", pageIndex);
        },
        isActive(index){
            return this.currentPageIndex == index; 
        }
    },
    computed:{
        pageCount() {
            return Math.ceil(this.totalListItemCount / this.listRowCount);
        },
        startPageIndex() {
            if (this.currentPageIndex % this.pageLinkCount == 0) {
                return ((this.currentPageIndex / this.pageLinkCount) - 1) * this.pageLinkCount + 1;
            } else {
                return (Math.floor(this.currentPageIndex / this.pageLinkCount) * this.pageLinkCount + 1);
            }
        },
        endPageIndex() {
            if (this.currentPageIndex % this.pageLinkCount == 0) {
                return ((this.currentPageIndex / this.pageLinkCount)-1) * this.pageLinkCount + this.pageLinkCount;
            } else {
                if(Math.floor(this.currentPageIndex / this.pageLinkCount) * this.pageLinkCount + this.pageLinkCount > this.pageCount){
                    return this.pageCount;
                }
                return (Math.floor(this.currentPageIndex / this.pageLinkCount) * this.pageLinkCount + this.pageLinkCount);
            }
        },
        prev() {
            if (this.currentPageIndex <= this.pageLinkCount) {
                return false;
            } else {
                return true;
            }
        },
        next() {
            if( ( Math.floor( this.pageCount / this.pageLinkCount ) * this.pageLinkCount ) < this.currentPageIndex){
                return false;
            }else{
                return true;
            }            
        }
    }
}
</script>

<style>

</style>