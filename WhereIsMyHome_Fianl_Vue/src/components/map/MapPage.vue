<template>
    <div id="mapWrapper" class="container position-relative mx-0 px-0">
        <div id="map" class="position-absolute z-index-0"></div>
        <ul id="category">
          <li>
            <span @click="showStation()"><img src ="@/assets/img/train.png" width ="35" height="35"></span>
          </li>
          <li v-for="(data, index) in DataToShow"
            :key="index"
            :id="data.id"
            @click="onClickCategory(data.id)"
            :data-order="index">
          <span><img :src = data.img width ="35" height="35"></span>
          </li>
        </ul>

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

                                            <h5>{{ place.aptName }}</h5>
                                            <!-- <p>면적 {{ place.area }} m <sup>2</sup> / {{ place.dealAmount }} 만원 &nbsp;&nbsp;</p> -->
                                            
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
                                        
                                        <road-view></road-view>
                                        <chart-data></chart-data>
                                              <!-- <p>
                                                거래 연도: {{ placeNow.dealYear }}-{{ placeNow.dealMonth }}-{{ placeNow.dealDay }}
                                            </p> -->
                                        <div>
           
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
import { propertyList } from "@/common/map.js";
import { stationList } from "@/common/map.js";
import RoadView from "@/components/map/RoadView.vue";
import ChartData from "@/components/map/ChartData.vue";

import { mapState } from "vuex";

const mapStore = "mapStore";

export default {
  components: { RoadView, ChartData },
  data() {
    return {
      isDetailMode: false,
      isSlidingMenu: false,
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

      options: {
        placeOverlay: null,
        contentNode: null,
        optionMarkers: [],
        currCategory: null,
        ps: null,
        stationList : [],
      },

      DataToShow: [
        { id: "BK9", name: "은행", class: "bank", img:require("@/assets/img/piggy-bank.png") },
        { id: "MT1", name: "마트", class: "mart", img:require("@/assets/img/grocery-cart.png")},
        { id: "HP8", name: "병원", class: "pharmacy", img:require("@/assets/img/hospital.png") },
        { id: "CE7", name: "카페", class: "cafe", img:require("@/assets/img/coffee.png") },
        { id: "CS2", name: "편의점", class: "store", img:require("@/assets/img/24-hours.png") },
      ],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=741cfff0b0e1d74a405abcb9f34aee54&libraries=services";
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
        center: new kakao.maps.LatLng(37.715133, 126.734086),
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
      if (this.map.value.dong.name.length != 0) {
        this.getPropertyList();
        this.displayPlaces(this.kakao.placeList);
      } 

      this.getStationList();

      this.options.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.options.contentNode = document.createElement("div");

      this.options.ps = new kakao.maps.services.Places(this.kakao.map);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.options.contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(this.options.contentNode, "mousedown", kakao.maps.event.preventMap);
      this.addEventHandle(this.options.contentNode, "touchstart", kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.options.placeOverlay.setContent(this.options.contentNode);
      // kakao.maps.event.addListener(this.kakao.map, 'idle', this.options.searchPlaces); --> 자꾸 에러남 일단
      this.addCategoryClickEvent();
    },

    displayOptionPlaces(places, isStation) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다

      var i=0;
      var marker='';
      if (isStation) {
        for (i = 0; i < places.length; i++) {
          marker = this.addOptionMarker(new kakao.maps.LatLng(places[i].lat, places[i].lng), -1);
        }
      }
      else {
        var order = document.getElementById(this.options.currCategory).getAttribute("data-order");
        for (i = 0; i < places.length; i++) {
          marker = this.addOptionMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);
        }
      }
    },
    displayPlaceInfo (place) {
    var content = '<div class="placeinfo">' +
                    '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

    if (place.road_address_name) {
        content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                    '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
    }  else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
    }                
   
    content += '    <span class="tel">' + place.phone + '</span>' + 
                '</div>' + 
                '<div class="after"></div>';

    var $this=this;
    $this.options.contentNode.innerHTML = content;
    $this.options.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));

    console.log ('display place info')
    console.log ($this.options.placeOverlay);

    $this.options.placeOverlay.setMap($this.kakao.map);  
},


    addOptionMarker(position, order) {

      console.log ('order : '+order);
      console.log ('position : '+position);

      var imageSrc='', imageSize='', imgOptions={}, markerImage='', marker='';
      if (order==-1) {
        imageSrc=require("@/assets/img/train.png");
      } else if (order==0){
        imageSrc =require("@/assets/img/piggy-bank.png");
      } else if (order==1) {
        imageSrc =require("@/assets/img/grocery-cart.png");
      } else if (order==2) {
        imageSrc =require("@/assets/img/hospital.png");
      } else if (order==3) {
        imageSrc =require("@/assets/img/coffee.png");
      } else if (order==4) {
        imageSrc=require("@/assets/img/24-hours.png");
      }

      imageSize = new kakao.maps.Size(31, 35); // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(31, 35), // 스프라이트 이미지의 크기
      }

      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
      position: position, // 마커의 위치
      image: markerImage,
      });


      marker.setMap(this.kakao.map); // 지도 위에 마커를 표출합니다
      this.options.optionMarkers.push(marker); // 배열에 생성된 마커를 추가합니다

      console.log ('add option marker!');
      // console.log(marker);
      return marker;
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
                console.log(image);
                const test = document.createElement("div");
                test.addEventListener("click", () => {
                    this.clickEvent(places[i]);
                });
                test.innerHTML = `
                    <div id = "mapMarker" class = "text-center">
                        <div>
                       <span class="badge rounded-pill bg-dark">
                            ${places[i].aptName}
                        </span>
                        </div>
                        <img src =${image} width ="48" height="48">
                        <div>
                        <span class="badge rounded-pill bg-light text-dark">
                        ${places[i].buildYear}
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
                    $this.showDetailCard (marker, places[i], i);
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
    addMarker(position, idx, content) {
      var marker = new kakao.maps.CustomOverlay({
              position: position,
              content: content,
              clickable: true,
          });
      marker.setMap(this.kakao.map); // 지도 위에 마커를 표출합니다
      this.kakao.markers.push(marker);

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

    showDetailCard(marker, place, idx) {
      console.log(marker);

      this.$store.commit("mapStore/SET_PROPERTY", place);

      this.isDetailMode = !this.isDetailMode;
      this.currentIndex = idx;
    },
    removeAllChildNods(el) {
      // while (el.hasChildNodes()) {
      //     el.removeChild (el.lastChild);
      // }
    },

    onClickCategory(el) {
      var value = document.getElementById(el);
      this.options.placeOverlay.setMap(null);

      if (value.className === "on") {
        console.log("marker click off!");
        this.options.currCategory = "";
        this.changeCategoryClass();
        this.removeOptionMarker();
      } else {
        console.log("marker click on!");
        this.options.currCategory = el;
        this.changeCategoryClass(value);
        this.searchPlaces();
      }
    },

    addCategoryClickEvent() {
      var category = document.getElementById("category");
      var children = category.children;

      for (var i = 0; i < children.length; i++) {
        children[i].onclick = this.onClickCategory;
      }
    },

    changeCategoryClass(el) {
      console.log("Change Category Class");
      var category = document.getElementById("category");
      var children = category.children;
      var i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        el.className = "on";
      }
    },
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        console.log("정상적으로 검색이 완료됐으면 지도에 마커를 표출");
        this.displayOptionPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        console.log("검색결과가 없습니다");
      } else if (status === kakao.maps.services.Status.ERROR) {
        console.log("에러로 인해 검색결과가 나오지 않았습니다");
      }
    },
    searchPlaces() {
      console.log("Current Category : " + this.options.currCategory);

      if (!this.options.currCategory) {
        console.log("Search  종료");
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.options.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeOptionMarker();

      this.kakao.ps.categorySearch(this.options.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    removeOptionMarker() {
      console.log ('Option mark remove!')
      console.log (this.options.optionMarkers);
      for (var i = 0; i < this.options.optionMarkers.length; i++) {
        this.options.optionMarkers[i].setMap(null);
      }
      this.options.optionMarkers = [];
    },
    /////////////////////////////////////////// 장소 마커 등록 끝

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
    setSlidingMenu () {
      this.isSlidingMenu=!this.isSlidingMenu;
    },
    showStation(){
      //this.displayPlaces(this.options.stationList);
      this.displayOptionPlaces(this.options.stationList, true);
    },
    // getAddr(){
    //     for (var i=0; i<propertyList.length; i++) {
    //       let lat=propertyList[i].lat;
    //       let lng=propertyList[i].lng;

    //     let geocoder = new kakao.maps.services.Geocoder();
    //     let coord = new kakao.maps.LatLng(lat, lng);
    //     let callback = function(result, status) {
    //       if (status === kakao.maps.services.Status.OK) {
    //           console.log(result);
    //       }
    //     }
    //   }
    //   geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
    // },

    async getPropertyList() {
      await propertyList(
        this.map.value.dong.name,
        this.map.value.gugun.code,
        ({ data }) => {
          this.kakao.placeList = data;
          console.log("getPropertyList >> " + data);
          this.displayPlaces(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getStationList() {
      console.log ("Station List");
        await stationList (
          ({ data }) => {
            this.options.stationList=data;
            console.log (data);
          },
          (error) => {
            console.log (error);
          }
        );
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

#category {
  position: absolute;
  top: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0);
  background: #fff;
  overflow: hidden;
  z-index: 2;
  right: 50px;
  top: 120px;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
</style>
