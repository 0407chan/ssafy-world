<template>
  <v-card>
    <v-navigation-drawer
      class="navigation"
      permanent
      left
    >
      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
        >
          <div class="menu">
            <v-btn class="menu-button" rounded color="primary" dark @click="changeNavigation">
              <v-icon class="button-icon">{{ item.icon }}</v-icon>
              {{ item.title }}
            </v-btn>
          </div>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-btn>
      안녕
    </v-btn>
  </v-card>
</template>

<script>
export default {
  name: 'BeforeLogin',
  components: {

  },
  data () {
      return {
        items: [
          { title: '로 그 인', icon: 'mdi-account' },
          { title: '회 원 가 입', icon: 'mdi-account-group-outline' },
        ],
      }
    },
  methods: {
      changeNavigation() {
        this.$store.state.currentNavigation = 1;
        console.log(this.$store.state.currentNavigation)
      }
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
