<template>
    <div>
            <!-- START SLIDER -->
        <section class="home17 overlay home-three bg-img3">
            <div class="hero-main">
                <div class="container" data-aos="zoom-in">
                    <div class="row">
                        <div class="col-12">
                            <div class="banner-inner">
                                <h5 class="sub-title">We Have Over Million Properties For You</h5>
                                <h1 class="title">Find Your <br> Dream Home</h1>
                            </div>
                        </div>
                        <!-- Search Form -->
                        <div class="col-12">
                            <div class="banner-search-wrap">
                                <ul class="nav nav-tabs rld-banner-tab">
                                    <li class="nav-item">
                                        <a class="nav-link active" data-toggle="tab" href="#tabs_1">For Sale</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#tabs_2">For Rent</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade show active" id="tabs_1">
                                        <div class="rld-main-search">
                                            <div class="row">
                                                <div class="rld-single-input">
                                                    <input type="text" placeholder="Enter Keyword...">
                                                </div>
                                                <div class="rld-single-select ml-22">
                                                    <select class="select single-select">
                                                        <option value="1">Property Type</option>
                                                        <option value="2">Family House</option>
                                                        <option value="3">Apartment</option>
                                                        <option value="3">Condo</option>
                                                    </select>
                                                </div>
                                                <div class="rld-single-select">
                                                    <select class="select single-select mr-0">
                                                        <option value="1">Location</option>
                                                        <option value="2">Los Angeles</option>
                                                        <option value="3">Chicago</option>
                                                        <option value="3">Philadelphia</option>
                                                        <option value="3">San Francisco</option>
                                                        <option value="3">Miami</option>
                                                        <option value="3">Houston</option>
                                                    </select>
                                                </div>
                                                <div class="dropdown-filter" @click="toggleAdvenced"><span>Advanced Search</span></div>
                                                <div class="col-xl-2 col-lg-2 col-md-4 pl-0">
                                                    <a class="btn btn-yellow" href="#">Search Now</a>
                                                </div>
                                                <div :class="exploreFormCheckboxList">
                                                    <div class="row">
                                                        <div class="col-lg-4 col-md-6 py-1 pr-30 pl-0">
                                                            <!-- Form Property Status -->
                                                            <div class="form-group categories">
                                                                <div class="nice-select form-control wide" tabindex="0"><span class="current"><i class="fa fa-home"></i>Property Status</span>
                                                                    <ul class="list">
                                                                        <li data-value="1" class="option selected ">For Sale</li>
                                                                        <li data-value="2" class="option">For Rent</li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                            <!--/ End Form Property Status -->
                                                        </div>
                                                        <div class="col-lg-4 col-md-6 py-1 pr-30 pl-0 ">
                                                            <!-- Form Bedrooms -->
                                                            <div class="form-group beds">
                                                                <div class="nice-select form-control wide" tabindex="0"><span class="current"><i class="fa fa-bed" aria-hidden="true"></i> Bedrooms</span>
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
                                                        <div class="col-lg-4 col-md-6 py-1 pr-30 pl-0">
                                                            <!-- Form Bathrooms -->
                                                            <div class="form-group bath">
                                                                <div class="nice-select form-control wide" tabindex="0"><span class="current"><i class="fa fa-bath" aria-hidden="true"></i> Bathrooms</span>
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
                                                        <div class="col-lg-5 col-md-12 col-sm-12 py-1 pr-30 mr-5 sld d-none d-lg-none d-xl-flex">
                                                            <!-- Price Fields -->
                                                            <div class="main-search-field-2">
                                                                <!-- Area Range -->
                                                                <div class="range-slider">
                                                                    <label>Area Size</label>
                                                                    <div id="area-range" data-min="0" data-max="1300" data-unit="sq ft"></div>
                                                                    <div class="clearfix"></div>
                                                                </div>
                                                                <br>
                                                                <!-- Price Range -->
                                                                <div class="range-slider">
                                                                    <label>Price Range</label>
                                                                    <div id="price-range" data-min="0" data-max="600000" data-unit="$"></div>
                                                                    <div class="clearfix"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3 col-md-6 col-sm-12 py-1 pr-30 d-none d-lg-none d-xl-flex">
                                                            <!-- Checkboxes -->
                                                            <div class="checkboxes one-in-row margin-bottom-10 ch-1">
                                                                <input id="check-2" type="checkbox" name="check">
                                                                <label for="check-2">Air Conditioning</label>
                                                                <input id="check-3" type="checkbox" name="check">
                                                                <label for="check-3">Swimming Pool</label>
                                                                <input id="check-4" type="checkbox" name="check">
                                                                <label for="check-4">Central Heating</label>
                                                                <input id="check-5" type="checkbox" name="check">
                                                                <label for="check-5">Laundry Room</label>
                                                                <input id="check-6" type="checkbox" name="check">
                                                                <label for="check-6">Gym</label>
                                                                <input id="check-7" type="checkbox" name="check">
                                                                <label for="check-7">Alarm</label>
                                                                <input id="check-8" type="checkbox" name="check">
                                                                <label for="check-8">Window Covering</label>
                                                            </div>
                                                            <!-- Checkboxes / End -->
                                                        </div>
                                                        <div class="col-lg-3 col-md-6 col-sm-12 py-1 pr-30 d-none d-lg-none d-xl-flex">
                                                            <!-- Checkboxes -->
                                                            <div class="checkboxes one-in-row margin-bottom-10 ch-2">
                                                                <input id="check-9" type="checkbox" name="check">
                                                                <label for="check-9">WiFi</label>
                                                                <input id="check-10" type="checkbox" name="check">
                                                                <label for="check-10">TV Cable</label>
                                                                <input id="check-11" type="checkbox" name="check">
                                                                <label for="check-11">Dryer</label>
                                                                <input id="check-12" type="checkbox" name="check">
                                                                <label for="check-12">Microwave</label>
                                                                <input id="check-13" type="checkbox" name="check">
                                                                <label for="check-13">Washer</label>
                                                                <input id="check-14" type="checkbox" name="check">
                                                                <label for="check-14">Refrigerator</label>
                                                                <input id="check-15" type="checkbox" name="check">
                                                                <label for="check-15">Outdoor Shower</label>
                                                            </div>
                                                            <!-- Checkboxes / End -->
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="tabs_2">
                                        <div class="rld-main-search">
                                            <div class="row">
                                                <div class="rld-single-input">
                                                    <input type="text" placeholder="Enter Keyword...">
                                                </div>
                                                <div class="rld-single-select ml-22">
                                                    <select class="select single-select">
                                                        <option value="1">Property Type</option>
                                                        <option value="2">Family House</option>
                                                        <option value="3">Apartment</option>
                                                        <option value="3">Condo</option>
                                                    </select>
                                                </div>
                                                <div class="rld-single-select">
                                                    <select class="select single-select mr-0">
                                                        <option value="1">Location</option>
                                                        <option value="2">Los Angeles</option>
                                                        <option value="3">Chicago</option>
                                                        <option value="3">Philadelphia</option>
                                                        <option value="3">San Francisco</option>
                                                        <option value="3">Miami</option>
                                                        <option value="3">Houston</option>
                                                    </select>
                                                </div>
                                                <div class="dropdown-filter"><span>Advanced Search</span></div>
                                                <div class="col-xl-2 col-lg-2 col-md-4 pl-0">
                                                    <a class="btn btn-yellow" href="#">Search Now</a>
                                                </div>
                                                <div class="explore__form-checkbox-list full-filter">
                                                    <div class="row">
                                                        <div class="col-lg-4 col-md-6 py-1 pr-30 pl-0">
                                                            <!-- Form Property Status -->
                                                            <div class="form-group categories">
                                                                <div class="nice-select form-control wide" tabindex="0"><span class="current"><i class="fa fa-home"></i>Property Status</span>
                                                                    <ul class="list">
                                                                        <li data-value="1" class="option selected ">For Sale</li>
                                                                        <li data-value="2" class="option">For Rent</li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                            <!--/ End Form Property Status -->
                                                        </div>
                                                        <div class="col-lg-4 col-md-6 py-1 pr-30 pl-0 ">
                                                            <!-- Form Bedrooms -->
                                                            <div class="form-group beds">
                                                                <div class="nice-select form-control wide" tabindex="0"><span class="current"><i class="fa fa-bed" aria-hidden="true"></i> Bedrooms</span>
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
                                                                <div class="nice-select form-control wide" tabindex="0"><span class="current"><i class="fa fa-bath" aria-hidden="true"></i> Bathrooms</span>
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
                                                                    <div id="area-range-rent" data-min="0" data-max="1300" data-unit="sq ft"></div>
                                                                    <div class="clearfix"></div>
                                                                </div>
                                                                <br>
                                                                <!-- Price Range -->
                                                                <div class="range-slider">
                                                                    <label>Price Range</label>
                                                                    <div id="price-range-rent" data-min="0" data-max="600000" data-unit="$"></div>
                                                                    <div class="clearfix"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3 col-md-6 col-sm-12 py-1 pr-30">
                                                            <!-- Checkboxes -->
                                                            <div class="checkboxes one-in-row margin-bottom-10 ch-1">
                                                                <input id="check-16" type="checkbox" name="check">
                                                                <label for="check-16">Air Conditioning</label>
                                                                <input id="check-17" type="checkbox" name="check">
                                                                <label for="check-17">Swimming Pool</label>
                                                                <input id="check-18" type="checkbox" name="check">
                                                                <label for="check-18">Central Heating</label>
                                                                <input id="check-19" type="checkbox" name="check">
                                                                <label for="check-19">Laundry Room</label>
                                                                <input id="check-20" type="checkbox" name="check">
                                                                <label for="check-20">Gym</label>
                                                                <input id="check-21" type="checkbox" name="check">
                                                                <label for="check-21">Alarm</label>
                                                                <input id="check-22" type="checkbox" name="check">
                                                                <label for="check-22">Window Covering</label>
                                                            </div>
                                                            <!-- Checkboxes / End -->
                                                        </div>
                                                        <div class="col-lg-3 col-md-6 col-sm-12 py-1 pr-30">
                                                            <!-- Checkboxes -->
                                                            <div class="checkboxes one-in-row margin-bottom-10 ch-2">
                                                                <input id="check-23" type="checkbox" name="check">
                                                                <label for="check-23">WiFi</label>
                                                                <input id="check-24" type="checkbox" name="check">
                                                                <label for="check-24">TV Cable</label>
                                                                <input id="check-25" type="checkbox" name="check">
                                                                <label for="check-25">Dryer</label>
                                                                <input id="check-26" type="checkbox" name="check">
                                                                <label for="check-26">Microwave</label>
                                                                <input id="check-27" type="checkbox" name="check">
                                                                <label for="check-27">Washer</label>
                                                                <input id="check-28" type="checkbox" name="check">
                                                                <label for="check-28">Refrigerator</label>
                                                                <input id="check-29" type="checkbox" name="check">
                                                                <label for="check-29">Outdoor Shower</label>
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
                    </div>
                </div>
            </div>
        </section>
        <!-- END SLIDER -->
        <!-- END SECTION HEADINGS -->

        <!-- START SECTION INFO -->
        <section _ngcontent-bgi-c3="" class="featured-boxes-area bg-white-1">
            <div _ngcontent-bgi-c3="" class="container-fluid">
                <div _ngcontent-bgi-c3="" class="featured-boxes-inner">
                    <div _ngcontent-bgi-c3="" class="row m-0">
                        <div _ngcontent-bgi-c3="" class="col-lg-3 col-sm-6 col-md-6 p-0" data-aos="fade-up" data-aos-delay="250">
                            <div _ngcontent-bgi-c3="" class="single-featured-box">
                                <div _ngcontent-bgi-c3="" class="icon color-fb7756"><img src="images/icons/i-1.svg" width="85" height="85" alt=""></div>
                                <h3 _ngcontent-bgi-c3="" class="mt-5">Find Your Home</h3>
                                <p _ngcontent-bgi-c3="">Lorem ipsum suspendisse ultrices gravida. Risus commodo viverra maecenas accumsan.</p><a _ngcontent-bgi-c3="" class="read-more-btn" href="single-property-1.html">Read More</a></div>
                        </div>
                        <div _ngcontent-bgi-c3="" class="col-lg-3 col-sm-6 col-md-6 p-0" data-aos="fade-up" data-aos-delay="350">
                            <div _ngcontent-bgi-c3="" class="single-featured-box">
                                <div _ngcontent-bgi-c3="" class="icon color-facd60"><img src="images/icons/i-2.svg" width="85" height="85" alt=""></div>
                                <h3 _ngcontent-bgi-c3="" class="mt-5">Trusted by thousands</h3>
                                <p _ngcontent-bgi-c3="">Lorem ipsum suspendisse ultrices gravida. Risus commodo viverra maecenas accumsan.</p><a _ngcontent-bgi-c3="" class="read-more-btn" href="single-property-1.html">Read More</a></div>
                        </div>
                        <div _ngcontent-bgi-c3="" class="col-lg-3 col-sm-6 col-md-6 p-0" data-aos="fade-up" data-aos-delay="450">
                            <div _ngcontent-bgi-c3="" class="single-featured-box">
                                <div _ngcontent-bgi-c3="" class="icon color-1ac0c6"><img src="images/icons/i-3.svg" width="85" height="85" alt=""></div>
                                <h3 _ngcontent-bgi-c3="" class="mt-5">Financing made easy</h3>
                                <p _ngcontent-bgi-c3="">Lorem ipsum suspendisse ultrices gravida. Risus commodo viverra maecenas accumsan.</p><a _ngcontent-bgi-c3="" class="read-more-btn" href="single-property-1.html">Read More</a></div>
                        </div>
                        <div _ngcontent-bgi-c3="" class="col-lg-3 col-sm-6 col-md-6 p-0" data-aos="fade-up" data-aos-delay="550">
                            <div _ngcontent-bgi-c3="" class="single-featured-box">
                                <div _ngcontent-bgi-c3="" class="icon"><img src="images/icons/i-4.svg" width="85" height="85" alt=""></div>
                                <h3 _ngcontent-bgi-c3="" class="mt-5">24/7 support</h3>
                                <p _ngcontent-bgi-c3="">Quis ipsum suspendisse ultrices gravida. Risus commodo viverra maecenas accumsan.</p><a _ngcontent-bgi-c3="" class="read-more-btn" href="single-property-1.html">Read More</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- END SECTION INFO -->
    </div>
</template>



<script>
export default {
    data() {
        return {
            exploreFormCheckboxList: "explore__form-checkbox-list full-filter",
        }
    },
   methods:{
        toggleAdvenced(){
            if(this.exploreFormCheckboxList.indexOf("filter-block") != -1){
                this.exploreFormCheckboxList = "explore__form-checkbox-list full-filter";
            }else{
                this.exploreFormCheckboxList = "explore__form-checkbox-list full-filter filter-block";
            }
        }
   },
   mounted() {
        let scripts = [
                "./js/jquery-3.5.1.min.js",
                "./js/rangeSlider.js",
                "./js/tether.min.js",
                "./js/moment.js",
                "./js/bootstrap.min.js",
                "./js/mmenu.min.js",
                "./js/mmenu.js",
                "./js/slick.min.js",
                "./js/fitvids.js",
                "./js/jquery.waypoints.min.js",
                "./js/jquery.counterup.min.js",
                "./js/imagesloaded.pkgd.min.js",
                "./js/isotope.pkgd.min.js",
                "./js/smooth-scroll.min.js",
                "./js/lightcase.js",
                "./js/search.js",
                "./js/owl.carousel.js",
                "./js/jquery.magnific-popup.min.js",
                "./js/ajaxchimp.min.js",
                "./js/newsletter.js",
                "./js/jquery.form.js",
                "./js/jquery.validate.min.js",
                "./js/searched.js",
                "./js/forms-2.js",
                "./js/range.js",
                "./js/color-switcher.js",
                "./revolution/js/jquery.themepunch.tools.min.js",
                "./revolution/js/jquery.themepunch.revolution.min.js",
                "./js/script.js",
            ];

        let scriptCount = document.getElementById("scriptForm").childElementCount;
        for(let i = 0; i < scriptCount; i++){
            let scriptNode = document.getElementById("myScript");
            scriptNode.parentNode.removeChild(scriptNode);
        }

        for (let i = 0, len = scripts.length; i < len; i++) {
            let script = document.createElement('script');
            script.setAttribute('src', scripts[i]);
            script.setAttribute('id', 'myScript');
            script.defer = true;
            document.getElementById('scriptForm').appendChild(script);
        }
   },
}
</script>

<style>
    @import "../../public/font/flaticon.css";
    @import "../../public/css/fontawesome-all.min.css";
    @import "../../public/css/fontawesome-5-all.min.css";
    @import "../../public/css/font-awesome.min.css";
    @import "../../public/css/search-form.css";
    @import "../../public/css/search.css";
    @import "../../public/css/animate.css";
    @import "../../public/css/aos.css";
    @import "../../public/css/aos2.css";
    @import "../../public/css/magnific-popup.css";
    @import "../../public/css/lightcase.css";
    @import "../../public/css/owl.carousel.min.css";
    @import "../../public/css/bootstrap.min.css";
    @import "../../public/css/menu.css";
    @import "../../public/css/slick.css";
    @import "../../public/css/styles.css";
    @import "../../public/css/colors/dark-gray.css";
</style>