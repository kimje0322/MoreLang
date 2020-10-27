<template>
  <div class="search">
    <!-- 상단 로고 & 메뉴 -->
    <header
      style="padding-top: 30px; top: 0; left: 0; width: 100%; display: block;"
    >
      <div class="navigation">
        <div
          style="margin: 0 100px; display: flex; align-items: center; justify-content: space-between;"
        >
          <router-link to="/" style="text-decoration: none;">
            <div
              style="top: 10px; font-weight: 600; font-size: 18px; pointer-event: none; font-family: 'Calibre', sans-serif; color: darkgray;"
            >
              #MORELANG
            </div>
          </router-link>
          <!-- 구글 로그인 -->
          <div></div>

          <!-- 검색 -->
          <div>
            <!-- <router-link to="/search" style="text-decoration: none;"> -->
            <form action="/search" method="get">
              <input
                id="search_word"
                type="search"
                placeholder="SEARCH"
                autofocus
                style="border-bottom: 1px solid #737475; font-size: 30px; letter-spacing: 0.075em; display: inline-block;"
              />
              <v-icon large style="display: inline-block">mdi-magnify</v-icon>
            </form>
            <!-- </router-link> -->
          </div>
        </div>
      </div>
    </header>

    <!-- 검색어 -->
    <div style="display: flex; justify-content: center; margin: 50px 0;">
      <h4 style="font-size: 18px;">Search results for " "...</h4>
    </div>

    <!-- 비디오 나오는 부분 -->
    <div
      style="padding: 0 90px; display: flex; flex-flow: row wrap; justify-content: space-between;"
    >
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
      <div
        style="width: clac(33.33% - 60px); margin-bottom: 80px; display: inline-block;"
      >
        <div style="margin-bottom: 15px;">
          <img
            src="https://9to5google.com/wp-content/uploads/sites/4/2017/08/youtube_logo_dark.jpg?quality=82&strip=all"
            alt=""
            width="330"
          />
        </div>
        <h3>YOUTUBE VIDEO</h3>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Search",
  components: {}
};
</script>

<style>
</style>