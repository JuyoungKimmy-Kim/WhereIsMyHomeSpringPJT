<template>
  <div id="wrapper">
    <nav-bar></nav-bar>
    
    <div class="clearfix"></div>

    <router-view></router-view>
    <router-view name="Footer"></router-view>
    
    <a data-scroll href="#wrapper" class="go-up"><i class="fa fa-angle-double-up" aria-hidden="true"></i></a>

    <div id="preloader">
        <div id="status">
            <div class="status-mes"></div>
        </div>
    </div>
    
    <div id="scriptForm"></div>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';


export default {
  components: {
    NavBar, 
<<<<<<< HEAD
  }
=======
  }, methods:{
        addScript() {
            let scripts = [
                "./js/jquery-3.5.1.min.js",
                "./js/ajaxchimp.min.js",
                "./revolution/js/jquery.themepunch.tools.min.js",
                "./revolution/js/jquery.themepunch.revolution.min.js",
                "./js/rangeSlider.js",
                "./js/tether.min.js",
                "./js/moment.js",
                "./js/bootstrap.min.js",
                "./js/mmenu.min.js",
                "./js/mmenu.js",
                "./js/jquery.waypoints.min.js",
                "./js/jquery.counterup.min.js",
                "./js/jquery.magnific-popup.min.js",
                "./js/newsletter.js",
                "./js/jquery.form.js",
                "./js/jquery.validate.min.js",
                "./js/slick.min.js",
                "./js/fitvids.js",
                "./js/imagesloaded.pkgd.min.js",
                "./js/isotope.pkgd.min.js",
                "./js/smooth-scroll.min.js",
                "./js/lightcase.js",
                "./js/search.js",
                "./js/owl.carousel.js",
                "./js/searched.js",
                "./js/forms-2.js",
                "./js/range.js",
                "./js/color-switcher.js",
                "./js/script.js",
                "./js/rangeSlider.js",
                "./js/bootstrap.min.js,",
                "./js/light.js",
                "./js/popup.js",
                "./js/searched.js",
                "./js/leaflet.js",
                "./js/leaflet-gesture-handling.min.js",
                "./js/leaflet-providers.js",
                "./js/leaflet.markercluster.js",
                "./js/color-switcher.js",
                "./js/inner.js",

            ];

            for (let i = 0, len = scripts.length; i < len; i++) {
                let script = document.createElement('script');
                script.setAttribute('type', 'text/javascript');
                script.setAttribute('src', scripts[i]);
                script.async = false;
                document.getElementsByTagName('body')[0].appendChild(script);
            }
        },
        aosSetting(){
          AOS.init();

          // You can also pass an optional settings object
          // below listed default settings
          AOS.init({
            // Global settings:
            // disable: false, // accepts following values: 'phone', 'tablet', 'mobile', boolean, expression or function
            startEvent: 'DOMContentLoaded', // name of the event dispatched on the document, that AOS should initialize on
            initClassName: 'aos-init', // class applied after initialization
            animatedClassName: 'aos-animate', // class applied on animation
            useClassNames: false, // if true, will add content of `data-aos` as classes on scroll
            disableMutationObserver: false, // disables automatic mutations' detections (advanced)
            debounceDelay: 50, // the delay on debounce used while resizing window (advanced)
            throttleDelay: 99, // the delay on throttle used while scrolling the page (advanced)
            

            // Settings that can be overridden on per-element basis, by `data-aos-*` attributes:
            offset: 40, // offset (in px) from the original trigger point
            delay: 150, // values from 0 to 3000, with step 50ms
            duration: 1000, // values from 0 to 3000, with step 50ms
            easing: 'ease', // default easing for AOS animations
            once: true, // whether animation should happen only once - while scrolling down
            mirror: false, // whether elements should animate out while scrolling past them
            anchorPlacement: 'top-bottom', // defines which position of the element regarding to window should trigger the animation
              
            disable: function() {
              var maxWidth = 991;
              return window.innerWidth < maxWidth;
            }

          });
        }
    },
    created(){
        this.addScript();
        this.aosSetting();
    },
    watch:{
      "$route"(){
          AOS.refresh();
      }
    }
>>>>>>> c06dddde0a7ee8248c945fe05c3858770841dc01
}
</script>

<style>
</style>
