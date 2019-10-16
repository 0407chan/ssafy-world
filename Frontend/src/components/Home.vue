<template>
  <div>
    <div class="navigation">
      <Navigation />
    </div>
    <div class="content">
      <Content />
    </div>
  </div>
</template>

<script>
import Navigation from "./Navigation"
import Content from "./Content"

export default {
  name: 'Home',
  components: {
    Navigation,
    Content,
  },
  data () {
      
  },
};
</script>

<style scoped>
  .navigation {
    width: 30%;
  }
  .content {
    width: 70%;
    float: right;
  }
</style>
