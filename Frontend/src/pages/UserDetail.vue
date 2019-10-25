<template>
  <v-container fluid my-12>
    <div class="detailContents">
      <v-row >
        <v-col cols="12">
          <v-row justify="center">
            <v-card width="300px" color="rgba(255, 255, 255, 0.8)" style="margin: 0 1%">
              <v-row justify="center">
                <v-col cols="12" v-if="isModify">
                  <v-row justify="center">
                    <v-img :src="modiImage"
                      aspect-ratio="1.8" contain >
                    </v-img>
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
                <v-col cols="12" v-else style="margin-top: 5%">
                  <!-- <v-img v-if="UserList[0] && UserList[0].image" :src="UserList[0].image"
                    aspect-ratio="1.8" contain >
                  </v-img> -->
                </v-col>

              </v-row>
            </v-card>
            <v-card width="300px" color="rgba(255, 255, 255, 0.8)" style="margin: 0 1%">
              <v-flex xs12 ma-3 v-if="isModify">
                <v-text-field  v-model="modiName"
                  label="이름" hide-details filled
                ></v-text-field>
                <v-select v-model="modiGender" :items="genders"
                  item-text="text" item-value="value" label="Gender*" hide-details filled/>
                <v-select v-model="modiAge" :items="ages"
                  item-text="text" item-value="value" label="Age*" hide-details filled/>
                <v-select v-model="modiJob" :items="occupations"
                  label="Occupation*" hide-details filled/>
              </v-flex>
              <v-flex xs12 ma-3 v-else>
                <!-- <v-text-field v-if="UserList[0]" v-model="UserList[0].username"
                  label="이름" hide-details readonly
                ></v-text-field>
                <v-text-field v-if="UserList[0]" v-model="UserList[0].gender"
                  label="성별" hide-details readonly
                ></v-text-field>
                <v-text-field v-if="UserList[0]" v-model="UserList[0].age"
                  label="나이" hide-details readonly
                ></v-text-field>
                <v-text-field v-if="UserList[0]" v-model="UserList[0].occupation"
                  label="직업" hide-details readonly
                ></v-text-field> -->
              </v-flex>
              <v-flex xs12 ma-3 v-if="isModify">
                <v-row justify="center">
                  <v-btn :color="index" rounded v-on:click="modifyAction" style="margin: 1% 1%">
                    <span class="btnStyle">
                      수정
                    </span>
                  </v-btn>
                  <v-btn rounded v-on:click="modifyActivate" style="margin: 0 1%">
                    취소
                  </v-btn>
                </v-row>
              </v-flex>
              <v-flex xs12 ma-3 v-else-if="!isModify && id == user.user_id">
                <v-row justify="center">
                  <v-btn :color="index" rounded v-on:click="modifyActivate">
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
              <v-btn rounded :color="index" @click="goBack">
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

    }
  },

  computed: {
    ...mapState({

    }),
  },
  mounted() {

  },
  methods: {

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
