import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage.vue'
import ChatRoomPage from '@/pages/ChatRoomPage.vue'
import ChatRoom from '@/views/ChatRoom.vue'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'login',
            component: LoginPage
        },
        {
            path: '/room',
            name: 'room',
            component: ChatRoomPage
        },
        {
            path: '/char-room/:username',
            name: 'ChatRoom',
            component: ChatRoom,
        },
    ]
})
