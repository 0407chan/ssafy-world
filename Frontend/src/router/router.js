import Vue from 'vue'
import Router from 'vue-router'
import MainPage from "@/pages/MainPage.vue"
import RegisterPage from "@/pages/RegisterPage.vue"
import ChatRoomPage from "@/pages/ChatRoomPage.vue"
import UserDetail from "@/pages/UserDetail.vue"

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'main',
            component: MainPage
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
        {
            path: '/chatroom/:id',
            name: 'selectroom',
            component: ChatRoomPage
        },
        {
            path: '/userDetail',
            name: 'userDetail',
            component: UserDetail
        },
    ]
})
