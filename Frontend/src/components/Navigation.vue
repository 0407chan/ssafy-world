<template>
    <v-layout>
        <BeforeLogin />
        <Menu />
        <FriendList />
    </v-layout>
</template>

<script>
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
    }
};
</script>
