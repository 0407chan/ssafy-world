<template>
  <v-card>
    <v-navigation-drawer
      class="navigation"
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
import Profile from "./profile/Profile"

export default {
  name: 'Friends',
  components: {
      Profile,
  },
  data () {
      return {
        items: [
          { title: '친 구 들', function: '', icon: 'mdi-account' },
          { title: '채 팅 방', function: '', icon: 'mdi-account-group-outline' },
          { title: '방 명 록', function: '', icon: 'mdi-home-city' },
          { title: '게 시 판', function: '', icon: 'mdi-account-group-outline' },
          { title: '공 지 글', function: '', icon: 'mdi-account-group-outline' },
        ],
      }
    },
    methods() {
        
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
