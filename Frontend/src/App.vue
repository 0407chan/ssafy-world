<template>
  <v-app xs12>
    <v-navigation-drawer v-model="drawer" app>
      <v-list dense v-show="currUser !=''">
        <template v-for="(item, i) in beforeLoginItems">
          <v-list-item :key="i" @click="() => { if (item.path) { goTo(item.path) } }">
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-title>
              {{ item.title }}
            </v-list-item-title>
          </v-list-item>
        </template>
      </v-list>

      <v-list dense v-show="currUser !='' ">
        <v-list-item @click="() => {goTo('userDetail')}">
          <v-list-item-avatar>
            <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
          </v-list-item-avatar>
        </v-list-item>
        <v-list-item>
          <v-btn @click="logout">로그아웃</v-btn>
        </v-list-item>
        <v-divider></v-divider>

        <v-list-item @click="()=>{if(afterLoginItems[0].path) {goTo(afterLoginItems[0].path)}}">
          <v-list-item-action >
            <v-icon>{{ afterLoginItems[0].icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-title>
            {{ afterLoginItems[0].title }}
          </v-list-item-title>
        </v-list-item>
        <!-- 친구 목록 열기 -->
        <v-list-item @click="reverse('friend')">
          <v-list-item-action>
            <v-icon>{{ afterLoginItems[1].icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-title>
            {{ afterLoginItems[1].title }}
          </v-list-item-title>
        </v-list-item>
        <FriendList />
        <!-- 대화방 열기 -->
        <v-list-item @click="reverse('chatlist')">
          <v-list-item-action>
            <v-icon>{{ afterLoginItems[2].icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-title>
            {{ afterLoginItems[2].title }}
          </v-list-item-title>
        </v-list-item>
        <ChatroomList />
      </v-list>

    </v-navigation-drawer>

    <v-btn @click="drawer = !drawer"></v-btn>
    <v-content>
      <router-view />
    </v-content>

  </v-app>
</template>

<script>
import ChatroomList from '@/components/navigations/ChatroomList'
import FriendList from '@/components/navigations/FriendList'
import { mapActions, mapState, mapMutations } from 'vuex';

export default {
  name: 'App',
  components: {
    ChatroomList,
    FriendList
  },
  data () {
    return {
      beforeLoginItems: [
        { title: '로 그 인', icon: 'mdi-account', path:"main"},
        { title: '회 원 가 입', icon: 'mdi-account-group-outline', path:"register" },
      ],
      afterLoginItems: [
        { title: '일 정 표', icon: 'mdi-calendar', path:"" },
        { title: '친 구 들', icon: 'mdi-account', path:"chatroom"},
        { title: '채 팅 방', icon: 'mdi-account-group-outline', path:"" },
      ],
      drawer: true,
      logincheck: false,
    }
  },
  computed :{
    ...mapState('data',['friend','chatlist']),

    ...mapState({
      currUser: state => state.data.currUser,
    }),
  },
  created(){
    //이미 접속한 이력이 있을 경우
    this.$socket.on('check',(data)=>{
      console.log(data.msg)
      if(data.msg==='already connect'){
        if(confirm("이미 접속한 브라우져가 존재합니다. \n 지금 브라우져를 사용하시겠습니까?")){
          this.$socket.emit('change',{
            msg : 'yes'
          })
        }else{
          this.$socket.emit('change',{
            msg : 'no'
          })
        }
      }
    })
    this.$socket.on('disc',(data)=>{
      this.$socket.disconnect()
    })

    // if (this.$session.has("token")) {
    //   this.$router.push({name : 'chatroom'})
    // }

    // let params = {
    //   'id' : sessionStorage.getItem('id'),
    //   'pw' : sessionStorage.getItem('pw')
    // }
    // console.log(params);
    // if(params.id !=null && params.pw != null)
    //   this.login(params).then(res=>{
    //     if(res.status=='200'){
    //       this.$router.push({name : 'chatroom'})
    //     }
    // })

  },
  methods: {
    ...mapMutations('data',['reverse']),
    ...mapActions('data',['login']),
    goTo(path) {
      console.log(path);
      this.$router.push({ name: path });
    },

    /* 2019.10.29 이찬호
    기능 : logout , session 삭제, currUser 삭제
    */
    logout() {
      console.log(this.$session.get("token"));
      if (this.$session.has("token")) {
        console.log("있다")
        this.$session.destroy();
        this.$store.state.currUser = ''
      }
      console.log("check");
      this.$router.push({ name: 'login' });
    },
  },
  destroyed(){
    this.$socket.emit('disconnect',{
      msg : 'disconnect'
    })
    this.logout()
  }
}
</script>
