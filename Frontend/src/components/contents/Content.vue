<template>
    <div>
        <v-img
            :src="require('@/assets/jaws.jpg')"
            class="my-3"
            contain
            height="200"
        ></v-img>
        <v-btn v-on:click="test"></v-btn>
    </div>
</template>

<script>
import api from '@/api'
import { mapState, mapActions } from "vuex";

export default {
    name: 'Content',
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
