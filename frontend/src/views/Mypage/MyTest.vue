<template>
  <v-container style="margin-top: 15px;">      
    <h1 class="mb-2">단어 테스트</h1>
    <span>단어모음에 저장된 단어들로 자가 테스트를 합니다.</span>
    <div v-if="!quizVoca" style="margin-top: 100px; text-align:center;">
      <v-icon size="70">mdi-file-document-edit-outline</v-icon>
      <p style="font-size: 20px; margin-top: 7px"> 단어장 추가를 하면 단어 테스트를 이용할 수 있습니다. </p>
    </div>
    <v-card 
    v-if="quizVoca"
    class="mt-5 px-5"
    color="white"
    max-width="620px"

  >
    <v-card-text style="color:black" >
      <!-- <div>English</div> -->
      <h2 class="display-1 my-3 mx-auto" style="text-align:center; font-weight: bold;">{{quizVoca}}</h2>
      <div style=" margin-top: 5px !important;" >
        <div v-for="(ans, i) in answerLst" :key="i" class="pretty p-icon p-round p-jelly my-5 mx-5" style="display:block;">
            <input :class="ans" type="radio" v-model="checkedAns" :value="i" />
            <div style="font-size: 22px;" class="state p-primary">
              <i class="icon mdi mdi-check"></i>
              <label :class="`${ans}`">{{ans}}</label>
            </div>
        </div>
      </div>
    </v-card-text>
    <v-card-actions>
      <v-dialog
        v-model="dialog2"
        width="30%"
        hide-overlay
        transition="dialog-bottom-transition"
      >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-bind="attrs" 
          v-on="on"
          text
          color="red accent-4"
          style="font-size: 18px;"
        >
          Learn More
        </v-btn>
      </template>
      <v-card>
      <iframe width="100%" height="500px" :src="dictUrl + quizVoca"></iframe>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error" text @click="dialog2 = false"> close </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
      <v-btn
        v-if="!ifChecked"
        class="ml-auto"
        style="font-size: 18px;"
        text
        color="blue accent-4"
        @click="checkAnswer(ans)"
      >
        채점하기
      </v-btn>
      <v-btn
        v-else
        class="ml-auto"
        style="font-size: 18px;"
        text
        color="blue accent-4"
        @click="nextQuiz"
      >
        다음 문제
      </v-btn>
    </v-card-actions>
  </v-card>
  </v-container>
</template>

<script>
import axios from "@/plugins/axios";
import "@/../public/css/MyTest.scss";
import Swal from "sweetalert2";
import $ from 'jquery';

export default {
  data() {
    return {
      dictUrl: "https://m.dic.daum.net/search.do?q=",
      quizVoca: '',
      answer: '',
      answerLst: [],
      maxIdx: 0,
      nextIdx: 0,
      checkedAns: '',
      ifChecked: false,
      dialog2: false,
    };
  },
  methods: {
    checkAnswer(ans) {
      this.ifChecked = true;
      if (this.checkedAns == this.answer) {
        Swal.fire({
            width: 430,
            text: "정답입니다!",
            timer: 1675,
            icon: "success",
            iconColor: "red",
            showConfirmButton: false,
          });
      } else {
          this.checkedAns = this.answer;
          $(`.${ans}`).css("color", "#F44336");
          // selectAns
          Swal.fire({
            width: 430,
            text: "😢 틀렸습니다.",
            timer: 1675,
            icon: "error",
            iconColor: "red",
            showConfirmButton: false,
        });
      }
    },
    nextQuiz() {
      this.checkedAns = '';
      this.ifChecked = false;
      if (this.nextIdx <= this.maxIdx) {
        axios.get(`user/myvoca-quize?index=${this.nextIdx}`
        ).then(res => {
          var quiz = res.data.result;
          this.quizVoca = quiz.problem;
          this.answerLst = quiz.answer_list;
          this.answer = quiz.answer;
          this.nextIdx = this.nextIdx + 1;
        })
      } 
    },
  },
  mounted() {
    axios.get(`user/init-quiz`);
    axios.get(
      `user/myvoca-quize?index=0`
    ).then(res => {
      var quiz = res.data.result;
      console.log(res.data.result);
      this.quizVoca = quiz.problem;
      this.answerLst = quiz.answer_list;
      this.answer = quiz.answer;
      this.maxIdx = quiz.maxIdx;
      this.nextIdx = 1;
      })
    },
  updated() {
  },
};
</script>


<style>
*{margin:0;padding:0;box-sizing:border-box;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;}
#container{
  height:230px;
}

.button-4{
  width:250px;
  height:50px;
  border:2px solid black;
  float:left;
  text-align:center;
  cursor:pointer;
  position:relative;
  box-sizing:border-box;
  overflow:hidden;
  /* margin:0 0 40px 50px; */
}
.button-4 a{
  font-family:arial;
  font-size:22px;
  color: #FFCDD2;
  text-decoration:none;
  line-height:50px;
  transition:all .5s ease;
  z-index:2;
  position:relative;
}
.eff-4{
  width:140px;
  height:50px;
  left:-140px;
  background: #FFCDD2;
  position:absolute;
  transition:all .5s ease;
  z-index:1;
}
.button-4:hover .eff-4{
  left:0;
}
.button-4:hover a{
  color:#fff;
}
footer{
  position:absolute;
  width:100%;
  height:30px;
  border-top:1px solid #34495e;
  bottom:0;
  display:none;
}
</style>