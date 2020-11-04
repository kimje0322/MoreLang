<template>
  <div>
    <div style="margin-top: 250px; margin-left: 250px;">
      <h1>'결제 진행중입니다.'</h1>
    </div>
  </div>
</template>

<script>
import axios from "axios";

// const SERVER_URL = "http://localhost:8080";
const SERVER_URL = "https://morelang.gq/api";

export default {
  components: {},
  data() {
    return {
      // 이 부분 고정값으로 되어있는거 변경 필요
      item_name: '',
      total_amount: 0,
    };
  },
  mounted() {
    this.item_name = this.$route.params.target/100 + "보석"
    this.total_amount = this.$route.params.target;
    axios.get(`${SERVER_URL}/pay/ready?item_name=${this.item_name}&total_amount=${this.total_amount}`).then(res => {
      this.$store.commit('setPayReady', res.data);
      window.open(res.data.next_redirect_pc_url, "_blank", 'width=450, height=500');
    });
  },
  methods: {
  }
};
</script>

<style scoped>
.title {
  color: black;
}
</style>