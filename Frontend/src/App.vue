<template>
<v-app xs12>
  <!-- <Navigation /> -->
  <template v-if="windows.width < 600">
    <v-navigation-drawer v-model="drawer" disable-resize-watcher app>
      <v-list dense v-show="currUser == ''">
        <BeforeLogin />
      </v-list>
      <v-list dense v-show="currUser != ''">
        <v-divider></v-divider>
        <AfterLogin />
      </v-list>
    </v-navigation-drawer>
  </template>
  <template v-else>
    <v-navigation-drawer v-model="drawer" disable-resize-watcher app permanent>
      <v-list dense v-show="currUser == ''">
        <BeforeLogin />
      </v-list>
      <v-list dense v-show="currUser != ''">
        <v-divider></v-divider>
        <AfterLogin />
      </v-list>
    </v-navigation-drawer>
  </template>

  <!-- <Header></Header> -->
  <v-app-bar id="headerBar" app clipped-left color="blue" elevation="0">
    <template v-if="windows.width < 600">
      <v-btn icon @click="drawer = !drawer">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-btn>
    </template>

    <v-spacer />

    <div v-if="currUser">
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn dark icon v-on="on">
            <v-avatar>
              <img :src="currUser.img">
            </v-avatar>
          </v-btn>
        </template>

        <v-list>
          <v-list-item>
            <v-list-item-avatar>
              <v-img v-if="currUser.img" :src="currUser.img"></v-img>
              <v-icon v-else>mdi-account</v-icon>
            </v-list-item-avatar>
            <v-list-item-title>{{ currUser.uname }} 님</v-list-item-title>
          </v-list-item>

          <v-divider></v-divider>

          <v-list-item @click="goTo('user')">
            <v-list-item-avatar>
              <v-icon >mdi-account</v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Profile</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>
          <v-list-item @click="logout">
            <v-list-item-avatar>
              <v-icon> mdi-logout </v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Logout</v-list-item-title>
            </v-list-item-content>

          </v-list-item>
        </v-list>
      </v-menu>
    </div>
  </v-app-bar>


  <v-content>
    <router-view />
  </v-content>
</v-app>
</template>

<script>
import Navigation from '@/components/Navigation'

import BeforeLogin from '@/components/navigations/BeforeLogin'
import AfterLogin from '@/components/navigations/AfterLogin'
import {
  mapState,
  mapActions,
  mapMutations,
} from 'vuex'

export default {
  name: 'App',
  components: {
    BeforeLogin,
    AfterLogin,
    // Navigation,
  },
  data() {
    return {
      drawer: true,

      windows: {
        width: 0,
        height: 0
      },
    };
  },
  computed: {
    ...mapState({
      currUser: state => state.data.currUser,
    }),

  },
  methods: {
    ...mapActions('data', ['refresh']),
    ...mapMutations('data',['clearUser']),

    handleResize() {
      this.windows.width = window.innerWidth;
      this.windows.height = window.innerHeight;
    },

    goTo(path) {
      this.$router.push({
        name: path
      });
    },

    /* 2019.11.01 이찬호
    기능 : 로그아웃
     - 세션 끊기
     - store에 currUser 초기화하기
     - login page로 돌아가기
    */
    logout() {
        this.clearUser()
        this.$session.destroy();
        this.$store.state.currUser = ''
        this.$router.push({ name: 'login' });
    },
  },

  created() {
    window.addEventListener('resize', this.handleResize)
    this.handleResize();
  },

  mounted() {
    this.$session.start()
    let token = this.$session.get('token')
    this.refresh(token)
  },
  destroyed() {
    this.$socket.emit('disconnect', {
        msg: 'disconnect'
      }),
      window.removeEventListener('resize', this.handleResize)
  },
}
</script>
