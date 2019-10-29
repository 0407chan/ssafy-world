<template>
    <v-list>
        <template v-for="(item, i) in items">
            <v-list-item :key="i" @click="() => { if (item.path) { goTo(item.path) } }">
                <v-list-item-action>
                    <v-icon>{{ item.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-title>
                    {{ item.title }}
                </v-list-item-title>
            </v-list-item>
        </template>
    </v-list>
</template>

<script>
export default {
    name: 'BeforeLogin',
    data() {
        return {
            items: [
                { title: '로 그 인', icon: 'mdi-account', path: 'login' },
                { title: '회 원 가 입', icon: 'mdi-account-group-outline', path: 'register' },
            ],
        }
    },
    methods: {
        goTo(path) {
            this.$router.push({ name: path });
        },
    },
}
</script>
