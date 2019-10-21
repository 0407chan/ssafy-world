<template>
  <div class="inner-wrap" fluid fill-height inner-wrap>
    <MessageList :msgs="messages" class="msg-list"></MessageList>
    <MessageInput v-on:submitMessage="sendMessage" class="msg-form" ></MessageInput>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import MessageList from '@/components/message/MessageList'
import MessageInput from '@/components/message/MessageInput'

export default {
  name: 'ChatRoomPage',
  data() {
    return {
      sendMessage: '',
    };
  },
  components: {
    MessageList,
    MessageInput,
  },
  mounted() {
    this.messageAction()
  },
  computed: {
    ...mapState({
      messages: state => state.data.messageList,
    }),
  },
  methods: {
    ...mapActions("data", ['getMessages']),
    async messageAction() {
      await this.getMessages()
    }
  }
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
