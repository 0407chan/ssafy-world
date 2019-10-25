<template>
  <v-app xs12>
    <!-- <Navigation /> -->
    <v-navigation-drawer v-model="drawer" app>

      <v-list dense v-show="first == 0">
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

      <v-list dense v-show="first == 1">
        <v-list-item>
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

export default {
  name: 'App',
  components: {
    Navigation,
  },
  data () {
    return {
      beforeLoginItems: [
        { title: '로 그 인', icon: 'mdi-account', path:"home"},
        { title: '회 원 가 입', icon: 'mdi-account-group-outline', path:"register" },
      ],
      afterLoginItems: [
        { title: '친 구 들', icon: 'mdi-account', path:"home"},
        { title: '채 팅 방', icon: 'mdi-account-group-outline', path:"register" },
        { title: '방 명 록', icon: 'mdi-account-group-outline', path:"register" },
        { title: '게 시 판', icon: 'mdi-account-group-outline', path:"register" },
        { title: '공 지 사 항', icon: 'mdi-account-group-outline', path:"register" },
      ],
      drawer: true,
      logincheck: false
    }
  },
  mounted() {
    this.$store.state.data.userLoginToken = sessionStorage.getItem('uid')
    console.log(sessionStorage.getItem('uid'))
  },
  computed :{  
    first : function() {
      if (this.$store.state.data.userLoginToken == '' || this.$store.state.data.userLoginToken == null)
        return 0;
      else
        return 1;
    },
  },
  methods: {
    goTo(path) {
      this.$router.push({ name: path });
    },
    logout() {
      sessionStorage.removeItem('uid');
      this.$store.state.data.userLoginToken = '';
      this.$router.push({ name: 'main' });
    },
  },
}
</script>
