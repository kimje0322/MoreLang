<template>
  <div class="video">
        <Navbar />
        <div style="width: 100%; height: 100vh; display: block; margin-top: 10%;">
        <button @click="changeMode(1)">Mode1</button>  
        <button @click="changeMode(2)">Mode2</button>  
        <button @click="changeMode(3)">Mode3</button>  
        <h3 id="page">비디오페이지</h3>
        <div>{{videoInfo}}</div>
        <!-- <h4>제목 : {{videoInfo.title}}</h4>
        <h4>설명 : {{videoInfo.description}}</h4>
        <h4>업로드일 : {{videoInfo.publishedAt}}</h4>
        <h4>채널 : {{videoInfo.channelTitle}}</h4>
        <h4>기본언어 : {{videoInfo.defaultLanguage}}</h4> -->
        
        
        
        
        <youtube id="ytp" :video-id="videoId" ref="youtube" :nocookie="true"  :player-vars="playerVars" @ready="getCaptionsList"  @paused="sayHi"  @playing="playing"></youtube>
        <button @click="playVideo">play</button>  
        <!-- <button @click="skipVideo">skip</button>   -->
        <button @click="pauseVideo">pause</button>  
        <div>   
            <button @click="seekVideo(parseFloat(timer)-parseFloat(unit))">back</button>  
            <input type="number" v-model="unit">
            <button @click="seekVideo(parseFloat(timer)+parseFloat(unit))">forward</button>  
        </div>
        <div>timer: {{timer}}</div>
        <div>state: {{state}}</div>
        <div>mode: {{mode}}</div>
        <button @click="beforeCaption">이전문장</button>  
        <button @click="nextCaption">다음문장</button>  
        <div><h2>  대사: {{nowText}}</h2></div>
        <div><h3>  대사인덱스: {{nowIdx}}</h3></div>
        <div><h3>  이전인덱스: {{preIdx}}</h3></div>
        


        <select v-model="selectedLang" @change="onSelectClick($event)" >
          <option disabled value="">Please select one</option>
          <option  v-for="(item,index) in items"  v-bind:key="index" >{{item._attributes.lang_code}}</option>
        </select>
        <span>선택함: {{ selectedLang }}</span>

        <ul id="example-2">
          <li v-for="(item,index) in caption"  :data-start = "parseFloat(item._attributes.start)" :data-end = "(parseFloat(item._attributes.start) + parseFloat(item._attributes.dur)).toFixed(3) " class="script" @click= "captionClick(index)"  v-bind:key="index" v-html="item._text">
            <!-- {{item._text}} -->
          </li>
        </ul>
      </div>
  </div>
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
      videoInfo :  null,
      isBlank : true,
      mode : 1,
      unit : 10,
      nowText : "",
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
          this.elements[this.nowIdx].classList.add("current");
           this.seekVideo(this.caption[this.nowIdx]._attributes.start);
        }
    },
    beforeCaption(){
        if(this.nowIdx>0){
        this.removeAll();
        this.preIdx = this.nowIdx;
        this.nowIdx--;
        this.nowText=this.elements[this.nowIdx].innerHTML;
        this.elements[this.nowIdx].classList.add("current");
         this.seekVideo(this.caption[this.nowIdx]._attributes.start);
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
                 
                  }else if(this.mode == 1 && this.nowIdx != tempIdx){
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
        this.items = JSON.parse(json).transcript_list.track;
        // console.log(this.items[0]._attributes.lang_code);
        this.selectedLang=this.items[0]._attributes.lang_code;

        
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
  }
};
</script>
<style>
.script:hover { background: orange }
.script.current { background: skyblue }
</style>