<template>
  <v-container fluid>
    <v-layout row>
      <!-- <div class="message" v-for="(message,index) in msgs" :class="{own: message.from.name == username}"> -->
      <v-col cols="12" id="messageBody" class="scrollable" no-gutters>
        <v-flex class="message-line" v-for="(message, index) in msgDatas">
          <v-col no-gutters>
            <v-row align="center">
              <div>
                <v-avatar tile v-if=" (index-1 >= 0 && msgDatas[index-1].from.name != message.from.name) || index == 0">
                  <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
                </v-avatar>
              </div>
              <div class="message">
                <div v-if=" (index-1 >= 0 && msgDatas[index-1].from.name != message.from.name) || index == 0">
                  <span class="username">
                    {{message.from.name}}
                  </span>
                  <span v-if="message.time">
                    {{getTime(message.time)}}
                  </span>
                </div>
                <div class="content">
                  {{message.msg}}
                  <!-- <chat-image v-if="message.image" :imgsrc="message.image" @imageLoad="imageLoad"></chat-image> -->
                </div>
              </div>
              <div v-if="message.time && index > 0" :class="{time: index-1 >= 0 && message.time.getMinutes() == msgDatas[index-1].time.getMinutes()}">
                {{getTime(message.time)}}
              </div>
            </v-row>
          </v-col>
        </v-flex>
      </v-col>
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
    </v-layout>
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
    };
  },
  computed: {
    ...mapState({
      'msgDatas': state => state.socket.msgDatas,
      //아이디 vuex 링크 잡기
    }),
    ...mapState('data',['currUser'])
  },
  mounted() {
    const $ths = this
    console.log("connect chatroom" , window.location.pathname);
    this.getMsg(window.location.pathname.split('/')[2])
    this.$socket.on(window.location.pathname, (data) => {
      var today = new Date(data.time);
      data.time = today;
      this.pushMsgData(data)
    });
  },
  methods: {
    ...mapActions('socket', ['getMsg']),
    ...mapMutations('socket',['pushMsgData']),

    sendMessage() {
      if (this.msg.length === 0) return false;
      var today = new Date();
      this.pushMsgData({
        from: {
          name: this.currUser.uid,
        },
        msg:this.msg,
        time:today,
      });
      this.$sendMessage({
        rid:window.location.pathname,
        name: this.currUser.uid,
        msg:this.msg,
        time:today.toString(),
      });
      this.msg = '';
      this.scrollToBottom();
    },

    test(){
      console.log(this.msgs);
    },

    scrollToBottom(){
      var messageBody = document.getElementById('messageBody');
      messageBody.scrollTop = messageBody.scrollHeight;
    },

    getTime(time){
      let ampm = '';
      let hour = time.getHours();
      let min = time.getMinutes();
      if(hour > 12){
        ampm = 'PM'
        hour -= 12;
      }else{
        ampm = 'AM'
        if(hour < 10){
          hour = "0"+hour
        }
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
</style>
