<template>
  <v-container fluid my-12>
    <div class="detailContents">
      <v-row >
        <v-col cols="12">
          <v-row justify="center">
            <v-card width="300px" color="rgba(255, 255, 255, 0.8)" style="margin: 0 1%">
              <v-row justify="center">
                <v-col cols="12">
                  <v-row justify="center">
                    <v-img :src="modiImage" aspect-ratio="1.8" contain></v-img>
                  </v-row>
                  <v-row justify="center" style="width:290px; margin: auto">
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
                  </v-row>
                </v-col>
              </v-row>
            </v-card>
            <v-card style="width: 35%;">
              <v-card-title style="margin: 20px;">
                회 원 정 보
              </v-card-title>
                <v-data-table
    :headers="headers"
    :items="desserts"
    hide-default-header
    hide-default-footer
    class="elevation-1"
  />
            </v-card>
          </v-row>
        </v-col>

        <v-col cols="12">
          <v-flex>
            <v-row justify="center" align="center">
              <v-btn rounded @click="goBack">
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
import Swal from 'sweetalert2'

export default {
  props: {
    id: 0
  },
  data() {
    return {
      dialog: false,
      user: '',
      ratings: [],

      isModify:false,
      modiAge:'',
      modiGender:'',
      modiJob:'',
      modiName:'',
      modiImage:'',
      imageName:'',
      dday:'',

      headers: [
          {
            align: 'center',
            value: 'name',
          },
          { value: 'context' },
        ],
        desserts: [
          {
            name: '이름',
            value: '',
          },
          {
            name: '등급',
            value: '',
          },
          {
            name: '아이디',
            value: '',
          },
          {
            name: '비밀번호',
            value: '',
          },
        ],
    }
  },
  computed: {
    ...mapState('data', ['userLoginToken']),
  },
  mounted() {
    this.getUserInfoAction()
  },
  methods: {

    ...mapActions("data", ['getUserInfo']),
    async getUserInfoAction() {
      let params = {
        id: this.$store,
      }
      let user = await this.getUserInfo(this.userLoginToken);

      console.log(user);
      // var user = await this.getUserInfo(userLoginToken);
      // this.desserts[0].value = user.uid;
    },

    /* 2019.10.08 이찬호
    * 프로필 수정 할 수 있게 된다.
    */
    modifyActivate(){
      this.isModify = !this.isModify;
      if(this.isModify){
        this.modiName = this.UserList[0].username;
        this.modiAge = this.UserList[0].age.toString();
        this.modiGender = this.UserList[0].gender ;
        this.modiJob = this.UserList[0].occupation;
        this.modiImage = this.UserList[0].image;
        this.imageName = this.UserList[0].image;
      }
    },

    /* 2019.10.08 이찬호
    * 수정된 정보를 DB에 저장한다.
    */
    async modifyAction(){
      this.isModify = !this.isModify;
      var params = {
        id: this.id,
        username: this.modiName,
        age: this.modiAge,
        occupation: this.modiJob,
        gender: this.modiGender,
        image: this.modiImage,
      };
      console.log(params)
      const res = await api.modifyUser(params);
      params = {
        id: this.id,
      };
      this.userDetail(params);
      Swal.fire({
        type: 'success',
        title: '수정되었습니다.',
        showConfirmButton: false,
        timer: 1000
      })
    },

    goBack() {
      router.go(-1)
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

</style>
