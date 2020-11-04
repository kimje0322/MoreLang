<template>
  <div>
    <h3>포인트 충전</h3>
    <h5>1 포인트 -> 100원</h5>

    <v-container>
      <v-row no-gutters style="text-align:center; width:70%;">
        <v-col v-for="i in 4" :key="i">
          <v-card
            class="pa-2 selectLang"
            outlined
            tile
            style="position:relative; margin-right: 50px;"
						@click="Charge(i)"
          >
						<p class="coin" id="coin"></p>
            <p class="my-auto"> {{ 100 * i }} 포인트</p>
            <p class="my-auto">{{ 10000 * i }} 원</p>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import axios from "axios";
// const SERVER_URL = "https://morelang.gq/api"

var Coin = document.getElementById("coin");
var degrees = 0;
Coin.onclick = function() {
  degrees += 1800;
  console.log(degrees)
  Coin.style.webkitTransform = "rotateY(" + degrees + "deg)";
  Coin.style.MozTransform = "rotateY(" + degrees + "deg)";
  Coin.style.msTransform = "rotateY(" + degrees + "deg)";
  Coin.style.OTransform = "rotateY(" + degrees + "deg)";
  Coin.style.transform = "rotateY(" + degrees + "deg)";
}


export default {
	data() {
		return {

		}
	},
	methods: {
		Charge(point) {
			console.log(point)
			// axios.post()
		}
	}
};
</script>


<style>

body {
  -webkit-transform: perspective(500px);
  -webkit-transform-style: preserve-3d;
}
.coin {
  background-image: url("http://coins.thefuntimesguide.com/images/blogs/presidential-dollar-coin-reverse-statue-of-liberty-public-domain.png");
  background-size: 100% 100%;
  border-radius: 100%;
  height: 100px;
  margin: 50px auto;
  position: relative;
  width: 100px;
  -webkit-transition: 2s linear;
  -webkit-transform-style: preserve-3d;
}
</style>