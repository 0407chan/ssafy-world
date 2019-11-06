<template>
  <v-container justify-center fill-height >
    <v-flex xs12 text-center>
      <div>
        <h1 style="color:white; font-size:150%">
          SSAFY WORLD에 오신것을 환영합니다.
        </h1>
      </div>
      <div>
        <h4 style="color:rgba(255,255,255,0.5)">
         SSAFY에서만 사용 가능한 사내 메신져를 사용해보세요!
        </h4>
      </div>

      <v-btn large rounded color="orange" @click.stop="dialog = true">
        <span class="btnText" >시작하기</span>
      </v-btn>
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
            <v-text-field
              label="Invite User"
              v-model='insertuid'
            ></v-text-field>
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
    </v-flex>

  </v-container>
</template>

<script>
import { mapState, mapActions,mapMutations  } from 'vuex'
import axios from 'axios'
import Swal from 'sweetalert2'

export default {
  data: () => ({
    user: {
      id:'',
      pw:'',
    },
    dialog: false,
    insertuid :'',
    roomname :''
  }),
  methods: {
    ...mapActions('data', ['registChatroom']),
    ...mapActions('socket', ['getMsg']),
    ...mapMutations('socket', ['clearMsg']),

    addChatroom() {
      let arr = []
      arr.push(this.currUser.uidx)
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
<style>
.loginInputs{
  color:white
}
.btnText{
  color: white;
  font-weight: bold;
}
</style>
