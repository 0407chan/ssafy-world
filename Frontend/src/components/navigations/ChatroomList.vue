<template>
  <v-list-item v-show="friend">
    <v-list-item-title>
      {{ afterLoginItems[1].title }}
    </v-list-item-title>
  </v-list-item>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex';
import api from '@/api'

export default {
  name: 'ChatroomList',
  components: {

  },
  data () {
      return {
        items: [],
      }
    },
    mounted(){
      items = api.
    },
    computed(){

    },
    methods: {

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
