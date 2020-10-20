<template>
  <div class="video">
    <h3>비디오페이지</h3>
    <youtube :video-id="videoId" ref="youtube" @playing="playing"></youtube>
    <button @click="playVideo">play</button>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Video",
  data() {
    return {
      videoId: "lG0Ys-2d4MA"
    };
  },
  methods: {
    playVideo() {
      this.player.playVideo();
    },
    playing() {
      console.log(" we are watching!!!");
    }
  },
  computed: {
    player() {
      return this.$refs.youtube.player;
    }
  }
};
</script>
