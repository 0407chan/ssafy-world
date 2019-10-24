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
      //아이디 vuex 링크 잡기
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
<<<<<<< HEAD
        name: this.$store.state.data.userLoginToken + ": ",
=======
        //가져온 vuex 아이디로 name을 변경하면 됌
        name: this.$route.params.username,
>>>>>>> b0836d0a509d573cf37d85befc60bdbaaa6ad577
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