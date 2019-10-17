<template>
  <v-app xs12>
    <Navigation />
    <router-view />
  </v-app>
</template>

<script>
import Navigation from "@/components/Navigation"

export default {
  name: 'App',
  components: {
    Navigation,
  },
  data: () => ({
    //
  }),
  mounted() {

  },
};
</script>
