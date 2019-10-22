<template>
  <v-container justify-center fill-height >
    <v-flex sm6 >
      <v-flex xs12 text-xs-center mx-5>
        <v-row justify="space-between">
          <v-col>
            <v-flex style="color:white">로그인</v-flex>
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
                        @keyup.enter="login"
                        style="margin: 2% 0; opacity: 0.7">
            <template v-slot:append>
              <v-fade-transition>
              <template v-if="user.id.length >= 4">
                <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
              </template>
              <template v-else-if="user.id.length > 0 && user.id.length <4">
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
            @keyup.enter="login"
            style="margin: 2% 0; opacity: 0.7">
            <template v-slot:append>
              <v-fade-transition>
                <template v-if="user.pw.length >= 4">
                  <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
                </template>
                <template v-else-if="user.pw.length > 0 && user.pw.length <4">
                  <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
                </template>
              </v-fade-transition>
            </template>
          </v-text-field>
        </v-flex>
      </v-flex>

      <v-flex text-sm-center ma-5>
        <!-- <v-btn rounded block :color="index" @click="login"><span class="btnText">Log in</span></v-btn> -->
        <v-btn v-if="usernameLen > 0 && passwordLen > 0" rounded block :color="index" @click="loginAction"><span class="btnText">Log in</span></v-btn>
        <v-btn v-else rounded dark disabled block>Log in</v-btn>
      </v-flex>
      <v-flex text-sm-center ma-5>
        <v-divider style="background:gray"></v-divider>
      </v-flex>

      <v-flex xs12 text-xs-center mx-5>
        <v-flex my-5 style="color:white">아이디가 없으신가요?</v-flex>
        <v-btn rounded block color="primary" :to="{name:'register-page'}">회원가입</v-btn>
      </v-flex>
    </v-flex>
  </v-container>
</template>

<script lang="ts">
import axios from 'axios'
import Swal from 'sweetalert2'
import api from '../api'
import Vue from 'vue';
import { mapState, mapActions } from "vuex";
export default Vue.extend({

  data(){
    return {
      closeCheck:false,
      dialog: false,
      userCheck: false,
      user: {
        id:String,
        pw:String,
      },
      validLogin: true,
      validRegister: true,
      loading: false,
  
      index: '',
    }
  },

  mounted() {

  },
  computed:{
    usernameLen():number {
      return this.user.id.length;
    },
    passwordLen():number{
      return this.user.pw.length;
    },

  },
  methods: {
    ...mapActions(['login']),

    /** 2019.10.18 이찬호
    * 기능 : back으로 입력한 id,pw를 전달
    */
    async loginAction(){
      
      let params={
        id: this.user.id,
        pw: this.user.pw,
      };
      console.log('startPage 가즈아',params);
      await this.login(6);
    },


    // async login() {
    //   if (this.credentials.username && this.credentials.password) {
    //     axios.post('http://localhost:8000/auth/', this.credentials).then(async res => {
    //       this.$session.start();
    //       this.$session.set('token', res.data.token);
    //       this.userCheck = true;
    //       this.getUser();
    //       this.getUserInfo();
    //       await this.postMatrix()
    //       this.$router.push({name:'main'});
    //       const Toast = Swal.mixin({
    //           toast: true,
    //           position: 'bottom-end',
    //           showConfirmButton: false,
    //           timer: 3000
    //         });

    //         Toast.fire({
    //           type: 'success',
    //           title: 'Signed in successfully.'
    //         })

    //     }).catch(e => {
    //       console.log(e);
    //       Swal.fire({
    //         title: 'Error!',
    //         text: '다시 입력해주세요.',
    //         type: 'error',
    //         confirmButtonText: 'Ok'
    //       })
    //     })
    //   }
    // },

    // async getUserInfo(){
    //     const params = {
    //       id: this.user.user_id,
    //     };
    // },
    // checkLoggedIn() {
    //   if (this.$session.has("token")) {
    //     this.userCheck = true
    //   }
    //   else {
    //     this.userCheck = false
    //   }
    // },
    // getUser() {
    //   let token = this.$session.get("token")
    //   // parseJwt
    //   let base64Url = token.split('.')[1];
    //   let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    //   let jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
    //     return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    //   }).join(''));
    //   this.user = JSON.parse(jsonPayload)
    //   this.$store.state.currentUser = this.user
    //   // return JSON.parse(jsonPayload);
    // },
  }
});
</script>
