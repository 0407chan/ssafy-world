<template>
  <div v-show="friend">
    <v-list v-show="friendList.length>0" v-for="i in friendList">
      <v-list-item @click="goTo(i.uidx)">
        <v-list-item-title >
          {{ i.uname }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-list>
      <v-list-item @click.stop="dialog = true">
        <v-list-item-title>
          친구 추가
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-dialog
      v-model="dialog"
      max-width="480">
     <v-card>
        <v-card-title class="headline">친구 추가</v-card-title>
        <v-card-text>
        </v-card-text>
        <v-card-text>
          <!-- 컨텐츠 확인 -->
          <v-text-field
            label="ID"
            v-model='friendUid'
            @keyup='findUser'>
          </v-text-field>
          <v-checkbox v-for="i in searchFriend" :label="i.uname" :value="i.uidx" v-model="selected" />
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="addChatroom()">
            확인
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="dialog = false" >
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex';
import api from '@/api'

export default {
  name: 'FriendList',
  data () {
    return {
      dialog: false,
      friendUid : '',
      searchFriend :[],
      selected : []
    }
  },
  mounted(){
    this.findUser()
  },
  components: {
  },
  computed:{
    //userLogintoken 부분 수정 해야함
    ...mapState({
      friendList: state => state.data.friendList,
      friend : state => state.data.friend
    }),
  },
  methods :{
    ...mapActions('data',['registFriend']),
    addFriend() {
      console.log("친구 추가 구현해야함");
    },
    goTo(friendUidx){
      console.log(friendUidx);
      
    },
    findUser(){
      api.searchUserAll(this.friendUid).then(res=>{
        this.searchFriend=res.data
      })
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
