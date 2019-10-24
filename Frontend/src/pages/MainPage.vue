<template>
  <v-container justify-center fill-height >
    <v-flex sm6 >
      <v-flex xs12 text-xs-center mx-5>
        <v-row justify="space-between">
          <v-col>
            <v-flex style="color:black">로그인</v-flex>
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
            @keyup.enter="loginAction"
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
        <v-btn v-if="usernameLen > 0 && passwordLen > 0" rounded block color="green" @click="loginAction"><span class="btnText">Log in</span></v-btn>
        <v-btn v-else rounded disabled block>Log in</v-btn>
      </v-flex>
      <v-flex text-sm-center ma-5>
        <v-divider style="background:gray"></v-divider>
      </v-flex>

      <v-flex xs12 text-xs-center mx-5>
        <v-flex my-5 style="color:black">아이디가 없으신가요?</v-flex>
        <v-btn rounded block color="primary" :to="{name:'register'}">회원가입</v-btn>
      </v-flex>
    </v-flex>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import api from '@/api'
import axios from 'axios'
import Swal from 'sweetalert2'

export default {
  data: () => ({
    user: {
      id:'',
      pw:'',
    },
  }),
  created() {
    if (this.$store.state.data.userLoginToken !== '')
      this.$router.push({ name: 'chatroom' })
  },
  computed:{
    usernameLen() {
      return this.user.id.length;
    },
    passwordLen() {
      return this.user.pw.length;
    },
  },
  methods: {
    ...mapActions("data", ['login']),
    async loginAction() {
      let params = {
        id: this.user.id,
        pw: this.user.pw,
      }
      console.log(await this.login(params))
      // if (await this.login(params) == 200) {
      //   this.$router.push({ name: 'chatroom' })
      // }
    },

    async logins() {
      if (this.credentials.username && this.credentials.password) {
        axios.post('http://localhost:8000/auth/', this.credentials).then(async res => {
          this.$session.start();
          this.$session.set('token', res.data.token);
          this.userCheck = true;
          this.getUser();
          this.getUserInfo();
          await this.postMatrix()
          this.$router.push({name:'main'});
          const Toast = Swal.mixin({
              toast: true,
              position: 'bottom-end',
              showConfirmButton: false,
              timer: 3000
            });

            Toast.fire({
              type: 'success',
              title: 'Signed in successfully.'
            })

        }).catch(e => {
          console.log(e);
          Swal.fire({
            title: 'Error!',
            text: '다시 입력해주세요.',
            type: 'error',
            confirmButtonText: 'Ok'
          })
        })
      }
    },
  }
};
</script>
