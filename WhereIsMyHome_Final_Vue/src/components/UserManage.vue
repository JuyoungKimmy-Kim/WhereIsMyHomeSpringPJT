<template>
<div>
    <header-page></header-page>

    <div class="card card-body sticky blur shadow-blur mx-3 mx-md-6 mt-n6">
        <section>
        <div class="container">
            <h4 class="row justify-content-center">사용자 관리</h4>
            <div class="row justify-content-center pb-5">
            <div class="col-lg-11">
                <div class="card">
                <div class="table-responsive" style="overflow-x: hidden">
                    <table class="table align-items-center mb-0">
                    <thead>
                        <tr>
                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">유저</th>
                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">관리</th>
                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">가입일자</th>
                        <th class="text-secondary text-xxs opacity-7"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                <div>
                                    <img src="@/assets/img/team-2.jpg" class="avatar avatar-sm me-3">
                                </div>
                                <div class="d-flex flex-column justify-content-center">
                                    <h6 class="mb-0 text-xs">John Michael</h6>
                                    <p class="text-xs text-secondary mb-0">john@creative-tim.com</p>
                                </div>
                                </div>
                            </td>
                            <td>
                                <p class="text-xs font-weight-bold mb-0">Manager</p>
                                <p class="text-xs text-secondary mb-0">Organization</p>
                            </td>
                            <td class="align-middle text-center">
                                <span class="text-secondary text-xs font-weight-bold">23/04/18</span>
                            </td>
                            <td class="align-middle">
                                <div class="btn-group dropdown">
                                    <a class="text-secondary font-weight-bold text-xs dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Edit</a>
                                    <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton">
                                        <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                        <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>

                        <tr>
                        <td>
                            <div class="d-flex px-2 py-1">
                            <div>
                                <img src="@/assets/img/team-3.jpg" class="avatar avatar-sm me-3">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                                <h6 class="mb-0 text-xs">Alexa Liras</h6>
                                <p class="text-xs text-secondary mb-0">alexa@creative-tim.com</p>
                            </div>
                            </div>
                        </td>
                        <td>
                            <p class="text-xs font-weight-bold mb-0">Programator</p>
                            <p class="text-xs text-secondary mb-0">Developer</p>
                        </td>
                        <td class="align-middle text-center">
                            <span class="text-secondary text-xs font-weight-bold">11/01/19</span>
                        </td>

                        <td class="align-middle">
                            <div class="btn-group dropdown">
                                <a class="text-secondary font-weight-bold text-xs dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Edit</a>
                                <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a></li>
                                </ul>
                            </div>
                        </td>
                        </tr>

                        <tr>
                        <td>
                            <div class="d-flex px-2 py-1">
                            <div>
                                <img src="@/assets/img/team-4.jpg" class="avatar avatar-sm me-3">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                                <h6 class="mb-0 text-xs">Laurent Perrier</h6>
                                <p class="text-xs text-secondary mb-0">laurent@creative-tim.com</p>
                            </div>
                            </div>
                        </td>
                        <td>
                            <p class="text-xs font-weight-bold mb-0">Executive</p>
                            <p class="text-xs text-secondary mb-0">Projects</p>
                        </td>
                        <td class="align-middle text-center">
                            <span class="text-secondary text-xs font-weight-bold">19/09/17</span>
                        </td>
                        <td class="align-middle">
                            <div class="btn-group dropdown">
                                <a class="text-secondary font-weight-bold text-xs dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Edit</a>
                                <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a></li>
                                </ul>
                            </div>
                        </td>
                        </tr>

                        <tr>
                        <td>
                            <div class="d-flex px-2 py-1">
                            <div>
                                <img src="@/assets/img/team-3.jpg" class="avatar avatar-sm me-3">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                                <h6 class="mb-0 text-xs">Michael Levi</h6>
                                <p class="text-xs text-secondary mb-0">michael@creative-tim.com</p>
                            </div>
                            </div>
                        </td>
                        <td>
                            <p class="text-xs font-weight-bold mb-0">Programator</p>
                            <p class="text-xs text-secondary mb-0">Developer</p>
                        </td>
                        <td class="align-middle text-center">
                            <span class="text-secondary text-xs font-weight-bold">24/12/08</span>
                        </td>
                        <td class="align-middle">
                            <div class="btn-group dropdown">
                                <a class="text-secondary font-weight-bold text-xs dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Edit</a>
                                <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a></li>
                                </ul>
                            </div>
                        </td>
                        </tr>

                        <tr>
                        <td>
                            <div class="d-flex px-2 py-1">
                            <div>
                                <img src="@/assets/img/team-2.jpg" class="avatar avatar-sm me-3">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                                <h6 class="mb-0 text-xs">Richard Gran</h6>
                                <p class="text-xs text-secondary mb-0">richard@creative-tim.com</p>
                            </div>
                            </div>
                        </td>
                        <td>
                            <p class="text-xs font-weight-bold mb-0">Manager</p>
                            <p class="text-xs text-secondary mb-0">Executive</p>
                        </td>
                        <td class="align-middle text-center">
                            <span class="text-secondary text-xs font-weight-bold">04/10/21</span>
                        </td>
                        <td class="align-middle">
                            <div class="btn-group dropdown">
                                <a class="text-secondary font-weight-bold text-xs dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Edit</a>
                                <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a></li>
                                </ul>
                            </div>
                        </td>
                        </tr>

                        <tr>
                        <td>
                            <div class="d-flex px-2 py-1">
                            <div>
                                <img src="@/assets/img/team-4.jpg" class="avatar avatar-sm me-3">
                            </div>
                            <div class="d-flex flex-column justify-content-center">
                                <h6 class="mb-0 text-xs">Miriam Eric</h6>
                                <p class="text-xs text-secondary mb-0">miriam@creative-tim.com</p>
                            </div>
                            </div>
                        </td>
                        <td>
                            <p class="text-xs font-weight-bold mb-0">Programtor</p>
                            <p class="text-xs text-secondary mb-0">Developer</p>
                        </td>
                        <td class="align-middle text-center">
                            <span class="text-secondary text-xs font-weight-bold">14/09/20</span>
                        </td>
                        <td class="align-middle">
                            <div class="btn-group dropdown">
                                <a class="text-secondary font-weight-bold text-xs dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Edit</a>
                                <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton">
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                    <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a></li>
                                </ul>
                            </div>
                        </td>
                        </tr>
                    </tbody>
                    </table>
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
import HeaderPage from '@/components/HeaderPage.vue'
export default {
    components:{
        HeaderPage
    }
}
</script>

<style>
</style>