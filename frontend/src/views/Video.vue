<template>
  <div class="video">
    <Navbar />
    
    <button @click="changeMode(1)">Mode1</button>  
    <button @click="changeMode(2)">Mode2</button>  
    <button @click="changeMode(3)">Mode3</button>  
  <input type="button" @click="increment()" value="increment"/>
    <h3 id="page">비디오페이지</h3>
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
    <div><h2>  대사인덱스: {{nowIdx}}</h2></div>
    


    <select v-model="selectedLang" @change="onSelectClick($event)" >
      <option disabled value="">Please select one</option>
      <option  v-for="(item,index) in items"  v-bind:key="index" >{{item._attributes.lang_code}}</option>
    </select>
    <span>선택함: {{ selectedLang }}</span>

    <ul id="example-2">
      <li v-for="(item,index) in caption"  :data-start = "parseFloat(item._attributes.start)" :data-end = "(parseFloat(item._attributes.start) + parseFloat(item._attributes.dur)).toFixed(3) " class="subtitle" @click= "seekVideo(parseFloat(item._attributes.start))"  v-bind:key="index" v-html="item._text">
        <!-- {{item._text}} -->
      </li>
    </ul>
  </div>
</template>

<script>
// import $ from 'jquery';
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
      mode : 1,
      unit : 10,
      nowText : "",
      nowIdx : 0,
      elements : null,
      videoId: "DFjIi2hxxf0",
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
        increment: function () {
      this.$store.commit('increment')
    },
    changeMode(num){
      this.mode = num;
      console.log("mode = ",this.mode);

    },
    nextCaption(){
        console.log("다음문장idx",this.nowIdx+1);
        // console.log(this.caption[this.nowIdx+1]._text);
        // console.log(this.caption[this.nowIdx+1]._attributes.start);
        if(this.nowIdx+1<this.caption.length){
           this.seekVideo(this.caption[this.nowIdx+1]._attributes.start);
        }
        
    },
    beforeCaption(){
        // console.log("이전문장idx",this.nowIdx-1);
        if(this.nowIdx>0){
         this.seekVideo(this.caption[this.nowIdx-1]._attributes.start);
        }
    },
    onSelectClick(event){
        // console.log(event.target.value);
        this.selectedLang = event.target.value;
        this.getCaption();
        // this.setCCLanguage();
    },
    async sayHi() {
      console.log("Hi");
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
    // async getOption() {                                     //현재 재생되고 있는 영상 자막이있는지 없는지 판별 가능 
    //    var promise =this.player.getOptions();
    //    promise
    //           .then(function(data){
    //             console.dir(data[0]);
    //             // module = data[0];
    //             })
    //           .catch(err => console.log(err));


    // },  
    

    playing() {
      console.log(" start play");
    },
    async setCCLanguage(){
      // console.log("cc호출");
      await this.player.setOption( "captions" , 'track' , { 'languageCode' : this.selectedLang } );
    },
    // async getTracklist(){
    //     this.items = await this.player.getOption( "captions" , 'tracklist');
    //     console.log(this.items);
    // },


    // async getTrack(){
    
    // var temp = await this.player.getOption( "captions" , 'track');
    //  console.log(temp.languageCode);
    // },
   async  youtubeStateChange (event) {
    
        var myTimer;
        // console.log('event:', event);
        console.log('state data : ',event.data);
        this.state = event.data;
        if(event.data==1) { // playing
            myTimer = setInterval(this.getCurrentTime, 100);
          }
        else { // not playing
            console.log("여기들어옴");
            clearInterval(myTimer);
        }
    },
    async  youtubApiChange (youtubeState) {
      
      console.log("stateChange",youtubeState);
      // var temp = await this.player.getOption( "captions" , 'track');
      // this.selectedLang = temp.languageCode;
            //  console.log(await this.player.getOptions()); 
    //  console.log(await this.player.setOption( "captions" , 'reload'));
         await this.player.setOption( "captions" , 'track',[]);
    },

    async getCurrentTime(){
       if(this.state==1){
        // this.timer =this.player.getCurrentTime();
        await this.player.getCurrentTime().then(data => this.timer=data);
          
          // var tf = false;
          var tempIdx= -1;
          if(this.elements != null){
            // console.log(Array.from(this.elements));
            Array.from(this.elements).some((el,i) =>{
              if (el.dataset.start <= this.timer &&this.timer < el.dataset.end){
                el.classList.add("current");
                // console.log(this.elements[this.nowIdx].className);
                // this.elements[this.nowIdx].classList.remove("current");
                

                this.nowText = el.innerHTML;
                // this.nowIdx = i;
                tempIdx = i;
                // tf = true;
                return true;
              } 
              // else el.classList.remove("current");
             
            });
            if(tempIdx != -1){
              // console.log("-1아니네?")
              if(tempIdx != this.nowIdx){
                if(this.mode == 2){
                  this.seekVideo(this.caption[this.nowIdx]._attributes.start);
                  
                }else{
                  
                  this.elements[this.nowIdx].classList.remove("current");
                  this.nowIdx = tempIdx;
                  this.elements[this.nowIdx].classList.add("current");
                }
              }
            }else{
              console.log("아무말도안하나");
              this.nowText="";
              this.elements[this.nowIdx].classList.remove("current");
              // this.seekVideo(this.caption[this.nowIdx]._attributes.start);
            }
          
          }
          // if(tf == false)this.nowText="";
          // console.log(this.caption[this.nowIdx]._text);
          if(this.elements != null){
            // console.log(this.elements);
            console.log(this.elements[this.nowIdx].innerHTML);
          }
       }
    },
    async getCaptionsList(){
      // console.log(await this.player.getOption( "captions" , 'track'));
      axios.get("http://video.google.com/timedtext?type=list",{
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
        
        this.elements = document.querySelectorAll('.subtitle');
    },

  },

  watch : {
    selectedLang : function(){
      // console.log("바뀜!!")
      this.getCaption();
    },
    nowIdx : function(){
      console.log("문장바뀜");
    }

  },
  computed: {
    player() {
      return this.$refs.youtube.player;
    }
  },
  mounted(){
    // console.log("mounted!!");
    this.player.addEventListener('onStateChange', this.youtubeStateChange)
    this.player.addEventListener('onApiChange', this.youtubApiChange)
  }
};
</script>
<style>
.subtitle:hover { background: orange }
.subtitle.current { background: skyblue }
</style>