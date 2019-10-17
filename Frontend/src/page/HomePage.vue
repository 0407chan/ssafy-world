<template>
  <div>
    <Navigation></Navigation>
    <Content></Content>
  </div>
</template>

<script>
import Navigation from "../components/Navigation"
import Content from "../components/Content"

export default {
  name: 'home',
  components: {
    Navigation,
    Content,
  },
  data() {
    return {

    }
  }
};
</script>
