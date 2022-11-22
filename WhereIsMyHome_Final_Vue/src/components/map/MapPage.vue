<template>
    <div id="mapWrapper" class="container position-relative mx-0 px-0">
        <div id="map" class="position-absolute z-index-0"></div>
        <div class="row margin-top position-absolute">
            <div class="col-sm-8">
                <div class="row">
                    <div class="col-sm-12 hidden-xs empty-block"></div>
                </div>
            </div>
        </div>
        <div id="list_wrap" class="row margin-top" style="width: 100%;height: 100vh;">
            <div class="col-sm-6 block-contain max-height-vh-90">
                <div class="row">
                    <div class="col-sm-12 col-md-3 px-0 max-height-vh-80 overflow-y-auto">
                        <div v-for="(place, index) in kakao.placeList" :key="index">
                            <div class="container ps-5 pe-4">
                                <div class="row justify-space-between py-1">
                                    <div class="card shadow-lg px-0" @click="setCardDetailMode(place, index)">
                                        <div class="card-body px-3 py-3">

                                            <h4>{{ place.aptName }}</h4>
                                            <p>면적 {{ place.area }} m <sup>2</sup> / {{ place.dealAmount }} 만원 &nbsp;&nbsp;</p>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-4 px-0 max-height-vh-90">
                        <div class="container ps-3 pe-0">
                            <div class="row justify-space-between py-1">
                                <div class="card shadow-lg px-0" v-if="isDetailMode">
                                    <div class="card-body px-3 py-3">
                                        
                                        <card-detail></card-detail>
                                        
                                        <div>
                                            <p>
                                                거래 연도: {{ placeNow.dealYear }}-{{ placeNow.dealMonth }}-{{ placeNow.dealDay }}
                                            </p>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>    
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {propertyList} from '@/common/map.js';
import CardDetail from "@/components/map/CardDetail.vue";

import {mapState} from "vuex";

const mapStore = "mapStore";

export default {
    components: { CardDetail },
    data() {
        return {
            isDetailMode: false,
            currentIndex: 0,
            kakao: {
                infowindow: null,
                map: null,
                mapTypeControl: null,
                zoomControl: null,
                markers: [],
                placeList: [],
                placeNow:{},
                selectedMarker: null,
            },
        };
    },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement("script");
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=741cfff0b0e1d74a405abcb9f34aee54&libraries=services";
            document.head.appendChild(script);
        }
        this.$store.commit("IS_MAP_VIEW", true);
    },
    destroyed() {
        this.$store.commit("IS_MAP_VIEW", false);
    },
    // gugun, dong 변경 시 --> watch를 통해 변화 감지 --> db 다시 접근해 property List 받아옴
    computed: {
        ...mapState(mapStore, ["map"]),
    },
    watch: {
        //selectedGugun: function () {},
        selectedDong: function () {
            this.getPropertyList();
            this.displayPlaces(this.kakao.placeList);
        },
    },
    methods: {
        initMap() {
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 5,
            };
            //지도 객체를 등록합니다.
            //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
            this.kakao.map = new kakao.maps.Map(container, options);
            this.kakao.ps = new kakao.maps.services.Places();
            this.kakao.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
            this.kakao.mapTypeControl = new kakao.maps.MapTypeControl();
            this.kakao.map.addControl(this.kakao.mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
            this.kakao.zoomControl = new kakao.maps.ZoomControl();
            this.kakao.map.addControl(this.kakao.zoomControl, kakao.maps.ControlPosition.RIGHT);
            // 검색된 동이 있을 경우
            if (this.map.dong != null) {
                this.getPropertyList();
                this.displayPlaces(this.kakao.placeList);
            }
            },
            displayPlaces(places) {
            var listEl = document.getElementById("placesList"),
                menuEl = document.getElementById("list_wrap"),
                fragment = document.createDocumentFragment(),
                bounds = new kakao.maps.LatLngBounds(),
                listStr = "";
            // 검색 결과 목록에 추가된 항목들을 제거합니다
            this.removeAllChildNods(listEl);
            // 지도에 표시되고 있는 마커를 제거합니다
            this.removeMarker();
            for (var i = 0; i < places.length; i++) {

                            const image = require("@/assets/img/apt.png");
                const test = document.createElement("div");
                test.addEventListener("click", () => {
                    this.clickEvent(places[i]);
                });
                test.innerHTML = `
                    <div id = "mapMarker" class = "text-center">
                        <div>
                            <span class="bg-primary rounded p-1 font-weight-bold text-white text-md">
                            ${places[i].aptName}
                            </span>
                        </div>
                        <img src =${image} width ="96" height="96">
                        <div>
                            <span class="bg-success rounded p-1 font-weight-bold text-white text-md">
                        ${places[i].dealAmount}
                            </span>
                        </div>
                    </div>`;
                // 마커를 생성하고 지도에 표시합니다
                var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng),
                marker = this.addMarker(placePosition, i, test),
                itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
                // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                // LatLngBounds 객체에 좌표를 추가합니다
                bounds.extend(placePosition);
                // 마커와 검색결과 항목에 mouseover 했을때
                // 해당 장소에 인포윈도우에 장소명을 표시합니다
                // mouseout 했을 때는 인포윈도우를 닫습니다
                let $this = this;
                (function (marker, title) {
                kakao.maps.event.addListener(marker, "mouseover", function () {
                    $this.displayInfowindow(marker, title);
                });
                kakao.maps.event.addListener(marker, "mouseout", function () {
                    $this.kakao.infowindow.close();
                });
                kakao.maps.event.addListener(marker, "click", function() {
                    $this.showDetailCard (marker, places[i]);
                });
                itemEl.onmouseover = function () {
                    $this.displayInfowindow(marker, title);
                };
                itemEl.onmouseout = function () {
                    this.kakao.infowindow.close();
                };
                })(marker, places[i].aptName);
                fragment.appendChild(itemEl);
            }
            // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
            console.dir(fragment);
            // listEl.appendChild(fragment);
            menuEl.scrollTop = 0;
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            this.kakao.map.setBounds(bounds);
            },
            getListItem(index, places) {
            var el = document.createElement("li"),
                itemStr =
                '<span class="markerbg marker_' +
                (index + 1) +
                '"></span>' +
                '<div class="info">' +
                "   <h5>" +
                places.AptName +
                "</h5>";
            if (places.road_address_name) {
                itemStr +=
                "    <span>" +
                places.road_address_name +
                "</span>" +
                '   <span class="jibun gray">' +
                places.address_name +
                "</span>";
            } else {
                itemStr += "    <span>" + places.address_name + "</span>";
            }
            itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";
            el.innerHTML = itemStr;
            el.className = "item";
            return el;
        },
        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        addMarker(position, idx, title) {
            

                var marker = new kakao.maps.CustomOverlay({
                    position: position,
                    content: title,
                    clickable: true,
                });
            marker.setMap(this.kakao.map); // 지도 위에 마커를 표출합니다
            this.kakao.markers.push(marker); // 배열에 생성된 마커를 추가합니다
        //     kakao.maps.event.addListener(marker, 'click', function() {
        //     if (this.kakao.selectedMarker!='') {
        //       this.isDetailMode=false;
        //       this.kakao.selectedMarker='';
                
        //     } else {
        //       this.isDetailMode=true;
        //       this.kakao.selectedMarker=idx;
        //     }
        // });
        return marker;
        },
        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        removeMarker() {
            for (var i = 0; i < this.kakao.markers.length; i++) {
                this.kakao.markers[i].setMap(null);
            }
            this.kakao.markers = [];
        },
        // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
        // 인포윈도우에 장소명을 표시합니다
        displayInfowindow(marker, title) {
            var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";
            this.kakao.infowindow.setContent(content);
            this.kakao.infowindow.open(this.kakao.map, marker);
        },
    /*
        if (this.isDetailMode) {
            this.$store.state.map.property = place;
        } else {
            this.$store.commit("SET_PROPERTY", place);
        }
        this.isDetailMode = !this.isDetailMode;
        this.currentIndex = index;
    */
        showDetailCard (marker, place, idx) {
            this.$store.commit("mapStore/SET_PROPERTY", place);

            
            this.isDetailMode=!this.isDetailMode;
            this.currentIndex=idx;
        },
        removeAllChildNods(el) {
            // while (el.hasChildNodes()) {
            //     el.removeChild (el.lastChild);
            // }
        },
        ////////////////////////////////////////////// kakao map API 종료 //////////////
        indexCheck(index) {
            if (this.currentIndex == index) return true;
            else return false;
        },
        setCardDetailMode(place, index) {
            this.$store.commit("mapStore/SET_PROPERTY", place);
            console.log(place.no);
            if(this.placeNow == null || this.placeNow.no != place.no){
                this.placeNow = place;
                this.isDetailMode = true;
                this.currentIndex = index;
            }else{
                this.placeNow = null
                this.isDetailMode = false;
            }
        },
        async getPropertyList(){
            await propertyList(this.map.dong.name, this.map.gugun.code,
                ({data})=>{
                    this.kakao.placeList = data;
                    console.log("getPropertyList >> " + data);
                    this.displayPlaces(data);
                },
                (error)=>{
                    console.log(error);
                })
        },
    },
};
</script>

<style scoped src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style scoped>
.container {
    max-width: 100%;
}
#map {
    width: 100%;
    height: 100vh;
}
.map_wrap,
.map_wrap * {
    margin: 0;
    padding: 0;
    font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
    font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
    color: #000;
    text-decoration: none;
}
.map_wrap {
    position: relative;
    width: 100%;
    height: 500px;
}
#menu_wrap {
    position: absolute;
    top: 130px;
    left: 0;
    bottom: 0;
    width: auto;
    margin: 10px 0 30px 10px;
    padding: 5px;
    overflow-y: auto;
    z-index: 1;
    font-size: 12px;
    border-radius: 10px;
}

#menu_wrap hr {
    display: block;
    height: 1px;
    border: 0;
    border-top: 2px solid #5f5f5f;
    margin: 3px 0;
}
#menu_wrap .option {
    text-align: center;
}
#menu_wrap .option p {
    margin: 10px 0;
}
#menu_wrap .option button {
    margin-left: 5px;
}
#menu_wrap::-webkit-scrollbar {
    display: none;
}
#placesList li {
    list-style: none;
}
#placesList .item {
    position: relative;
    border-bottom: 1px solid #888;
    overflow: hidden;
    cursor: pointer;
    min-height: 65px;
}
#placesList .item span {
    display: block;
    margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
}
#placesList .item .info {
    padding: 10px 0 10px 55px;
}
#placesList .info .gray {
    color: #8a8a8a;
}
#placesList .info .jibun {
    padding-left: 26px;
    background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
    color: #009900;
}
#placesList .item .markerbg {
    float: left;
    position: absolute;
    width: 36px;
    height: 37px;
    margin: 10px 0 0 10px;
    background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
    background-position: 0 -10px;
}
#placesList .item .marker_2 {
    background-position: 0 -56px;
}
#placesList .item .marker_3 {
    background-position: 0 -102px;
}
#placesList .item .marker_4 {
    background-position: 0 -148px;
}
#placesList .item .marker_5 {
    background-position: 0 -194px;
}
#placesList .item .marker_6 {
    background-position: 0 -240px;
}
#placesList .item .marker_7 {
    background-position: 0 -286px;
}
#placesList .item .marker_8 {
    background-position: 0 -332px;
}
#placesList .item .marker_9 {
    background-position: 0 -378px;
}
#placesList .item .marker_10 {
    background-position: 0 -423px;
}
#placesList .item .marker_11 {
    background-position: 0 -470px;
}
#placesList .item .marker_12 {
    background-position: 0 -516px;
}
#placesList .item .marker_13 {
    background-position: 0 -562px;
}
#placesList .item .marker_14 {
    background-position: 0 -608px;
}
#placesList .item .marker_15 {
    background-position: 0 -654px;
}
</style>