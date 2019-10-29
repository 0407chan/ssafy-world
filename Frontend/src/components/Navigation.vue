<template>
    <v-layout>
        <v-navigation-drawer v-model="drawer">
            <v-list dense v-show="checkLogin == 0">
                <BeforeLogin />
            </v-list>
            <v-list dense v-show="checkLogin == 1">
                <Profile />
                <v-divider></v-divider>
                <AfterLogin />
            </v-list>
        </v-navigation-drawer>
        <v-btn @click="drawer = !drawer"></v-btn>
    </v-layout>
</template>

<script>
import Profile from '@/components/navigations/profile/Profile'
import BeforeLogin from '@/components/navigations/BeforeLogin'
import AfterLogin from '@/components/navigations/AfterLogin'

export default {
    name: 'Navigation',
    components: {
        Profile,
        BeforeLogin,
        AfterLogin,
    },
    data() {
        return {
            drawer: true,
            checkLogin: 0,
        }
    }
};
</script>
