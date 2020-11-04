<template>
  <div></div>
</template>

<script>
export default {
  components: {},
  data() {
    return {};
  },
  mounted() {
    window.opener.location.href = "http://localhost:8081/mypage";
    // window.opener.location.href = "http://morelang.gq/mypage";
    window.close()
  },
  methods: {}
};
</script>

<style scoped>
</style>