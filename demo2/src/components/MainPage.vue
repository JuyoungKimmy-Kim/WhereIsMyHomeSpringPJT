<template>
<div>
  <header-page></header-page>
  <search-box></search-box>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6">
    <section class="pt-3 pb-4" id="count-stats">
      <div class="container">
        <div class="row">
          <div class="col-lg-9 mx-auto py-3">
            <div class="row">
              <div class="col-md-4 position-relative">
                <div class="p-3 text-center">
                  <h1 class="text-gradient text-dark"><span id="state1" countTo="70">0</span>+</h1>
                  <h5 class="mt-3">Coded Elements</h5>
                  <p class="text-sm font-weight-normal">From buttons, to inputs, navbars, alerts or cards, you are covered</p>
                </div>
                <hr class="vertical dark">
              </div>
              <div class="col-md-4 position-relative">
                <div class="p-3 text-center">
                  <h1 class="text-gradient text-dark"> <span id="state2" countTo="15">0</span>+</h1>
                  <h5 class="mt-3">Design Blocks</h5>
                  <p class="text-sm font-weight-normal">Mix the sections, change the colors and unleash your creativity</p>
                </div>
                <hr class="vertical dark">
              </div>
              <div class="col-md-4">
                <sample-table></sample-table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</div>

</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue"
import SearchBox from "@/components/SearchBox.vue"
import SampleTable from "@/components/SampleTable.vue"

export default {
  components : {
    HeaderPage,SearchBox, SampleTable
  }
}
</script>

<style>

</style>