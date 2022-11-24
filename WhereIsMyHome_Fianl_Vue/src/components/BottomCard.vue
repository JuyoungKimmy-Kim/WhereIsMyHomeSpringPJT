<template>
<section class="pt-8 pb-7">
    <div class="container">
    <div class="row">
        <div class="col-lg-4 col-md-6">
        <div class="card">
            <div class="card-header p-0 mx-3 mt-n4 position-relative z-index-2">
            <a class="d-block blur-shadow-image">
                <img :src="img[0]" alt="img-blur-shadow" class="img-fluid border-radius-lg">
            </a>
            </div>
            <div class="card-body pt-3">
                <h5> {{ this.cheapestPropertyList[0].aptName }} {{ numberToKorean(this.cheapestPropertyList[1].dealAmount) }}만원</h5>
                <p>거래 일자 : {{this.cheapestPropertyList[0].dealYear}}/{{this.cheapestPropertyList[0].dealMonth}}/{{this.cheapestPropertyList[0].dealDay}}</p>
            <div style="float:right;" class="text-info icon-move-right" @click="showDetail()">지금 바로 보러가기
              <i class="fas fa-arrow-right text-sm" aria-hidden="true"></i>
            </div>
            </div>
        </div>
        </div>
        <div class="col-lg-4 col-md-6">
        <div class="card">
            <div class="card-header p-0 mx-3 mt-n4 position-relative z-index-2">
            <a class="d-block blur-shadow-image">
                <img :src="img[1]" alt="img-blur-shadow" class="img-fluid border-radius-lg">
            </a>
            </div>
            <div class="card-body pt-3">
                <h5>{{ this.cheapestPropertyList[1].aptName }} {{ numberToKorean(this.cheapestPropertyList[1].dealAmount) }}만원</h5>
                <p>거래 일자 : {{this.cheapestPropertyList[1].dealYear}}/{{this.cheapestPropertyList[1].dealMonth}}/{{this.cheapestPropertyList[1].dealDay}}</p>
                <div style="float:right;" class="text-info icon-move-right" @click="showDetail()">지금 바로 보러가기
                <i class="fas fa-arrow-right text-sm" aria-hidden="true"></i>
                </div>
            </div>

        </div>
        </div>
        <div class="col-lg-4 col-md-6">
        <div class="card">
            <div class="card-header p-0 mx-3 mt-n4 position-relative z-index-2">
            <a class="d-block blur-shadow-image">
                <img :src="img[2]" alt="img-blur-shadow" class="img-fluid border-radius-lg">
            </a>
            </div>
            <div class="card-body pt-3">
                <h5>{{ this.cheapestPropertyList[2].aptName }} {{ numberToKorean(this.cheapestPropertyList[1].dealAmount) }}만원</h5>
                <p>거래 일자 : {{this.cheapestPropertyList[2].dealYear}}/{{this.cheapestPropertyList[2].dealMonth}}/{{this.cheapestPropertyList[2].dealDay}}</p>
            <div style="float:right;" class="text-info icon-move-right" @click="showDetail()">지금 바로 보러가기
              <i class="fas fa-arrow-right text-sm" aria-hidden="true"></i>
            </div>
            </div>
        </div>
        </div>
    </div>
    </div>
</section>
</template>


<script>
import { cheapestProperty } from "@/common/map.js";
// import {mapState} from "vuew";
// const mapStore = "mapStore";
// const userStore = "userStore";

export default {
    data () {
        return {
            cheapestPropertyList:[],
            gugunCode: '11170', //default
            img : [
                require("@/assets/img/apt1.jpg"),
                require("@/assets/img/apt2.jpg"),
                require("@/assets/img/apt3.jpg"),
            ]
        }
    },
    created () {        
        if (this.userInfo==null) {
            this.gugunCode='11170';
        } else {
            this.gugunCode = this.userInfo.gugunCode
        }
        this.getCheapestProperty(this.gugunCode);
    },
    mounted () {

    },
    computed : {
        // ...mapState(userStore, ["userInfo"]),
        // ...mapState(mapStore, ["map"]),
    },
    methods : {
        async getCheapestProperty(gugunCode) {
            console.log ('Cheapest Property 구하러 들어옴!')
            await cheapestProperty(
                gugunCode,
                ({data}) => {
                    this.cheapestPropertyList=data;
                    console.log(this.cheapestPropertyList);
                },
                (error) => {
                   console.log(error);
                }
            );
            return this.gugunCode;
        },
        numberToKorean(number) {
            var inputNumber = number < 0 ? false : number;
            var unitWords = ["", "억", "조", "경"];
            var splitUnit = 10000;
            var splitCount = unitWords.length;
            var resultArray = [];
            var resultString = "";

            for (var i = 0; i < splitCount; i++) {
            var unitResult =
                (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
            unitResult = Math.floor(unitResult);
            if (unitResult > 0) {
                resultArray[i] = unitResult;
            }
            }
            for (i = 0; i < resultArray.length; i++) {
            if (!resultArray[i]) continue;
            resultString = String(resultArray[i]) + unitWords[i] + resultString;
            }
            return resultString;
        },
    },
    watch : {
        
    },

}
</script>

<style>

</style>