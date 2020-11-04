<template>
  <v-container>
    <div class="video">
          <Navbar />
            <div style="width: 100%; height: 100vh; display: block; margin-top: 7%;">
              <v-row no-gutters>
                 <v-col  cols="8">
             
                  
                  
                  <v-card>
                  <youtube id="ytp" :video-id="videoId" ref="youtube" :nocookie="true" width="95%"  :player-vars="playerVars" @ready="getCaptionsList"  @paused="sayHi"  @playing="playing"></youtube>
                  </v-card>
                  <template v-if = "videoInfo != null">
                    <v-slider
                      v-model="timer"
                      :max="videoInfo.duration/1000"
                      @click="seekVideo(parseFloat(timer))"
                      class="align-center"
                    >
                    </v-slider>
                  </template>
                 
                    <v-card>
                  

                    <v-tabs>
                      <v-tab>
                        <v-icon left>
                          mdi-comment-processing-outline
                        </v-icon>
                        Text
                      </v-tab>
                      <v-tab>
                        <v-icon left>
                          mdi-help
                        </v-icon>
                        Quiz
                      </v-tab>
                      <v-tab>
                        <v-icon left>
                          mdi-text-to-speech
                        </v-icon>
                        Rec
                      </v-tab>
                      <v-tab>
                        <v-icon left>
                          mdi-information-outline
                        </v-icon>
                        Info
                      </v-tab>

                      <v-tab-item>
                        <v-card flat >
                          <v-card-text>
                             <div><h2>  <v-icon>mdi-comment-processing-outline</v-icon> : {{nowText}}</h2></div>
                          </v-card-text>
                          <v-card-actions >
                            <v-row>
                            <v-col cols="10">
                              <h3><v-icon>mdi-google-translate</v-icon> : 
                              {{translated}}
                              </h3>
                            </v-col>
                            <v-col cols="2">
                            <v-btn
                              outlined
                              rounded
                              text
                              color="primary"
                              @click="translate"  
                            >
                              번역
                            </v-btn>
                            </v-col>
                            </v-row>
                          </v-card-actions>
                        </v-card>
                      </v-tab-item>
                      <v-tab-item>
                        <v-card flat>
                          <v-card-text>
                           
                          </v-card-text>
                        </v-card>
                      </v-tab-item>
                      <v-tab-item>
                        <v-card flat>
                          <v-card-text>
                           
                          </v-card-text>
                        </v-card>
                      </v-tab-item>
                      <v-tab-item>
                        <v-card flat>
                          <v-card-text>
                              <template v-if="videoInfo != null">
                    <h4>제목 : {{videoInfo.title}}</h4>
                    <h4>설명 : {{videoInfo.description}}</h4>
                    <h4>업로드일 : {{videoInfo.publishedAt}}</h4>
                    <h4>채널 : {{videoInfo.channelTitle}}</h4>
                    <h4>채널ID : {{videoInfo.channelId}}</h4>
                    <h4>기본언어 : {{videoInfo.defaultLanguage}}</h4>
                    <h4>재생시간 : {{videoInfo.duration/1000}}</h4>
                    
                  </template>
                          </v-card-text>
                        </v-card>
                      </v-tab-item>
                    </v-tabs>
                  </v-card>
                  <!-- <button @click="skipVideo">skip</button>   -->
                  <!-- <div>    -->
                      <!-- <button @click="seekVideo(parseFloat(timer)-parseFloat(unit))">back</button>   -->
                      <!-- <input type="number" v-model="unit"> -->
                      <!-- <button @click="seekVideo(parseFloat(timer)+parseFloat(unit))">forward</button>   -->
                  <!-- </div> -->
                  <!-- <div>timer: {{timer}}</div> -->
                  <!-- <div>state: {{state}}</div> -->
                  <!-- <div>mode: {{mode}}</div> -->
                  <!-- <button @click="beforeCaption">이전문장</button>   -->
                  <!-- <button @click="nextCaption">다음문장</button>   -->
                 <!-- <select v-model="selectedLang" @change="onSelectClick($event)" >
                    <option disabled value="">Please select one</option>
                    <option  v-for="(item,index) in items"  v-bind:key="index" >{{item._attributes.lang_code}}</option>
                  </select> -->
                  
                  <!-- <span>선택단어: {{ word }}</span> -->
               

               <vue-record-audio mode="press" @result="onResult" />

                 </v-col>

                  <v-col  cols="4" >
                    <v-card outlined height="600px"  class="scroll">
                      <ul id="example-2">
                        <li v-for="(item,index) in caption"  :data-start = "parseFloat(item._attributes.start)" :data-end = "(parseFloat(item._attributes.start) + parseFloat(item._attributes.dur)).toFixed(3) " class="script" @click= "captionClick(index)"  v-bind:key="index" v-html="item._text">
                          <!-- {{item._text}} -->
                        </li>
                      </ul>
                    </v-card>
                  </v-col>
          </v-row>
        </div>
        <v-row justify="center">
            <v-dialog
                  v-model="dialog"
              scrollable
              max-width="300px"
            >
            <template v-slot:activator="{ on, attrs }">
              <v-btn  dark large color="primary" fixed right bottom v-bind="attrs"  v-on="on" >
                  <!-- <v-icon dark>{{ selectedLang }}</v-icon> -->
                  언어변경
              </v-btn>
            </template>
             <v-card>
              <v-card-title>Select Language</v-card-title>
              <v-divider></v-divider>
              <v-card-text style="height: 300px;">
                <v-radio-group
                  v-model="selectedLang"
                  column
                >
                    <!-- <option  v-for="(item,index) in items"  v-bind:key="index" >{{item._attributes.lang_code}}</option> -->
                  <template v-if="Array.isArray(this.items)">
                    <template  v-for="(item,index) in items" >
                    <v-radio 
                      v-bind:label="item._attributes.lang_translated"
                      v-bind:value="item._attributes.lang_code"
                      v-bind:key="index"
                    ></v-radio>
                    </template>
                  </template>
                  <template v-else-if="items != null">
                     <v-radio 
                    v-bind:label="items._attributes.lang_translated"
                    v-bind:value="items._attributes.lang_code"
                  ></v-radio>
                  </template>

                
                </v-radio-group>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="dialog = false"
                >
                close
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>


        
        <div id="controller" style="bottom:270px">
          <v-btn class="ctrBtn" fab dark small color="primary"  @click="changeMode(1)">
              <v-icon dark>mdi-trending-neutral</v-icon>
          </v-btn>
        </div>  
        <div id="controller" style="bottom:220px">
          <v-btn class="ctrBtn" fab dark small color="primary"  @click="changeMode(2)">
              <v-icon dark> mdi-keyboard-tab</v-icon>
          </v-btn>
        </div>  
        <div id="controller" style="bottom:170px">
          <v-btn class="ctrBtn" fab dark small color="primary"  @click="changeMode(3)">
              <v-icon dark>mdi-refresh</v-icon>
          </v-btn>
        </div>  
        <div id="controller" style="bottom:110px">
          <v-btn class="ctrBtn" fab dark small color="primary"  @click="playVideo">
              <v-icon dark>mdi-play</v-icon>
          </v-btn>
           <v-btn class="ctrBtn" fab dark small color="primary"   @click="pauseVideo">
              <v-icon dark>mdi-pause</v-icon>
          </v-btn>
        </div>
        <div id="controller" style="bottom:60px">
          <v-btn class="ctrBtn" fab dark small color="primary"  @click="seekVideo(parseFloat(timer)-parseFloat(unit))">
              <v-icon dark>mdi-chevron-left</v-icon>
          </v-btn>
            <!-- <vue-numeric-input size="60px" :step="10"  v-model="unit" autofocus controls-type="updown"></vue-numeric-input> -->
           <v-btn class="ctrBtn" fab dark small color="primary"   @click="seekVideo(parseFloat(timer)+parseFloat(unit))">
              <v-icon dark> mdi-chevron-right</v-icon>
          </v-btn>
        </div>
            <div id="controller" style="bottom:10px">
          <v-btn class="ctrBtn" fab dark small color="primary"  @click="beforeCaption">
              <v-icon dark>mdi-chevron-double-left</v-icon>
          </v-btn>
           <v-btn class="ctrBtn" fab dark small color="primary"   @click="nextCaption">
              <v-icon dark>mdi-chevron-double-right</v-icon>
          </v-btn>
        </div>
    </div>


    <span id="tool">
      <v-dialog v-model="dialog2"  width="30%"  hide-overlay    transition="dialog-bottom-transition">
                    <template v-slot:activator="{ on, attrs }">
                    <v-btn color="primary" dark  v-bind="attrs" v-on="on">사전검색</v-btn>
                    </template>
                      <v-card >
                      <iframe  width="100%" height= "500px" :src="dictUrl+word"></iframe>
                      <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                        color="primary"
                        text
                        @click="dialog2 = false"
                      >
                        close
                      </v-btn>
                    </v-card-actions>
                      </v-card>
                </v-dialog>
      <v-btn>단어장추가</v-btn>
    </span>
  </v-container>
</template>

<script>
import Navbar from "@/components/Navbar";
import axios from "axios";
var convert = require('xml-js')

export default {
  name: "Video",
  components: {
    Navbar,
  },
  data() {
    return {
      dictUrl : "https://m.dic.daum.net/search.do?q=",
      word : "",
      dialog: false,
      dialog2: false,
      videoInfo :  null,
      isBlank : true,
      mode : 1,
      unit : 10,
      nowText : "",
      translated : "",
      nowIdx : -1,
      preIdx : -1,
      elements : null,
      videoId: "",
      selectedLang : "",
      state : 0,
      playerVars: {
          autoplay: 1,
          cc_load_policy: 0,
          // cc_lang_pref: 'en',
          controls:0,
          modestbranding:1,
          fs:0,
          enablejsapi :1,
          disablekb:0},
      items : null,
      caption : null,
      timer : 0
    };
  },
  methods: {
      onResult (data) {
      console.log('The blob data:', data);
      console.log('Downloadable audio', window.URL.createObjectURL(data));
    },
    translate(){
      if(this.nowText != ""){
        // console.log(this.nowText);
        var temp2 = this.nowText.replace(/(\r\n|\n|\r)/gm,"");
        // console.log(temp2);
          var temp = this.selectedLang.substr(0, 2);
          if(temp == 'ko'){
            temp ='kr'
          }else if(temp == 'ja'){
            temp = 'jp'
          }else if(temp == 'zh'){
            temp = 'cn'
          }
          axios.get("https://morelang.gq/api/translate",{
              params: {
                query : temp2,
                src_lang : temp,
                target_lang : 'kr'
              }
            })
            .then((res) => {
              if (res.data == ""){
                this.translated = "현재 언어는 번역이 지원되지 않습니다.";
              }else{
                // console.log("res = ",res);
              this.translated = res.data;
              }
            });

      }
      
    },
    removeAll(){
             Array.from(this.elements).some((el) =>{
               if(el.classList.contains("current")){
                 el.classList.remove("current");
                 return true;
               }
              });
    },
    captionClick(idx){
      this.removeAll();
      this.preIdx = this.nowIdx;
      this.nowIdx=idx;
      if(this.nowIdx != -1) this.elements[this.nowIdx].classList.add("current");
      this.seekVideo(this.caption[this.nowIdx]._attributes.start);
    },
    changeMode(num){ 
      this.mode = num;
    },
    nextCaption(){
        if(this.nowIdx+1<this.caption.length){
          this.removeAll();
          this.preIdx = this.nowIdx;
          this.nowIdx ++; 
          this.nowText=this.elements[this.nowIdx].innerHTML;
           this.seekVideo(this.caption[this.nowIdx]._attributes.start);
          this.elements[this.nowIdx].classList.add("current");
        }
    },
    beforeCaption(){
        if(this.nowIdx>0){
        this.removeAll();
        this.preIdx = this.nowIdx;
        this.nowIdx--;
        this.nowText=this.elements[this.nowIdx].innerHTML;
         this.seekVideo(this.caption[this.nowIdx]._attributes.start);
        this.elements[this.nowIdx].classList.add("current");
        }
    },
    async playMode(){
         if(this.state==1){
            var tempIdx= -1;
            if(this.elements != null){
              Array.from(this.elements).some((el,i) =>{
                if (el.dataset.start < this.timer &&this.timer < el.dataset.end){
                  tempIdx = i;
                  return true;
                } 
              });
              if(tempIdx != -1){                              ///말하는중
                  if(this.mode == 2 && tempIdx != this.nowIdx){     //현재 대사 반복 재생  == 현재 인덱스 반복 재생   //새로운 대사 시작?  => 기존의 인덱스 시작으로 넘어가야함(이때 인덱스 -1 은 예외 처리)
                      if(this.nowIdx != -1){
                        this.seekVideo(this.caption[this.nowIdx]._attributes.start);
                        this.nowText=this.elements[this.nowIdx].innerHTML;
                      }else{
                        this.nowIdx=tempIdx;
                        this.elements[this.nowIdx].classList.add("current");  
                        this.nowText=this.elements[this.nowIdx].innerHTML;
                      }
                  }
                  else if(this.mode == 3){                          //현재 문장 끝나면 일시 정지 시키기 
                    if(tempIdx != this.nowIdx){

                      if(this.nowIdx != -1 &&  this.isBlank ==false){
                            this.pauseVideo();
                            await this.sleep(100);
                            this.preIdx  = this.nowIdx;
                            this.nowIdx=tempIdx;
                      }
                      else{
                        this.nowIdx=tempIdx;
                      }
                    }else{                                                              //새 대사?
                        this.removeAll();
                        if(this.nowIdx != -1)this.elements[this.nowIdx].classList.add("current");  
                        this.nowText=this.elements[this.nowIdx].innerHTML;
                    }
                 
                  }else if(this.mode == 1){
                    if(this.nowIdx != -1){
                      this.removeAll();
                      this.preIdx = this.nowIdx;
                    }
                    this.nowIdx = tempIdx;
                    this.elements[this.nowIdx].classList.add("current");
                    this.nowText=this.elements[this.nowIdx].innerHTML;
                  }

                  this.isBlank = false;
              }
              
              else if(this.isBlank == false){                                           //정적이 흐르는중
                this.isBlank = true;
                if(this.mode == 2){
                    this.seekVideo(this.caption[this.nowIdx]._attributes.start);
                }
                else if(this.mode ==3){
                    this.pauseVideo();
                }else if(this.mode ==1) {
                  this.nowText="";
                  this.preIdx = this.nowIdx;
                  this.removeAll();
                }
              }else if(this.mode ==3){
                this.nowText="";
                this.removeAll();
                this.preIdx = this.nowIdx;
              }
            
            }
            
         }
      },
      
      async getCurrentTime(){
         if(this.state==1){
          await this.player.getCurrentTime().then(data => this.timer=data);
         }
      },
    
    async playVideo() {
      await this.player.playVideo();
    },
    async skipVideo() {
      await this.player.loadVideoById({videoId:'lG0Ys-2d4MA',
                      startSeconds:1000,
                      endSeconds:1005,
                      suggestedQuality:'default'});
    },
    async pauseVideo() {
      await this.player.pauseVideo();
    },
    async seekVideo(t) {
      await this.player.seekTo(t, true);
      await this.playVideo();
    },

    async setCCLanguage(){
      await this.player.setOption( "captions" , 'track' , { 'languageCode' : this.selectedLang } );
    },
   async  youtubeStateChange (event) {
        var myTimer;
        var setMode;
        // console.log('event:', event);
        // console.log('state data : ',event.data);
        this.state = event.data;
        if(event.data==1) { // playing
            myTimer = setInterval(this.getCurrentTime, 100);
            setMode = setInterval(this.playMode,1000);
          }
        else { // not playing
            clearInterval(myTimer);
            clearInterval(setMode);
        }
    },
    async getCaptionsList(){
      // console.log(await this.player.getOption( "captions" , 'track'));
      axios.get("https://video.google.com/timedtext?type=list",{
        params: {
          v : this.videoId
        }
      })
      .then((res) => {
        var xml = res.data
        var json = convert.xml2json(xml, { compact: true })
        // console.log("json = ",json)
        this.items = JSON.parse(json).transcript_list.track;
        // console.log(this.items[0]._attributes.lang_code);
        // console.log("items = ",this.items.length)
        // console.log("type = ",typeof this.items)
        // console.log(this.items);
        // console.log(this.items[0]);
        // console.log("isarray=",Array.isArray(this.items))
        if(Array.isArray(this.items)){
          this.selectedLang=this.items[0]._attributes.lang_code;
        }else{
           this.selectedLang=this.items._attributes.lang_code;
        }
        

        
        });
    },
    async getCaption(){
      await axios.get("https://video.google.com/timedtext",{
        params:{
          v : this.videoId,
          lang : this.selectedLang
        }
      })
      .then((res) => {
        var xml = res.data
        var json = convert.xml2json(xml, { compact: true })
        this.caption = JSON.parse(json).transcript.text;
        // console.log(this.caption);
        });
        
        this.elements = document.querySelectorAll('.script');
        
    },
    async  youtubApiChange (youtubeState) {
      
      console.log("stateChange",youtubeState);
         await this.player.setOption( "captions" , 'track',[]);
    },
    onSelectClick(event){
        this.selectedLang = event.target.value;
        this.getCaption();
    },
    
    playing() {
      console.log(" start play");
    },
    async sayHi() {
      console.log("Hi");
    },
    sleep(t){
      return new Promise(resolve=>setTimeout(resolve,t));
    },
        // async getOption() {                                     //현재 재생되고 있는 영상 자막이있는지 없는지 판별 가능 
    //    var promise =this.player.getOptions();
    //    promise
    //           .then(function(data){
    //             console.dir(data[0]);
    //             // module = data[0];
    //             })
    //           .catch(err => console.log(err));
    // }, 
    
    // async getTracklist(){
        //     this.items = await this.player.getOption( "captions" , 'tracklist');
        //     console.log(this.items);
        // },
        

        // async getTrack(){
        
        // var temp = await this.player.getOption( "captions" , 'track');
        //  console.log(temp.languageCode);
        // },
 
  },

  watch : {
    selectedLang : function(){
      // console.log("바뀜!!")
      this.getCaption();
    },
    nowText : function(){
      this.translated ="";
    }

  },
  computed: {
    player() {
      return this.$refs.youtube.player;
    }
  },
  created(){
    console.log(this.videoId);
    this.videoId=this.$route.params.vid
      axios.get("https://morelang.gq/api/video",{
        params: {
          id : this.videoId
        }
      })
      .then((res) => {
        this.videoInfo = res.data;
        console.log(this.videoInfo);
        
        });
  },
  mounted(){
    // console.log("mounted!!");
    this.player.addEventListener('onStateChange', this.youtubeStateChange)
    this.player.addEventListener('onApiChange', this.youtubApiChange)
    document.addEventListener('mouseup', event => {
        // console.log(event);
        this.word = window.getSelection().toString();
        var temp =document.getElementById("tool")
        // console.log(word != "");
        if(this.word != "" && temp.style.display=="none"){
          console.log(this.word);
          temp.style.display = "block ";
          temp.style.left=event.pageX+"px"
          temp.style.top=event.pageY+"px"
        }else if(this.word == ""){
          temp.style.display = "none";
        }
    });
    //  document.addEventListener('mousedown', function() {
        // console.log(event);
        // window.getSelection().empty();
        // var word = window.getSelection().toString();
        // var temp =document.getElementById("tool")
        // console.log(word != "");
        // if(word != ""){
          // console.log(word);
          // temp.style.display = "block ";
          // temp.style.left=event.pageX+"px"
          // temp.style.top=event.pageY+"px"
        // }else{
          // temp.style.display = "none";
        // }
    // });
  }
};

  //  function selectText() {
  //               var selectionText = "";
  //               if (document.getSelection) {
  //                   selectionText = document.getSelection();
  //               } else if (document.selection) {
  //                   selectionText = document.selection.createRange().text;
  //               }
  //               return selectionText;
  //           }
           
  //           document.onmouseup = function() {
  //               document.getElementById("console").innerHTML = selectText();
  //           }

</script>
<style scoped>
.script:hover { background: orange }
.script.current { background: skyblue }
.scroll {
   overflow-y: scroll
}
#controller {
  position: fixed;
  left: 10px;
}
.ctrBtn {
  margin: 10px;
}


#tool{
  /* background: orange; */
  display: none;
  position: absolute;
  /* top:  512px; left: 178px; */
}


</style>