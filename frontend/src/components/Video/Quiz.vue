<template>
  <div class='wrap'>
    <div class="code-block-container">
    <h3>[공지] 수정중입니다! 임시로 여기서 작업중입니다.</h3>

      <!-- 단어 -->
      <img src="https://image.dongascience.com/Photo/2015/07/14370336879614[1].jpg" style="width:670px margin-left:15px;" alt="">
      <div class="code-box mx-auto" @drop="drop" @dragover="dragover">
        <div class="play-box mx-auto mt-5">
          <span>___</span>
          <p>phone at the Consumer</p>
          <!-- <p class="mx-auto mt-2" style="color:black; width: 92%;font-size: 16px;">{{quizText}}</p> -->
        </div>
        <div class="block-box">
          <div v-show="isMove" class="block-list" >
            <div v-for="(item, index) in items.block0" :key="`a+${index}`" class="block block0" draggable="true" @dragstart="dragstart" >keynote</div>
            <div v-for="(item, index) in items.block1" :key="`b+${index}`" class="block block1" draggable="true" @dragstart="dragstart">Pichai</div>
            <div v-for="(item, index) in items.block2" :key="`c+${index}`" class="block block2" draggable="true" @dragstart="dragstart">Sundar</div>
            <div v-for="(item, index) in items.block3" :key="`d+${index}`" class="block block3" draggable="true" @dragstart="dragstart">new</div>
            <div v-for="(item, index) in items.block4" :key="`e+${index}`" class="block block4" draggable="true" @dragstart="dragstart">love</div>
            <div v-for="(item, index) in items.block5" :key="`f+${index}`" class="block block5" draggable="true" @dragstart="dragstart">Android</div>
            <div v-for="(item, index) in items.block6" :key="`g+${index}`" class="block block6" draggable="true" @dragstart="dragstart">Consumer</div>
          </div>
        </div>

        <!-- <div class="play-box">
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const SERVER_URL = "https://morelang.gq/api";

export default {
  name: 'CodeBlock',
  data() {
    return {
      // quiz
      quizText: [],
      // drag
      isMove: true,
      isObstacle: false,
      distX: '',
      distY: '',
      targetClass: '',
      targetClass2: '',
      targetNum: '',
      targetFlag: false,
      items: {
        block0: 1, block1: 1, block2: 1, block3: 1, block4: 1, block5: 1, block6: 1
      },
      onQuiz: [],
      classId: 'a',
    }
  },
  components: {
  },
  computed: {
  },
  created() {
    // window.addEventListener('scroll', this.handleScroll)
  },
  mounted() {
    this.onMove();
    axios.post(
      `${SERVER_URL}/guest/puzzletest?inputText=a`  
      ).then(res => {
        // console.log(res.data);
        this.quizText = res.data.quizeText;
      })
  },
  watch: {
  },
  methods: {
    onMove() {
      this.isMove = true; 
    },
    dragstart(event) {
      // event.target.style.position = 'absolute';
      let posX = event.pageX;
      let posY = event.pageY;
      this.distX = event.srcElement.offsetLeft - posX;
      this.distY = event.srcElement.offsetTop - posY;
      event.target.classList.add(`${this.classId}`)
      this.classId += '0'
      this.targetClass = event.target.classList[2]
      this.targetClass2 = event.target.classList[1]
    },
    dragover(event) {
      event.stopPropagation();
      event.preventDefault();
    },
    drop(event) {
      event.stopPropagation();
      event.preventDefault();
      console.log('될거지?')
      console.log(event.pageX)
      let posX = event.pageX;
      let posY = event.pageY;
      // if (posX >= 300 && posX <= 1450) {
        // if (posY >= 113 && posY <= 520) {
          document.querySelector(`.${this.targetClass}`).style.position = 'absolute';
          document.querySelector(`.${this.targetClass}`).style.top = 0;
          document.querySelector(`.${this.targetClass}`).style.left = 0;
          document.querySelector(`.${this.targetClass}`).style.marginLeft = posX + this.distX + 'px';
          document.querySelector(`.${this.targetClass}`).style.marginTop = posY + this.distY + 'px';
          const CLONE = document.querySelectorAll(`.${this.targetClass2}`)
          for (let i=0; i<CLONE.length; i++) {
            if (CLONE[i].classList.length == 2) {
              this.targetFlag = false
            } else {
              this.targetFlag = true
            }
          }
        // }
      // }
      console.log(posX, posY, this.distX, this.distY)
      // $('#mydiv').css('margin-left', posX + this.distX + 'px')
      //     .css('margin-top', posY + this.distY + 'px');
    }
  },
  beforeDestroy () {
    // window.removeEventListener('scroll', this.handleScroll)
  },
}
</script>

<style scoped>
.code-block-container {
  /* display: flex; */
  /* margin-top: 100px; */
  width: 100%;
  text-align: center;
}

.code-block-container .unity-box {
  width: 80%;
  margin-right: 1%;
  height: 450px;
  /* background-color: grey; */
}

.code-block-container .code-box {
  /* width: 40%; */
  /* height: 450px; */
  /* display: flex; */
  position: relative;
}

.code-box .block-box {
  width: 14%;
  display: flex;
}
.play-box {
  width: 78%;
  background-color: #def5df;
  border-radius: 7px;
  padding: 12px;
}
.code-box {
  width: 80%;
  height: 70%;
  /* background-color: #def5df; */
  border-radius: 7px;
  /* border: 1px solid green; */
  /* position: relative; */
}

.block-box .block-menu-bar {
  width: 30%;
}

.block-box .block-list {
  width: 100%;
  height: 350px;
  padding: 10px;
  margin: 0 2px;
  /* background-color: #def5df; */
  border: 1px solid green;
  border-radius: 7px;
}

.block-box .block-menu-bar .on-menu-bar {
  background-color: black;
  font-weight: 700;
}

.block-box .block-menu-bar .menu {
  display: flex;
  justify-content: center;
  align-items: center;
  padding-left: 5px;
  height: 50px;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 10px;
  border: 1px solid #a4d4ff;
  margin-bottom: 5px;
  cursor: pointer;
  transition: background-color .5s ease;
}

.block-box .block-list .block {
  padding: 2px 7px;
  border-radius: 8px;
  background-color: rgb(22, 177, 22);
  margin-bottom: 10px;
  cursor: pointer;
  color: #fff;
  font-size: 14px;
  float: left;
  clear: both;
}

.play-box .play {
  position: absolute;
  top: 0;
  left: 0;
  width: 100px;
  height: 50px;
  background-color: bisque;
}

.code-block-container {
  width: 100%;
  height: 100%;
}

</style>