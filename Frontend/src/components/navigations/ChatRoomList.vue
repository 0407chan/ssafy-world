<template>
  <div v-show="chatlist">
    <v-list v-for="i in chatroomList">
      <v-list-item @click="goTo(i.ridx)">
        <v-list-item-title>
          {{ i.rname }}
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-list>
      <v-list-item @click.stop="dialog = true">
        <v-list-item-title>
          Create Chenal
        </v-list-item-title>
      </v-list-item>
    </v-list>
    <v-dialog
      v-model="dialog"
      max-width="480">
     <v-card>
        <v-card-title class="headline">Create Chenal</v-card-title>
        <v-card-text>
        </v-card-text>
        <v-card-text>
          <!-- 컨텐츠 확인 -->
          <v-text-field
            label="Room Name"
            v-model='roomname'
          ></v-text-field>
          <!-- <v-text-field
            label="Invite User"
            v-model='insertuid'
          ></v-text-field> -->
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
import { mapActions, mapState, mapMutations } from 'vuex'
import api from '@/api'
import MakeChatRoom from '@/components/room/MakeChatRoom'

export default {
  name: 'ChatRoomList',
  data () {
      return {
        dialog: false,
        roomname :''
      }
    },
  components: {
    MakeChatRoom,
  },
  computed:{
    //userLogintoken 부분 수정 해야함
    ...mapState('data', ['chatlist', 'chatroomList','currUser']),
    ...mapState('socket', ['msgDatas']),
  },
  methods: {
    ...mapActions('data', ['registChatroom']),
    ...mapActions('socket', ['getMsg']),
    ...mapMutations('socket', ['clearMsg']),
    addChatroom() {
      console.log(this.insertuid);
      console.log(this.roomname);
      let arr = []
      arr.push(this.currUser.uidx)
      console.log(arr);
      // 룸 생성
      // 룸 참가
      this.createRoom(this.roomname)
      // 룸 최신화
      this.getRoomList()
      this.dialog=false
      this.roomname=''
      this.insertuid=''
    },
    async goTo(rid) {
      // await this.clearMsg();
      // await this.getMsg(rid);
      this.$router.push('/chatroom/'+rid)
    },
    //디비에서 내가 접속한 방들 다 가져옴
     getRoomList(){
       this.registChatroom(this.currUser.uidx)
     },
     //룸 생성, 참가
     createRoom(roomname){
       api.postRoom(roomname).then(res=>{
         let rid = res.data
         this.$socket.emit('create',{
           ridx : rid,
           rname : roomname
           })
          this.chatroomList.push({
            ridx : rid,
            rname : roomname
            })

          api.postEnterRoom(this.currUser.uidx,rid).then(res=>{
            this.registChatroom()
          })
       })
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
