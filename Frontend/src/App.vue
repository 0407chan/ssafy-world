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

export default {
    name: 'App',
    components: {
        Navigation,
    },
    data () {
        return {
            
        }
    },
}
</script>
