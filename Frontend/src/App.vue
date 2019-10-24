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
        { title: '채팅', icon: 'mdi-account-group-outline', path:"chatroompage" },
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
  computed :{  
    first : function() {
      if (this.$store.state.data.userLoginToken === '')
        return 0;
      else
        return 1;
    },
  },
  methods: {
    goTo: function(path) {
      this.$router.push({ name: path });
    },
  },
}
</script>
