<template>
  <div v-show="chatlist">
    <v-list v-for="i in items">
      <v-list-item @click="goTo(i.path)">
        <v-list-item-title>
          {{ i.name }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-list>
      <v-list-item @click="addChatroom">
        <v-list-item-title>
          추가 하기
        </v-list-item-title>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex';
import api from '@/api'

export default {
  name: 'ChatRoomList',
  components: {

  },
  data () {
    return {
      items: [],
    }
  },
  computed:{
    //userLogintoken 부분 수정 해야함
    ...mapState('data',['userLoginToken' , 'userLoginPassword','chatlist'])
  },
  mounted(){
    // this.api.getUserByRoom(this.userLoginToken).then(res=>{
    //   items.push(res.data);
    // })
    // this.items.push({
    //   'name': 'test',
    //   'path': '/chatroom/1'
    // })
  },
  methods :{
    addChatroom(){
      console.log("구현해야함");
    },
    goTo(rid){
      console.log(rid);
      
      this.$router.push(rid)
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
