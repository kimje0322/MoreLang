<template>
   <v-container>
    <v-row no-gutters class="mt-10">
      <v-col cols="12" sm="5">
        <v-card
          tile
          class="pa-9"
          height="650px"
          align="center"
        >
          <v-card-title class="pa-0 mb-7 justify-space-between">
            <h1 class="ma-0" style="font-family: 'Do Hyeon', sans-serif;">
              로그인
            </h1>
            <v-btn fab dark>
              <v-icon>mdi-home-outline</v-icon>
            </v-btn>
          </v-card-title>
          <v-form ref="form">
            <v-text-field
              class="my-3"
              placeholder="hello@ssalog.com"
              label="이메일"
              required
            ></v-text-field>
            <v-text-field
              type="password"
              label="비밀번호"
              required
            ></v-text-field>
            <!-- <v-card-text style="height: 16px;"> -->
              <!-- <small class="red--text">{{ errorMsg }}</small> -->
              <!-- <v-progress-circular
                indeterminate
              ></v-progress-circular> -->
            <!-- </v-card-text> -->
            <v-card-actions class="px-0 mt-4">
              <v-btn
                block
                tile
                >로그인</v-btn
              >
            </v-card-actions>

            <v-card-actions class="px-0 my-3">
              <v-btn
                block
                tile
                >회원 가입</v-btn
              >
            </v-card-actions>
            <small
              style="cursor:pointer"
              >비밀번호찾기</small
            >
            <div>
              <hr style="width:41%; display: inline-block;" class="mb-1 mr-3">
              <p style="display: inline-block;">OR</p>
              <hr style="width:41%; display: inline-block;" class="mb-1 ml-3">
              </div>
            <!-- 소셜 로그인 -->
              <v-row justify="center">
                <v-container style="text-align:center;">
                  <v-row no-gutters>
                    <v-col
                      class="mb-3"
                      cols="12"
                      sm="12"
                    >
                      <v-card
                        class="pa-2"
                        outlined
                        tile
                      >
                        <img style="width:26px; float:left" src="@/assets/img/google.png" alt="">
                        <p class="my-auto" style="display:inline-block; ">Sign up with Google</p>
                      </v-card>
                    </v-col>
                    <v-col
                      class="mb-3"
                      cols="12"
                      sm="12"
                    >
                      <v-card
                        class="pa-2"
                        outlined
                        tile
                      >
                        <img style="width:26px; float:left" src="@/assets/img/naver.png" alt="">
                        <p class="my-auto">Sign up with naver</p>
                      </v-card>
                    </v-col>
                    <v-col
                      class="mb-3"
                      cols="12"
                      sm="12"
                    >
                      <v-card
                        class="pa-2"
                        outlined
                        tile
                      >
                        <img style="width:26px; float:left" src="@/assets/img/kakao.png" alt="">
                        <p class="my-auto">Sign up with kakao</p>
                      </v-card>
                    </v-col>
                  </v-row>
                </v-container>
              </v-row>


          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  
}
</script>

<style scoped>
  .loginPage {
    text-align: center!important;
  }
</style>