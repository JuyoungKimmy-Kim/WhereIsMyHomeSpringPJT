<template>
    <div id="mapWrapper" class="container position-relative mx-0 px-0">
        <div id="map" class="position-absolute z-index-0"></div>
        <ul id="category" class="ps-0">
          <li>
            <div class="align-middle text-center" @click="showStation()"><img src ="@/assets/img/train.png" width ="35" height="35"></div>
          </li>
          <li v-for="(data, index) in DataToShow"
            :key="index"
            :id="data.id"
            @click="onClickCategory(data.id)"
            :data-order="index"
            >
          <div class="align-middle text-center"><img :src=data.img width ="35" height="35"></div>
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
                    <div class="col-sm-12 col-md-4 px-0 max-height-vh-80 overflow-y-auto">
                        <div v-for="(place, index) in kakao.placeList" :key="index">
                            <div class="container ps-5 pe-4">
                                <div class="row justify-space-between py-1">
                                    <div :id=place.no class="card shadow-lg px-0" @click="setCardDetailMode(place, index)">
                                        <div class="card-body px-3 py-3">

                                            <h5>{{ place.aptName }}</h5>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-6 px-0 max-height-vh-90">
                        <div class="container ps-3 pe-0">
                            <div class="row justify-space-between py-1">
                                <div class="card shadow-lg px-0" v-if="isDetailMode">
                                    <div class="card-body px-3 py-3">

                                        <chart-data></chart-data>
                                        <road-view></road-view>
                                        <div>
                                            <!-- <p>
                                                ?????? ??????: {{ placeNow.dealYear }}-{{ placeNow.dealMonth }}-{{ placeNow.dealDay }}
                                            </p> -->
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
import { propertyListByDongName, propertyListByRegionCode } from "@/common/map.js";
import { stationList } from "@/common/map.js";
import RoadView from "@/components/map/RoadView.vue";
import ChartData from "@/components/map/ChartData.vue";

import { mapState, mapActions } from "vuex";


const mapStore = "mapStore";
const userStore = "userStore";

export default {
  components: { RoadView, ChartData },
  data() {
    return {
      isDetailMode: false,
      isSlidingMenu: false,
      currentIndex: 0,

      openCardID: null,

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
        { id: "BK9", name: "??????", class: "bank", img:require("@/assets/img/piggy-bank.png") },
        { id: "MT1", name: "??????", class: "mart", img:require("@/assets/img/grocery-cart.png")},
        { id: "HP8", name: "??????", class: "pharmacy", img:require("@/assets/img/hospital.png") },
        { id: "CE7", name: "??????", class: "cafe", img:require("@/assets/img/coffee.png") },
        { id: "CS2", name: "?????????", class: "store", img:require("@/assets/img/24-hours.png") },
      ],
    };
  },
  mounted() {
    this.$store.commit("IS_MAP_VIEW", true);
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = async () => {
        await kakao.maps.load(this.initMap);
      }
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=741cfff0b0e1d74a405abcb9f34aee54&libraries=services";
      document.head.appendChild(script);
    }

  },
  destroyed() {
    this.$store.commit("mapStore/SET_SIDO", {});
    this.$store.commit("mapStore/SET_GUGUN", {});
    this.$store.commit("mapStore/SET_DONG", {});
    this.$store.commit("IS_MAP_VIEW", false);
  },
  // gugun, dong ?????? ??? --> watch??? ?????? ?????? ?????? --> db ?????? ????????? property List ?????????
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(mapStore, ["map"]),
  },
  watch:{
    'map.propertyList'(){
      this.reloadProperty();
    }
  },
  methods: {
    ...mapActions(mapStore, ["getGugunList"]),
    reloadProperty(){
      this.removeMarker();
      this.isDetailMode = false;

      if(this.openCardID != null){
        document.getElementById(this.openCardID).style.backgroundColor ="";
        this.openCardID = null;
      }

      if(this.map.propertyList.length > 0){
        this.kakao.placeList = [...this.map.propertyList];
        this.displayPlaces(this.map.propertyList);
      }else{
        this.kakao.placeList = [];
      }
    },
    async setMapState(gugunCode){
      var sidoCode = gugunCode.substr(0, 2);
      let sido = null;
      let gugun = null;

      console.log("SET")
      console.log(sidoCode);
      console.log(gugunCode);
      this.map.sidoList.forEach(item=>{
        if(item.code == sidoCode){
          sido = {...item};
        }
      })

      this.$store.commit("mapStore/SET_SIDO", sido);
      await this.getGugunList(sido);
      
      this.map.gugunList.forEach(item=>{
        if(item.code == gugunCode){
          gugun = {...item};
        }
      })
      this.$store.commit("mapStore/SET_GUGUN", gugun);

    },
    async initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.715133, 126.734086),
        level: 5,
      };

      //?????? ????????? ???????????????.
      //?????? ????????? ????????? ?????? ????????? ???????????? initMap?????? ???????????????.
      this.kakao.map = new kakao.maps.Map(container, options);
      this.kakao.ps = new kakao.maps.services.Places();
      this.kakao.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.kakao.mapTypeControl = new kakao.maps.MapTypeControl();
      this.kakao.map.addControl(this.kakao.mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      this.kakao.zoomControl = new kakao.maps.ZoomControl();
      this.kakao.map.addControl(this.kakao.zoomControl, kakao.maps.ControlPosition.RIGHT);
      this.kakao.customOverlay = new kakao.maps.CustomOverlay({});

      this.getStationList();

      this.options.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.options.contentNode = document.createElement("div");

      this.options.ps = new kakao.maps.services.Places(this.kakao.map);

      // ????????? ??????????????? ????????? ????????? css class??? ???????????????
      this.options.contentNode.className = "placeinfo_wrap";

      // ????????? ??????????????? ????????? ????????? mousedown, touchstart ???????????? ???????????????
      // ?????? ????????? ???????????? ???????????? ????????? ????????? ???????????? kakao.maps.event.preventMap ???????????? ???????????????
      this.addEventHandle(this.options.contentNode, "mousedown", kakao.maps.event.preventMap);
      this.addEventHandle(this.options.contentNode, "touchstart", kakao.maps.event.preventMap);

      // ????????? ???????????? ???????????? ???????????????
      this.options.placeOverlay.setContent(this.options.contentNode);
      // kakao.maps.event.addListener(this.kakao.map, 'idle', this.options.searchPlaces); --> ?????? ????????? ??????
      this.addCategoryClickEvent();

      let gugunCode = null;
      
      if(Object.keys(this.map.gugun).length > 0) {
        gugunCode = this.map.gugun.code;
      }else if(Object.keys(this.userInfo).length > 0 && Object.keys(this.map.gugun).length === 0){
        gugunCode = this.userInfo.gugunCode
      }else{
        gugunCode = "11170";
      }

      console.log("GUGUNCODE")
      console.log(gugunCode);
      this.setMapState(gugunCode);
      this.initPropertyList(gugunCode);
      this.displayPlaces(this.kakao.placeList);
    },
    displayOptionPlaces(places, isStation) {
      // ????????? ??????????????? ???????????? ????????? ???????????????
      // ??? ????????? ??????????????? ?????????????????? ????????? ??????????????? ???????????????

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
                      '  <span class="jibun" title="' + place.address_name + '">(?????? : ' + place.address_name + ')</span>';
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

      imageSize = new kakao.maps.Size(31, 35); // ?????? ???????????? ??????
      imgOptions = {
        spriteSize: new kakao.maps.Size(31, 35), // ??????????????? ???????????? ??????
      }

      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // ????????? ??????
        image: markerImage,
      });


      marker.setMap(this.kakao.map); // ?????? ?????? ????????? ???????????????
      this.options.optionMarkers.push(marker); // ????????? ????????? ????????? ???????????????

      console.log ('add option marker!');
      // console.log(marker);
      return marker;
    },
    gotoCardByNo(event, place){
      
      if(this.isDetailMode){
        console.log(place);
        // ?????? ????????? ???????????????
        if(Object.keys(this.map.property).length > 0 && this.map.property.no == place.no){
          document.getElementById(place.no).style.backgroundColor="";
          this.$store.commit("mapStore/SET_PROPERTY", {});
          this.openCardID = null;    
          this.isDetailMode = false;
        }else{
          document.getElementById(this.map.property.no).style.backgroundColor="";
          document.getElementById(place.no).style.backgroundColor="#99ccff";
          this.$store.commit("mapStore/SET_PROPERTY", place);
          this.openCardID = place.no;
        }
      }else{
        document.getElementById(place.no).style.backgroundColor="#99ccff";
        this.$store.commit("mapStore/SET_PROPERTY", place);
        this.openCardID = place.no;
        this.isDetailMode = true;
      }
      
      this.kakao.map.panTo(new kakao.maps.LatLng(place.lat, place.lng));
      location.href=`#${place.no}`;
    }
    ,
    displayPlaces(places) {
      var listEl = document.getElementById("placesList"),
                menuEl = document.getElementById("list_wrap"),
                fragment = document.createDocumentFragment(),
                bounds = new kakao.maps.LatLngBounds(),
                listStr = "";
            // ?????? ?????? ????????? ????????? ???????????? ???????????????
            // this.removeAllChildNods(listEl);
            // ????????? ???????????? ?????? ????????? ???????????????
            // this.removeMarker();
            for (var i = 0; i < places.length; i++) {
                const now = places[i];
                const image = require("@/assets/img/apt.png");
                const iconHtml = document.createElement("div");

                let $this = this;
                iconHtml.addEventListener("click", (event)=>{$this.gotoCardByNo(event, now)});
                iconHtml.innerHTML = `
                    <div id = "mapMarker" class = "text-center">
                        <div>
                          <span class="badge rounded-pill bg-dark">
                            ${now.aptName}
                          </span>
                        </div>
                          <img src =${image} width ="48" height="48">
                        <div>
                          <span class="badge rounded-pill bg-light text-dark">
                            ${now.buildYear}
                          </span>
                        </div>
                    </div>`;
                // ????????? ???????????? ????????? ???????????????
                var placePosition = new kakao.maps.LatLng(now.lat, now.lng),
                marker = this.addMarker(placePosition, i, iconHtml),
                itemEl = this.getListItem(i, now); // ?????? ?????? ?????? Element??? ???????????????
                // ????????? ?????? ????????? ???????????? ?????? ????????? ?????????????????????
                // LatLngBounds ????????? ????????? ???????????????
                bounds.extend(placePosition);
                // ????????? ???????????? ????????? mouseover ?????????
                // ?????? ????????? ?????????????????? ???????????? ???????????????
                // mouseout ?????? ?????? ?????????????????? ????????????
                (function (marker, title) {
                kakao.maps.event.addListener(marker, "mouseover", function () {
                    $this.displayInfowindow(marker, title);
                });
                kakao.maps.event.addListener(marker, "mouseout", function () {
                    $this.kakao.infowindow.close();
                });
                kakao.maps.event.addListener(marker, "click", function() {
                    $this.showDetailCard (marker, now);
                });
                itemEl.onmouseover = function () {
                    $this.displayInfowindow(marker, title);
                };
                itemEl.onmouseout = function () {
                    this.kakao.infowindow.close();
                };
                })(marker, now.aptName);
                fragment.appendChild(itemEl);
            }
            // ???????????? ???????????? ???????????? ?????? Element??? ???????????????
            console.dir(fragment);
            // listEl.appendChild(fragment);
            menuEl.scrollTop = 0;
            // ????????? ?????? ????????? ???????????? ?????? ????????? ??????????????????
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
    // ????????? ???????????? ?????? ?????? ????????? ???????????? ???????????????
    addMarker(position, idx, content) {
      var marker = new kakao.maps.CustomOverlay({
              position: position,
              content: content,
              clickable: true,
              no: position.no
          });


        
      marker.setMap(this.kakao.map); // ?????? ?????? ????????? ???????????????
      

      this.kakao.markers.push(marker);

      return marker;
    },
    // ?????? ?????? ???????????? ?????? ????????? ?????? ???????????????
    removeMarker() {
      for (var i = 0; i < this.kakao.markers.length; i++) {
        this.kakao.markers[i].setMap(null);
      }
      this.kakao.markers = [];
    },

    // ???????????? ?????? ?????? ????????? ???????????? ??? ???????????? ???????????????
    // ?????????????????? ???????????? ???????????????
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";
      this.kakao.infowindow.setContent(content);
      this.kakao.infowindow.open(this.kakao.map, marker);
    },

    showDetailCard(marker, place, idx) {
      this.$store.commit("mapStore/SET_PROPERTY", place);

      this.isDetailMode = !this.isDetailMode;
      this.currentIndex = idx;
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
        console.log("??????????????? ????????? ??????????????? ????????? ????????? ??????");
        this.displayOptionPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        console.log("??????????????? ????????????");
      } else if (status === kakao.maps.services.Status.ERROR) {
        console.log("????????? ?????? ??????????????? ????????? ???????????????");
      }
    },
    searchPlaces() {
      console.log("Current Category : " + this.options.currCategory);

      if (!this.options.currCategory) {
        console.log("Search  ??????");
        return;
      }

      // ????????? ??????????????? ????????????
      this.options.placeOverlay.setMap(null);

      // ????????? ???????????? ?????? ????????? ???????????????
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
    /////////////////////////////////////////// ?????? ?????? ?????? ???

    ////////////////////////////////////////////// kakao map API ?????? //////////////
    indexCheck(index) {
      if (this.currentIndex == index) return true;
      else return false;
    },
    setCardDetailMode(place, index) {
        this.$store.commit("mapStore/SET_PROPERTY", place);
        
        let targetDOM = null;
        if(this.openCardID != null){
          targetDOM = document.getElementById(this.openCardID);
          
          if(targetDOM != null){
            targetDOM.style.backgroundColor="";
          }else this.openCardID = null;
        }

        if(this.placeNow == null || this.placeNow.no != place.no){
            this.placeNow = place;
            this.isDetailMode = true;
            this.currentIndex = index;
            this.openCardID = place.no;

            let targetDOM = document.getElementById(this.openCardID);
            targetDOM.style.backgroundColor="#99ccff";

            this.kakao.map.panTo(new kakao.maps.LatLng(place.lat, place.lng));
        }else{
            this.placeNow = null;
            this.openCardID = null;
            this.isDetailMode = false;
        }
    },
    setSlidingMenu () {
      this.isSlidingMenu=!this.isSlidingMenu;
    },
    showStation(){
      this.displayOptionPlaces(this.options.stationList, true);
    },
    async initPropertyList(gugunCode) { 
      await propertyListByRegionCode(gugunCode, 
        ({ data }) => {
          this.kakao.placeList = [...data];
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
    font-family: "Malgun Gothic", dotum, "??????", sans-serif;
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
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
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