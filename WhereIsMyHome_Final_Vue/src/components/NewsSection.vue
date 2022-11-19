<template>
<div id="newSection" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <section id="card">
                <div class="container">
                    <div class="row justify-space-between py-2">
                    <div class="mx-auto">
                        <div class="card card-background" style="background-image: url(@/assets/img/examples/blog2.jpg)">
                        <div class="full-background"></div>
                        <div class="card-body body-left">
                            <div class="content-left py-4">
                                <h4 class="card-title text-white">"중국 부동산 판매, 내년에도 10~15% 하락할 것"</h4>
                            </div>
                        </div>
                        </div>
                    </div>
                    </div>
                </div>
            </section>
        </div>
        <div class="carousel-item">
            <section id="card">
                <div class="container">
                    <div class="row justify-space-between py-2">
                    <div class="mx-auto">
                        <div class="card card-background">
                        <div class="full-background"></div>
                        <div class="card-body body-left">
                            <div class="content-left py-4">
                                <h4 class="card-title text-white">“부동산 시장 현안 대응 방안 발표한 정부”… 직접적 수혜 받는 천왕역 모아엘가 트레뷰 주목</h4>
                            </div>
                        </div>
                        </div>
                    </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#newSection" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#newSection" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
</template>

<script>
export default {

};
</script>

<style scoped>
.card-title {
    width:300px;
    display:-webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
.card.card-background .card-body .content-center, .card.card-background .card-body .content-left{
    display: flex;
    padding-top: 60px;
    padding-bottom: 60px;
}
</style>