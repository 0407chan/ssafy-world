<template>
  <div v-show="friend">
    <v-list v-for="i in items">
      <v-list-item @click="goTo(i.path)">
        <v-list-item-title >
          {{ i.uid }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-list>
      <v-list-item @click="addFriend">
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
  name: 'FriendList',
  components: {

  },
  data () {
    return {
      items: [],
    }
  },
  computed:{
    //userLogintoken 부분 수정 해야함
    ...mapState('data',['userLoginToken' , 'userLoginPassword','friend'])
  },
  mounted(){
    api.postFriend(sessionStorage.getItem('id')).then(res=>{

      for(let i=0;i<res.data.length;i++){
        this.items.push({
          'uid' : res.data[i],
        });
      }
      console.log(res);
      
    }).catch(error=>{
      console.log("test");
      
    });
    
  },
  methods :{
    addFriend(){
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
