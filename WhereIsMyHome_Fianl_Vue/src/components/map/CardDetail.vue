<template>
  <div>
    <!-- <b-modal id="modal-lg" size="lg" ref="my-modal" hide-footer title="로드뷰" no-enforce-focus> -->
    <div id="roadview" class="map"></div>
    <br><br>
    <!-- </b-modal> -->
  </div>
</template>

<script>
export default {
  data () {
    return {
      selectedProperty :'',
    } 
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src ="//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=741cfff0b0e1d74a405abcb9f34aee54&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {

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

      this.selectedProperty=this.$store.getters.getProperty
      console.log(this.selectedProperty);
      //var position = new kakao.maps.LatLng(33.450701, 126.570667); //지도의 중심좌표.
      var position = new kakao.maps.LatLng(this.selectedProperty.lat,this.selectedProperty.lng); //지도의 중심좌표.


      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      }); //지도 생성 및 객체 리턴
    },
  },
};
</script>

<style>
.map {
  width: 100%;
  height: 250px;
}
/* #loadview {
  height: 600px;
  background-color: #ffffff;
} */
</style>
