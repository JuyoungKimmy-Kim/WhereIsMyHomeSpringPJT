<template>
<div id="newSection" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div :class="[index == 0 ? 'carousel-item active' : 'carousel-item']" v-for="(news, index) in newsList" :key="index">
            <img :src=news.url class="d-block w-100">
            <div class="carousel-caption d-none d-md-block">
                <a :href=news.link target='_blank'>
                    <h4 v-html=news.title></h4>
                    <p v-html=news.description></p>
                </a>
            </div>    
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#newSection" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#newSection" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
</template>

<script>
import http from '@/common/axios';


export default {
    data() {
        return {
            newsList:[],
        }
    },
    mounted() {
        http.get("/boards/news")
        .then(({data})=>{
            data.items.forEach(el=>{
                this.newsList.push(el)
            });

            
        this.newsList[0].url = "https://images.unsplash.com/photo-1495020689067-958852a7765e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=450&h=300";
        this.newsList[1].url = "https://images.unsplash.com/photo-1523995462485-3d171b5c8fa9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDF8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=450&h=300"
        })

    },
};
</script>

<style scoped>
a h4,p {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: auto;
    background-color:white;
}
</style>