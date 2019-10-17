<template>
  <div>
    <div xs8>
      <BeforeLogin v-if="navigationPage == 0" />
      <Navigation v-else-if="navigationPage == 1" />
      <FriendList v-else-if="navigationPage == 2" />
    </div>

    <div xs4>
      <BeforeLoginContent v-if="contentPage == 0" />
      <FriendChatRoom v-else-if="contentPage == 1" />
      <GroupChatRoom v-else-if="contentPage == 2" />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex"
import BeforeLogin from "../components/navigations/BeforeLogin"
import Menu from "../components/navigations/Menu"
import Friends from "../components/navigations/Friends"
import BeforeLoginContent from "../components/contents/BeforeLoginContent"
import FriendChatRoom from "../components/contents/FriendChatRoom"
import GroupChatRoom from "../components/contents/GroupChatRoom"

export default {
  name: 'home',
  components: {
    BeforeLogin,
    Menu,
    Friends,
    BeforeLoginContent,
    FriendChatRoom,
    GroupChatRoom,
  },
  data() {
    return {

    }
  },
  computed: {
    ...mapState({
      navigationPage: state => state.data.currentNavigation,
      contentPage: state => state.data.currentContent,
    }),
  },
  mounted() {
    console.log(this.$store.state.currentNavigation);
    console.log(this.navigationPage);
  }
};
</script>
