<template>
  <div v-show="friend">
    <v-list v-for="i in friendList">
      <v-list-item @click="goTo(i.uidx)">
        <v-list-item-title >
          {{ i.uname }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-list>
      <v-list-item @click="addFriend">
        <v-list-item-title>
          친구 추가
        </v-list-item-title>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex';
import api from '@/api'

export default {
  name: 'FriendList',
  components: {

  },
  computed:{
    //userLogintoken 부분 수정 해야함
    ...mapState('data', ['friend', 'friendList'])
  },
  methods :{
    addFriend() {
      console.log("친구 추가 구현해야함");
    }
  }
};
</script>

<style scoped>
  .navigation {
    background: rgba(0, 0, 0, 0);
    display: inline-block;
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
