<template>
  <v-app xs12>
    <!-- <Navigation /> -->
    <v-navigation-drawer v-model="drawer" app>

      <v-list dense v-show="checkLogin == 0">
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

      <v-list dense v-show="checkLogin == 1">
        <v-list-item @click="() => {goTo('userDetail')}">
          <v-list-item-avatar>
            <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
          </v-list-item-avatar>
          <v-btn @click="logout">로그아웃</v-btn>
        </v-list-item>

        <v-divider></v-divider>

        <template v-for="(item, i) in afterLoginItems">
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

    </v-navigation-drawer>

    <v-btn @click="drawer = !drawer"></v-btn>
    <v-content>
      <router-view />
    </v-content>

  </v-app>
</template>

<script>
import Navigation from '@/components/Navigation'
import { mapActions, mapState, mapMutations } from 'vuex';

export default {
  name: 'App',
  components: {
    Navigation,
  },
  data () {
    return {
      beforeLoginItems: [
        { title: '로 그 인', icon: 'mdi-account', path:"main"},
        { title: '회 원 가 입', icon: 'mdi-account-group-outline', path:"register" },
      ],
      afterLoginItems: [
        { title: '친 구 들', icon: 'mdi-account', path:"main"},
        { title: '채 팅 방', icon: 'mdi-account-group-outline', path:"" },
        { title: '방 명 록', icon: 'mdi-account-group-outline', path:"" },
        { title: '게 시 판', icon: 'mdi-account-group-outline', path:"" },
        { title: '공 지 사 항', icon: 'mdi-account-group-outline', path:"" },
      ],
      drawer: true,
      logincheck: false,
      first : 0
    }
  },
  computed :{
    ...mapState('data',['userLoginToken:','userLoginPassword','checkLogin']),
  },
  mounted(){
    let params = {
      'id' : sessionStorage.getItem('id'),
      'pw' : sessionStorage.getItem('pw')
    }
    console.log(params);

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

    if(params.id !=null && params.pw != null)
      this.login(params).then(res=>{
        if(res.data==='LOGIN SUCCESS'){
          this.$router.push({name : 'chatroom'})
        }
      })

    //로그인 여부 확인
    if (this.userLoginToken == '' || this.userLoginPassword=='')
      this.first= 0;
    else
      this.first= 1;
  },
  methods: {
    ...mapMutations('data',['setMenu','clearUser']),
    goTo(path) {
      console.log(path);
      this.$router.push({ name: path });
    },
    ...mapActions('data',['login']),
    logout() {
      this.clearUser()
      this.setMenu(0);
      sessionStorage.removeItem('id')
      sessionStorage.removeItem('pw')
      console.log("check");


      this.$router.push({ name: 'main' });
    },
  },
}
</script>
