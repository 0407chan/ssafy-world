<template>
  <v-navigation-drawer v-model="drawer" app>
    <v-list dense v-show="currUser == ''">
      <BeforeLogin />
    </v-list>
    <v-list dense v-show="currUser != ''">
      <v-divider></v-divider>
      <AfterLogin />
    </v-list>

    
  </v-navigation-drawer>
</template>

<script>
import {
  mapState
} from 'vuex'
import Profile from '@/components/navigations/profile/Profile'
import BeforeLogin from '@/components/navigations/BeforeLogin'
import AfterLogin from '@/components/navigations/AfterLogin'

export default {
  name: 'Navigation',
  components: {
    Profile,
    BeforeLogin,
    AfterLogin,
  },
  computed: {
    ...mapState({
      currUser: state => state.data.currUser,
    }),
  },
  methods:{
    goTo(path) {
      this.$router.push({name: path})
    },
  }
};
</script>
