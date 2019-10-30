<template>
    <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
            <v-btn v-on="on">채팅방 만들기</v-btn>
        </template>
        <v-card>
            <v-card-title>
                <span class="headline">Make Chat Room</span>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field label="Chat Room Name*" v-model="roomName" required></v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
                <small>*indicates required field</small>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="createChatRoomAction">Make</v-btn>
                <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    name: 'MakeChatRoom',
    data() {
        return {
            dialog: false,
            roomId: 0,
            roomName: '',
        }
    },
    methods: {
        ...mapActions('data', ['createChatRoom']),
        async createChatRoomAction() {
            console.log(this.roomName)
            let params = {
                rname: this.roomName,
            }
            this.roomId = await this.createChatRoom(params)
            this.dialog = false
        },
    },
}
</script>
