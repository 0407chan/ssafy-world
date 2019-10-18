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
          <v-text-field v-model="user.email"
                        label="username*"
                        solo
                        hide-details
                        @keyup.enter="loginAction"
                        style="margin: 2% 0; opacity: 0.7">
            <template v-slot:append>
              <v-fade-transition>
              <template v-if="user.email.length >= 4">
                <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
              </template>
              <template v-else-if="user.email.length > 0 && user.email.length <4">
                <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
              </template>
              </v-fade-transition>
            </template>
          </v-text-field>

          <v-text-field v-model="user.password"
            label="Password*"
            type="password"
            solo
            hide-details
            @keyup.enter="loginAction"
            style="margin: 2% 0; opacity: 0.7">
            <template v-slot:append>
              <v-fade-transition>
                <template v-if="user.password.length >= 4">
                  <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
                </template>
                <template v-else-if="user.password.length > 0 && user.password.length <4">
                  <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
                </template>
              </v-fade-transition>
            </template>
          </v-text-field>
        </v-flex>
      </v-flex>

      <v-flex text-sm-center ma-5>
        <!-- <v-btn rounded block :color="index" @click="loginAction"><span class="btnText">Log in</span></v-btn> -->
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

<script>
import axios from 'axios'
import Swal from 'sweetalert2'
import api from '@/api'
import { mapState, mapActions } from "vuex";

export default {
  name: 'StartPage',
  data: () => ({
    closeCheck:false,
    dialog: false,
    userCheck: false,
    user: {
      email: '',
      password: '',
    },
    validLogin: true,
    validRegister: true,
    loading: false,
    index: '',
  }),
  mounted() {

  },
  computed:{
    usernameLen(){
      return this.user.email.length;
    },
    passwordLen(){
      return this.user.password.length;
    },
  },

  methods: {
    ...mapActions("data", ['login']),

    /**
     * 2019. 10. 18. 준범이
     * 싸늘하다..
     * 비수가 날아와 가슴에 꽂힌다.
     * 이메일 한 발...
     * 비밀번호 한 발...
     * 그리고 정마담한테 loginAct...?!
     * 동작 그만!!
     * 
     */
    async loginAction() {
        var param = {
          email: this.user.email,
          password: this.user.password,
        }
        console.log(param.email)



        let res = await this.login(param);
        console.log(res);
    },
  }
};
</script>
