<template>
  <section id="registerForm">
    <div class="container py-4">
      <div class="row">
        <div class="col-lg-7 mx-auto d-flex justify-content-center flex-column">
          <h3 class="text-center">회원가입</h3>
          <br><br>
          <form role="form" id="contact-form" method="post" autocomplete="off">
            <div class="card-body">
              <div class="row">
                <div class="col-md-6">
                  <div class="input-group input-group-static mb-4">
                    <label>이름</label>
                    <input class="form-control" placeholder="eg. 홍길동" type="text" >
                  </div>
                </div>



                <div class="col-md-6 ps-2">
                  <div class="input-group input-group-static mb-4">
                    <label>생년월일</label>
                    <input class="form-control" placeholder="eg. YYYYMMDD" type="text" >
                  </div>
                </div>
              </div>
              <div class="mb-4">
                  <div class="input-group input-group-static mb-4">
                    <label>이메일</label>
                    <input class="form-control" placeholder="eg. hong@gildong.com" type="email" >
                  </div>
              </div>
              <div class="mb-4">
                  <div class="input-group input-group-static mb-4">
                    <label>비밀번호</label>
                    <input class="form-control" placeholder="최소 8자리 이상 : 영어 대문자, 소문자, 숫자, 특수문자 중 3종류 조합 " type="password" >
                  </div>
              </div>
              <div class="mb-4">
                  <div class="input-group input-group-static mb-4">
                    <label>비밀번호 확인</label>
                    <input class="form-control" placeholder="" type="password" >
                  </div>
              </div>
              <div class="input-group mb-4 input-group-static">
                <label>관심 지역을 1개 이상 선택해주세요</label>
                <textarea name="message" class="form-control" id="message" rows="4">여기서 관심 지역 체크박스 형태? 로 선택하게 해서 
                  아무 선택 없이 (시, 구군, 동) map 화면으로 들어갔을 때, 관심 지역을 먼저 보여주게 하는 거 어때요?
                </textarea>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-check form-switch mb-4 d-flex align-items-center">
                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" checked="" />
                    <label class="form-check-label ms-3 mb-0" for="flexSwitchCheckDefault">
                      <a href="javascript:;" class="text-dark"><u>이용약관</u></a
                      >에 동의합니다.</label
                    >
                  </div>
                </div>
                <div class="col-md-12">
                  <button type="submit" class="btn bg-gradient-dark w-100">가입</button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style>
#registerForm {
  padding-top: 100px;
}
</style>
