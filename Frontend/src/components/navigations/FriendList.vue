<template>
  <div v-show="friend">
    <v-list v-show="friendList.length>0" v-for="i in friendList">
      <v-list-item @click="profile(i.uidx)">
        <v-list-item-title >
          {{ i.uname }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-list>
      <v-list-item @click="search()">
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
            @click="addFriend()">
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
    <FriendProfile :user ="selectedUser" :display="selectedDisplay" />
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex';
import api from '@/api'
import FriendProfile from '@/components/friend/FriendProfile'

export default {
  name: 'FriendList',
  data () {
    return {
      dialog: false,
      friendUid : '',
      searchFriend :[],
      selected : [],
      selectedDisplay : false,
      selectedUser : '',
    }
  },
  components: {
    FriendProfile
  },mounted(){
    this.selectedUser = this.currUser
  },
  computed:{
    //userLogintoken 부분 수정 해야함
    ...mapState({
      friendList: state => state.data.friendList,
      friend : state => state.data.friend,
      currUser : state => state.data.currUser
    }),
  },
  methods :{
    ...mapActions('data',['registFriend']),
    search(){
      this.dialog=true
      this.selectedDisplay=false
      this.findUser()
    },
    async addFriend() {
      for(let i=0;i<this.selected.length;i++){
        await api.postAddFriend(this.currUser.uidx,this.selected[i])
      }
      this.dialog=false
      this.selected = []
      this.registFriend()
    },
    profile(friendUidx){
      api.getUserInfo(friendUidx).then(res=>{
        this.selectedUser= res.data
        this.selectedDisplay=true
      })
    },
    findUser(){
      api.searchUserAll(this.friendUid).then(res=>{
        let data = []
        for(let i = 0;i<res.data.length;i++){
          if(res.data[i].uidx!=this.currUser.uidx){
            let flag = true
            for(let l =0;l<this.friendList.length;l++){
              if(res.data[i].uidx==this.friendList[l].uidx){
                flag = false
                break;
              }
            }
            if(flag==true)
              data.push(res.data[i])
          }
        }
        this.selected = []
        this.searchFriend=data
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
