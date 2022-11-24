<template>
<div>
  <h4>{{this.map.property.aptName}}</h4>
  <div>
    <canvas id="chart-data"></canvas>
  </div>
</div>
</template>

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
            detailList : {},
        }
    },
    computed : {
      ...mapState(mapStore, ["map"]),  
    },
    created () {
        this.houseNo=this.map.property.no;
        this.getDetailList();
    },
    mounted () {
        this.ChartData.data=[1000,100,100,100,100,100,100,100,100];
        this.createChart('chart-data', this.ChartData);
    },
    methods : {
        createChart(chartId, ChartData) {
            console.log ("Making Chart")
            const ctx=document.getElementById(chartId);
            const myChart=new Chart (ctx, {
                type : ChartData.type,
                data : ChartData.data,
                options : ChartData.options,
            });

            console.log (myChart.type);
        },
        async getDetailList() {
            console.log("Property Detail List");
            await propertyDetail(
                this.houseNo,
                ({data}) => {
                    this.detailList=data;
                },
                (error)=> {
                    console.log(error);
                }
            );
        },
    },
}
</script>

<style>

</style>