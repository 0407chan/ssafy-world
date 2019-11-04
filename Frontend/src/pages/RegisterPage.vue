<template>
  <v-container justify-center fill-height >
      <v-flex sm6>
        <v-flex ma-5 style="color:black">회원가입</v-flex>
        <v-flex xs12 text-xs-center mx-5>
          <v-flex xs12 text-xs-center>
            <v-text-field v-model="uid" label="ID*"
              solo hide-details @keyup.enter="registerAction" style="margin: 2% 0; opacity: 0.7">
              <template v-slot:append>
                <v-fade-transition>
                <template v-if="uid.length >= 4">
                  <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
                </template>
                <template v-else-if="uid.length > 0 && uid.length <4">
                  <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
                </template>
                </v-fade-transition>
              </template>
            </v-text-field>

            <v-text-field v-model="uname" label="Name*"
              solo hide-details @keyup.enter="registerAction" style="margin: 2% 0; opacity: 0.7">
              <template v-slot:append>
                <v-fade-transition>
                  <template v-if="uname.length >= 2">
                    <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
                  </template>
                  <template v-else-if="uname.length > 0 && uname.length <4">
                    <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
                  </template>
                </v-fade-transition>
              </template>
            </v-text-field>

            <v-text-field v-model="password" label="Password*" type="password"
              solo hide-details @keyup.enter="registerAction" style="margin: 2% 0; opacity: 0.7">
              <template v-slot:append>
                <v-fade-transition>
                  <template v-if="password.length >= 4">
                    <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
                  </template>
                  <template v-else-if="password.length > 0 && password.length <4">
                    <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
                  </template>
                </v-fade-transition>
              </template>
            </v-text-field>

            <v-flex text-sm-center my-5>
              <v-divider style="background:gray"></v-divider>
            </v-flex>

          </v-flex>
        </v-flex>
        <v-flex text-sm-center ma-5>
          <v-btn v-if="uid.length >= 4 && uname.length >= 2 && password.length >=4" rounded block :color="colorIndex" @click="registerAction"><span class="btnText" >회원가입</span></v-btn>
          <v-btn v-else rounded disabled block>회원가입</v-btn>
        </v-flex>
      </v-flex>
  </v-container>

</template>

<style>
.loginModal_btn {
  width: 100px;
}
</style>


<script>
import Swal from 'sweetalert2'
import { mapState, mapActions } from "vuex";
export default {
  data: () => ({
    uid: "",
    uname: "",
    password: "",
    image: '',
    colorIndex:"orange"
  }),
  methods: {
    ...mapActions('data', ['register','login']),

    /** 2019.10.25 이찬호
    *기능 : 회원가입 버튼을 누르면 회원 정보를 등록하고 로그인 한다.
    *      회원가입이 완료 되면 rating page로 이동해 rating을 준다.
    */
    async registerAction(){
      const params = {
        uid: this.uid,
        uname : this.uname,
        password: this.password,
        img: "https://i.imgur.com/PuEZpTi.png"
      };
      let res = await this.register(params);
      console.log("registerAction",res)
      if (res.status == '200') {
        this.successAlert("WELCOME!","User Created successfully");
        await this.login({
          id : params.uid,
          pw : params.password
        })
        this.$router.push({ name: 'chatroom' })
      }
      else {
        this.errorAlert(res.data,"Try other name");
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

    /* 2019.10.21 이찬호
    기능 : router 이동
    변수 : 이동하고 싶은 페이지 이름
    */
    goTo(page){
      this.$router.push({name:page});
    },

    getUser() {
      let token = this.$session.get("token")
      // parseJwt
      let base64Url = token.split('.')[1];
      let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      let jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));
      this.user = JSON.parse(jsonPayload)
      this.$store.state.currentUser = this.user
      // return JSON.parse(jsonPayload);
    },

  }
};
</script>
