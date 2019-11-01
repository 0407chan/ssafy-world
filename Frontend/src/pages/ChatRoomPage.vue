<template>
  <v-container fluid wrap>
    <v-row id="messageBody" class="scrollable">
      <v-col cols="12" style="padding:12px;">
        <v-row class="message-line" v-for="(message, index) in msgDatas" no-gutters>
          <template v-if="message.from.name != currUser.uname">
            <v-col cols="12" style="padding : 4px;">
              <v-row no-gutters >
                <!-- 사진 -->
                <v-col cols="1" style="padding : 4px;">
                  <v-row no-gutters >
                    <v-col v-if=" (index-1 >= 0 && msgDatas[index-1].from.name != message.from.name) || index == 0">
                      <div align ="end">
                        <v-avatar tile >
                          <v-img @click.stop="dialog = true" src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
                        </v-avatar>
                        <v-dialog
                          v-model="dialog"
                          hide-overlay
                          persistent
                          width="300"
                        >
                          <v-card
                            color="primary"
                            dark
                          >
                            <v-card-text>
                              Please stand by
                            </v-card-text>
                          </v-card>
                        </v-dialog>
                      </div>
                    </v-col>
                    <v-col v-else >
                      <div align ="end" class="time" v-if="message.time && index > 0" :class="{time: index-1 >= 0 && message.time.getMinutes() == msgDatas[index-1].time.getMinutes()}">
                        {{getTime(message.time)}}
                      </div>
                    </v-col>
                  </v-row>
                </v-col>
                <!-- 이름, 메세지 -->
                <v-col cols="11">
                  <v-row no-gutters >
                    <v-col cols="12" v-if=" (index-1 >= 0 && msgDatas[index-1].from.name != message.from.name) || index == 0">
                      <span class="username">
                        {{message.from.name}}
                      </span>
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
                    <v-col cols="12" v-if=" (index-1 >= 0 && msgDatas[index-1].from.name != message.from.name) || index == 0">
                      <div align="end">
                        <span class="profileTime">
                          {{getTime(message.time)}}
                        </span>
                        <span class="username">
                          {{message.from.name}}
                        </span>
                      </div>
                    </v-col>
                    <v-col cols="auto" class="content"  style="padding : 4px;">
                      {{message.msg}}
                    </v-col>
                  </v-row>
                </v-col>
                <!-- 사진 -->
                <v-col cols="1" style="padding : 4px;">
                  <v-row no-gutters>
                    <v-col  v-if=" (index-1 >= 0 && msgDatas[index-1].from.name != message.from.name) || index == 0">
                      <v-row justify='center'>
                        <v-menu offset-y>
                          <template v-slot:activator="{ on }">
                            <v-btn icon v-on="on">
                              <v-avatar tile>
                                <img :src="currUser.img">
                              </v-avatar>
                            </v-btn>
                          </template>

                          <v-card
                            max-width="250"
                            class="mx-auto"
                          >
                            <v-img
                              :src="currUser.img"
                              height="200px"
                              dark
                            >
                            </v-img>

                            <v-list>
                              <v-list-item>
                                <v-list-item-title>
                                  <span class="username">
                                    {{ currUser.uname }}
                                  </span>
                                </v-list-item-title>
                              </v-list-item>

                              <v-divider></v-divider>

                              <v-list-item>
                                <v-list-item-action>
                                  <router-link :to="{name:'userinfo', params:{id:currUser.uidx}}">
                                    <div style="color:black">Profile</div>
                                  </router-link>
                                </v-list-item-action>
                                <v-list-item-action>
                                  <router-link :to="{name:'userinfo', params:{id:currUser.uidx}}">
                                    <div style="color:black">Message</div>
                                  </router-link>
                                </v-list-item-action>
                              </v-list-item>
                            <!--
                              <v-divider></v-divider>
                              <v-list-item>
                                <v-btn text block >
                                  <v-icon> mdi-logout </v-icon>
                                  로그아웃
                                </v-btn>
                              </v-list-item> -->
                            </v-list>
                          </v-card>

                        </v-menu>
                      </v-row>
                    </v-col>
                    <v-col v-else >
                      <div class="time" v-if="message.time && index > 0" :class="{time: index-1 >= 0 && message.time.getMinutes() == msgDatas[index-1].time.getMinutes()}">
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
        <v-flex>
          <v-text-field
            v-model="msg"
            label="chat"
            placeholder="보낼 메세지를 입력하세요."
            solo
            hide-details
            @keyup.13="sendMessage"
          ></v-text-field>
        </v-flex>
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
      dialog:false,
    };
  },
  computed: {
    ...mapState({
      msgDatas: state => state.socket.msgDatas,
      currUser: state => state.data.currUser,
    }),
  },
  mounted() {
    const $ths = this
    console.log("connect chatroom" , window.location.pathname);
    this.getMsg(window.location.pathname.split('/')[2])
    this.$socket.on(window.location.pathname,async (data) => {
      var today = new Date(data.time);
      data.time = today;
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
    async sendMessage() {
      if (this.msg.length === 0) return false;
      var today = new Date();
      this.pushMsgData({
        from: {
          name: this.currUser.uname,
        },
        msg:this.msg,
        time:today,
      });
      await this.$sendMessage({
        rid:window.location.pathname,
        uidx: this.currUser.uidx,
        name: this.currUser.uname,
        msg:this.msg,
        time:today.toString(),
      });
      this.msg = '';
      this.scrollToBottom();
    },

    scrollToBottom(){
      var messageBody = document.getElementById('messageBody');
      messageBody.scrollTop = messageBody.scrollHeight;
    },

    getTime(time){
      let ampm = '';
      let hour = time.getHours();
      let min = time.getMinutes();
      // if(hour > 12){
      //   ampm = 'PM'
      //   hour -= 12;
      // }else{
      //   ampm = 'AM'
      //   if(hour < 10){
      //     hour = "0"+hour
      //   }
      // }
      if(hour < 10){
        hour = "0"+hour
      }
      if(min < 10){
        min = "0"+min;
      }

      return hour +":"+min+" "+ampm
    }
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
.content{
  border-radius: 5px;
  background-color: orange;
  padding: 5px;
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
