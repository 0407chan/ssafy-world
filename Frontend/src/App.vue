<template>
<v-app xs12>
  <!-- <Navigation /> -->
  <template v-if="windows.width < 600">
    <v-navigation-drawer dark class="" v-model="drawer" disable-resize-watcher width="200px" app>
      <v-list dense v-show="currUser == ''">
        <BeforeLogin />
      </v-list>
      <v-list dense v-show="currUser != ''">
        <AfterLogin />
      </v-list>
    </v-navigation-drawer>
  </template>
  <template v-else>
    <v-navigation-drawer dark v-model="drawer" disable-resize-watcher width="200px" app permanent>
      <v-list dense v-show="currUser == ''">
        <BeforeLogin />
      </v-list>
      <v-list dense v-show="currUser != ''">
        <AfterLogin />
      </v-list>

      <v-list dense>
        <v-list-item @click="goTo('chatroom')">
            <v-list-item-title>
                <span>
                채팅방 ㄱㄱㄱ
              </span>
            </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </template>

  <!-- <Header></Header> -->
  <v-app-bar id="headerBar" app dark elevation="0">
    <template v-if="windows.width < 600">
      <v-btn icon @click="drawer = !drawer">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-btn>
    </template>

    <v-toolbar-title>{{currRoom.rname}}</v-toolbar-title>
    <div>
      <template v-if="currRoom.rPeople.length>0">
        <v-badge
          color="primary"
          overlap
          class="align-self-center"
        >
          <template v-slot:badge>
            <span>{{currRoom.rPeople.length}}</span>
          </template>
          <v-icon large>
            mdi-account
          </v-icon>
        </v-badge>



      </template>
    </div>

    <v-spacer />

    <!-- 집에서 테스트하기위한 -->

    <v-btn icon @click="invite()">
      <v-icon>mdi-account-multiple-plus</v-icon>
    </v-btn>
    <Invite :user="allUser" :display="inviteDisplay" />

    <div v-if="currUser">
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-avatar>
              <img :src="currUser.img">
            </v-avatar>
          </v-btn>
        </template>

        <v-list>
          <v-list-item>
            <v-list-item-avatar>
              <v-img v-if="currUser.img" :src="currUser.img"></v-img>
              <v-icon v-else>mdi-account</v-icon>
            </v-list-item-avatar>
            <v-list-item-title>{{ currUser.uname }} 님</v-list-item-title>
          </v-list-item>

          <v-divider></v-divider>

          <v-list-item @click="goTo('userinfo')">
            <v-list-item-avatar>
              <v-icon >mdi-account</v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Profile</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>
          <v-list-item @click="logout">
            <v-list-item-avatar>
              <v-icon> mdi-logout </v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Logout</v-list-item-title>
            </v-list-item-content>

          </v-list-item>
        </v-list>
      </v-menu>
    </div>
  </v-app-bar>


  <v-content class="contents">
    <router-view />
  </v-content>

  <div id="startBackground"></div>
  <div class="darker"></div>

</v-app>
</template>

<script>
import api from '@/api'
import Navigation from '@/components/Navigation'

import Invite from '@/components/invite/Invite'
import BeforeLogin from '@/components/navigations/BeforeLogin'
import AfterLogin from '@/components/navigations/AfterLogin'
import {
  mapState,
  mapActions,
} from 'vuex'

export default {
  name: 'App',
  components: {
    BeforeLogin,
    AfterLogin,
    Invite,
    // Navigation,
  },
  data() {
    return {
      drawer: true,
      windows: {
        width: 0,
        height: 0
      },
      allUser : [],
      inviteDisplay : false
    };
  },
  computed: {
    ...mapState({
      currUser: state => state.data.currUser,
      currRoom: state => state.data.currChatRoom,
    }),
  },
  methods: {
    ...mapActions('data', ['refresh']),
    ...mapActions('data',['clearCurrUser']),
    ...mapActions('data', ['setCurrUser']),

    invite(){
      api.getUsers().then(res=>{
        console.log(res.data);

        let data = []
        for(let i=0;i<res.data.length;i++){
          if(this.currUser.uidx!=res.data[i].uidx){
            let flag =true
            for(let l=0;l<this.currRoom.rPeople.length;l++){
              console.log(this.currRoom.rPeople[l].uidx,res.data[i].uidx);

              if(this.currRoom.rPeople[l].uidx==res.data[i].uidx){
                flag=false
                break
              }
            }
            if(flag==true)
              data.push(res.data[i])
          }
        }
        console.log(data);
        console.log(this.currRoom.rPeople);

        this.allUser=data
        this.inviteDisplay=true
      })
    },

    handleResize() {
      this.windows.width = window.innerWidth;
      this.windows.height = window.innerHeight;
    },

    goTo(path) {
      this.$router.push({
        name: path
      });
    },

    /* 2019.11.01 이찬호
    기능 : 로그아웃
     - 세션 끊기
     - store에 currUser 초기화하기
     - login page로 돌아가기
    */
    logout() {
        this.clearCurrUser()
        this.$session.destroy();
        this.$router.push({ name: 'login' });
    },

    setProfile(){
      // console.log(this.currRoom);
      let params={
        uidx :1,
        uname : "이찬호",
        img: "https://i.imgur.com/WiBTxG7.jpg",
        uid:"0407chan@naver.com",
        staff: 0
      }
      this.$session.set('token',params)
      this.setCurrUser(params);
    },
    setProfile2(){
      let params={
        uidx :2,
        uname : "엔젤릭버스터",
        img: "https://i.imgur.com/oqTAGYt.png",
        uid:"1004ej@maple.com",
        staff: 1
      }
      this.$session.set('token',params)
      this.setCurrUser(params);
    },
    setBackground(){
      // document.getElementById('startBackground').style.backgroundImage="url(https://i.imgur.com/bmdyui1.jpg)";
    }
  },

  mounted() {
    this.inviteDisplay=false
    if(this.$session.has('token')){
      let token = this.$session.get('token')
      console.log("App.vue Mounted")
      this.setCurrUser(token).then(res=>{
        this.refresh(token)
      })
    }
    this.setBackground();
  },

  created() {
    window.addEventListener('resize', this.handleResize)
    this.handleResize();
  },
  destroyed() {
    this.$socket.emit('disconnect', {
        msg: 'disconnect'
      }),
      window.removeEventListener('resize', this.handleResize)
  },
}
</script>

<style>
#startBackground{
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.5);
  background-position: center center;
  background-size: cover;
  background-repeat: no-repeat;
  z-index: 1;
}

.contents{
  z-index: 4;
}

.darker {
  position: absolute;
  left: 0px;
  top: 0px;
  width: 100%;
  height: 100%;
  z-index: 2;
  background-color: #000000;

  -webkit-filter: blur(4px);
  -moz-filter: blur(4px);
  -ms-filter: blur(4px);
  -o-filter: blur(4px);
  filter: blur(4px);

  opacity: 0.7;
}
</style>
