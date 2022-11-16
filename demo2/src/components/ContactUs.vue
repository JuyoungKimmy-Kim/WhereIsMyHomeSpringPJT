<template>
<div>
  <header-page></header-page>
  <div class="card card-body sticky blur shadow-blur mx-3 mx-md-12 mt-n6">
    <section class="py-7">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-8 mx-auto text-center">
            <div class="ms-3 mb-md-5">
              <h3>Contact us</h3>
              <p>
                For further questions, including partnership opportunities, please email hello@creative-tim.com
                or contact using our contact form.
              </p>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-8 mx-auto">
            <div class="card card-plain">
              <form id="contact-form" method="post" autocomplete="off">
                <div class="card-body">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="input-group input-group-outline mb-4">
                        <label class="form-label">Full Name</label>
                        <input class="form-control" aria-label="Full Name" type="text">
                      </div>
                    </div>
                    <div class="col-md-6 ps-md-2">
                      <div class="input-group input-group-outline">
                        <label class="form-label">Email</label>
                        <input type="email" class="form-control">
                      </div>
                    </div>
                  </div>
                  <div class="input-group input-group-outline mb-4 mt-md-0 mt-4">
                    <label class="form-label">What can we help you?</label>
                    <textarea name="message" class="form-control" id="message" rows="6"></textarea>
                  </div>
                  <div class="row">
                    <div class="col-md-12 text-center">
                      <button type="submit" class="btn bg-gradient-primary mt-4">Send Message</button>
                    </div>
                  </div>
                </div>
              </form>
            </div>
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