<template v-slot:prepend>
    <v-list>
        <v-list-item two-line @click="goTo('user')">
            <v-list-item-avatar>
                <v-img src="https://randomuser.me/api/portraits/women/75.jpg"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
                <v-list-item-title>{{ currUser.uname }}</v-list-item-title>
                <v-list-item-subtitle>Logged In</v-list-item-subtitle>
            </v-list-item-content>
            <v-btn @click="logout">로그아웃</v-btn>
        </v-list-item>
    </v-list>
</template>

<script>
import { mapMutations, mapState } from 'vuex';
export default {
    name: 'Profile',
    data() {
        return {

        }
    },
    computed:{
        ...mapState('data', ['currUser'])
    },
    methods: {
        ...mapMutations('data', ['clearUser']),
        goTo(path) {
            if(this.currUser != '')
                this.$router.push({ name: path });
        },
        logout() {
            this.clearUser()
            this.$session.set('token', '')
            this.$router.push({ name: 'login' });
        },
    },
}
</script>
