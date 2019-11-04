<template>
    <v-app xs12>
        <Navigation />
        <v-content>
            <router-view />
        </v-content>
    </v-app>
</template>

<script>
import Navigation from '@/components/Navigation'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'App',
    components: {
        Navigation,
    },
    data () {
        return {
            
        }
    },
    computed:{
        
    },
    methods :{
        ...mapActions('data', ['refresh']),
    },
    mounted() {
        this.$session.start()
        let token = this.$session.get('token')
        this.refresh(token)
    },
    destroyed() {
        this.$socket.emit('disconnect', {
            msg: 'disconnect'
        })
    },
}
</script>
