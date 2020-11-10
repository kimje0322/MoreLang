<template>
  <div class="code-box mx-auto" @dragover="dragover">
    <!-- 퀴즈 Content -->
    <div class="play-box mx-auto mt-5">
      <div style="display:inline-block" class="pr-1" v-for="(item, i) in quizBox" :key=i>
        <div v-if="item.quiz!='blank'" style="margin-bottom: 10px; color: black; font-size:16px;">
          {{item.quiz}}
        </div>
        <!-- 퀴즈 빈칸 -->
        <div @dragover="ondragover(`b${item.index}`)" v-else id="blank" :class="`b${item.index}`" class="blank droppable" @drop="drop(item.index)">
        </div>
      </div>
    </div>
    <!-- 퀴즈 키워드 -->
    <div class="block-box">
      <div class="block-list mt-5 droppable" @drop="drop">
        <div class="droppable" @drop="drop">
          <Draggable v-for="(keyword, i) in keyword" :key=i :class="`k${i}`" style="display: inline">
            <span class="block block1" draggable="true" @dragstart="dragstart(keyword.original, i)">{{keyword.key}}</span>
          </Draggable>
        </div>
      </div>

    </div>

    <Container @drop="onDrop">            
      <Draggable v-for="item in items" :key="item.id">
        <div class="draggable-item">
          {{item.data}}
        </div>
      </Draggable>
    </Container>
  </div>
</template>
<!-- <div v-for="(item, index) in items.block0" :key="`a+${index}`" class="block block0" draggable="true" @dragstart="dragstart" >keynote</div> -->

<script>
import axios from "@/plugins/axios";
import $ from 'jquery';
import { Container, Draggable } from "vue-smooth-dnd";
import { applyDrag, generateItems } from "./utils";

const SERVER_URL = "https://morelang.gq/api";

export default {
  name: 'Quiz',
  components: { Container, Draggable },
  data() {
    return {
      // vue-smooth-dnd
      items: generateItems(50, i => ({ id: i, data: "Draggable " + i })),
      // quiz
      quizBox: [],
      keyword: [],
      blankSize: '',
      answer: {},
      score: 0,
      userAns: 0,
      rightAns: [],
      keyIdx: '',
      keyIdxWidth: 0,
      // drag
      isMove: true,
      isObstacle: false,
      distX: '',
      distY: '',
      targetClass: '',
      targetClass2: '',
      targetNum: '',
      targetFlag: false,
      keywordsWidth: [],
      onQuiz: [],
      classId: 'a',
    }
  },
  computed: {
  },
  created() {
    // window.addEventListener('scroll', this.handleScroll)
  },
  mounted() {
    this.onMove();
    axios.post(
      "/newuser/puzzletest?inputText=a"  
      ).then(res => {
        this.answer = res.data.answer;
        // this.quizBox = res.data.quizeText;
        var quizInput = res.data.inputTextArray;
        this.keyword = res.data.keyword;
        var j = 1;
        for (var i=0; i<quizInput.length; i++) {
          if (quizInput[i] === '______') { 
              this.quizBox.push({index: j++, quiz: 'blank'});
          } else if (quizInput[i].startsWith('______')) {
              this.quizBox.push({index: j++, quiz: 'blank'})
              this.quizBox.push({index: 0, quiz: quizInput[i].slice(6)})
          } else {
            this.quizBox.push({index: 0, quiz: quizInput[i]})    
          }
        }
      })
  },
  updated() {
    // this.keywordsWidth
    for (var i=0; i<this.keyword.length; i++) {
      this.keywordsWidth.push(this.keyword[i]);

    }
  //     $(".blank").css("width", "50px");
  //   정답체크
  //   console.log(this.userAns);
  //     if (idx === this.userAns && !this.rightAns.includes(idx)) {
  //       if (score < Object.keys(this.answer).length) {
  //         this.score += 1;
  //       }
  //       this.rightAns.push(idx);
  //       alert('정답')
  //   }
  },
  watch: {
    checkAnswer: function () {
      if (this.score === this.keyword.length) {
        // 정답입니다 텍스트 보여주기
        console.log('정답')
      }
    }
  },
  methods: {
    onDrop(dropResult) {
      this.items = applyDrag(this.items, dropResult);
    },
    onMove() {
      this.isMove = true; 
    },
    dragstart(ans, i) {
      this.keyIdx = `k${i}`;
      // var KI = $(`.${this.keyIdx}`).width() - 10;
      console.log('드래그start-키너비'+this.keyIdxWidth);
      console.log(this.keyIdx);
      // event.target.style.position = 'absolute';
      let posX = event.pageX;
      let posY = event.pageY;
      this.distX = event.srcElement.offsetLeft - posX;
      this.distY = event.srcElement.offsetTop - posY;
      event.target.classList.add(`${this.classId}`)
      this.classId += '0'
      this.targetClass = event.target.classList[2]
      this.targetClass2 = event.target.classList[1]
      // 키워드 
      this.userAns = ans;
    },
    ondragover(idx) {
      // 사이즈 변경
      // idx width(빈칸 너비) => this.keyIdx(키워드 너비)로 바꾸기
      // console.log('드래그오버1-키너비'+this.keyIdxWidth);
      this.keyIdxWidth === $(`.${this.keyIdx}`).width() - 10;
      $(`.${idx}`).css("width", `${this.keyIdxWidth}`);
      // console.log('키워드 클래스'+ this.keyIdx);
      // console.log('드래그오버2-키너비'+this.keyIdxWidth);
    },
    dragover(event) {
      event.stopPropagation();
      event.preventDefault();
    },
    drop(idx) {
      // 정답처리
      // idx: 빈칸이 몇번째 칸인지
      if (idx === this.userAns && !this.rightAns.includes(idx)) {
        if (this.score < Object.keys(this.answer).length) {
          this.score += 1;
        }
        this.rightAns.push(idx);
        // alert('정답!')
      }
      if (this.score === Object.keys(this.answer).length) {
        alert('정답입니다.')}
      event.stopPropagation();
      event.preventDefault();
      // 드롭
      let posX = event.pageX;
      let posY = event.pageY;
      // if (posX >= 300 && posX <= 1450) {
        // if (posY >= 113 && posY <= 520) {
      if(event.target.classList && event.target.classList.contains("droppable")){
          document.querySelector(`.${this.targetClass}`).style.position = 'absolute';
          document.querySelector(`.${this.targetClass}`).style.top = 0;
          document.querySelector(`.${this.targetClass}`).style.left = 0;
          document.querySelector(`.${this.targetClass}`).style.marginLeft = posX + this.distX + 'px';
          document.querySelector(`.${this.targetClass}`).style.marginTop = posY + this.distY + 'px';
          // console.log(event.target);
          // $("div").width(400);
          // document.querySelector(".i").style.width = 100 ;
          // $('div.i').width( '100px' );
          const CLONE = document.querySelectorAll(`.${this.targetClass2}`)
          for (let i=0; i<CLONE.length; i++) {
            if (CLONE[i].classList.length == 2) {
              this.targetFlag = false;
            } else {
              this.targetFlag = true;
            }
          }
        }
        // }
      // }
      // console.log(posX, posY, this.distX, this.distY)
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
.code-block-container .unity-box {
  width: 80%;
  margin-right: 1%;
  /* height: 0px; */
  /* background-color: grey; */
}
.code-block-container .code-box {
  /* height: 450px; */
  /* display: flex; */
  position: relative;
}
/* .code-box .block-box {
  width: 14%;
  display: flex;
} */
.play-box {
  width: 100%;
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
  /* height: 350px; */
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
  /* display: flex; */
  justify-content: center;
  align-items: center;
  padding-left: 5px;
  height: 50px;
  border-top-left-radius: 20px;
  border-bottom-left-radius: 10px;
  margin-bottom: 5px;
  cursor: pointer;
  transition: background-color .5s ease;
}
 .block-list .block {
  padding: 2px 7px;
  margin-right: 8px;
  border-radius: 8px;
  background-color: rgb(22, 177, 22);
  margin-bottom: 10px;
  cursor: pointer;
  color: #fff;
  font-size: 14px;
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
.blank {
  /* border: dashed grey 1px; */
  border-radius: 8px;
  background-color: lightgrey;
  width: 50px;
  height: 25px;
}
.draggable-item {
  border: 1px solid black;
  width: 200px;
  margin: 5px;
}

</style>