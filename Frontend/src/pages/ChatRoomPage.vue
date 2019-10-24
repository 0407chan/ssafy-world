<template>
  <v-layout class="inner-wrap" fluid fill-height inner-wrap>
    <MessageList :msgs="msgDatas" class="msg-list" />
    <MessageInput v-on:submitMessage="sendMessage" class="msg-form" />
  </v-layout>
</template>

<script>
import { mapMutations, mapState, mapActions } from 'vuex'
import Constant from '@/Constant'
import MessageList from '@/components/message/MessageList.vue'
import MessageInput from '@/components/message/MessageInput.vue'

export default {
  name: 'ChatRoomPage',
  data() {
    return {
      datas: [],
    };
  },
  components: {
    MessageList,
    MessageInput,
  },
  computed: {
    ...mapState({
      'msgDatas': state => state.socket.msgDatas,
    }),
  },
  created() {
    if (this.$store.state.data.userLoginToken === '')
      this.$router.push({ name: 'main' })
    
    const $ths = this
    this.$socket.on('chat', (data) => {
      this.pushMsgData(data)
      $ths.datas.push(data)
    });
  },
  methods: {
    ...mapMutations({
      'pushMsgData': Constant.PUSH_MSG_DATA,
    }),
    sendMessage(msg) {
      this.pushMsgData({
        from: {
          name: this.$store.state.data.userLoginToken + ": ",
        },
        msg,
      });
      this.$sendMessage({
        name: this.$store.state.data.userLoginToken + ": ",
        msg,
      });
    },
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
.msg-list {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 60px;
  overflow-x: scroll;
}
</style>