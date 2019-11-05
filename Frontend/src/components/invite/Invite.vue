<template>
    <v-dialog
        v-model="display"
        max-width="480">
        <v-card>
        <v-card-title class="headline">친구 초대</v-card-title>
        <v-card-text>
          <v-row>
              <v-col cols="2" >
                선택
              </v-col>
              <v-col cols="2" >
                이미지
              </v-col>
              <v-col cols="4" >
                이름
              </v-col>
              <v-col cols="4" >
                아이디
              </v-col>
          </v-row>
          <template v-for="i in user">
            <v-row>
              <v-col cols="2" >
                <v-checkbox  :value="i.uidx" v-model="selected" />
              </v-col>
              <v-col cols="2" >
                <v-img :src="i.img" max-height="64" max-width="64" />
              </v-col>
              <v-col cols="4" >
                {{i.uname}} 
              </v-col>
              <v-col cols="4" >
                 ({{i.uid}})
              </v-col>
            </v-row>
          </template>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green darken-1"
            text
            @click="inviteFriend()">
            확인
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="display = false" >
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>
<script>
import api from '@/api'
export default {
  name: 'Invite',
  props:{
    user : Object,
    display: Boolean,
  },
  data(){
    return {
      selected:[]
    }
  },
  methods:{
    inviteFriend(){
      for (let index = 0; index < this.selected.length; index++) {
        api.postEnterRoom(this.selected[index],window.location.pathname.split('/')[2])
      }
      this.display=false
      
    }
  }
};
</script>