<template>
<div>
  <header-page></header-page>
  <div class="card card-body sticky blur shadow-blur mx-3 mx-md-12 mt-n6">
    <section class="py-7">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ms-auto me-auto">
            <div>
              <h4 class="text-center mb-5">Q&A</h4>
              <div class="d-flex">
                <div>
                  <a href="javascript:;">
                    <div class="position-relative">
                      <div class="blur-shadow-avatar rounded-circle">
                        <img class="avatar" src="@/assets/img/team-3.jpg" alt="...">
                      </div>
                    </div>
                  </a>
                </div>
                <div class="ms-3">
                  <h6>Tina Andrew <span class="text-muted text-xs">· 7 minutes ago</span></h6>

                  <p>Chance too good. God level bars. I&#39;m so proud of @LifeOfDesiigner #1 song in the country. Panda! Don&#39;t be scared of the truth because we need to restart the human foundation in truth I stand with the most humility. We are so
                    blessed!</p>
                  <p>All praises and blessings to the families of people who never gave up on dreams. Don&#39;t forget, You&#39;re Awesome!</p>

                  <div class="ms-auto text-end">
                    <a href="javascript:;" class="btn text-dark px-2 btn-link" rel="tooltip" title="" data-original-title="Reply to Comment">
                      <i class="fa fa-reply"></i> Reply
                    </a>
                    <a href="javascript:;" class="btn text-danger px-2 btn-link">
                      <i class="fas fa-heart"></i> 243
                    </a>
                  </div>
                </div>
              </div>

              <div class="d-flex">
                <div>
                  <a href="javascript:;">
                    <div class="position-relative">
                      <div class="blur-shadow-avatar rounded-circle">
                        <img class="avatar" src="@/assets/img/team-4.jpg" alt="...">
                      </div>
                    </div>
                  </a>
                </div>
                <div class="ms-3">
                  <h6>John Camber <span class="text-muted text-xs">· Yesterday</span></h6>

                  <p>Hello guys, nice to have you on the platform! There will be a lot of great stuff coming soon. We will keep you posted for the latest news.</p>
                  <p>Don&#39;t forget, You&#39;re awesome!</p>

                  <div class="ms-auto text-end">
                    <a href="javascript:;" class="btn text-dark px-2 btn-link" rel="tooltip" title="" data-original-title="Reply to Comment">
                      <i class="fa fa-reply"></i> Reply
                    </a>
                    <a href="javascript:;" class="btn text-danger px-2 btn-link">
                      <i class="fas fa-heart"></i> 25
                    </a>
                  </div>

                  <div class="d-flex">
                    <div>
                      <a href="javascript:;">
                        <div class="position-relative">
                          <div class="blur-shadow-avatar rounded-circle">
                            <img class="avatar" src="@/assets/img/team-2.jpg" alt="...">
                          </div>
                        </div>
                      </a>
                    </div>
                    <div class="ms-3">
                      <h6>Tina Andrew <span class="text-muted text-xs">· 2 minutes ago</span></h6>

                      <p>Hello guys, nice to have you on the platform! There will be a lot of great stuff coming soon. We will keep you posted for the latest news.</p>
                      <p>Don&#39;t forget, You&#39;re awesome!</p>

                      <div class="ms-auto text-end">
                        <a href="javascript:;" class="btn text-dark px-2 btn-link" rel="tooltip" title="" data-original-title="Reply to Comment">
                          <i class="fa fa-reply"></i> Reply
                        </a>
                        <a href="javascript:;" class="btn text-danger px-2 btn-link">
                          <i class="fas fa-heart"></i> 12
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <h4 class="text-center mb-4 mt-5">Post your comment</h4>
            <div class="d-flex">
              <div>
                <a class="author" href="javascript:;">
                  <div class="position-relative">
                    <div class="blur-shadow-avatar rounded-circle">
                      <img class="avatar" alt="64x64" src="@/assets/img/team-2.jpg">
                    </div>
                  </div>
                </a>
              </div>
              <div class="ms-3 w-100">
                <div class="input-group input-group-static">
                  <label>Your message</label>
                  <textarea class="form-control" placeholder="Write a nice reply or go home..." rows="4"></textarea>
                </div>
                <div>
                  <a href="javascript:;" class="btn bg-gradient-primary pull-end mt-2">
                    <i class="fa fa-send me-2"></i> Reply
                  </a>
                </div>
              </div>
            </div> <!-- end media-post -->
          </div>
        </div>
      </div>
    </section>
  </div>
</div>
</template>

<script>
import HeaderPage from './HeaderPage.vue'
export default {
  components: { HeaderPage },

}
</script>

<style>
.row {
    padding-top: 5%;
}


</style>