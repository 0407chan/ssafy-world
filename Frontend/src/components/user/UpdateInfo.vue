<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
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
                        <v-col cols="2">ID: </v-col>
                        <v-col cols="10"><v-label>{{ user[1] }}</v-label></v-col>
                        <v-col cols="12">
                            <v-text-field label="Name*" :value="user[2]" v-bind="name" required></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field label="Password*" v-bind="pw" type="password" required></v-text-field>
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
import { mapActions } from "vuex"

export default {
    name: 'UpdateInfo',
    data() {
        return {
            dialog: false,
            name: '',
            pw: '',
        }
    },
    props: [
        'user',
    ],
    methods: {
        ...mapActions("data", ['update']),
        async updateAction() {
            let params = {
                uidx: this.user[0],
                uname: this.name,
                password: this.pw
            }
            console.log(params.uname),
            console.log(params.password),

            await this.update(params)
            this.pw = ''
            this.dialog = false
        },
    },
}
</script>
