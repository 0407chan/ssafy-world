<template>
    <v-dialog
        v-model="display"
        max-width="250">
        <v-card max-width="250" class="mx-auto">
            <v-img :src="user.img" height="200px" dark></v-img>

            <v-card-title>{{ user.uname }}</v-card-title>

            <v-card-text>
            <div>{{ user.uid }}</div>
            </v-card-text>
            <v-divider class="mx-4"></v-divider>
            <v-list-item>
            <v-list-item-action>
                <v-btn color="deep-purple accent-4" outlined>
                Message
                </v-btn>
            </v-list-item-action>
            </v-list-item>
        </v-card>
    </v-dialog>
</template>
<script>
export default {
  name: 'FriendProfile',
  props:{
    user : Object,
    display: Boolean,
  },
};
</script>