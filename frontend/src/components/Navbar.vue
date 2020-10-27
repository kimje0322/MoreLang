<template>
<!-- 상단 -->
  <div class="Navbar">
    <div >
      <v-card-title>
        <router-link to="/">
          <h2 class="logo px-5 py-1" style="display:inline-block">Morelang</h2>
        </router-link>
        <div class="mx-auto" style="display:inline-block; width: 33%;"> 
          <v-text-field
            height="37"
            hide-details=""
            placeholder='키워드 검색'
            outlined clearable
            prepend-inner-icon="mdi-magnify"
            
          />
          </div>
        <span class="mr-5">로그아웃</span>
      </v-card-title>
    </div>
</div>

</template>

<script scoped>
import "@/../public/css/Navbar.scss"
  export default {
    data () {
      return {
        items: [
          { title: 'Home', icon: 'mdi-home-city' },
          { title: 'My Account', icon: 'mdi-account' },
          { title: 'Users', icon: 'mdi-account-group-outline' },
        ],
      }
    },
  }
</script>

<style scoped>
  .logo {
    font-family: 'Kaushan Script', cursive;
  }
  a {
    color: black !important;
  }
</style>