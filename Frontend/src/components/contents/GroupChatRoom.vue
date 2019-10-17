<template>
    
</template>

<script>
import api from '@/api'
import { mapState, mapActions } from "vuex";

export default {
    name: 'GroupChatRoom',
    components: {

    },
    data() {
      return {

      }
    },
    methods: {
      ...mapActions("data", ['getUser']),
      test() {
        console.log("해보자")
        var params = {
          id: 0,
          name: "이찬호",
        }
        this.getUser(params);
      }
    }
};
</script>
