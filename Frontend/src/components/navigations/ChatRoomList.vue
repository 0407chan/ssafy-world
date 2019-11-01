<template>
  <div v-show="chatlist">
    <v-list v-for="i in chatroomList">
      <v-list-item @click="goTo(i.rid)">
        <v-list-item-title>
          {{ i.rname }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-list>
      <MakeChatRoom />
      <v-list-item @click="enterChatroom">
        <v-list-item-title />
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex'
import api from '@/api'
import MakeChatRoom from '@/components/room/MakeChatRoom'

export default {
  name: 'ChatRoomList',
  data() {
    return {
      
    }
  },
  components: {
    MakeChatRoom,
  },
  computed:{
    //userLogintoken 부분 수정 해야함
    ...mapState('data', ['chatlist', 'chatroomList']),
    ...mapState('socket', ['msgDatas'])
  },
  methods : {
    ...mapActions('socket', ['getMsg']),
    ...mapMutations('socket', ['clearMsg']),
    enterChatroom() {
      console.log("구현해야함");
    },
    async goTo(rid) {
      await this.clearMsg();
      await this.getMsg(rid);
      this.$router.push('/chatroom/'+rid)
    }
  }
};
</script>

<style scoped>
  .navigation {
    background: rgba(0, 0, 0, 0);
  }
  .menu {
    margin-top: 50px;
  }
  .menu-button {
    margin: 10px;
    width: 200px;
  }
  .button-icon {
    margin-right: 10px;
  }
</style>
