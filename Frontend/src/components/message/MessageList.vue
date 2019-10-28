<template>
  <v-layout row>
    <!-- <div class="message" v-for="(message,index) in msgs" :class="{own: message.from.name == username}"> -->
    <v-btn @click="test"></v-btn>
    <v-col cols="12" no-gutters>
      <v-flex class="message-line" v-for="(message,index) in msgs">
        <v-col no-gutters>
          <v-row align="center" >
            <div>
              <v-avatar tile v-if=" (index-1 >= 0 && msgs[index-1].from.name != message.from.name) || index == 0">
                <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
              </v-avatar>
            </div>
            <div class="message">
              <div v-if=" (index-1 >= 0 && msgs[index-1].from.name != message.from.name) || index == 0">
                <span class="username">
                  {{message.from.name}}
                </span>
                <span>
                  {{message.time.getHours() +":"+message.time.getMinutes() }}
                </span>
              </div>
              <div class="content">
                {{message.msg}}
                <!-- <chat-image v-if="message.image" :imgsrc="message.image" @imageLoad="imageLoad"></chat-image> -->
              </div>
            </div>
            <div class="time">
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

  <!-- <v-list v-auto-bottom="msgs">
    <transition-group name="list" >
      <div v-for="(msg,index) in msgs" v-bind:key="index">
        <v-list>
            <v-list-item-avatar>
              <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
            </v-list-item-avatar>
            <div style="display: inline-block;">
                {{msg.from.name}}
                <br>
                {{msg.msg}}
            </div>
        </v-list>
      </div>
    </transition-group>
  </v-list> -->
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
      return true;
    },
  }
};
</script>

<style>

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
