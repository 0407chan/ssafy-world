<template>
  <v-container justify-center fill-height >
    <v-flex sm6 >
      <v-flex xs12 text-xs-center mx-5>
        <v-row justify="space-between">
          <v-col>
            <v-flex class="loginInputs">로그인</v-flex>
          </v-col>
          <v-col>
            <!-- <v-flex>비밀번호를 잊어버리셨나요?</v-flex> -->
          </v-col>
        </v-row>
      </v-flex>
      <v-flex xs12 text-xs-center mx-5>
        <v-flex xs12 text-xs-center>
          <v-text-field v-model="user.id"
                        label="username*"
                        solo
                        hide-details
                        @keyup.enter="loginAction"
                        style="margin: 2% 0; opacity: 0.7">
            <template v-slot:append>
              <v-fade-transition>
              <template v-if="idLen >= 4">
                <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
              </template>
              <template v-else-if="idLen > 0 && idLen <4">
                <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
              </template>
              </v-fade-transition>
            </template>
          </v-text-field>

          <v-text-field v-model="user.pw"
            label="Password*"
            type="password"
            solo
            hide-details
            @keyup.enter="loginAction"
            style="margin: 2% 0; opacity: 0.7">
            <template v-slot:append>
              <v-fade-transition>
                <template v-if="passwordLen >= 4">
                  <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
                </template>
                <template v-else-if="passwordLen > 0 && passwordLen <4">
                  <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
                </template>
              </v-fade-transition>
            </template>
          </v-text-field>
        </v-flex>
      </v-flex>

      <v-flex text-sm-center ma-5>
        <!-- <v-btn rounded block :color="index" @click="login"><span class="btnText">Log in</span></v-btn> -->
        <v-btn v-if="idLen >= 4 && passwordLen >= 4" rounded block :color="colorIndex" @click="loginAction"><span class="btnText">Log in</span></v-btn>
        <v-btn v-else rounded disabled block dark>Log in</v-btn>
      </v-flex>
      <v-flex text-sm-center ma-5>
        <v-divider style="background:gray"></v-divider>
      </v-flex>

      <v-flex xs12 text-xs-center mx-5>
        <v-flex my-5 class="loginInputs">아이디가 없으신가요?</v-flex>
        <v-btn rounded block color="primary" :to="{name:'register'}">회원가입</v-btn>
      </v-flex>
    </v-flex>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import Swal from 'sweetalert2'

export default {
  data: () => ({
    user: {
      id:'',
      pw:'',
    },
    colorIndex:"orange"
  }),
  computed:{
    idLen() {
      return this.user.id.length;
    },
    passwordLen() {
      return this.user.pw.length;
    },
  },
  methods: {
    ...mapActions("data", ['login']),

    /** 2019.10.21 이찬호
    * 기능 : 로그인 액션
    * 로그인 성공 시 페이지 변경
    * 로그인 실패 시 오류 메세지 출력
    */
    async loginAction() {
      let params = {
        id: this.user.id,
        pw: this.user.pw,
      }
      let res = await this.login(params)
      console.log("loginAction",res)
      if (res.status == 200) {
        this.successAlert("환영합니다!", "최고의 채팅을 이용해보세요.");
        this.$session.start();
        this.$session.set('token', res.data);
        this.$router.push({ name: 'mainpage' }).catch(err => {})
      }
      else {
        this.errorAlert(res.data);
      }
    },

    /** 2019.10.21 이찬호
    * 기능 : 성공메세지 창, 1초후에 사라진다.
    * 변수 : 성공메세지로 표시할 내용
    */
    successAlert(title,text){
      Swal.fire({
        title: title,
        text: text,
        type: 'success',
        showConfirmButton: false,
        timer: 1000
      })
    },

    /* 2019.10.21 이찬호
    기능 : 에러메세지 창, 1초후에 사라진다.
    변수 : 에러메세지로 표시할 내용
    */
    errorAlert(title,text){
      Swal.fire({
        title: title,
        text: text,
        type: 'error',
        showConfirmButton: false,
        timer: 1000
      })
    },
  }
};
</script>
<style>
.loginInputs{
  color:white
}
</style>
