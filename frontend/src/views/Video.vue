<template>
  <div class="video">
    <Navbar />
    <h3 id="page">비디오페이지</h3>
    <youtube id="ytp" :video-id="videoId" ref="youtube" :player-vars="playerVars" @ready="getCaptionsList"  @paused="sayHi"  @playing="playing"></youtube>
    <button @click="playVideo">play</button>  
    <button @click="skipVideo">skip</button>  
    <button @click="pauseVideo">pause</button>  
    <button @click="seekVideo">seek</button>  
    <!-- <button @click="getCurrentTime">getCurrentTime</button>   -->
    <!-- <button @click="getCaptionsList">getCaptionsList</button>   -->
    <!-- <button @click="getCaption">getCaption</button>   -->

<!-- <button @click="getJquery">getJquery</button>   -->
    
    <!-- <button @click="getOption">getOption</button>   -->
        <!-- <button @click="setCCLanguage">setCCLanguage</button>   -->
                <!-- <button @click="getTracklist">getTracklist</button>   -->
                <!-- <button @click="getTrack">getTrack</button>   -->
    <!-- <div>nowLnag: {{nowLang}}</div> -->
    <div>timer: {{timer}}</div>
    
    <!-- <button @click="nowLang">nowLang</button>   -->
    <!-- <ul id="example-1">
      <li v-for="item in items"  v-bind:key="item._attributes.id">
        {{item._attributes.lang_code}} - {{item._attributes.lang_translated}}
        
      </li>
    </ul> -->


    <select v-model="selectedLang" @change="onSelectClick($event)" >
      <option disabled value="">Please select one</option>
      <option  v-for="(item,index) in items"  v-bind:key="index" >{{item._attributes.lang_code}}</option>
    </select>
    <span>선택함: {{ selectedLang }}</span>

    <ul id="example-2">
      <li v-for="(item,index) in caption"  v-bind:key="index">
        {{item}}
      </li>
    </ul>
  </div>
</template>

<script>
// import $ from 'jquery';
import Navbar from "@/components/Navbar";
import axios from "axios";
var convert = require('xml-js')

// @ is an alias to /src
// import $ from 'jquery';
export default {
  name: "Video",
  components: {
    Navbar,
  },
  data() {
    return {
      videoId: "DFjIi2hxxf0",
      selectedLang : null,
      playerVars: {
          autoplay: 1,
          // cc_load_policy: 0,
          // cc_lang_pref: 'en',
          controls:1,
          modestbranding:1,
          fs:0,
          enablejsapi :1,
          disablekb:1},
      items : null,
      caption : null,
      timer : ""
    };
  },
  methods: {
    onSelectClick(event){
        // console.log(event.target.value);
        this.selectedLang = event.target.value;
        this.getCaption();
        this.setCCLanguage();
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
    async seekVideo() {
      await this.player.seekTo(144.38, true);
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
    
    // nowLang(){
     
    //    console.dir(this.$refs.youtube);
    // },
    
    playing() {
      console.log(" we are watching!!!");
    },
    async setCCLanguage(){
      console.log("cc호출");
      await this.player.setOption( "captions" , 'track' , { 'languageCode' : this.selectedLang } );
    },
    // async getTracklist(){
    // //  console.log(await this.player.getOption( "captions" , 'tracklist'));
    //     this.items = await this.player.getOption( "captions" , 'tracklist');
    //     console.log(this.items);
    // },
    async getTrack(){
      // console.log(await this.player.getOptions( "captions" ));
      // await this.player.setOption( "captions" , 'track' , { 'languageCode' : 'ko' } );
    //  console.log(await this.player.getOptions()); 
    
    //  console.log(await this.player.getOptions( "captions"));
    //  console.log(await this.player.setOption( "captions" , 'reload'));
    var temp = await this.player.getOption( "captions" , 'track');
     console.log(temp.languageCode);
    //  console.log( this.player.getCurrentTime());
    },
   async  youtubeStateChange (event) {
        var myTimer;
        // console.log('event:', event);
        console.log('state data : ',event.data);
        if(event.data==1) { // playing
            myTimer = setInterval(this.getCurrentTime, 100);
          }
        else { // not playing
            clearInterval(myTimer);
        }
    },
    async  youtubApiChange (youtubeState) {
      console.log("stateChange",youtubeState);
      // this.getTrack();
            // console.log(this.player);
      var temp = await this.player.getOption( "captions" , 'track');
      // console.log(temp.languageCode);
      this.selectedLang = temp.languageCode;
        // console.log('youtubeState:', youtubeState);
            //  console.log(await this.player.getOptions()); 
    //  console.log(await this.player.getOptions( "captions"));
    //  console.log(await this.player.setOption( "captions" , 'reload'));
    //  console.log(await this.player.getOption( "captions" , 'track'));
    },
    getCurrentTime(){
      // this.timer =this.player.getCurrentTime();
      // console.log(this.player.getCurrentTime());
      this.player.getCurrentTime().then(data => this.timer=data);
            // var x = document.getElementById("ytp").contentWindow.document;
            // var x =$("#ytp");
            // .document.getElementById("player");
            
        // console.log(x);

    },
    async getCaptionsList(){
          // console.log("여기어때");
      // var temp =  this.player.getOption( "captions" , 'track');
      // console.log(this.player);
      // console.log(await this.player.getOption( "captions" , 'track'));
      // await this.getTrack();

      axios.get("http://video.google.com/timedtext?type=list",{
        params: {
          v : this.videoId
        }
      })
      .then((res) => {
        var xml = res.data
        var json = convert.xml2json(xml, { compact: true })
        this.items = JSON.parse(json).transcript_list.track;
        // console.log(this.items);
        });
    },
    getCaption(){
      axios.get("https://video.google.com/timedtext",{
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
    },
    getJquery(){
      
    
        // console.log($('#ytp'));
                    // var x = document.getElementById("ytp");
        // console.log(x);
        console.log(this.player.getIframe());

        
    }



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
  mounted(){
    // console.log("mounted!!");


    // var videoData = this.player;
    // console.log(videoData);
    

    this.player.addEventListener('onStateChange', this.youtubeStateChange)
    this.player.addEventListener('onApiChange', this.youtubApiChange)



  }
};
</script>
