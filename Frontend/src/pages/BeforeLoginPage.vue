<template>
    <v-layout>
        <h1>Home</h1>
        <div>{{ greeting }}</div>
    </v-layout>
</template>

<script>
import axios from 'axios'

export default {
    name: 'BeforeLoginPage',
    components: {

    },
    data() {
        return {
            greeting: '',
        }
    },
    created() {
        axios.get('http://70.12.246.62:8080/ssafyworld/user')
            .then(result => this.greeting = result.data.greeting)
    },
};
</script>
