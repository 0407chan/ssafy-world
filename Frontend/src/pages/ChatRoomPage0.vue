<template>
  <v-container fluid wrap>
    <v-row id="messageBody" class="scrollable">
      <v-col cols="12">
        <v-row class="message-line" v-for="(message, index) in msgDatas" no-gutters>
          <template v-if="message.user.uname != currUser.uname">
            <div aglin="center">
              <div aglin="center" style="display: inline-block; width:48px; max-width:48px;">
                <v-avatar tile>
                  <v-img :src="message.user.img"></v-img>
                </v-avatar>
              </div>
              <div aglin="center" style="display: inline-block">
                <div>
                  {{ message.user.uname }} {{getTime(message.time)}}
                </div>
                <div>
                  {{message.msg}}
                </div>
              </div>
            </div>

            <v-col cols="12" style="padding : 4px;">



              <table style="border: 1px solid black width:100%">
                <tr style="border: 1px solid black">
                  <td style="padding: 4px">
                    <v-avatar tile>
                      <v-img :src="message.user.img"></v-img>
                    </v-avatar>
                  </td>
                  <td style="padding: 4px">
                    <div>
                      {{ message.user.uname }} {{getTime(message.time)}}
                    </div>
                    <div>
                      {{message.msg}}
                    </div>
                  </td>
                </tr>
              </table>


              <v-row no-gutters >
                <!-- 사진 -->
                <v-col :cols="col" style="padding : 4px;">
                  <v-row no-gutters >
                    <v-col v-if=" (index-1 >= 0 && msgDatas[index-1].user.uname != message.user.uname) || index == 0">
                      <div >
                        <v-menu bottom offset-y>
                          <template v-slot:activator="{ on }">
                            <v-btn icon v-on="on">
                              <v-avatar tile>
                                <img :src="message.user.img">
                              </v-avatar>
                            </v-btn>
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
                      </div>
                    </v-col>
                    <v-col v-else >
                      <div align ="end" class="time" v-if="message.time && index > 0" :class="{time: index-1 >= 0 && getMinute(message.time) == getMinute(msgDatas[index-1].time)}">
                        {{getTime(message.time)}}
                      </div>
                    </v-col>
                  </v-row>
                </v-col>
                <!-- 이름, 메세지 -->
                <v-col cols="11">
                  <v-row no-gutters >
                    <v-col cols="12" v-if=" (index-1 >= 0 && msgDatas[index-1].user.uname != message.user.uname) || index == 0">
                      <v-menu right offset-x>
                        <template v-slot:activator="{ on }">
                          <span class="username" v-on="on" style="padding : 4px;">
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
                      <span class="profileTime">
                        {{getTime(message.time)}}
                      </span>
                    </v-col>
                    <v-col cols="auto" class="content"  style="padding : 4px;">
                      {{message.msg}}
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-col>
          </template>

          <template v-else>
            <v-col cols="12" style="padding : 4px;">
              <v-row no-gutters justify="end">

                <!-- 이름, 메세지 -->
                <v-col cols="11">
                  <v-row no-gutters justify="end">
                    <v-col cols="12" v-if=" (index-1 >= 0 && msgDatas[index-1].user.uname != message.user.uname) || index == 0">
                      <div align="end">
                        <span class="profileTime">
                          {{getTime(message.time)}}
                        </span>

                          <v-menu left offset-x>
                            <template v-slot:activator="{ on }">
                              <span class="username" v-on="on" style="padding : 4px;">
                                {{message.user.uname}}
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
                      </div>
                    </v-col>
                    <v-col cols="auto" style="padding : 4px;">
                      <span class="time" v-if="windows.width < 600 && message.time && index > 0" :class="{time: index-1 >= 0 && getMinute(message.time) == getMinute(msgDatas[index-1].time)}">
                        {{getTime(message.time)}}
                      </span>
                      <span class="content">
                      {{message.msg}}
                      </span>
                    </v-col>
                  </v-row>
                </v-col>
                <!-- 사진 -->
                <v-col v-if="windows.width >= 600" cols="1" style="padding : 4px;">
                  <v-row no-gutters>
                    <v-col v-if=" (index-1 >= 0 && msgDatas[index-1].user.uname != message.user.uname) || index == 0">
                      <v-row justify='center'>
                        <v-menu bottom offset-y>
                          <template v-slot:activator="{ on }">
                            <v-btn icon v-on="on">
                              <v-avatar tile>
                                <img :src="currUser.img">
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
                      </v-row>
                    </v-col>
                    <v-col v-else >
                      <div class="time" v-if="message.time && index > 0" :class="{time: index-1 >= 0 && getMinute(message.time) == getMinute(msgDatas[index-1].time)}">
                        {{getTime(message.time)}}
                      </div>
                    </v-col>
                  </v-row>
                </v-col>

              </v-row>
            </v-col>
          </template>
        </v-row>
      </v-col>
    </v-row>

    <v-row id="chatInput">
      <v-col cols="12">
        <v-row no-gutters class="inputBorder">
          <v-col cols="12" >
            <div>
              <v-text-field
                v-model="msg"
                placeholder="보낼 메세지를 입력하세요."
                hide-details
                solo
                @keyup.13="sendMessage"
              >
              <v-btn icon >
                <v-icon class="chatInputImage">
                  mdi-paperclip
                </v-icon>
              </v-btn>
              </v-text-field>
            </div>
            <div>
              <v-btn icon >
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
    </v-row>
      <!-- <div class="message" v-for="(message,index) in msgs" :class="{own: message.from.name == username}"> -->
  </v-container>
</template>

<script>
import { mapMutations, mapState, mapActions } from 'vuex'

export default {
  name: 'ChatRoomPage',
  data() {
    return {
      datas: [],
      msg:'',
      findPeople:false,
      dialog:false,

      windows: {
        width: 0,
        height: 0
      },
    };
  },
  computed: {
    ...mapState({
      msgDatas: state => state.socket.msgDatas,
      currUser: state => state.data.currUser,
    }),
    getCurrUser(){
      return this.$store.state.currUser;
    },

    col(){
      if(this.windows.width >= 600){
        return 1;
      }else{
        return 2;
      }
    }
  },
  watch:{
    getCurrUser(newCount, oldCount) {
      // Our fancy notification (2).
      console.log(`from ${oldCount} We have ${newCount} fruits now, yaay!`)
    }
  },
  mounted() {
    const $ths = this
    console.log("connect chatroom" , window.location.pathname);
    this.getMsg(window.location.pathname.split('/')[2])
    this.$socket.on(window.location.pathname,async (data) => {
      data.time = this.getToday();
      await this.pushMsgData(data)
      this.scrollToBottom();
    });
    this.scrollToBottom();
  },
  methods: {
    ...mapActions('socket', ['getMsg']),

    ...mapMutations('socket',['pushMsgData']),

    test(){
      var today = new Date();
      console.log("이거",today)
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

      if(this.msg == '#점심'){
        let params ={
          uid: "ssafy@ssafy.com",
          uidx: 0,
          staff: 1,
          img: "https://i.imgur.com/6woB3eO.png",
          uname: "SSAFY"
        }
        this.pushMsgData({
          user:params,
          msg:"오늘의 점심입니다.",
          time:this.getToday(),
        });
        return false;
      }
      this.pushMsgData({
        user: this.currUser,
        msg:this.msg,
        time:this.getToday(),
      });
      // await this.$sendMessage({
      //   rid:window.location.pathname,
      //   user: this.currUser,
      //   msg:this.msg,
      //   time:this.getToday(),
      // });
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
    }
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
  height: 80vh;
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
   color : black;
 }

.message-line:hover {
  background: rgba(220,220,220,0.5);
}
.username{
  font-weight: bold;
}
.username:hover{
  text-decoration: underline;
  cursor:pointer;
}
.content{
  border-radius: 5px;
  background-color: orange;
  padding: 5px;
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
}

</style>
