<template>
  <v-app xs12>
    <v-navigation-drawer
      v-model="drawer"
      app
      clipped
    >
      <v-list dense>
        <template v-for="(item, i) in items">
          <v-list-item
             :key="i"
             @click="() => {
               if (item.path) {
                 goTo(item.path)
               }
             }"
           >
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
import Navigation from "@/components/Navigation"

export default {
  name: 'App',
  components: {
    Navigation,
  },
  data () {
    return {
      items: [
        { title: '로 그 인', icon: 'mdi-account', path:"home" },
        { title: '회 원 가 입', icon: 'mdi-account-group-outline', path:"register" },
      ],
      drawer: true,
    }
  },
  methods: {
    goTo: function(path) {
      router.push({ name: path });
    }
  },
  mounted() {

  },
};
</script>
