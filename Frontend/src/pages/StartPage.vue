<template>
  <v-container justify-center fill-height>
      <v-flex xs12 text-center ma-5>
        <h1 style="color:white">
          최고의 영화 추천을 경험하세요
        </h1>
        <p style="color:rgba(255,255,255,0.5)">
         1,000,000개의 데이터를 바탕으로 된 추천 시스템을 경험하세요
        </p>
        <v-btn large rounded :color="index" :to="{name:'login-page'}"><span class="btnText" >시작하기</span></v-btn>
      </v-flex>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  data: () => ({
    url: "",
    index: '',
    isLoad: false,
  }),

  mounted() {

  },

  computed: {

  },
  watch: {

  },
  methods: {

  }
}
</script>
