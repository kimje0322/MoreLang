<template>
  <div class="row px-5 py-5">
    <div class="col-3">
      <h3>Draggable 1</h3>
      <draggable
        class="dragArea list-group"
        :list="list1"
        :clone="clone"
        :group="{ name: 'people', pull: pullFunction }"
        @start="start"
      >
        <div class="list-group-item1" v-for="element in list1" :key="element.id">
          {{ element.name }}
        </div>
      </draggable>
    </div>

    <div class="col-3">
      <h3>Draggable 2</h3>
      <!-- 퀴즈 Content -->
      <div class="play-box mx-auto mt-5">
        <div style="display:inline-block" class="pr-1" v-for="(item, i) in quizBox" :key=i>
          <div v-if="item.quiz!='blank'" style="margin-bottom: 10px; color: black; font-size:16px;">
            {{item.quiz}}
          </div>
          <!-- 퀴즈 빈칸 -->
          <div v-else id="blank" :class="`b${item.index}`" class="blank droppable" @drop="drop(item.index)">
          </div>
        </div>
      </div>

      <draggable class="dragArea list-group" :list="list2" group="people">
        <div @drop="drop" class="list-group-item2" v-for="element in list2" :key="element.id">
          {{ element.name }}
        </div>
      </draggable>
    </div>

    <!-- <rawDisplayer class="col-3" :value="list1" title="List 1" />
    <rawDisplayer class="col-3" :value="list2" title="List 2" /> -->
  </div>
</template>

<script>
import axios from "axios";
// import $ from 'jquery';
import draggable from "vuedraggable";

const SERVER_URL = "https://morelang.gq/api";

let idGlobal = 8;
export default {
  name: "clone-on-control",
  display: "Clone on Control",
  instruction: "Press Ctrl to clone element from list 1",
  order: 4,
  components: {
    draggable
  },
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
      list1: [
        { name: "Jesus", id: 1 },
        { name: "Paul", id: 2 },
        { name: "Peter", id: 3 }
      ],
      list2: [
        { name: "Luc", id: 5 },
        { name: "Thomas", id: 6 },
        { name: "John", id: 7 }
      ],
      controlOnStart: true
    };
  },
  mounted() {
    axios.post(
      `${SERVER_URL}/newuser/puzzletest?inputText=a`  
      ).then(res => {
        this.answer = res.data.answer;
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
  methods: {
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
    },
    clone({ name }) {
      return { name, id: idGlobal++ };
    },
    pullFunction() {
      return this.controlOnStart ? "clone" : true;
    },
    start({ originalEvent }) {
      this.controlOnStart = originalEvent.ctrlKey;
    }
  }
};
</script>
<style scoped>
.blank {
  /* border: dashed grey 1px; */
  border-radius: 8px;
  background-color: lightgrey;
  width: 50px;
  height: 25px;
}
 .list-group-item1{
    border-radius: 8px;
    background-color: rgb(22, 177, 22);
    width: 50px;
    height: 25px;
    cursor: pointer;
  }
  .list-group-item2 {
    border-radius: 8px;
    background-color: lightgrey;
    width: 50px;
    height: 25px;
  }
</style>