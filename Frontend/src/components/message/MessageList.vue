<template>
  <v-layout row >
    <!-- <div class="message" v-for="(message,index) in msgs" :class="{own: message.from.name == username}"> -->
    <v-col cols="12" id="messageBody" class="scrollable" no-gutters>
      <v-flex class="message-line" v-for="(message,index) in msgs" >
        <v-col no-gutters>
          <v-row align="center">
            <div>
              <v-avatar tile v-if=" (index-1 >= 0 && msgs[index-1].user.uname != message.user.uname) || index == 0">
                <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
              </v-avatar>
            </div>
            <div class="message">
              <div v-if=" (index-1 >= 0 && msgs[index-1].user.uname != message.user.uname) || index == 0">
                <span class="username">
                  {{message.user.uname}}
                </span>
                <span v-if="message.time">
                  {{message.time.getHours() +":"+message.time.getMinutes() }}
                </span>
              </div>
              <div class="content">
                {{message.msg}}
                <!-- <chat-image v-if="message.image" :imgsrc="message.image" @imageLoad="imageLoad"></chat-image> -->
              </div>
            </div>
            <div class="time" v-if="message.time">
              {{message.time.getHours() +":"+message.time.getMinutes() }}
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
          @keyup.13="submitMessageFunc"
        ></v-text-field>
      </v-flex>
    </v-col>
  </v-layout>

</template>

<script>
export default {
  name: 'MessageList',
  props: ['msgs'],
  data() {
    return {
      msg: '',
    };
  },
  methods: {
    test(){
      console.log(this.msgs);
    },

    submitMessageFunc() {
      if (this.msg.length === 0) return false;
      this.$emit('submitMessage', this.msg);
      this.msg = '';
      this.scrollToBottom();
      return true;
    },

    scrollToBottom(){
      var messageBody = document.getElementById('messageBody');
      console.log(messageBody.scrollTop)
      messageBody.scrollTop = messageBody.scrollHeight - messageBody.clientHeight;
      console.log(messageBody.scrollTop)
    },

  }
};
</script>
