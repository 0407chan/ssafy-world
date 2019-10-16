<template>
  <v-card>
    <v-navigation-drawer
      class="navigation"
      absolute
      permanent
      left
    >
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/women/81.jpg">
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>Jane Smith</v-list-item-title>
            <v-list-item-subtitle>Logged In</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
        >
          <div class="menu" right>
            <router-link v-bind:to="item.route">
              <v-btn class="menu-button" rounded color="primary" dark>
                <v-icon class="button-icon">{{ item.icon }}</v-icon>
                {{ item.title }}
              </v-btn>
            </router-link>
          </div>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  name: 'Navigation',
  components: {

  },
  data () {
      return {
        items: [
          { title: '친 구 들', route: '/home', icon: 'mdi-account' },
          { title: '채 팅 방', route: '/hello_world', icon: 'mdi-account-group-outline' },
          { title: '방 명 록', route: '/hello_world', icon: 'mdi-home-city' },
          { title: '게 시 판', route: '/hello_world', icon: 'mdi-account-group-outline' },
          { title: '공 지 글', route: '/hello_world', icon: 'mdi-account-group-outline' },
        ],
      }
    },
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
