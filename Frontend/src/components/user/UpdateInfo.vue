<template>
    <v-dialog v-model="dialog" persistent max-width="800px">
        <template v-slot:activator="{ on }">
            <v-btn color="primary" dark v-on="on">회원정보수정</v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">User Profile</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-card width="300px" color="rgba(255, 255, 255, 0.8)" style="margin: 0 1%">
                            <v-row justify="center">
                                <v-col cols="12">
                                    <v-row justify="center">
                                        <v-img :src="modiImage" aspect-ratio="1.8" contain></v-img>
                                    </v-row>
                                    <v-row justify="center" style="width:290px; margin: auto">
                                        <v-text-field label="Select Image" @click="pickFile" v-model="imageName" prepend-icon="mdi-paperclip">
                                        </v-text-field>
                                        <input type="file" style="display: none" ref="image" accept="image/*" @change="onFilePicked" />
                                    </v-row>
                                </v-col>
                            </v-row>
                        </v-card>
                        <v-col cols="1"></v-col>
                        <v-col cols="1">ID: </v-col>
                        <v-col cols="4"><v-label>{{ user[1] }}</v-label></v-col>
                        <v-col cols="6">
                            <v-text-field label="Name*" v-model="name" required></v-text-field>
                        </v-col>
                        <v-col cols="6">
                            <v-text-field label="Password*" v-model="pw" type="password" required></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="updateAction">Save</v-btn>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import { mapActions } from 'vuex'
import axios from 'axios'
import Swal from 'sweetalert2'

export default {
    name: 'UpdateInfo',
    data() {
        return {
            dialog: false,
            name: '',
            pw: '',
            modiImage:'',
            imageName:'',
            loading: false,
        }
    },
    props: [
        'user',
    ],
    mounted() {
        this.inputUserInfo();
    },
    methods: {

        inputUserInfo() {
            this.name = this.user[2];
        },

        ...mapActions("data", ['update']),
        async updateAction() {
            let params = {
                uidx: this.user[0],
                uname: this.name,
                password: this.pw,
                img: imageName,
            }

            let res = await this.update(params)
            if (res.status == 200) {
                this.successAlert("수정되었습니다!")
                this.$session.set('token', res.data)
                this.$emit('return')
            }
            else
                this.errorAlert(res.data)

            this.pw = ''
            this.dialog = false
        },

        successAlert(title) {
            Swal.fire({
                title: title,
                type: 'success',
                showConfirmButton: false,
                timer: 1000
            })
        },

        errorAlert(title) {
            Swal.fire({
                title: title,
                type: 'error',
                showConfirmButton: false,
                timer: 1000
            })
        },

        // 이미지 변경해서 imgur에 올리기
        pickFile() {
            this.$refs.image.click();
        },

        setImageUrl(url) {
            this.modiImage = url;
        },

        onFilePicked(e) {
            this.modiImage = '';
            const files = e.target.files;
            this.loading = true;

            if (files[0] !== undefined) {
                this.imageName = files[0].name;
                if (this.imageName.lastIndexOf(".") <= 0)
                    return;

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

            }
            else {
                this.imageName = "";
                this.modiImage = "";
            }
        },

    },
}
</script>
