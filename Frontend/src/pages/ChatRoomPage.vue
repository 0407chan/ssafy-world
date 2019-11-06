<template>
  <v-container fluid fill-height>

    <v-row >
      <v-col cols="12" id="messageBody" class="scrollable">
        <div class="message-line" v-for="(message, index) in msgDatas" no-gutters>

          <template v-if="message.user.uidx != currUser.uidx">
            <v-row style="padding: 4px;">
              <template v-if=" (index-1 >= 0 && msgDatas[index-1].user.uidx != message.user.uidx) || index == 0">
                <div align-center justify-center style="padding: 6px; display: inline-block; width:48px">
                  <v-menu bottom offset-y>
                    <template v-slot:activator="{ on }">
                      <v-btn tile icon large v-on="on">
                        <v-avatar size="44px" tile>
                          <v-img :src="message.user.img">
                          </v-img>
                        </v-avatar>
                      </v-btn>
                    </template>

                    <v-card max-width="250" class="mx-auto">
                      <v-img :src="message.user.img" height="200px" dark></v-img>

                      <v-card-title >{{ message.user.uname }}</v-card-title>

                      <v-card-text>
                        <div>{{ getStaff(message.user.staff) }}</div>
                        <div>{{ message.user.uid }}</div>
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
                </div>
              </template>
              <template v-else >
                <div align ="end" class="time" v-if="message.time && index > 0" :class="{time: index-1 >= 0 && getMinute(message.time) == getMinute(msgDatas[index-1].time)}" style="padding: 4px; width:48px">
                  {{getTime(message.time)}}
                </div>
              </template>

              <template v-if=" (index-1 >= 0 && msgDatas[index-1].user.uidx != message.user.uidx) || index == 0">
                <div style="display: inline-block" >
                  <v-menu right offset-x>
                    <template v-slot:activator="{ on }">
                      <span class="username" v-on="on" style="padding : 4px; display: inline-block">
                        {{message.user.uname}}
                      </span>
                    </template>
                    <v-card max-width="250" class="mx-auto">
                      <v-img :src="message.user.img" height="200px" dark></v-img>
                      <v-card-title>{{ message.user.uname }}</v-card-title>
                      <v-card-text>
                        <div>{{ getStaff(message.user.staff) }}</div>
                        <div>{{ message.user.uid }}</div>
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
                  <div class="profileTime" style="display: inline-block">
                    {{getTime(message.time)}}
                  </div>
                  <br/>
                  <div class="contentOther" style=" margin-left: 4px; display: inline-block" :class="{contentSsafy: message.user.uname == 'SSAFY'}">
                    <div>
                      {{message.msg}}
                    </div>
                    <div v-if="message.img">
                      <v-img class="contentImage" @click="viewImage" :src="message.img" width="300px">
                      </v-img>
                    </div>
                    <div v-if="message.count == 1">
                      <Timer
                       :starttime="start"
                       :endtime="end"
                       trans='{
                       "day":"Day",
                       "hours":"Hours",
                       "minutes":"Minuts",
                       "seconds":"Seconds",
                       "expired":"Event has been expired.",
                       "running":"Till the end of event.",
                       "upcoming":"Till start of event.",
                       "status": {
                          "expired":"Expired",
                          "running":"Running",
                          "upcoming":"Future"
                         }}'
                       ></Timer>
                    </div>
                    <div v-if="message.count == 2">
                      <Timer
                       :starttime="start"
                       :endtime="endTest"
                       trans='{
                       "day":"Day",
                       "hours":"Hours",
                       "minutes":"Minuts",
                       "seconds":"Seconds",
                       "expired":"Event has been expired.",
                       "running":"Till the end of event.",
                       "upcoming":"Till start of event.",

                       "status": {
                          "expired":"Expired",
                          "running":"Running",
                          "upcoming":"Future"
                         }}'
                       ></Timer>
                    </div>
                  </div>
                </div>
              </template>
              <template v-else>
                <div class="contentOther" style=" margin-left: 4px; display: inline-block" :class="{contentSsafy: message.user.uname == 'SSAFY'}">
                  <div>
                  {{message.msg}}
                  </div>
                  <div v-if="message.img">
                    <v-img class="contentImage" @click="viewImage" :src="message.img" width="300px">
                    </v-img>
                  </div>
                  <div v-if="message.count == 1">
                    <Timer
                     :starttime="start"
                     :endtime="end"
                     trans='{
                     "day":"Day",
                     "hours":"Hours",
                     "minutes":"Minuts",
                     "seconds":"Seconds",
                     "expired":"Event has been expired.",
                     "running":"Till the end of event.",
                     "upcoming":"Till start of event.",
                     "status": {
                        "expired":"Expired",
                        "running":"Running",
                        "upcoming":"Future"
                       }}'
                     ></Timer>
                  </div>
                  <div v-if="message.count == 2">
                    <Timer
                     :starttime="start"
                     :endtime="endTest"
                     trans='{
                     "day":"Day",
                     "hours":"Hours",
                     "minutes":"Minuts",
                     "seconds":"Seconds",
                     "expired":"Event has been expired.",
                     "running":"Till the end of event.",
                     "upcoming":"Till start of event.",

                     "status": {
                        "expired":"Expired",
                        "running":"Running",
                        "upcoming":"Future"
                       }}'
                     ></Timer>
                  </div>
                </div>
              </template>
            </v-row>
          </template>

          <!-- 자기 채팅 -->
          <template v-else>
            <v-row style="padding: 4px;" justify="end">
              <!-- 시간, 이름 메세지 -->
              <template v-if=" (index-1 >= 0 && msgDatas[index-1].user.uidx != message.user.uidx) || index == 0">
                <div style=" display: inline-block" align="end">
                  <div class="profileTime" style="display: inline-block" >
                    {{getTime(message.time)}}
                  </div>
                  <v-menu left offset-x>
                    <template v-slot:activator="{ on }">
                      <span class="username" v-on="on" style="padding : 4px; display: inline-block">
                        {{currUser.uname}}
                      </span>
                    </template>
                    <v-card max-width="250" class="mx-auto">
                      <v-img :src="currUser.img" height="200px" dark></v-img>
                      <v-card-title>{{ currUser.uname }}</v-card-title>
                      <v-card-text>
                        <div>{{ getStaff(currUser.staff) }}</div>
                        <div>{{ currUser.uid }}</div>
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

                  <br/>
                  <div class="contentMine" style=" margin-right: 4px; display: inline-block">
                    {{message.msg}}
                  </div>
                </div>
              </template>
              <template v-else>
                <div class="contentMine" style=" margin-right: 4px; display: inline-block">
                  {{message.msg}}
                </div>
              </template>

              <!-- 사진 -->
              <template v-if=" (index-1 >= 0 && msgDatas[index-1].user.uidx != message.user.uidx) || index == 0">
                <div align-center justify-center style="padding: 6px; display: inline-block; width:48px">
                  <v-menu bottom offset-y>
                    <template v-slot:activator="{ on }">
                      <v-btn tile icon large v-on="on">
                        <v-avatar size="44px" tile>
                          <v-img :src="currUser.img">

                          </v-img>
                        </v-avatar>
                      </v-btn>
                    </template>

                    <v-card max-width="250" class="mx-auto">
                      <v-img :src="currUser.img" height="200px" dark></v-img>

                      <v-card-title>{{ currUser.uname }}</v-card-title>

                      <v-card-text>
                        <div>{{ getStaff(currUser.staff) }}</div>
                        <div>{{ currUser.uid }}</div>
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
                </div>
              </template>
              <template v-else >
                <div class="time" v-if="message.time && index > 0" :class="{time: index-1 >= 0 && getMinute(message.time) == getMinute(msgDatas[index-1].time)}" style="padding: 4px; width:48px">
                  {{getTime(message.time)}}
                </div>
              </template>
            </v-row>
          </template>

        </div>
      </v-col>

      <v-col cols="12" id="chatInput">
        <v-row no-gutters class="inputBorder">
          <v-col cols="12" >
            <input
              type="file"
              style="display: none"
              ref="image"
              accept="image/*"
              @change="onFilePicked"
            />
            <div>
              <v-text-field
                v-model="msg"
                placeholder="보낼 메세지를 입력하세요."
                hide-details
                solo
                dark
                @keyup.13="sendMessage"
              >
              </v-text-field>
            </div>
            <div>
              <v-btn icon click="pickFile">
                <v-icon class="chatInputImage">
                  mdi-paperclip
                </v-icon>
              </v-btn>
              <v-btn icon >
                <v-icon class="chatInputEmoticon">
                  mdi-emoticon-outline
                </v-icon>
              </v-btn>
            </div>
          </v-col>
        </v-row>
      </v-col>

      <v-dialog
        v-model="dialog"
        fullscreen
        hide-overlay
        scrollable
      >
        <v-card >
          <v-toolbar flat dark>
            <v-toolbar-title>Images</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-divider
              class="mx-4"
              inset
              vertical
            ></v-divider>

            <v-btn
              icon
              dark
              @click="dialog = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>

          </v-toolbar>

          <v-container fill-height>
            <v-img v-if="images.length>0"
              :max-height="windows.height" :max-width="windows.width"

              :src="images[0].img"
              :aspect-ratio="ratio"
              contain
            ></v-img>
          </v-container>
        </v-card>
      </v-dialog>
    </v-row>

  </v-container>
</template>

<script>
import { mapMutations, mapState, mapActions } from 'vuex'
import Timer from '../components/Timer'
export default {
  name: 'ChatRoomPage',
  components:{
    Timer,
  },
  data() {
    return {
      datas: [],
      msg:'',
      findPeople:false,
      dialog:false,
      images:[],
      ratio: 1.5,
      dialog: false,
      windows: {
        width: 0,
        height: 0
      },

      end: new Date(new Date().getFullYear()+"-"+(new Date().getMonth()+1)
                    +"-"+new Date().getDate()+" 18:00:00").getTime(),
      endTest: new Date(new Date().getFullYear()+"-"+(new Date().getMonth()+1)
                    +"-"+new Date().getDate()+" "+new Date().getHours()+":"+(new Date().getMinutes()+1)+":"+new Date().getSeconds()).getTime(),
      start: new Date().getTime(),
    };
  },
  computed: {
    ...mapState({
      msgDatas: state => state.socket.msgDatas,
      currUser: state => state.data.currUser,
      currRoom: state => state.data.currChatRoom,
    }),

    imageIcon () {
      return this.icons[this.iconIndex]
    },


  },
  mounted() {
    console.log("connect chatroom" , window.location.pathname);
    this.startSocket();
    this.getChatRoomMsgAction();
    this.setCurrChatRoomInfo();
    this.scrollToBottom();
    this.getRoomImageAction();

  },

  watch: {
    '$route'(to, from) {
      this.startSocket();
      this.setCurrChatRoomInfo();
      this.getChatRoomMsgAction();
      this.scrollToBottom();
      this.getRoomImageAction();
    },
  },
  methods: {
    ...mapActions('data', ['setCurrChatRoom']),
    ...mapActions('data', ['getRoom']),
    ...mapActions('data', ['getRoomPeople']),
    ...mapActions('data', ['postRoomImage']),
    ...mapActions('data', ['getRoomImage']),

    ...mapActions('socket',['getMsg']),
    ...mapActions('socket',['pushMsg']),
    ...mapMutations('socket',['clearMsg']),


    test(){
      console.log(this.start)
      console.log(this.end)
      console.log(new Date(this.start).getDate())
    },



    async getRoomImageAction(){

      let res = await this.getRoomImage(this.currRoom.ridx);
      if(res.status==500){
        console.log("서버에러", res.status)
      }else{
        this.images = res.data;
      }
    },

    viewImage(){
      this.dialog = true;
      console.log(this.images);
      this.getRoomImageAction();
    },

    async startSocket(){
      const $ths = this
      await this.$socket.on(window.location.pathname, async (data) => {
        data.time = this.getToday();
        await this.pushMsg(data);
        this.scrollToBottom();
      });
    },

    /* 2019.11.05 이찬호
    기능 : 해당 방의 메세지를 가져옵니다.
    */
    async getChatRoomMsgAction(){
      console.log("읽어보자!")
      await this.clearMsg();
      if(window.location.pathname.split('/')[2]!=undefined){
        await this.getMsg(window.location.pathname.split('/')[2])
      }
    },

    /* 2019.11.05 이찬호
    기능 : 현재 방 정보를 vuex에 저장합니다.
    */
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


    setMessageList(data){
      console.log(data.uidx.length);

      let arr = []
      for(let i =0;i<data.uidx.length;i++){

        arr.push({
          'user' : data.user[i],
          'time': data.time[i],
          'msg':data.messages[i]
        })
      }
      this.pushMsg(arr)
      this.scrollToBottom();
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

    goTo(path,uidx) {
      this.$router.push({name: path, params:{ uidx: uidx}})
    },

    checkMsg(){
      console.log(this.msg);
    },

    /* 2019.11.02 이찬호
    기능 : 창 크기 실시간 감지
    */
    handleResize() {
      this.windows.width = window.innerWidth;
      this.windows.height = window.innerHeight;
    },

    async sendMessage() {
      if (this.msg.length === 0) return false;
      if(this.msg == '#점심' || this.msg=='@점심'){
        let params ={
          uid: "ssafy@ssafy.com",
          uidx: 0,
          staff: 1,
          img: "https://i.imgur.com/bp8N7YT.png",
          uname: "SSAFY"
        }
        await this.pushMsg({
          user:params,
          msg:"오늘의 점심입니다.",
          time:this.getToday(),
          img:"https://i.imgur.com/fBjay1B.png"
        });
        await this.postRoomImageAction("https://i.imgur.com/fBjay1B.png");
        this.msg='';
        this.scrollToBottom();
        return false;
      }else if(this.msg == '#퇴근' || this.msg=='@퇴근' || this.msg=='@퇴실' ||this.msg=='#퇴실'){
        let ssafy ={
          uid: "ssafy@ssafy.com",
          uidx: 0,
          staff: 1,
          img: "https://i.imgur.com/bp8N7YT.png",
          uname: "SSAFY"
        }
        await this.pushMsg({
          user:ssafy,
          time:this.getToday(),
          count:1,
        });

        this.msg='';
        this.scrollToBottom();
        return false;
      }else if(this.msg == '#퇴근테스트' || this.msg=='@퇴근테스트' || this.msg=='@퇴실테스트' ||this.msg=='#퇴실테스트'){
        let ssafy ={
          uid: "ssafy@ssafy.com",
          uidx: 0,
          staff: 1,
          img: "https://i.imgur.com/bp8N7YT.png",
          uname: "SSAFY"
        }
        await this.pushMsg({
          user:ssafy,
          time:this.getToday(),
          count:2,
        });

        this.msg='';
        this.scrollToBottom();
        return false;
      }
      await this.pushMsg({
        user: this.currUser,
        msg:this.msg,
        time:this.getToday(),
      });
      await this.$sendMessage({
        rid:window.location.pathname,
        user: this.currUser,
        msg:this.msg,
        time:this.getToday(),
      });
      this.msg = '';
      this.scrollToBottom();
    },

    /* 2019.11.03 이찬호
    기능 : 오늘 날짜 2019-04-05 11:23:03 형태로 리턴해줌
    */
    getToday(){
      var today = new Date();
      let day = today.getFullYear()+"-"+(today.getMonth()+1)+"-"+today.getDate();
      var hour = today.getHours();
      var min = today.getMinutes();
      var sec = today.getSeconds();
      if(hour < 10){
        hour = "0"+hour
      }
      if(min < 10){
        min = "0"+min;
      }
      if(sec < 10){
        sec = "0"+sec;
      }
      return day +" "+ hour +":"+ min +":"+ sec
    },

    /* 2019.11.03 이찬호
    기능 : 채팅방 스크롤 맨 밑으로 보내줌
    */
    scrollToBottom(){
      var messageBody = document.getElementById('messageBody');
      messageBody.scrollTop = messageBody.scrollHeight;
    },

    /* 2019.11.03 이찬호
    기능 : 우리 time 포멧에서 분 리턴 해줌
    */
    getMinute(time){
      return time.split(':')[1];
    },

    /* 2019.11.03 이찬호
    기능 : 우리 time 포멧에서 시:분 형태로 리턴해줌
    */
    getTime(time){
      let date = time.split(' ')[0];
      let tim = time.split(' ')[1];

      return tim.substring(0,5);
    },


    /* 2019.11.05 이찬호
      이미지 있으면 이미지 디비에 저장하기
    */
    async postRoomImageAction(img){
      let params={
        ridx : this.currRoom.ridx,
        img : img
      }
      await this.postRoomImage(params);
    },

    // 이미지 변경해서 imgur에 올리기
    pickFile() {
      console.log(this.$refs);
      this.$refs.image.click();
    },
    setImageUrl(url){
      this.modiImage = url;
    },
    onFilePicked(e) {
      this.modiImage = '';
      const files = e.target.files;
      this.loading = true;
      if (files[0] !== undefined) {
        this.imageName = files[0].name;
        if (this.imageName.lastIndexOf(".") <= 0) {
          return;
        }

        let formData = new FormData();
        formData.append('image', files[0]); //required

        axios({
           method: 'POST',
           url: 'https://api.imgur.com/3/image',
           data: formData,
           headers: {
           Authorization: "Client-ID aac995cb6f223ce"
           },
           mimeType: 'multipart/form-data'
           }).then(res => {
             this.modiImage = res.data.data.link;
             this.loading = false;
           }).catch(e => {
             console.log(e)
        });

      } else {
        this.imageName = "";
        this.modiImage = "";
      }
    },

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
};
</script>

<style>
.msg-form {
  bottom: -28px;
  position: absolute;
  left: 0;
  right: 0;
}
.scrollable {
  overflow-y: auto;
  height: 70vh;
}
.message {
  margin: 5px;
  border-radius: 4px;
}
.time{
  color : rgba(0,0,0,0);
  font-size: 12px;
}

.message-line:hover .time {
   color : white;
 }

.message-line:hover {
  background: rgba(20,20,20,0.5);
}
.username{
  color: white;
  font-weight: bold;
}
.username:hover{
  text-decoration: underline;
  cursor:pointer;
}

.contentImage:hover{
  cursor:pointer;
}

.contentMine{
  border-radius: 5px;
  background-color: rgba(255,255,255,0.9);
  padding-left: 6px;
  padding-right: 6px;
  padding-top:2px;
  padding-bottom: 2px;
  color: black;
}

.contentOther{
  border-radius: 5px;
  background-color: rgba(0,0,0,0.5);
  padding-left: 6px;
  padding-right: 6px;
  padding-top:2px;
  padding-bottom: 2px;
  color: white;
}

.contentSsafy{
  border-radius: 5px;
  background-color: rgba(0,0,255,0.5);
  padding-left: 6px;
  padding-right: 6px;
  padding-top:2px;
  padding-bottom: 2px;
  color: white;
}

.inputBorder{
  border: 1px solid rgba(220,220,220,0.9);
  border-radius: 5px;
}
#chatInput{
  position: sticky;
  bottom: 0px;
  z-index: 1;

}
.profileTime{
  font-size: 12px;
  color:white;
}

#bannerCard {
  position: sticky;
  top: 0px;
  z-index: 1;
}
</style>
