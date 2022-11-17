<template>
<div>
  <header-page></header-page>
  <search-box></search-box>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6">
    <section class="pt-8 pb-4" id="count-stats">
      <div class="container">
        <div class="row py-0">
          <div class="col-lg-9 mx-auto py-3">
            <div class="row container-fluid px-0">
              <div class="col-md-6 position-relative">
                <news-section></news-section>
                <hr class="vertical dark">
              </div>
              <div class="col-md-6 justify-content-end">
                <notice-fragment></notice-fragment>
              </div>
            </div>
          </div>
        </div>

        <bottom-card></bottom-card>
      </div>
    </section>
  </div>
</div>

</template>

<script>
import HeaderPage from "@/components/HeaderPage.vue"
import SearchBox from "@/components/SearchBox.vue"
import NoticeFragment from "@/components/NoticeFragment.vue"
import NewsSection from "@/components/NewsSection.vue"
import BottomCard from "@/components/BottomCard.vue"

export default {
  components : {
    HeaderPage,SearchBox, NoticeFragment, NewsSection, BottomCard
  }
}
</script>

<style>

</style>