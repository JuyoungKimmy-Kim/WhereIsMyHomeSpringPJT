<template>
  <!-- Navbar Transparent -->
  <nav
    class="navbar navbar-expand-lg position-absolute top-0 z-index-3 w-100 shadow-none my-3 navbar-transparent"
  >
    <div class="container">
      <a
        class="navbar-brand text-white"
        rel="tooltip"
        title="Designed and Coded by Creative Tim"
        data-placement="bottom"
      >
        <router-link to="/">Home</router-link></a
      >
      <button
        class="navbar-toggler shadow-none ms-2"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navigation"
        aria-controls="navigation"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon mt-2">
          <span class="navbar-toggler-bar bar1"></span>
          <span class="navbar-toggler-bar bar2"></span>
          <span class="navbar-toggler-bar bar3"></span>
        </span>
      </button>
      <div
        class="collapse navbar-collapse w-100 pt-3 pb-2 py-lg-0 ms-lg-12 ps-lg-5"
        id="navigation"
      >
        <ul class="navbar-nav navbar-nav-hover ms-auto">
          <li class="nav-item dropdown dropdown-hover mx-2 ms-lg-6">
            <a
              class="nav-link ps-2 d-flex cursor-pointer align-items-center"
              id="dropdownMenuPages2"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="material-icons opacity-6 me-2 text-md">dashboard</i>
              Pages
              <img
                src="../assets/img/down-arrow-white.svg"
                alt="down-arrow"
                class="arrow ms-auto ms-md-2 d-lg-block d-none"
              />
              <img
                src="../assets/img/down-arrow-dark.svg"
                alt="down-arrow"
                class="arrow ms-auto ms-md-2 d-lg-none d-block"
              />
            </a>
          </li>

          <li class="nav-item dropdown dropdown-hover mx-2">
            <a
              class="nav-link ps-2 d-flex cursor-pointer align-items-center"
              id="dropdownMenuBlocks"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="material-icons opacity-6 me-2 text-md">view_day</i>
              Sections
              <img
                src="../assets/img/down-arrow-white.svg"
                alt="down-arrow"
                class="arrow ms-auto ms-md-2 d-lg-block d-none"
              />
              <img
                src="../assets/img/down-arrow-dark.svg"
                alt="down-arrow"
                class="arrow ms-auto ms-md-2 d-lg-none d-block"
              />
            </a>
          </li>

          <li class="nav-item dropdown dropdown-hover mx-2">
            <a
              class="nav-link ps-2 d-flex cursor-pointer align-items-center"
              id="dropdownMenuDocs"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="material-icons opacity-6 me-2 text-md">article</i>
              Docs
              <img
                src="../assets/img/down-arrow-white.svg"
                alt="down-arrow"
                class="arrow ms-auto ms-md-2 d-lg-block d-none"
              />
              <img
                src="../assets/img/down-arrow-dark.svg"
                alt="down-arrow"
                class="arrow ms-auto ms-md-2 d-lg-none d-block"
              />
            </a>
          </li>

          <li class="nav-link ps-2 d-flex cursor-pointer align-items-center">
            <router-link to="/map">Map</router-link>
          </li>
          <li class="nav-link ps-2 d-flex cursor-pointer align-items-center">
            <router-link to="/register">Register</router-link>
          </li>
          <li class="nav-link ps-2 d-flex cursor-pointer align-items-center">
            <router-link to="/login">Login</router-link>
          </li>
          <li class="nav-link ps-2 d-flex cursor-pointer align-items-center">
            <router-link to="/contact">Contact</router-link>
          </li>
          <li class="nav-link ps-2 d-flex cursor-pointer align-items-center">
            <router-link to="/search">Search</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
</template>

<script></script>

<style scoped></style>
