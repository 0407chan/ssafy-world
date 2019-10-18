<template>
    <div>
        <v-img
            :src="require('@/assets/jaws.jpg')"
            class="my-3"
            contain
        ></v-img>
        <v-btn v-on:click="test"></v-btn>
    </div>
</template>

<script>
export default {
    name: 'BeforeLoginPage',
    components: {

    },
    data() {
        return {
          
        }
    },
};
</script>
