<template>
  <div>
    <!-- <button type="button" class="btn bg-gradient-light w-auto me-2" @click="showRoadView()">로드뷰 보기</button> -->
            <div style="float:right;" class="text-info icon-move-right" @click="showRoadView()">로드뷰 보기
              <i class="fas fa-arrow-right text-sm" aria-hidden="true"></i>
            </div>
    <div v-show="isRoadViewMode" id="roadview" class="map"></div>
  </div>
</template>

<script>
import {mapState} from 'vuex';

const mapStore = "mapStore";

export default {
    data () {
        return {
            isRoadViewMode:false,
            selectedProperty :'',
        } 
    },
    mounted() {

    },
    methods: {
        showRoadView() {
            if (this.isRoadViewMode) {
                this.isRoadViewMode=false;
            } else {
                this.isRoadViewMode=true;
                        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement("script");
            script.onload = () => kakao.maps.load(this.initMap);
            script.src ="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=741cfff0b0e1d74a405abcb9f34aee54&libraries=services";
            document.head.appendChild(script);
        }
            }

        },
        hideModal() {
            this.$refs["my-modal"].hide();
        },
        addKakaoMapScript() {
            const script = document.createElement("script");
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src =
                "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=741cfff0b0e1d74a405abcb9f34aee54";
            document.head.appendChild(script);
        },
        initMap() {
            var roadviewContainer = document.getElementById("roadview"); //지도를 담을 영역의 DOM 레퍼런스
            // var roadviewContainer = this.$refs['map'];
            var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
            var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
            //지도를 생성할 때 필요한 기본 옵션
            console.log(this.map.property);
            //var position = new kakao.maps.LatLng(33.450701, 126.570667); //지도의 중심좌표.
            var position = new kakao.maps.LatLng(this.map.property.lat, this.map.property.lng); //지도의 중심좌표.
                roadviewClient.getNearestPanoId(position, 50, function (panoId) {
                roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
            }); //지도 생성 및 객체 리턴
        },
    },
    computed:{
        ...mapState(mapStore, ["map"]),
    }
};
</script>

<style>
.map {
    width: 100%;
    height: 250px;
}

</style>