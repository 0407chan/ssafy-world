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

    </v-navigation-drawer>


  </template>


  <!-- <Header></Header> -->
  <v-app-bar id="headerBar" app dark elevation="0">
    <template v-if="windows.width < 600">
      <v-btn icon @click="drawer = !drawer">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-btn>
    </template>

    <v-toolbar-title>{{currRoom.rname}}
      <span v-if="currRoom.rPeople.length>0" style="color:rgba(255,255,255,0.8); font-size:80%">{{currRoom.rPeople.length}}
      </span>
    </v-toolbar-title>
    <v-divider
      class="mx-4"
      inset
      vertical
    ></v-divider>
    <div>
      <template v-if="currRoom.rPeople.length>0">
        <v-badge
          v-model="badgeShow"
          color="primary"
          overlap
          class="align-self-center"
        >
          <template v-slot:badge>
            <span>{{currRoom.rPeople.length}}</span>
          </template>
          <v-menu bottom offset-y open-on-hover>
            <template v-slot:activator="{ on }">
              <v-btn dark icon v-on="on" large>
              <v-icon large
                @mouseover="badgeShow = true"
                @mouseout="badgeShow = false"
              >mdi-account</v-icon>
            </v-btn>
            </template>
            <v-card max-width="250" class="mx-auto">
              <span v-for="(item, i) in currRoom.rPeople" :key="i">
                <v-menu bottom offset-y open-on-hover>
                  <template v-slot:activator="{ on }">
                    <v-btn tile icon large v-on="on">
                      <v-avatar size="44px" tile>
                        <v-img :src="item.img">
                        </v-img>
                      </v-avatar>
                    </v-btn>
                  </template>

                  <v-card max-width="250" class="mx-auto">
                    <v-img :src="item.img" height="200px" dark></v-img>

                    <v-card-title >{{ item.uname }}</v-card-title>

                    <v-card-text>
                      <div>{{ getStaff(item) }}</div>
                      <div>{{item.id }}</div>
                    </v-card-text>
                    <v-divider class="mx-4"></v-divider>
                    <v-list-item>
                      <v-list-item-action>
                        <v-btn color="deep-purple accent-4" outlined>
                          Message
                        </v-btn>
                      </v-list-item-action>
                    </v-list-item>
                  </v-card>
                </v-menu>
              </span>
            </v-card>
          </v-menu>
        </v-badge>
      </template>
    </div>



    <v-spacer />

    <v-btn v-if="currRoom.rPeople.length>0" icon @click="invite()">
      <v-icon>mdi-account-multiple-plus</v-icon>
    </v-btn>

    <v-dialog v-if="currRoom.rPeople.length>0"
      v-model="roomSettingDialog"
      width="50%"
    >
      <template v-slot:activator="{ on }">
        <v-btn v-if="currRoom.rPeople.length>0" icon v-on="on">
          <v-icon>mdi-settings</v-icon>
        </v-btn>
      </template>

      <v-container fill-height >
        <v-row>
          <v-col cols="12">
            <v-card contain >
              <v-card-title
              class="headline grey lighten-2"
              primary-title
              >
                Room Setting
              </v-card-title>

              <v-card-text>
                <v-text-field v-model="newRoomName"
                  label="RoomName"
                  solo
                  hide-details
                  @keyup.enter="updateRoomNameAction"
                  style="margin: 2% 0; opacity: 0.7">
                  <template v-slot:append>
                    <v-fade-transition>
                      <template v-if="newRoomName.length > 0">
                        <v-icon small color="green darken-2">mdi-check-circle-outline</v-icon>
                      </template>
                      <template v-else>
                        <v-icon small color="red darken-2">mdi-close-circle-outline</v-icon>
                      </template>
                    </v-fade-transition>
                  </template>
                </v-text-field>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-text>
                <div align="center">
                  <v-btn v-if="newRoomName.length > 0" rounded color="orange" @click="updateRoomNameAction"><span class="btnText">수정하기</span></v-btn>
                  <v-btn v-else rounded disabled>수정하기</v-btn>
                  <v-btn rounded v-on:click="roomSettingDialog = false" style="margin: 0 1%">
                    취소
                  </v-btn>
                </div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-dialog>
    <!-- <Invite :user="allUser" :display="inviteDisplay" /> -->

    <v-dialog
        v-model="display"
        max-width="480">
        <v-card>
        <v-card-title class="headline">친구 초대</v-card-title>
        <v-card-text>
          <v-row>
              <v-col cols="2" >
                선택
              </v-col>
              <v-col cols="2" >
                이미지
              </v-col>
              <v-col cols="4" >
                이름
              </v-col>
              <v-col cols="4" >
                아이디
              </v-col>
          </v-row>
          <template v-for="i in user">
            <v-row>
              <v-col cols="2" >
                <v-checkbox  :value="i.uidx" v-model="selected" />
              </v-col>
              <v-col cols="2" >
                <v-img :src="i.img" max-height="64" max-width="64" />
              </v-col>
              <v-col cols="4" >
                {{i.uname}}
              </v-col>
              <v-col cols="4" >
                 ({{i.uid}})
              </v-col>
            </v-row>
          </template>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="inviteFriend()">
            확인
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="display = false" >
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>



    <v-divider
      class="mx-4"
      inset
      vertical
    ></v-divider>


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
import Swal from 'sweetalert2'
import Navigation from '@/components/Navigation'
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
    // Navigation,
  },
  data() {
    return {
      drawer: true,
      roomUserList: false,
      badgeShow: false,
      windows: {
        width: 0,
        height: 0
      },

      newRoomName:'',

      allUser : [],
      // inviteDisplay : false,
      selected:[],
      user:'',
      display:false,

      roomSettingDialog:false,
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
    ...mapActions('data', ['getRoom']),
    ...mapActions('data', ['getRoomPeople']),
    ...mapActions('data', ['setCurrChatRoom']),
    ...mapActions('data', ['roomUpdate']),


    async updateRoomNameAction(){
      this.roomSettingDialog = false;
      let params ={
        ridx : this.currRoom.ridx,
        rname : this.newRoomName
      }
      let res = await this.roomUpdate(params);
      console.log(res);
      if(res.status=='200'){
        this.successAlert("방 이름이 변경되었습니다.",'');
        this.setCurrChatRoomInfo();
      }else{
        console.log(res.data);
      }
    },
    successAlert(title,text){
      Swal.fire({
        title: title,
        text: text,
        type: 'success',
        showConfirmButton: false,
        timer: 1000
      })
    },

    async setCurrChatRoomInfo(){
      let room = null;
      let roomPeople = null;
      if(window.location.pathname.split('/')[2]!= undefined){
        room = await this.getRoom(window.location.pathname.split('/')[2]);
        roomPeople = await this.getRoomPeople(window.location.pathname.split('/')[2]);
      }

      if(room != null && roomPeople != null){
        let params = {
          ridx : room.data.ridx,
          rname : room.data.rname,
          rPeople : roomPeople.data,
        }
        this.setCurrChatRoom(params);
      }
    },

    getStaff(staff){
      if(staff == 0){
        return "학생"
      }else if(staff == 1){
        return "프로"
      }else{
        return "관리자"
      }
    },


    /* 2019.11.05 이찬호
      기능 : 현재 방 사람들 목록 보여줌
    */
    showRoomUserList(){
      this.roomUserList = !this.roomUserList;
    },
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

        this.user=data
        this.display=true
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
    },
    async inviteFriend(){
      for (let index = 0; index < this.selected.length; index++) {
        await api.postEnterRoom(this.selected[index],window.location.pathname.split('/')[2])
      }
      this.display=false
      this.setCurrChatRoomInfo();
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
