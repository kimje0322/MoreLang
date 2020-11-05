<template>
<div>
</div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "https://morelang.gq/api";

export default {
  components: {},
  data() {
    return {
      pg_token: null,
      payReady: null,
    };
  },
  mounted() {
    this.pg_token = this.$route.query.pg_token;
    this.payReady = this.$store.state.payReady;
    console.log(this.payReady)
    axios.get(`${SERVER_URL}/user/pay/approve?tid=${this.payReady.tid}&pg_token=${this.pg_token}&total_amount=${this.payReady.total_amount}`).then(res => {
      console.log(res)
      window.opener.location.href = "https://morelang.gq/mypage";
      window.close()
    });
  },
  methods: {
  }
}
</script>

<style scoped>
</style>