<template>
  <v-list>
    <div v-for="(msg, index) in msgs.data" v-bind:key="index">
      <span>{{msg.uid}}</span>: <span>{{msg.text}}</span>
      <v-divider></v-divider>
    </div>
  </v-list>
</template>

<script>
export default {
  name: 'MessageList',
  props: ['msgs'],
};
</script>

<style>
.list-item {
  display: inline-block;
  margin-right: 10px;
}
.list-enter-active, .list-leave-active {
  transition: all 1s;
}
.list-enter, .list-leave-to /* .list-leave-active below version 2.1.8 */ {
  opacity: 0;
  transform: translateX(30px);
}
</style>
