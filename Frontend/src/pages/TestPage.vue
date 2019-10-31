<template>
    <v-container fluid my-12>
        <v-btn @click="test('q17Eg1P4GkZ3YhTx5Hx6')"> test </v-btn>
        <v-btn @click="createTest()"> create </v-btn>
        <v-btn @click="addMessage('q17Eg1P4GkZ3YhTx5Hx6')"> create </v-btn>
    </v-container>
</template>

<script>
import firebaseMy from '@/plugins/FirebaseService'
import { mapState } from 'vuex';

export default {
  data() {
    return {
      
    }
  },
  computed :{
    ...mapState('data',['currUser'])
  },
  methods: {
    test(id){
      // this.$firebaseMy.getRoomInfo('sgd')
      firebaseMy.getRoomInfo(id).then(res=>{
        console.log(res);
      })
    },
    createTest(){
      // this.$firebaseMy.getRoomInfo('sgd')
      firebaseMy.createRoom(this.currUser.uidx,"안녕하세요").then(res=>{
        //룸 아이디를 받았다
        console.log(res);
      })
    },
    addMessage(id,message,time){
      // this.$firebaseMy.getRoomInfo('sgd')
      firebaseMy.addMessage(id,{
        message : message,
        time : time,
        uidx : this.currUser.uidx
      })
    },
  }
}
</script>
<style>
.detailContents{
  z-index: 4;
}
.watchedMovie:hover {
  background-color: #fdc23e;
  font-weight: bold;
  color: #000000;
}

</style>
