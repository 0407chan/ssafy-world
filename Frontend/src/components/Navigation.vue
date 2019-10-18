<template>
    <v-layout>
        <BeforeLogin />
        <Menu />
        <FriendList />
    </v-layout>
</template>

<script>
import { mapState, mapActions } from "vuex"
import BeforeLogin from "@/components/navigations/BeforeLogin"
import Menu from "@/components/navigations/Menu"
import FriendList from "@/components/navigations/FriendList"

export default {
    name: 'Navigation',
    components: {
        BeforeLogin,
        Menu,
        FriendList,
    },
    data() {
        return {

        }
    },
    methods: {
        ...mapActions("data", ['getUser','getRoomByUser']),
        test() {
            console.log("해보자")
            var params = {
                id: 0,
                name: "이찬호",
            }
            var param = 1
            this.getUser(params)
            this.getRoomByUser(param)
        }
    }
};
</script>
