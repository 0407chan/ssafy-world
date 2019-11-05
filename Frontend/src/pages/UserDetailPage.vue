<template>
  <v-container fluid fill-height justify-center>
    <div class="detailContents">
      <v-row >
        <v-col cols="12">
          <v-row justify="center" >

            <v-card width="300px" color="rgba(255, 255, 255, 0.8)" style="margin: 0 1%">
              <v-img v-if="isModify" height="200px" :src="modiImage"></v-img>
              <v-img v-else height="200px" :src="currUser.img"></v-img>

              <v-flex xs12 ma-3 v-if="isModify">
                <v-text-field
                  label="Select Image"
                  @click="pickFile"
                  v-model="imageName"
                  prepend-icon="mdi-paperclip"
                ></v-text-field>

                <input
                  type="file"
                  style="display: none"
                  ref="image"
                  accept="image/*"
                  @change="onFilePicked"
                />
                <v-text-field  v-model="modiName"
                  label="이름" hide-details filled
                ></v-text-field>
              </v-flex>

              <v-flex xs12 ma-3 v-else>
                <v-text-field v-model="currUser.uname"
                  label="이름" hide-details readonly
                ></v-text-field>
                <v-text-field v-model="currUser.uid"
                  label="아이디" hide-details readonly
                ></v-text-field>
                <v-text-field v-model="userGrade[currUser.staff]"
                  label="사용자 등급" hide-details readonly
                ></v-text-field>
              </v-flex>

              <v-flex xs12 ma-3 v-if="isModify">
                <v-row justify="center">
                  <v-btn :color="colorIndex"
                          rounded
                          v-on:click="modifyAction"
                          style="margin: 1% 1%"
                          :loading="loading"
                          :disabled="loading">
                    <span class="btnStyle">
                      수정
                    </span>
                  </v-btn>
                  <v-btn rounded v-on:click="modifyActivate" style="margin: 0 1%">
                    취소
                  </v-btn>
                </v-row>
              </v-flex>
              <v-flex xs12 ma-3 v-else>
                <v-row justify="center">
                  <v-btn :color="colorIndex" rounded v-on:click="modifyActivate">
                    <span class="btnStyle">
                      프로필 변경
                    </span>
                  </v-btn>
                </v-row>
              </v-flex>
            </v-card>
          </v-row>
        </v-col>

        <v-col cols="12">
          <v-flex>
            <v-row justify="center" align="center">
              <v-btn rounded :color="colorIndex" @click="goBack">
                <span class="btnStyle">BACK
                </span>
              </v-btn>
            </v-row>
          </v-flex>
        </v-col>
      </v-row>
    </div>

  </v-container>
</template>

<script>
import {mapState,mapActions} from "vuex";
import axios from "axios";
import api from '@/api'
import Swal from 'sweetalert2'
export default {
  props: {
    uidx: 0
  },
  data() {
    return {
      dialog: false,
      user: '',
      ratings: [],
      currU:{
        uidx:1,
        uname:"이찬호",
        uid:"0407chan@naver.com",
        img:"https://i.imgur.com/WiBTxG7.jpg",
      },
      userGrade:[
        "학생",
        "프로",
        "관리자",
      ],

      isModify:false,
      modiAge:'',
      modiGender:'',
      modiJob:'',
      modiName:'',
      modiImage:'',
      imageName:'',
      dday:'',

      loader: null,
      loading: false,

      colorIndex:"orange"
    }
  },
  computed: {
    ...mapState({
      currUser: state => state.data.currUser,
    }),
  },
  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]

      setTimeout(() => (this[l] = false), 3000)

      this.loader = null
    },
  },
  methods: {
    ...mapActions('data', ['update']),

    /* 2019.10.08 이찬호
    * 프로필 수정 할 수 있게 된다.
    */
    modifyActivate(){
      this.isModify = !this.isModify;
      if(this.isModify){
        this.modiName = this.currUser.uname;
        this.modiImage = this.currUser.img;
        this.imageName = this.currUser.img;
      }
    },

    /* 2019.10.08 이찬호
    * 수정된 정보를 DB에 저장한다.
    */
    async modifyAction(){
      this.isModify = !this.isModify;

      //수정부분
      var params = {
        uidx: this.currUser.uidx,
        uid: this.currUser.uid,
        staff: this.currUser.staff,
        uname: this.modiName,
        img: this.modiImage,
      };
      console.log("가자",params)
      const res = await this.update(params);
      this.$session.set('token',res.data);
      
      if (res.status == '200') {
        Swal.fire({
          type: 'success',
          title: '수정되었습니다.',
          showConfirmButton: false,
          timer: 1000
        })
      }else if(res.status == '500'){
        Swal.fire({
          title: '서버가 준비중입니다.',
          text: '이용에 불편을 드려 죄송합니다.',
          type: 'error',
          showConfirmButton: false,
          timer: 1000
        })
      }else if(res.status =='400'){
        Swal.fire({
          title: '잘못된 요청입니다.',
          text: '다시 시도해주세요.',
          type: 'error',
          showConfirmButton: false,
          timer: 1000
        })
      }
    },

    getStaff(staff){
      if(staff == 0){
        return "학생"
      }else if(staff == 1){
        return "프로"
      }else{
        return "관리자"
      }
    },

    goBack() {
      this.$router.go(-1)
    },

    // 이미지 변경해서 imgur에 올리기
    pickFile() {
      this.$refs.image.click();
    },
    setImageUrl(url){
      this.modiImage = url;
    },
    onFilePicked(e) {
      this.modiImage = '';
      const files = e.target.files;
      this.loading = true;
      if (files[0] !== undefined) {
        this.imageName = files[0].name;
        if (this.imageName.lastIndexOf(".") <= 0) {
          return;
        }

        let formData = new FormData();
        formData.append('image', files[0]); //required

        axios({
           method: 'POST',
           url: 'https://api.imgur.com/3/image',
           data: formData,
           headers: {
           Authorization: "Client-ID aac995cb6f223ce"
           },
           mimeType: 'multipart/form-data'
           }).then(res => {
             this.modiImage = res.data.data.link;
             this.loading = false;
           }).catch(e => {
             console.log(e)
        });

      } else {
        this.imageName = "";
        this.modiImage = "";
      }
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

.custom-loader {
    animation: loader 1s infinite;
    display: flex;
  }
  @-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }

</style>
