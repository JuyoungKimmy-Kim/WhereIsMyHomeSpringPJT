<template>
<div>
  <h4 style="display:inline;">{{this.map.property.aptName}}</h4>
  <div @click="addToCart()"> 
    <div v-if="addToCartMode"><img src="@/assets/img/red_heart.png" width="30" height="30" style="float:right;"></div>
    <div v-if="!addToCartMode"><img src="@/assets/img/empty_heart.png" width="30" height="30" style="float:right;"></div>
    </div>
    <canvas id="chart-data"></canvas>


<!-- 세부 사항 시작-->
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-8">
                <h5>도로명 주소 / 지번 주소</h5>
                <li class="list-group-item">{{this.address}}</li>
                <li class="list-group-item">{{this.jibunAddress}}</li>
            </div>
            <div class="col-lg-4">
                <h5>평균 거래가</h5>
                <p>{{this.avgString}} (만원)</p>
                <p></p>
            </div>
        </div>
    </div>
<!-- 세부 사항 종료 -->


</div>
</template>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=741cfff0b0e1d74a405abcb9f34aee54&libraries=services"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0"></script>
<script>

import Chart from 'chart.js';
import ChartData from '@/common/chart-data.js';

import {propertyDetail} from "@/common/map.js";
import { mapState } from "vuex";
const mapStore = "mapStore";

export default {
    data() {
        return {
            ChartData,
            houseNo:'',
            detailList:[],
            month:[0,0,0,0,0,0,0,0,0,0,0,0],
            count:[0,0,0,0,0,0,0,0,0,0,0,0],
            avg:0, 
            avgString:'',
            addToCartMode: false,

            coord:[0,0],
            geocoder:'',
            regionCode:'', //행정동 주소
            address:'', //법정동 주소
            jibunAddress:'',
        }
    },
    computed : {
      ...mapState(mapStore, ["map"]),
    },
    created () {
        this.geocoder = new kakao.maps.services.Geocoder();
        this.houseNo=this.map.property.no;
        this.coord[0]=this.map.property.lng;
        this.coord[1]=this.map.property.lat;
        
    },
    async mounted () {
        await this.getDetailList();
        this.searchAddrFromCoords(this.coord, this.getAddress);
        this.searchDetailAddrFromCoords(this.coord, this.detailAddress);
    },
    methods : {
        addToCart() {
            if (!this.addToCartMode) {
                this.addToCartMode=true;
            } else {
                this.addToCartMode=false;
            }
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
        for (var i = 0; i < resultArray.length; i++) {
        if (!resultArray[i]) continue;
        resultString = String(resultArray[i]) + unitWords[i] + resultString;
        }
        return resultString;
        },

        ///////////////////////////////// 지번/도로명/법정동 주소 시작
        getAddress(result, status) {
            if (status === kakao.maps.services.Status.OK) {

                for(var i = 0; i < result.length; i++) {
                    // 행정동의 region_type 값은 'H' 이므로
                    if (result[i].region_type === 'H') {
                        this.regionCode = result[i].address_name;
                        break;
                    }
                }
                console.log(this.regionCode);
            }
        },
        searchAddrFromCoords(coords, callback) {
            this.geocoder.coord2RegionCode(coords[0], coords[1], callback);
        },
        searchDetailAddrFromCoords(coords, callback) {
            this.geocoder.coord2Address(coords[0], coords[1], callback);
        },
        detailAddress(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                this.address=result[0].road_address.address_name;
                this.jibunAddress=result[0].address.address_name;
                console.log(this.address);
                console.log(this.jibunAddress);
            }   
        },
        ///////////////////////////////// 지번/도로명/법정동 주소 끝


        //////////////////////////////////// 차트 생성, 관련 데이터
        setMonthDetailList() {
            console.log ("----Set Detail List----")
            for (var i=0; i<this.detailList.length; i++) {
                var m=this.detailList[i].dealMonth;
                var dealAmount=this.detailList[i].dealAmount;
                this.month[m]+=parseInt(dealAmount);
                this.count[m]++;
            }

            var totalCount=0, totalAmount=0;
            for (i=0; i<12; i++) {
                console.log ((i+1)+'월 --> count : '+this.count[i]+' ,  총 금액: '+this.month[i]);
                if (this.month[i]==0) continue;

                totalAmount+=(parseInt)(this.month[i]);
                totalCount+=(parseInt)(this.count[i]);
                this.month[i]=Math.ceil((parseInt(this.month[i]))/ (parseInt(this.count[i])));
                console.log ((i+1)+'월 평균 : '+this.month[i]);
            }

            this.avg=Math.ceil((parseInt(totalAmount))/ (parseInt(totalCount)));
            console.log(this.avg);
            this.avgString=this.numberToKorean(this.avg);
            console.log(this.avgString);
        },
        createChart(chartId, ChartData) {
            console.log ("----Create Chart----")
            const ctx=document.getElementById(chartId);
            const myChart=new Chart (ctx, {
                type : ChartData.type,
                data : ChartData.data,
                options : ChartData.options,
            });

            myChart.data.datasets[0].data=this.month;
            myChart.update();
            
            console.log ('----Update Chart----')
            console.log (ChartData.data.datasets[0].data);
            
        },
        async getDetailList() {
            console.log ("----Get Detail List-----")
            console.log(this.houseNo)

            await propertyDetail(
                this.houseNo,
                ({data}) => {
                    this.detailList=data;
                    this.setMonthDetailList();

                    this.createChart('chart-data', this.ChartData);
                    //this.addData(this.myChart, this.month);
                },
                (error)=> {
                    console.log(error);
                }
            );
        },
        //////////////////////////////////// 차트 생성 관련 종료
    },
}
</script>


<style>

</style>