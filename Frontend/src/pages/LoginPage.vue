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
import axios from "axios"
import api from "@/api"
import { mapState, mapActions } from "vuex"

export default {
  name: 'LoginPage',
  data() {
    return {
      user: {
        email: '',
        password: '',
      },
    }
  },
  mounted() {

  },
  computed:{
    ...mapState({
       accessToken : state => state.data.accessToken
    }),
    usernameLen(){
      return this.user.email.length;
    },
    passwordLen(){
      return this.user.password.length;
    },
  },
  methods: {
    /**
     * 2019. 10. 18. 준범이
     * 싸늘하다..
     * 비수가 날아와 가슴에 꽂힌다.
     * 이메일 한 발...
     * 비밀번호 한 발...
     * 그리고 정마담한테 loginActi?!
     * 동작 그만!!
     * 불법 로그인이냐?!
     * 내가 빙다리 핫바지로 보이냐
     * 
     */
    ...mapActions("data", ['login']),
    async loginAction() {
        var param = {
          email: this.user.email,
          password: this.user.password,
        }
        await this.login(param)
  
        if (this.accessToken) {
          this.$router.push({
            name: 'room',
          })
        }
        else {

        }
    },
  }
};
</script>
