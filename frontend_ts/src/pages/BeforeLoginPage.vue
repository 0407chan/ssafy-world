<template>
    <div>
        <v-img
            :src="require('@/assets/jaws.jpg')"
            class="my-3"
            contain
        ></v-img>
        <v-btn v-on:click="test"></v-btn>
    </div>
</template>

<script>
import api from '@/api'
import { mapState, mapActions } from "vuex";

export default {
    name: 'BeforeLoginPage',
    components: {

    },
    data() {
      return {

      }
    },
    methods: {
      ...mapActions("data", ['getUser','getRoomByUser']),
      test() {
        console.log("해보자")
        var params = {
          id: 0,
          name: "이찬호",
        }
        var param = 1
        this.getUser(params);
        this.getRoomByUser(param);
      }
    }
};
</script>
