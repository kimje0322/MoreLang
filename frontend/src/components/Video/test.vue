<template>
  <div class="code-box mx-auto" @dragover="dragover">
    <!-- 퀴즈 Content -->
    {{keyword}}
    <div class="play-box mx-auto mt-5">
      <h5>퀴즈 Content</h5>
      <div style="display:inline-block" class="pr-1" v-for="(item, i) in quizBox" :key=i>
        <!-- 퀴즈 내용 -->
        <div v-if="item.quiz!='blank'" style="margin-bottom: 10px; color: black; font-size:16px;">
          {{item.quiz}}
        </div>
        <!-- 퀴즈 속 빈칸 -->
        <div @dragover="ondragover(`b${item.index}`)" v-else id="blank" :class="`b${item.index}`" class="blank droppable" @drop="drop(item.index)">
        </div>
      </div>
    </div>
    <!-- 퀴즈 키워드 -->
    <div class="block-box">
      <div class="block-list mt-5 droppable" @drop="drop">
      <h5>키워드</h5>
        <div class="droppable" @drop="drop">
          <div v-for="(keyword, i) in keyword" :key=i :class="`k${i}`" style="display: inline">
            <span class="block block1" draggable="true" @dragstart="dragstart(keyword.original, i)">{{keyword.key}}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<!-- <div v-for="(item, index) in items.block0" :key="`a+${index}`" class="block block0" draggable="true" @dragstart="dragstart" >keynote</div> -->

<script>
import axios from "axios";
import $ from 'jquery';

const SERVER_URL = "https://morelang.gq/api";

export default {
  name: 'Quiz',
  data() {
    return {
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
      keywordWidth: {},
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
  created() {
    // window.addEventListener('scroll', this.handleScroll)
  },
  mounted() {
    this.onMove();
    axios.post(
      `${SERVER_URL}/newuser/puzzletest?inputText=a`  
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
    },
  },
  methods: {
    onMove() {
      this.isMove = true; 
    },
    dragstart(ans, i) {
      this.keyIdx = `k${i}`;
      if (!this.keywordWidth[[this.keyIdx]]) {
        this.keywordWidth[[this.keyIdx]] = $(`.${this.keyIdx}`).width();
      } 
      console.log('드래그 시작, keyIdx 생김');
      console.log(this.keywordWidth);
      // event.target.style.position = 'absolute';
      let posX = event.pageX;
      let posY = event.pageY;
      this.distX = event.srcElement.offsetLeft - posX;
      this.distY = event.srcElement.offsetTop - posY;
      event.target.classList.add(`${this.classId}`)
      this.classId += '0'
      this.targetClass = event.target.classList[2]
      this.targetClass2 = event.target.classList[1]
      // console.log(event.target)
      // 키워드 
      this.userAns = ans;
    },
    ondragover(idx) {
      // 사이즈 변경
      // idx width를 this.keyIdx로 바꾸기
      console.log('키너비'+this.keyIdxWidth);
      console.log('키워드 클래스'+ this.keyIdx);
 
      // 너비 유지
      if ($(`.${this.keyIdx}`).width() === 0) {
        this.keyIdxWidth = this.keywordWidth[[this.keyIdx]];    
      }
      // for (var i=0; i<this.keyword.length; i++) {
      //  $(`.k${i}`).css("width", `${this.keyIdxWidth}`);
      // } 
      else {
        this.keyIdxWidth = $(`.${this.keyIdx}`).width() - 10;
        $(`.${idx}`).css("width", `${this.keyIdxWidth}`);
      }
      console.log('드래그오버 중')
    },
    dragover(event) {
      event.stopPropagation();
      event.preventDefault();
    },
    drop(idx) {
      console.log(this.keywordWidth[[this.keyIdx]])

      console.log('확인한다 오바')
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
          const CLONE = document.querySelectorAll(`.${this.targetClass2}`)
          for (let i=0; i<CLONE.length; i++) {
            if (CLONE[i].classList.length == 2) {
              this.targetFlag = false;
            } else {
              this.targetFlag = true;
            }
          }
        }
    }
  },
}
</script>

<style scoped>

/* .code-block-container {
  display: flex;
  width: 100%;
  text-align: center;
} */
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

</style>
