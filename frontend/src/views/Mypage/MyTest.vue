<template>
  <v-container style="margin-top: 15px;">      
    <v-select
      v-if="`quizVoca`"
      style="float:right; max-width:220px; margin-right: 50px"
      :items="language"
      label="Language"
      v-model="selectlang"
      dense
      outlined
    ></v-select>
    <h1 class="mb-3">단어 테스트</h1>
    <span>단어모음에 저장된 단어들로 자가 테스트를 합니다.</span>


    <div v-if="!quizVoca && !testCompleted" style="margin-top: 100px; text-align:center;">
      <v-icon size="70">mdi-file-document-edit-outline</v-icon>
      <p style="font-size: 20px; margin-top: 7px"> 단어장 추가를 하면 테스트를 이용할 수 있습니다. </p>
    </div>
    <div v-else-if="!nextVoca && testCompleted">
      <p style="font-size: 20px; margin-top: 110px; text-align:center;"> 단어장의 모든 단어 테스트를 완료하였습니다. </p>
    </div>
    <v-card 
      v-if="quizVoca && !testCompleted"
      class="mt-5 px-5"
      color="white"
      max-width="620px"
    >
    <v-card-text style="color:black" >
      <div v-if="selectlang != 'All'">{{this.selectlang}}</div>
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
        @click="checkAnswer"
      >
        채점하기
      </v-btn>
      <v-btn
        v-if="ifChecked && nextVoca"
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
// import $ from 'jquery';

export default {
  data() {
    return {
      language: ["All"],
      selectlang: 'All',
      dictUrl: "https://m.dic.daum.net/search.do?q=",
      quizVoca: '',
      answer: '',
      answerLst: [],
      maxIdx: 0,
      nextIdx: 0,
      checkedAns: '',
      ifChecked: false,
      dialog2: false,
      nextVoca: false,
      testCompleted: false,
    };
  },
  watch: {
    selectlang: function () {
      this.selectlanguage(this.selectlang);
    },
    nextIdx: function() {
      if (this.nextIdx > this.maxIdx + 1) {
        this.nextVoca = false;
        this.testCompleted = true;
        this.quizVoca = false;
      }
    }
  },
  methods: {
    selectlanguage() {
      // if (this.nextIdx <= this.maxIdx) {
      //   this.nextVoca = false;
      // } else if (this.nextIdx > this.maxIdx) {
      //   this.nextVoca = true;
      // }
      if (this.selectlang == 'All') {
        axios.get(
        `user/myvoca-quize?index=0`
      ).then(res => {
        if (res.data.result == 'not exist') {
            console.log('data == not exist??')
            this.testCompleted = true;
          } else {
        console.log('여기 퀴즈 전체로 mounted됐을때');
        console.log(res.data.result);
        var quiz = res.data.result;
        this.quizVoca = quiz.problem;
        this.answerLst = quiz.answer_list;
        this.answer = quiz.answer;
        this.maxIdx = quiz.maxIdx;
        this.nextIdx = 1;
          }
        })
      } else {
        axios.get(
          `user/myvoca-quize?country=${this.selectlang}&index=0`
        ).then(res => {
          if (res.data.result == 'not exist') {
              this.testCompleted = true;
          } else {
          console.log('여기 퀴즈 특정언어로 mounted됐을때');
          console.log(res.data.result);
          var quiz = res.data.result;
          this.quizVoca = quiz.problem;
          this.answerLst = quiz.answer_list;
          this.answer = quiz.answer;
          this.maxIdx = quiz.maxIdx;
          this.nextIdx = 1;
            }
          })
        }
      },
    checkAnswer() {
      this.ifChecked = true;
      if (this.nextIdx > this.maxIdx) {
        this.nextVoca = false;
      } else if (this.nextIdx <= this.maxIdx) {
        this.nextVoca = true;
      };
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
        // $(`.${ans}`).css("color", "#F44336");
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

      console.log("nextIdx " + this.nextIdx);
      console.log("maxIdx " +this.maxIdx);
    
      if (this.nextIdx <= this.maxIdx) {
        console.log('여기로 와야지 다음 맥ㄳ그 퀴즈 가지ㅠ')
        if (this.selectlang == 'All') {
          console.log('다음퀴즈// nextIdx ' + this.nextIdx);
          axios.get(`user/myvoca-quize?index=${this.nextIdx}`)
          .then(res => {
            console.log(res.data.result);
            if (res.data.result == 'not exist') {
              console.log(res.data.result);
              this.testCompleted = true;
            } else {
            var quiz = res.data.result;
            this.quizVoca = quiz.problem;
            this.answerLst = quiz.answer_list;
            this.answer = quiz.answer;
            this.maxIdx = quiz.maxIdx;
            this.nextIdx += 1;
            console.log(this.nextIdx);
            }
          })
        } else {
          axios.get(`user/myvoca-quize?country=${this.selectlang}&index=${this.nextIdx}`
          ).then(res => {
            if (res.data.result == 'not exist') {
              console.log(res.data.result);

              this.testCompleted = true;
            } else {
            var quiz = res.data.result;
            this.quizVoca = quiz.problem;
            this.answerLst = quiz.answer_list;
            this.answer = quiz.answer;
            this.nextIdx += 1;
            }
          })
        }
      // } else if (this.nextIdx > this.maxIdx) {
      //   this.nextVoca = false;
      //   this.testCompleted = true;
      }
    },
  },
  mounted() {
    axios.get(`user/init-quiz`);
    axios.get(
      `user/myvoca-country`
    ).then(res => {
      var lang = res.data;
      for (var i=0; i < lang.length; i++) {
        this.language.push(lang[i]);
      }
      console.log(this.language);
      if (lang.length != 0) {
        this.selectlanguage();
      }
    })
    // axios.get(
    //     `user/myvoca-quize?index=0`
    //   ).then(res => {
    //     console.log('여기 퀴즈 mounted됐을때')
    //     console.log(res.data.result);
    //     var quiz = res.data.result;
    //     this.quizVoca = quiz.problem;
    //     this.answerLst = quiz.answer_list;
    //     this.answer = quiz.answer;
    //     this.maxIdx = quiz.maxIdx;
    //     this.nextIdx = 1;
    //     });

    },
  updated() {
    console.log('여기는 updated// nextIdx ' + this.nextIdx);
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