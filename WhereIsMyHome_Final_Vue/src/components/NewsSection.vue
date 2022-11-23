<template>
<div id="newSection" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div :class="[index == 0 ? 'carousel-item active' : 'carousel-item']" v-for="(news, index) in newsList" :key="index">
            <img :src=news.image_url class="d-block w-100" :alt=news.image_alt>
            <div class="carousel-caption d-none d-md-block">
                <a :href=news.url target='_blank'>
                    <h4>{{news.title}}</h4>
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
import cheerio from 'cheerio';


export default {
    data() {
        return {
            newsList:[],
        }
    },
    mounted() {
        http.get("https://cors-anywhere.herokuapp.com/https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260",{
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,OPTIONS',
                'Access-Control-Allow-Headers': 'Content-Type, Authorization, Content-Length, X-Requested-With'
            }
        })
        .then(html => {
            this.newsList = [];
            const $ = cheerio.load(html.data);
            const $bodyList = $("ul.type06_headline").children("li")
            
            for(let i = 0; i < 2; i++){
                
                let $this = $bodyList.get(i);
                this.newsList[i] = {
                    title: $($this).find('dt.photo a img').attr('alt'),
                    url: $($this).find('dt.photo a').attr('href'),
                    image_url: $($this).find('dt.photo a img').attr('src'),
                    image_alt: $($this).find('dt.photo a img').attr('alt'),
                };
            }

            const data = this.newsList.filter(n => n.title);
            return data;
        })
        .then(res => {
            console.log(res)
        })
        .catch(error=>{
            console.log(error)
        });
    },
};
</script>

<style scoped>
a h4 {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: auto;
  background-color:white;
}
</style>