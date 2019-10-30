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
                        <v-col cols="10"><v-label>{{ user[0] }}</v-label></v-col>
                        <v-col cols="12">
                            <v-text-field label="Name*" :value="user[1]" required></v-text-field>
                        </v-col>
                        <v-col cols="12">
                            <v-text-field label="Password*" type="password" required></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
export default {
    name: 'UpdateInfo',
    data() {
        return {
            dialog: false,
        }
    },
    props: [
        'user',
    ],
}
</script>
