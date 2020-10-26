<template>
  <div class="search">
    <!-- 상단 로고 & 메뉴 -->
      <header
        style="padding-top: 30px; top: 0; left: 0; z-index: 40; width: 100%; display: block; position: fixed;"
      >
        <div class="navigation">
          <div
            style="margin: 0 100px; display: flex; align-items: center; justify-content: space-between;"
          >
            <div
              style="top: 10px; font-weight: 600; font-size: 18px; pointer-event: none; font-family: 'Calibre',sans-serif; color: darkgray;"
            >
              #MORELANG
            </div>
            <!-- 구글 로그인 -->
            <div></div>

            <!-- 검색 -->
            <div>
              <!-- <router-link to="/search" style="text-decoration: none;"> -->
              <v-icon large @click="search = true">mdi-magnify</v-icon>
              <!-- </router-link> -->
            </div>
            <div v-if="search" scroll="no">
              <div style="visibility: visible; opacity: 1; transition-delay: 0s; width: 100%; height: 100vh; position: fixed; top: 0; left: 0; background-color: rgba(231,229,221,.95); transition: visibility .3s ease-in-out,opacity .3s ease-in-out; z-index: 15;">
                <!-- <div style="justify-content: center; align-items: center;"> -->
                  <div style="margin: 10% 20%;">
                  <!-- <form action="/search" method="get"></form> -->
                  <input type="search" placeholder="SEARCH" autofocus style="border-bottom: 1px solid #737475; font-size: 30px; letter-spacing: 0.075em; display: inline-block;" />
                  <span style="display: inline-block" @click="search = false">X</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>

      <!-- 검색어 -->
      <div style="justify-content: center;">
        <span>Search results for "  "...</span>
      </div>
  </div>
</template>

<script>
export default {
  name: "Search",
  components: {

  }
};
</script>

<style>
</style>