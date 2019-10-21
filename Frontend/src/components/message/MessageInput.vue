<template>
  <div class="inner-wrap">
    <v-text-field
      v-model="msg"
      label="chat"
      placeholder="보낼 메세지를 입력하세요."
      solo
      @keyup.13="sendMessageAction"
    ></v-text-field>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  name: 'MessageInput',
  data() {
    return {
      msg: '',
    };
  },
  computed: {
    ...mapState({
       accessToken : state => state.data.accessToken
    }),
  },
  methods: {
    ...mapActions("data", ['sendMessages']),
    async sendMessageAction() {
      let param = {
        uid: this.accessToken,
        text: this.msg,
      }

      if (param.text === '')
        return false;
      
      await this.sendMessages(param)
      this.msg = '';
      return true;
    },
  },
};
</script>
