<template>
  <v-container>      
    <h2 class="mt-5 mb-2">포인트 충전</h2>
    <p class="ml-1" style="font-size: 13px;">10원당 100포인트로 전환됩니다.</p>
      <v-row no-gutters style="text-align:center; width:98%;">
        <v-col v-for="i in 4" :key="i">
          <v-card
            class="pa-2 selectLang"
            outlined
            tile
            style="position:relative; margin-right: 38px; margin-bottom: 20px;"
            @click="Charge(i)"
          >
            <img @click="Coin(i)" style="width: 80%" :src="require(`@/assets/img/${i}.png`)" alt="coin">
            <p class="my-auto">{{ 10000 * i }} 포인트</p>
            <p class="my-auto">{{ 1000 * i }} 원</p>
          </v-card>
        </v-col>
      </v-row>
    <h2 class="mt-5 mb-2">포인트 이용내역</h2>
      <v-data-table
        :headers="headers"
        :items="point"
        :items-per-page="5"
        class="elevation-1"
      ></v-data-table>
      
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      degrees: 0,
      point: [],
      headers: [
        {
          text: '',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        { text: '날짜', value: 'date' },
        { text: '구분', value: 'charge' },
        { text: '이용 포인트', value: 'usedpoint' },
        { text: '남은 포인트', value: 'restpoint' },
      ],
      desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
            fat: 6.0,
            carbs: 24,
            protein: 4.0,
            iron: '1%',
          },
        ],
		};
  },
  methods: {
    Charge(point) {
			console.log(point);
			this.$router.push({ name: "Pay", params: { point: point * 10000} });
    },
    Coin(index) {
      var Coin = document.getElementById(index);
      // var degrees = 0;
      // Coin.onclick = function() {
      this.degrees += 1800;
      console.log(this.degrees);
      Coin.style.webkitTransform = "rotateY(" + this.degrees + "deg)";
      Coin.style.MozTransform = "rotateY(" + this.degrees + "deg)";
      Coin.style.msTransform = "rotateY(" + this.degrees + "deg)";
      Coin.style.OTransform = "rotateY(" + this.degrees + "deg)";
      Coin.style.transform = "rotateY(" + this.degrees + "deg)";
			// };
			// Coin = null;
    }
  },
  mounted() {
    axios.get(
      `${SERVER_URL}/user/pay/my-pointlogs?direction=ASC&page=0&size=10`
    )
  .then(res => {
    // for 반복문 돌리며 필요 data만 {}형식으로 넣기
    this.point = res.data.content;
    console.log(this.point);
    
    })
  },
};
</script>


<style>
/* body { */
  /* -webkit-transform: perspective(500px); */
  /* -webkit-transform-style: preserve-3d; */
/* } */

.coin {
  /* background-image: url("http://coins.thefuntimesguide.com/images/blogs/presidential-dollar-coin-reverse-statue-of-liberty-public-domain.png"); */
  background-size: 100% 100%;
  border-radius: 100%;
  height: 100px;
  margin: 25px auto;
  position: relative;
  width: 100px;
  /* -webkit-transition: 2s linear;
  -webkit-transform-style: preserve-3d; */
}
</style>