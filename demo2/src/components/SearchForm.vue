<template>
  <!-- Search Form -->
  <div class="col-12 px-0 parallax-searchs">
    <div class="banner-search-wrap">
      <div class="tab-content">
        <div class="tab-pane fade show active" id="tabs_1">
          <div class="rld-main-search">
            <div class="row">
              <div class="rld-single-input">
                <input type="text" placeholder="아파트 이름으로 검색" />
              </div>
              <div class="rld-single-select ml-22">
                <select class="select single-select">
                  <option value="1">시 선택</option>
                  <option value="2">Family House</option>
                  <option value="3">Apartment</option>
                  <option value="3">Condo</option>
                </select>
              </div>
              <div class="rld-single-select">
                <select class="select single-select">
                  <option value="1">구군 선택</option>
                  <option value="2">Los Angeles</option>
                  <option value="3">Chicago</option>
                  <option value="3">Philadelphia</option>
                  <option value="3">San Francisco</option>
                  <option value="3">Miami</option>
                  <option value="3">Houston</option>
                </select>
              </div>
              <div class="rld-single-select">
                <select class="select single-select">
                  <option value="1">동 선택</option>
                  <option value="2">Los Angeles</option>
                  <option value="3">Chicago</option>
                  <option value="3">Philadelphia</option>
                  <option value="3">San Francisco</option>
                  <option value="3">Miami</option>
                  <option value="3">Houston</option>
                </select>
              </div>
              <div class="dropdown-filter" @click="test"><span>상세 선택</span></div>
              <div class="col-xl-2 col-lg-2 col-md-4 pl-0">
                <a class="btn btn-yellow" href="#">Search Now</a>
              </div>
              <div class="explore__form-checkbox-list full-filter">
                <div class="row">
                  <div class="col-lg-4 col-md-6 py-1 pr-30 pl-0">
                    <!-- Form Property Status -->
                    <div class="form-group categories">
                      <div class="nice-select form-control wide" tabindex="0">
                        <span class="current"><i class="fa fa-home"></i>Property Status</span>
                        <ul class="list">
                          <li data-value="1" class="option selected">For Sale</li>
                          <li data-value="2" class="option">For Rent</li>
                        </ul>
                      </div>
                    </div>
                    <!--/ End Form Property Status -->
                  </div>
                  <div class="col-lg-4 col-md-6 py-1 pr-30 pl-0">
                    <!-- Form Bedrooms -->
                    <div class="form-group beds">
                      <div class="nice-select form-control wide" tabindex="0">
                        <span class="current"
                          ><i class="fa fa-bed" aria-hidden="true"></i> Bedrooms</span
                        >
                        <ul class="list">
                          <li data-value="1" class="option selected">1</li>
                          <li data-value="2" class="option">2</li>
                          <li data-value="3" class="option">3</li>
                          <li data-value="3" class="option">4</li>
                          <li data-value="3" class="option">5</li>
                          <li data-value="3" class="option">6</li>
                          <li data-value="3" class="option">7</li>
                          <li data-value="3" class="option">8</li>
                          <li data-value="3" class="option">9</li>
                          <li data-value="3" class="option">10</li>
                        </ul>
                      </div>
                    </div>
                    <!--/ End Form Bedrooms -->
                  </div>
                  <div class="col-lg-4 col-md-6 py-1 pl-0 pr-0">
                    <!-- Form Bathrooms -->
                    <div class="form-group bath">
                      <div class="nice-select form-control wide" tabindex="0">
                        <span class="current"
                          ><i class="fa fa-bath" aria-hidden="true"></i> Bathrooms</span
                        >
                        <ul class="list">
                          <li data-value="1" class="option selected">1</li>
                          <li data-value="2" class="option">2</li>
                          <li data-value="3" class="option">3</li>
                          <li data-value="3" class="option">4</li>
                          <li data-value="3" class="option">5</li>
                          <li data-value="3" class="option">6</li>
                          <li data-value="3" class="option">7</li>
                          <li data-value="3" class="option">8</li>
                          <li data-value="3" class="option">9</li>
                          <li data-value="3" class="option">10</li>
                        </ul>
                      </div>
                    </div>
                    <!--/ End Form Bathrooms -->
                  </div>
                  <div class="col-lg-5 col-md-12 col-sm-12 py-1 pr-30 mr-5 sld">
                    <!-- Price Fields -->
                    <div class="main-search-field-2">
                      <!-- Area Range -->
                      <div class="range-slider">
                        <label>Area Size</label>
                        <div id="area-range" data-min="0" data-max="1300" data-unit="sq ft"></div>
                        <div class="clearfix"></div>
                      </div>
                      <br />
                      <!-- Price Range -->
                      <div class="range-slider">
                        <label>Price Range</label>
                        <div id="price-range" data-min="0" data-max="600000" data-unit="$"></div>
                        <div class="clearfix"></div>
                      </div>
                    </div>
                  </div>
                  <div class="col-lg-3 col-md-6 col-sm-12 py-1 pr-30">
                    <!-- Checkboxes -->
                    <div class="checkboxes one-in-row margin-bottom-10 ch-1">
                      <input id="check-2" type="checkbox" name="check" />
                      <label for="check-2">Air Conditioning</label>
                      <input id="check-3" type="checkbox" name="check" />
                      <label for="check-3">Swimming Pool</label>
                      <input id="check-4" type="checkbox" name="check" />
                      <label for="check-4">Central Heating</label>
                      <input id="check-5" type="checkbox" name="check" />
                      <label for="check-5">Laundry Room</label>
                      <input id="check-6" type="checkbox" name="check" />
                      <label for="check-6">Gym</label>
                      <input id="check-7" type="checkbox" name="check" />
                      <label for="check-7">Alarm</label>
                      <input id="check-8" type="checkbox" name="check" />
                      <label for="check-8">Window Covering</label>
                    </div>
                    <!-- Checkboxes / End -->
                  </div>
                  <div class="col-lg-3 col-md-6 col-sm-12 py-1 pr-30">
                    <!-- Checkboxes -->
                    <div class="checkboxes one-in-row margin-bottom-10 ch-2">
                      <input id="check-9" type="checkbox" name="check" />
                      <label for="check-9">WiFi</label>
                      <input id="check-10" type="checkbox" name="check" />
                      <label for="check-10">TV Cable</label>
                      <input id="check-11" type="checkbox" name="check" />
                      <label for="check-11">Dryer</label>
                      <input id="check-12" type="checkbox" name="check" />
                      <label for="check-12">Microwave</label>
                      <input id="check-13" type="checkbox" name="check" />
                      <label for="check-13">Washer</label>
                      <input id="check-14" type="checkbox" name="check" />
                      <label for="check-14">Refrigerator</label>
                      <input id="check-15" type="checkbox" name="check" />
                      <label for="check-15">Outdoor Shower</label>
                    </div>
                    <!-- Checkboxes / End -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--/ End Search Form -->
</template>

<script>
  export default {
       methods:{
        test(){
            let t = document.querySelector(".explore__form-checkbox-list").classList;
            if(t.contains('filter-block')){
                t.remove('filter-block');
            }else{
                t.add("filter-block");
            }  
        }
   },
  }
</script>

<style scoped>
.rld-main-search {
  height: 100%;
}

.the-search .rld-single-input input {
    width: 280px;
    border: 1px solid #ebebeb;
    margin-right: 15px;
}
</style>
