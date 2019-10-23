import Vue from 'vue'
import Router from 'vue-router'
import StartPage from "@/pages/StartPage.vue"
import RegisterPage from "@/pages/RegisterPage.vue"
import ChatRoomPage from "@/pages/ChatRoomPage.vue"

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'home',
            component: StartPage
        },
        {
            path: '/register',
            name: 'register',
            component: RegisterPage
        },
        {
            path: '/chatroom',
            name: 'chatroom',
            component: ChatRoomPage
        },
    ]
})
