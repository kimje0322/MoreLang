<template>
  <div>
  <!-- 퀴즈 지문 -->
  <div style="display:inline-block;" class="pr-1" v-for="(item, i) in quizBox" :key="'A' + i">
    <div v-if="item.quiz!='blank'" style="margin-bottom: 10px; color: white; font-size:16px;">
      {{item.quiz}}
    </div>
    <div v-else id="blank" :class="`b${item.index}`" class="empty droppable" @dragover="dragOver" @dragenter="dragEnter" @dragend="dragLeave" @drop="dragDrop(`b${item.index}`)">
    </div>
  </div>
  <!-- 키워드 -->
   <div v-for="(keyword, i) in keyword" :key=i :class="`k${i}`" class="fill" style="display: inline" @dragstart="dragStart" @dragend="dragEnd" draggable="true">
      <span>{{keyword.key}}</span>
    </div>

  </div>
</template>


<script>
import axios from "axios";
const SERVER_URL = "https://morelang.gq/api";

export default {
  data() {
    return {
      className: '',
      cn: '',
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
    } 
  },
  mounted() {
    // this.cn = document.querySelector('.fill')
    console.log(this.className);
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
  computed: {
    fillSelector() {
        return document.querySelector('.fill')
    }
},
  methods: {
  // Drag Functions
    dragStart(event) {
      event.target.className += ' hold';
      setTimeout(() => (event.target.className = 'invisible'), 0);
      console.log('dragstart: '+ event.target.className);
      console.log(event.target.className);
    },

    dragEnd(event) {
      event.target.className = 'fill';
      console.log('dragend: '+ event.target.className);

    },

    dragOver(e) {
      e.preventDefault();
      console.log('dragover: '+ e.target.className);

    },

    dragEnter(e) {
      e.preventDefault();
      e.target.className += ' hovered';
      console.log('dragenter: '+  e.target.className);

    },

    dragLeave(event) {
      event.target.className = 'empty';
      console.log('dragleave: '+ event.target.className);

    },

    dragDrop(idx) {
      event.target.className = 'empty';
      console.log('drop확인')
      console.log(event.target);
      event.target.append(idx);
      // event.target.append(this.fillSelector);
      console.log('dragdrop: ', event.target.className);
      console.log("?? = " + event.target);
    },
  }
}
</script>


<style>
  body {
  background: darksalmon;
}

.fill {
  /* background-image: url('https://source.unsplash.com/random/150x150'); */
  background-color: indianred;
  color: white;
  padding: 0 5px;
  margin-right: 5px;
  position: relative;
  height: 30px;
  width: 50px;
  top: 5px;
  left: 5px;
  cursor: pointer;
}

.hold {
  border: solid 5px #ccc;
}

.empty {
  display: inline-block;
  height: 30px;
  width: 60px;
  margin: 10px;
  border: solid 3px salmon;
  background: white;
}

.hovered {
  background: #f4f4f4;
  border-style: dashed;
}
</style>