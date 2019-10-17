<template>
  <v-card>
    <v-navigation-drawer
      class="navigation"
      absolute
      permanent
      left
    >
      <Profile />

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
        >
          <div class="menu" right>
            <v-btn class="menu-button" rounded color="primary" dark @click="changeNavigation">
              <v-icon class="button-icon">{{ item.icon }}</v-icon>
              {{ item.title }}
            </v-btn>
          </div>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
import Profile from "./profile/Profile"

export default {
  name: 'Menu',
  components: {

  },
  data () {
      return {
        items: [
          { title: '친 구 들', icon: 'mdi-account' },
          { title: '채 팅 방', icon: 'mdi-account-group-outline' },
          { title: '방 명 록', icon: 'mdi-home-city' },
          { title: '게 시 판', icon: 'mdi-account-group-outline' },
          { title: '공 지 글', icon: 'mdi-account-group-outline' },
        ],
      }
    },
    methods: {
      changeNavigation() {
        this.$store.state.currentNavigation = 3;
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
