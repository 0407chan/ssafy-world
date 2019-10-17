<template>
  <div>
    <div xs8>
      <BeforeLogin v-if="navigationPage == 0" />
      <Navigation v-else-if="navigationPage == 1" />
      <FriendList v-else-if="navigationPage == 2" />
    </div>

    <div xs4>
      <Content />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex"
import BeforeLogin from "../components/navigations/BeforeLogin"
import Menu from "../components/navigations/Menu"
import Friends from "../components/navigations/Friends"
import Content from "../components/contents/Content"

export default {
  name: 'home',
  components: {
    BeforeLogin,
    Menu,
    Friends,
    Content,
  },
  data() {
    return {

    }
  },
  computed: {
    ...mapState({
      navigationPage: state => state.data.currentNavigation,
    }),
  },
  mounted() {
    console.log(this.$store.state.currentNavigation);
    console.log(this.navigationPage);
  }
};
</script>
